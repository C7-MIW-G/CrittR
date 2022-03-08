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

    @EventListener
    public void seedFunFact(ContextRefreshedEvent event) {
        if (funFactService.getAll().size() == 0) {
            funFactService.addFunFact("Cows can sleep standing up, but they can only dream lying down.");
            funFactService.addFunFact("Just like you, cows have best friends. Not only does companionship make cows happier, it also makes them smarter.");
            funFactService.addFunFact("Goats were one of the first animals to be tamed by humans and were being herded 9,000 years ago.");
            funFactService.addFunFact("Goats can be taught their name and to come when called");
            funFactService.addFunFact("Goats’ pupils (like many hooved animals) are rectangular. This gives them vision for 320 to 340 degrees (compared to humans with 160-210) around them without having to move and they are thought to have excellent night vision.");

            funFactService.addFunFact("Pigs have the intelligence of a human toddler and are ranked as the fifth most intelligent animal in the world! In fact, pigs are more intelligent and trainable than any breed of dog.");
            funFactService.addFunFact("Pigs are navigators: they can find their way home over large distances. They can often trot long distances and can reach up to 11 miles per hour running.");
            funFactService.addFunFact("The average fully grown pig can weigh between 300 and 700 pounds. However the world record for the heaviest pig has not been beaten since 1933. Big Bill from Tennessee weighed in at 2,552 pounds. Now that is a big pig.");
            funFactService.addFunFact("Pigs have amazing memories. They can remember things for years and years and can recognize and remember people and places, sources of food and even how to use objects.");

            funFactService.addFunFact("Sheep are herbivores, which means their diet does not include meat. They typically eat seeds, grass and plants.");
            funFactService.addFunFact("A group of sheep is known as a flock, herd, or mob.");
            funFactService.addFunFact("The eyes of a sheep are placed on its head in such a way that they have a field of vision of around 300 degrees. This allows the sheep to see behind themselves – without having to turn their head!");

            funFactService.addFunFact("Chickens dream when they sleep! Just like humans and other mammals, chickens have a REM phase of sleep, which signifies dreaming.");
            funFactService.addFunFact("Chickens can literally sleep with one eye open! Chickens have a sleep phase known as USWS (unihemispheric slow-wave sleep), where one half of the brain is lseeping while the other is awake.");
            funFactService.addFunFact("Chickens have complex communication with many different meanings! Chickens are capable of at least 30 differet vocalizations and one means something specific. They alert each other for food, call young chicks, purr in contentment and warn other flock members of predators. Mother hens even talk to their babies while they are still in their eggs!");
            funFactService.addFunFact("Chickens have a profound memory and are able to distinguish between more than 100 faces of their species. They can also recognise people.");
            funFactService.addFunFact("Chickens are smart! The cognitive skills of chickens are similarly high to those of a dog or cat.");
            funFactService.addFunFact("Chickens can taste salt but cannot perceive any sweet tastes.");

            funFactService.addFunFact("Donkeys can live for over 50 years");
            funFactService.addFunFact("A donkey is stronger than a horse of the same size.");
            funFactService.addFunFact("Donkeys are more independent in their thinking than horses and will reason, then make decisions based on their safety.");
            funFactService.addFunFact("Donkeys don’t like being kept on their own although a single donkey will live quite happily with goats.");

            funFactService.addFunFact("To express happiness, bunnies will sometimes jump around and flick their heads and feet. That adorable behavior is known within the rabbit community as a “binky.");
            funFactService.addFunFact("A bunny’s big ears aren’t just for listening! They also help regulate the rabbit’s body temperatures. The ears’ blood vessels swell when it’s hot out, and contract when it’s cold.");
            funFactService.addFunFact("Rabbits can hop and BOY can they also jump! In fact, rabbits can jump to impressive heights and distances….a little over 3 feet high and a whopping 10 feet long!");
            funFactService.addFunFact("The Guinness World Record for the largest rabbit is held by a 55-pound British pet rabbit named Ralph. He eats $90 of food a week!");

            funFactService.addFunFact("A male deer is called a buck but some larger males are referred to as stags. A Female deer is called a doe or hind. A young deer is called a fawn. Bucks and does can be distinguished from each other by the presence of antlers.");
            funFactService.addFunFact("Each year, antlers fall off and regrow. As they regrow, they are covered in a furry coat called velvet. The velvet is rich in nerves and blood vessels, allowing the antlers to regrow quickly");
            funFactService.addFunFact("A fawn can normally walk within half an hour of being born.");
            funFactService.addFunFact("When most deer are born, they have white spots which disappear as they mature. The spots help fawns to blend into their background, acting as camouflage");
        }
    }
}
