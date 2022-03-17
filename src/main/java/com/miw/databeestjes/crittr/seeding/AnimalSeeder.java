package com.miw.databeestjes.crittr.seeding;

import com.miw.databeestjes.crittr.model.AnimalStatus;
import com.miw.databeestjes.crittr.service.AnimalService;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

    public void seedAnimal() {
        if (animalService.getAll().size() == 0) {
            animalService.addNewAnimal("Goat", "Gary", LocalDate.parse("2008-03-08"), "Gary is super friendly and loves to get cuddles from our visitors", getPicture("goat1.jpg"));
            animalService.addNewAnimal("Goat", "Gomez", LocalDate.parse("2013-08-03"), "Gomez is a very mischievous goat. He always gets himself in trouble with his tricks. He makes us laugh all the time.", getPicture("goat2.png"), AnimalStatus.INCOMING);
            animalService.addNewAnimal("Goat", "Gabriella", LocalDate.parse("2020-06-23"), "Grace and Gabriella are twins. They have the typical sister love-hate relationship. If you spot Grace, you can be sure that Gabriella is just around the corner!", getPicture("goat3.jpg"));
            animalService.addNewAnimal("Goat", "Grace", LocalDate.parse("2020-06-23"), "Grace and Gabriella are twins. They have the typical sister love-hate relationship. If you spot Grace, you can be sure that Gabriella is just around the corner!", getPicture("goat4.jpg"));

            animalService.addNewAnimal("Horse", "Harry", LocalDate.parse("2020-04-15"), "Harry is a super sweet Horse. He loves to get visitors and is very gentle with children.", getPicture("horse1.jpg"));
            animalService.addNewAnimal("Horse", "Homer", LocalDate.parse("2016-08-30"), "Homes can sometimes have a bit of a temper, but he always get around if you give him some treats.", getPicture("horse2.jpg"));

            animalService.addNewAnimal("Sheep", "Sandy", LocalDate.parse("2016-02-17"), "Sandy is a cuty. She loves to come up to you to get to know you.", getPicture("sheep1.jpg"), AnimalStatus.RELOCATED);
            animalService.addNewAnimal("Sheep", "Sally", LocalDate.parse("2018-05-30"), "Sally is very sassy sheep. She always get herself in trouble with the other animals. She is very strong headed and likes things to just stay the way she knows.", getPicture("sheep2.jpg"));
            animalService.addNewAnimal("Sheep", "Suzy", LocalDate.parse("2019-12-30"), "Suzy is a bit shy. She is often the victim of Sally's shenanigans, so give her a little extra love when you visit us.", getPicture("sheep3.jpg"));
            animalService.addNewAnimal("Sheep", "Shaun", LocalDate.parse("2015-05-05"), "Shaun is super greedy. He is very food oriented and will do anything for a little treat.", getPicture("sheep4.jpg"), AnimalStatus.RELOCATED);

            animalService.addNewAnimal("Donkey", "Danny", LocalDate.parse("2019-07-14"), "Danny is Daniella's baby. He can be a bit mischievous and will nibble on your clothes. He means no harm though, he just wants your attention.", getPicture("donkey1.jpg"));
            animalService.addNewAnimal("Donkey", "Daniella", LocalDate.parse("2002-10-01"), "Daniella is Danny's mom. She is very gentle when you approach her, but will not come to you by herself.", getPicture("donkey2.jpg"));

            animalService.addNewAnimal("Deer", "Deirdre", LocalDate.parse("2016-04-30"), "Deirdre is very focused on humans and will always be the first at the fence.", getPicture("deer2.jpg"));
            animalService.addNewAnimal("Deer", "Dory", LocalDate.parse("2017-09-02"), "Dory is a shy dear, but follows Deirdre everywhere.", getPicture("deer3.JPG"));
            animalService.addNewAnimal("Deer", "Dudley", LocalDate.parse("2014-03-14"), "Dudley is not very sweet, he is a real alpha male. He mostly ignores visitors, it's not personal.", getPicture("deer1.jpg"), AnimalStatus.RELOCATED);

            animalService.addNewAnimal("Cow", "Clara", LocalDate.parse("2017-08-30"), "Clara used to live on a dairy farm, where she was kept to produce milk. Clara is no longer able to get pregnant and can therefore no longer produce milk, so she came to live with us!", getPicture("cow1.jpg"));
            animalService.addNewAnimal("Cow", "Carol", LocalDate.parse("2016-04-15"), "Carol loves it when you rub her nose.", getPicture("cow2.jpg"), AnimalStatus.INCOMING);

            animalService.addNewAnimal("Pig", "Palpatine", LocalDate.parse("2022-02-28"), "Palpatine is our little piglet. He is adorable and will always come running when we call him for his dinner.", getPicture("pig1.jpg"));
            animalService.addNewAnimal("Pig", "Peggy", LocalDate.parse("2012-03-25"), "Peggy is a very playful pig, she most likes to play with her ball! Peggy also really likes to lay in the mud to soak up the sun.", getPicture("pig2.jpg"));
            animalService.addNewAnimal("Pig", "Pepper", LocalDate.parse("2010-11-20"), "Pepper is super lazy. She loves to dig in the mud and take mud baths.", getPicture("pig3.jpg"), AnimalStatus.INCOMING);

            animalService.addNewAnimal("Rabbit", "Rory", LocalDate.parse("2020-07-20"), "Rory and Roger are a couple. They like to cuddle and Rory loves to make sure Roger is clean. Rory is very playful and loves to run and jump around!", getPicture("rabbit1.jpg"));
            animalService.addNewAnimal("Rabbit", "Roger", LocalDate.parse("2016-03-15"), "Rory and Roger are a couple. They like to cuddle and Roger loves it when Rory cleans him. Roger is the sweetest rabbit, he loves to cuddle and his favourite spot is right behind his ears!", getPicture("rabbit2.jpg"));

            animalService.addNewAnimal("Chicken", "Charlie", LocalDate.parse("2019-04-21"), "", getPicture("chicken1.jpg"), AnimalStatus.DECEASED);
            animalService.addNewAnimal("Chicken", "Charlotte", LocalDate.parse("2020-07-14"), "", getPicture("chicken2.JPG"));
            animalService.addNewAnimal("Chicken", "Charlize", LocalDate.parse("2021-05-13"), "", getPicture("chicken3.jpg"));
        }
    }

    private byte[] getPicture(String filename) {
        File file = new File("src/main/resources/static/assets/seedingpics/" + filename);
        byte[] picture = new byte[(int) file.length()];
        try {
            picture = new FileInputStream(file).readAllBytes();
        } catch (IOException s) {
            s.printStackTrace();
        }
        return picture;
    }
}
