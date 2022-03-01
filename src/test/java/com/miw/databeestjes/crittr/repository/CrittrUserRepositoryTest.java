package com.miw.databeestjes.crittr.repository;

import com.miw.databeestjes.crittr.model.CrittrUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.TypeMismatchException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CrittrUserRepositoryTest {

    @Mock
    CrittrUserRepository testRepository;

    CrittrUser testUser;

    @BeforeEach
    public void setup() {
        testUser = new CrittrUser();
    }

    @Test
    public void testSave() {
        when(testRepository.save(any(CrittrUser.class))).thenAnswer(i -> i.getArguments()[0]);

        assertEquals(testUser, testRepository.save(testUser));
    }

}