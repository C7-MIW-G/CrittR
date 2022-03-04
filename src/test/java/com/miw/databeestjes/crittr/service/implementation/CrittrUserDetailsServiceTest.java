package com.miw.databeestjes.crittr.service.implementation;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.repository.CrittrUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CrittrUserDetailsServiceTest {

    @Mock
    private CrittrUserRepository testRepository;

    private CrittrUser testUser;

    private CrittrUserDetailsService testService;

    @BeforeEach
    public void setUp() {
        testService = new CrittrUserDetailsService(testRepository);
        testUser = new CrittrUser();
        testUser.setUserId(-1);
    }

    @Test
    public void testSave() {
       doAnswer(invocation -> {
           CrittrUser user = invocation.getArgument(0);

           assertEquals(testUser, user, "Saving user failed");
           return null;
       }).when(testRepository).save(any(CrittrUser.class));

       testService.save(testUser);
    }

    @Test
    public void testSaveWithPassword() {
        doAnswer(invocation -> {
            CrittrUser user = invocation.getArgument(0);

            assertEquals(testUser, user, "User save (password) failed");
            assertEquals("password", user.getPassword(), "Wrong password included");
            return null;
        }).when(testRepository).save(any(CrittrUser.class));

        testService.saveWithPassword(testUser, "password");
    }


    @Test
    public void testFindById() {
       when(testRepository.findById(anyLong())).thenReturn(Optional.of(testUser));
       assertEquals(testUser, testService.findById(testUser.getUserId()).get(), "Correct user not retrieved");
    }

    @Test
    public void testFindAll() {
        when(testRepository.findAll()).thenReturn(new ArrayList<>());
        List<CrittrUser> usersFound = testService.findAll();
        assertNotNull(usersFound, "No users retrieved");
    }

    @Test
    public void testFindByUsername() {
        when(testRepository.findByEmail(anyString())).thenReturn(Optional.of(testUser));
        assertEquals(testUser, testRepository.findByEmail("email").get(), "Could not find by email");
    }

    @Test
    public void testUserNameException() {
        when(testRepository.findByEmail(anyString())).thenThrow(new UsernameNotFoundException("Exception thrown"));
        UsernameNotFoundException thrown = Assertions.assertThrows(UsernameNotFoundException.class, () ->
                testService.loadUserByUsername("email"));
        assertEquals("Exception thrown", thrown.getMessage(), "Wrong exception thrown");
    }
}