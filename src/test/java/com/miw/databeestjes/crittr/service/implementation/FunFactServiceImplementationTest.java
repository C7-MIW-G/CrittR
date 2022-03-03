package com.miw.databeestjes.crittr.service.implementation;

import com.miw.databeestjes.crittr.model.FunFact;
import com.miw.databeestjes.crittr.repository.FunFactRepository;
import com.miw.databeestjes.crittr.service.FunFactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FunFactServiceImplementationTest {

    @Mock
    FunFactRepository funFactRepository;

    private FunFactService funFactService;

    @BeforeEach
    public void setup(){
        funFactService = new FunFactServiceImplementation(funFactRepository);
    }

    @Test
    public void testRandomFact(){
        when(funFactRepository.findAll()).thenReturn(new ArrayList<>());
        List<FunFact> testList = funFactRepository.findAll();
        testList.add(new FunFact());
        assertNotNull(funFactService.getRandomFact(), "Fact not found");
        // There is no guarantee it's random but it has retrieved an object from the list
    }

}