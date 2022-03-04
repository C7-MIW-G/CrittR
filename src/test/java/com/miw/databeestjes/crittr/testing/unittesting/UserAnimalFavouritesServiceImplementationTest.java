package com.miw.databeestjes.crittr.testing.unittesting;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.model.UserAnimalFavourites;
import com.miw.databeestjes.crittr.model.UserAnimalFavouritesKey;
import com.miw.databeestjes.crittr.repository.UserAnimalFavouritesRepository;
import com.miw.databeestjes.crittr.service.UserAnimalFavouritesService;
import com.miw.databeestjes.crittr.service.implementation.UserAnimalFavouritesServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EmbeddedId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAnimalFavouritesServiceImplementationTest {

    @Mock
    private UserAnimalFavouritesRepository testRepository;

    private CrittrUser testUser;
    private UserAnimalFavourites testFavs;
    private UserAnimalFavouritesKey testKey;
    private Animal testAnimal;

    private UserAnimalFavouritesService testService;

    @BeforeEach
    public void setUp() {
        testService = new UserAnimalFavouritesServiceImplementation(testRepository);
        testUser = new CrittrUser();
        testFavs = new UserAnimalFavourites();
        testKey = new UserAnimalFavouritesKey();
        testUser.setUserId(-1);
        testFavs.setUserAnimalFavouritesKey(testKey);
        testFavs.setAnimal(testAnimal);
        testFavs.setUser(testUser);
    }

    @Test
    void getAll() {
        when(testRepository.findAll()).thenReturn(new ArrayList<>());
        List<UserAnimalFavourites> favsFound = testService.getAll();
        assertNotNull(favsFound, "No users retrieved");
    }

    @Test
    void save() {
        doAnswer(invocation -> {
            UserAnimalFavourites userAnimalFavourites = invocation.getArgument(0);

            assertEquals(testFavs, userAnimalFavourites, "Saving user failed");
            return null;
        }).when(testRepository).save(any(UserAnimalFavourites.class));

        testService.save(testFavs);
    }

}