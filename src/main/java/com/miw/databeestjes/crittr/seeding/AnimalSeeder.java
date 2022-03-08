package com.miw.databeestjes.crittr.seeding;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.AnimalStatus;
import com.miw.databeestjes.crittr.service.AnimalService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author Bianca Burema <b.burema@st.hanze.nl>
 * <p>
 * Seeding class to fill the database
 */

@Component
public class AnimalSeeder {
    private AnimalService animalService;

    public AnimalSeeder(AnimalService animalService) {
        this.animalService = animalService;
    }

    @EventListener
    public void seedAnimal(ContextRefreshedEvent event) {
        if (animalService.getAll().size() == 0) {
            animalService.addNewAnimal("Goat", "Gary", LocalDate.parse("2008-03-08"), "It's super cute!!");
            animalService.addNewAnimal("Goat", "Gomez", LocalDate.parse("2013-08-03"), "");
            animalService.addNewAnimal("Goat", "Gabriella", LocalDate.parse("2020-06-23"), "It's twin is Grace!");
            animalService.addNewAnimal("Goat", "Grace", LocalDate.parse("2020-06-23"), "It's twin is Gabriella!");

            animalService.addNewAnimal("Horse", "Harry", LocalDate.parse("2020-04-15"), "");
            animalService.addNewAnimal("Horse", "Homer", LocalDate.parse("2016-08-30"), "");

            animalService.addNewAnimal("Sheep", "Sandy", LocalDate.parse("2016-02-17"), "");
            animalService.addNewAnimal("Sheep", "Sally", LocalDate.parse("2018-05-30"), "");
            animalService.addNewAnimal("Sheep", "Suzy", LocalDate.parse("2019-12-30"), "");
            animalService.addNewAnimal("Sheep", "Shaun", LocalDate.parse("2015-05-05"), "");

            animalService.addNewAnimal("Donkey", "Danny", LocalDate.parse("2019-07-14"), "Daniella is it's mom!");
            animalService.addNewAnimal("Donkey", "Daniella", LocalDate.parse("2002-10-01"), "It's Danny's mom!");

            animalService.addNewAnimal("Deer", "Deirdre", LocalDate.parse("2016-04-30"), "");
            animalService.addNewAnimal("Deer", "Dory", LocalDate.parse("2017-09-02"), "");
            animalService.addNewAnimal("Deer", "Dudley", LocalDate.parse("2014-03-14"), "");

            animalService.addNewAnimal("Cow", "Clara", LocalDate.parse("2019-08-30"), "");
            animalService.addNewAnimal("Cow", "Carol", LocalDate.parse("2016-04-15"), "");

            animalService.addNewAnimal("Pig", "Palpatine", LocalDate.parse("2010-11-20"), "");
            animalService.addNewAnimal("Pig", "Peggy", LocalDate.parse("2012-03-25"), "");
            animalService.addNewAnimal("Pig", "Pepper", LocalDate.parse("2022-02-28"), "");

            animalService.addNewAnimal("Rabbit", "Rory", LocalDate.parse("2020-07-20"), "");
            animalService.addNewAnimal("Rabbit", "Roger", LocalDate.parse("2016-03-15"), "");

            animalService.addNewAnimal("Chicken", "Homer", LocalDate.parse("2016-08-30"), "");


        }

    }
}
