package com.miw.databeestjes.crittr.seeding;

import com.miw.databeestjes.crittr.model.EduInfoCategory;
import com.miw.databeestjes.crittr.service.EduInfoService;
import org.springframework.stereotype.Component;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This class helps seed the database with Educational Info on our animals
 */

@Component
public class EduInfoSeeder {
    EduInfoService eduInfoService;

    public EduInfoSeeder(EduInfoService eduInfoService) {
        this.eduInfoService = eduInfoService;
    }

    public void seedEduInfo(){
        if (eduInfoService.getAll().size() == 0){
            eduInfoService.addNewEduInfo("Bos taurus", "Cow", EduInfoCategory.LATIN_NAME);
            eduInfoService.addNewEduInfo("Mammal", "Cow", EduInfoCategory.CLASS);
            eduInfoService.addNewEduInfo("15 - 20 Years", "Cow", EduInfoCategory.LIFE_EXPECTANCY);
            eduInfoService.addNewEduInfo("1.60 - 1.80 meters", "Cow", EduInfoCategory.HEIGHT);
            eduInfoService.addNewEduInfo("Herbivore", "Cow", EduInfoCategory.DIET);

            eduInfoService.addNewEduInfo("Capra hircus", "Goat", EduInfoCategory.LATIN_NAME);
            eduInfoService.addNewEduInfo("Mammal", "Goat", EduInfoCategory.CLASS);
            eduInfoService.addNewEduInfo("11 - 12 Years", "Goat", EduInfoCategory.LIFE_EXPECTANCY);
            eduInfoService.addNewEduInfo("41 - 58 cm", "Goat", EduInfoCategory.HEIGHT);
            eduInfoService.addNewEduInfo("Herbivore", "Goat", EduInfoCategory.DIET);

            eduInfoService.addNewEduInfo("Ovis aries", "Sheep", EduInfoCategory.LATIN_NAME);
            eduInfoService.addNewEduInfo("Mammal", "Sheep", EduInfoCategory.CLASS);
            eduInfoService.addNewEduInfo("10 - 12 Years", "Sheep", EduInfoCategory.LIFE_EXPECTANCY);
            eduInfoService.addNewEduInfo("1.70 meters", "Sheep", EduInfoCategory.HEIGHT);
            eduInfoService.addNewEduInfo("Herbivore", "Sheep", EduInfoCategory.DIET);

            eduInfoService.addNewEduInfo("Gallus domesticus", "Chicken", EduInfoCategory.LATIN_NAME);
            eduInfoService.addNewEduInfo("Aves", "Chicken", EduInfoCategory.CLASS);
            eduInfoService.addNewEduInfo("5 - 10 years", "Chicken", EduInfoCategory.LIFE_EXPECTANCY);
            eduInfoService.addNewEduInfo("50 - 70 centimeters", "Chicken", EduInfoCategory.HEIGHT);
            eduInfoService.addNewEduInfo("Omnivore", "Chicken", EduInfoCategory.DIET);

            eduInfoService.addNewEduInfo("Equus ferus caballus", "Horse", EduInfoCategory.LATIN_NAME);
            eduInfoService.addNewEduInfo("Mammal", "Horse", EduInfoCategory.CLASS);
            eduInfoService.addNewEduInfo("25 - 30 Years", "Horse", EduInfoCategory.LIFE_EXPECTANCY);
            eduInfoService.addNewEduInfo("1.40 - 1.80 meters", "Horse", EduInfoCategory.HEIGHT);
            eduInfoService.addNewEduInfo("Herbivore", "Horse", EduInfoCategory.DIET);

            eduInfoService.addNewEduInfo("Oryctolagus cuniculus domesticus", "Rabbit", EduInfoCategory.LATIN_NAME);
            eduInfoService.addNewEduInfo("Mammal", "Rabbit", EduInfoCategory.CLASS);
            eduInfoService.addNewEduInfo("5 - 10 Years", "Rabbit", EduInfoCategory.LIFE_EXPECTANCY);
            eduInfoService.addNewEduInfo("20 - 40 centimeters", "Rabbit", EduInfoCategory.HEIGHT);
            eduInfoService.addNewEduInfo("Herbivore", "Rabbit", EduInfoCategory.DIET);

            eduInfoService.addNewEduInfo("Equus asinus", "Donkey", EduInfoCategory.LATIN_NAME);
            eduInfoService.addNewEduInfo("Mammal", "Donkey", EduInfoCategory.CLASS);
            eduInfoService.addNewEduInfo("25 - 30 Years", "Donkey", EduInfoCategory.LIFE_EXPECTANCY);
            eduInfoService.addNewEduInfo("79 - 160 centimeters", "Donkey", EduInfoCategory.HEIGHT);
            eduInfoService.addNewEduInfo("Herbivore", "Donkey", EduInfoCategory.DIET);

            eduInfoService.addNewEduInfo("Capreolus capreolus", "Deer", EduInfoCategory.LATIN_NAME);
            eduInfoService.addNewEduInfo("Mammal", "Deer", EduInfoCategory.CLASS);
            eduInfoService.addNewEduInfo("15 - 20 Years", "Deer", EduInfoCategory.LIFE_EXPECTANCY);
            eduInfoService.addNewEduInfo("63 - 67 centimeters", "Deer", EduInfoCategory.HEIGHT);
            eduInfoService.addNewEduInfo("Herbivore", "Deer", EduInfoCategory.DIET);

            eduInfoService.addNewEduInfo("Sus domesticus", "Pig", EduInfoCategory.LATIN_NAME);
            eduInfoService.addNewEduInfo("Mammal", "Pig", EduInfoCategory.CLASS);
            eduInfoService.addNewEduInfo("12 - 18 Years", "Pig", EduInfoCategory.LIFE_EXPECTANCY);
            eduInfoService.addNewEduInfo("51 - 97 centimeters", "Pig", EduInfoCategory.HEIGHT);
            eduInfoService.addNewEduInfo("Omnivore", "Pig", EduInfoCategory.DIET);
        }
    }
}
