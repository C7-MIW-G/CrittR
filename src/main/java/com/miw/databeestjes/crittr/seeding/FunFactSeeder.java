package com.miw.databeestjes.crittr.seeding;

import com.miw.databeestjes.crittr.service.FunFactService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Bianca Burema <b.burema@st.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */

@Component
public class FunFactSeeder {
    private FunFactService funFactService;

    public FunFactSeeder(FunFactService funFactService) {
        this.funFactService = funFactService;
    }

    public void seedFunFact() {
        if (funFactService.getAll().size() == 0) {
            funFactService.addFunFact("Cows can sleep standing up, but they can only dream lying down.", "Cow");
            funFactService.addFunFact("Just like you, cows have best friends. Not only does companionship make cows happier, it also makes them smarter.", "Cow");

            funFactService.addFunFact("Goats were one of the first animals to be tamed by humans and were being herded 9,000 years ago.", "Goat");
            funFactService.addFunFact("Goats can be taught their name and to come when called.", "Goat");
            funFactService.addFunFact("Goats’ pupils (like many hooved animals) are rectangular. This gives them vision for 320 to 340 degrees (compared to humans with 160-210) around them without having to move and they are thought to have excellent night vision.", "Goat");

            funFactService.addFunFact("Pigs have the intelligence of a human toddler and are ranked as the fifth most intelligent animal in the world! In fact, pigs are more intelligent and trainable than any breed of dog.", "Pig");
            funFactService.addFunFact("Pigs are navigators: they can find their way home over large distances. They can often trot long distances and can reach up to 17 km per hour running.", "Pig");
            funFactService.addFunFact("The average fully grown pig can weigh between 135 and 315 kilo. However the world record for the heaviest pig has not been beaten since 1933. Big Bill from Tennessee weighed in at 1157 kilo. Now that is a big pig.", "Pig");
            funFactService.addFunFact("Pigs have amazing memories. They can remember things for years and years and can recognize and remember people and places, sources of food and even how to use objects.", "Pig");

            funFactService.addFunFact("Sheep are herbivores, which means their diet does not include meat. They typically eat seeds, grass and plants.", "Sheep");
            funFactService.addFunFact("A group of sheep is known as a flock, herd, or mob.", "Sheep");
            funFactService.addFunFact("The eyes of a sheep are placed on its head in such a way that they have a field of vision of around 300 degrees. This allows the sheep to see behind themselves – without having to turn their head!", "Sheep");

            funFactService.addFunFact("Chickens dream when they sleep! Just like humans and other mammals, chickens have a REM phase of sleep, which signifies dreaming.", "Chicken");
            funFactService.addFunFact("Chickens can literally sleep with one eye open! Chickens have a sleep phase known as USWS (unihemispheric slow-wave sleep), where one half of the brain is lseeping while the other is awake.", "Chicken");
            funFactService.addFunFact("Chickens have complex communication with many different meanings! Chickens are capable of at least 30 differet vocalizations and one means something specific. They alert each other for food, call young chicks, purr in contentment " +
                    "and warn other flock members of predators. Mother hens even talk to their babies while they are still in their eggs!", "Chicken");
            funFactService.addFunFact("Chickens have a profound memory and are able to distinguish between more than 100 faces of their species. They can also recognise people.", "Chicken");
            funFactService.addFunFact("Chickens are smart! The cognitive skills of chickens are similarly high to those of a dog or cat.", "Chicken");
            funFactService.addFunFact("Chickens can taste salt but cannot perceive any sweet tastes.", "Chicken");

            funFactService.addFunFact("Donkeys can live for over 50 years","Donkey");
            funFactService.addFunFact("A donkey is stronger than a horse of the same size.","Donkey");
            funFactService.addFunFact("Donkeys are more independent in their thinking than horses and will reason, then make decisions based on their safety.","Donkey");
            funFactService.addFunFact("Donkeys don’t like being kept on their own although a single donkey will live quite happily with goats.","Donkey");

            funFactService.addFunFact("To express happiness, bunnies will sometimes jump around and flick their heads and feet. That adorable behavior is known within the rabbit community as a “binky.","Rabbit");
            funFactService.addFunFact("A bunny’s big ears aren’t just for listening! They also help regulate the rabbit’s body temperatures. The ears’ blood vessels swell when it’s hot out, and contract when it’s cold.","Rabbit");
            funFactService.addFunFact("Rabbits can hop and BOY can they also jump! In fact, rabbits can jump to impressive heights and distances….a little over 90 cm high and a whopping 3 m long!","Rabbit");
            funFactService.addFunFact("The Guinness World Record for the largest rabbit is held by a 55-pound British pet rabbit named Ralph. He eats $90 of food a week!","Rabbit");

            funFactService.addFunFact("A male deer is called a buck but some larger males are referred to as stags. A Female deer is called a doe or hind. A young deer is called a fawn. Bucks and does can be distinguished from each other by the presence of antlers.", "Deer");
            funFactService.addFunFact("Each year, antlers fall off and regrow. As they regrow, they are covered in a furry coat called velvet. The velvet is rich in nerves and blood vessels, allowing the antlers to regrow quickly", "Deer");
            funFactService.addFunFact("A fawn can normally walk within half an hour of being born.", "Deer");
            funFactService.addFunFact("When most deer are born, they have white spots which disappear as they mature. The spots help fawns to blend into their background, acting as camouflage", "Deer");

            funFactService.addFunFact("Horses can’t breathe through their mouth." +
                    " Horses are “obligate nose breathers” meaning that they can only breathe through their nose, not through their mouth as humans can.", "Horse");
            funFactService.addFunFact("Horses can sleep standing up." +
                    " Horses have what’s called a “stay-apparatus” which is a system of tendons and ligaments that allows the horse to lock their legs in position so they can relax without falling over.", "Horse");
            funFactService.addFunFact("Horses have lightning fast reflexes." +
                    " Should a situation arise when they need to fight, they can go from standing still to delivering a powerful kick in just 0.3 seconds, whereas human reaction time is 1.6 seconds.", "Horse");
            funFactService.addFunFact("Horses have 10 different muscles in their ears." +
                    " This allows them to rotate nearly 180 degrees and move independently of one another. Humans only have three muscles in their ears.", "Horse");
            funFactService.addFunFact("Horses have a nearly 360 degree field of vision." +
                    " This is due to the positioning of their eyes on the sides of their head. However, they have two blind spots – one directly behind them, and the other just in-front and below their nose. " +
                    "This means that they cannot see the grass they are grazing on, or the carrot you are sticking out to them! Instead they use their mobile and sensitive lips, whiskers and sense of smell to know what is in-front of them and decide if they want to eat it.", "Horse");
        }
    }
}
