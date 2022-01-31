package com.miw.databeestjes.crittr.service.implementation;


import com.miw.databeestjes.crittr.repository.AnimalRepository;
import com.miw.databeestjes.crittr.service.AnimalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AnimalServiceImplementationTest {


    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalServiceImplementation animalService;

    @BeforeEach
    public void init () {
        animalService = new AnimalServiceImplementation(animalRepository);
    }

    @Test
    public void testGetAll() {
        assertNotNull(animalService.getAll());
    }
}