import java.util.Random;

public class Simulation {
    public static void main(String[] args) {
        String mode = args[0];
        RunSimulation(mode);
    }
    
    public static int RunSimulation(String mode) {
        Person[] people = {new Person("doctor"), new Person("farmer"), new Person("carpenter"), new Person("hunter")};
        int dayCount = 1;

        if(mode.equalsIgnoreCase("society") == false && mode.equalsIgnoreCase("anarchy") == false) {
            System.out.println("invalid mode: " + mode);
            System.exit(0);
        }

        System.out.println("Running in " + mode + " mode...");

        while(dayCount <= 365) {
            Random rand = new Random();

            // 1. Apply the skills described in the table above for each person based on the mode
            if(mode.equalsIgnoreCase("society")) {
                // doctor
                if(people[0].getDeath() == false) {
                    if(people[0].getFood() == 1) {
                        people[0].incrementFood(1);
                    }
                    else {
                        people[1].incrementHealth(2);
                        people[2].incrementHealth(2);
                        people[3].incrementHealth(2);
                    }
                }

                //farmer
                if(people[1].getDeath() == false) {
                    if(dayCount % 3 == 0) {
                        for(Person p: people) {
                            p.incrementFood(3);
                        }
                    }
                }

                // carpenter
                if(people[2].getDeath() == false) {
                    if(people[2].getFood() == 1) {
                        people[2].incrementFood(1);
                    }
                    else {
                        people[0].incrementShelter(1);
                        people[1].incrementShelter(1);
                        people[3].incrementShelter(1);
                    }
                }

                // hunter
                if(people[3].getDeath() == false) {
                    if(rand.nextInt(5) == 0) {
                        for(Person p: people) {
                            p.incrementFood(2);
                        }
                    }
                }
            }
            else {
                // doctor
                if(people[0].getDeath() == false) {
                    if(people[0].getFood() == 1) {
                        people[0].incrementFood(1);
                    }
                    else {
                        people[0].incrementHealth(2);
                    }
                }

                //farmer
                if(people[1].getDeath() == false) {
                    if(dayCount % 3 == 0) {
                        people[1].incrementFood(3);
                    }
                }

                // carpenter
                if(people[2].getDeath() == false) {
                    if(people[2].getFood() == 1) {
                        people[2].incrementFood(1);
                    }
                    else {
                        people[2].incrementShelter(2);
                    }
                }

                // hunter
                if(people[3].getDeath() == false) {
                    if(rand.nextInt(5) == 0) {
                        people[3].incrementFood(2);
                    }
                }
            }

            // 2. Decrement the food attribute of each person.
            for(Person p: people) {
                p.incrementFood(-1);
            }

            // 3. Generate a random disaster (with a 1 in 5 probability of no disaster)
            int disasterChance = rand.nextInt(5);
            
            // 4. Apply the disaster to each person according to the table shown above
            System.out.print("Day " + dayCount + ": ");
            switch(disasterChance) {
                case 0:
                    // None
                    System.out.println();
                    break;
                case 1:
                    // Hurricane
                    System.out.println("hurricane");
                    for(Person p: people) {
                        if(p.getShelter() == 0) {
                            p.incrementHealth(-5);
                        }
                        else {
                            p.incrementShelter(-3);
                        }
                    }
                    break;
                case 2:
                    // Famine
                    System.out.println("famine");
                    for(Person p: people) {
                        p.incrementFood(-2);
                    }
                    break;
                case 3:
                    // Disease
                    System.out.println("disease");
                    for(Person p: people) {
                        p.incrementHealth(-2);
                    }
                    break;
                case 4:
                    // Wolves
                    System.out.println("wolves");
                    if(mode.equalsIgnoreCase("society")) {
                        if(people[3].getHealth() != 0) {
                            for(Person p: people) {
                                p.incrementHealth(-1);
                            }
                        }
                        else {
                            for(Person p: people) {
                                p.incrementHealth(-3);
                            }
                        }
                    }
                    else {
                        people[0].incrementHealth(-3);
                        people[1].incrementHealth(-3);
                        people[2].incrementHealth(-3);
                        people[3].incrementHealth(-1);
                    }
                    break;
            }

            // 5. Display the status of each person and their 3 attributes
            System.out.println("------------------------------");
            for(Person p: people) {
                System.out.println(p.toString());
            }
            System.out.println();

            // 6. For each person check if the health or food attribute is 0
            for(Person p: people) {
                if(p.getHealth() == 0 || p.getFood() == 0) {
                    // 7. If a person’s health or food is 0 that person is dead and removed from the simulation
                    if(p.getDeath() == false) {
                        System.out.println(p.getSkill() + " is dead");
                        p.setDeath(true);
                    }
                }
            }

            int numDead = 0;
            for(Person p: people) {
                if(p.getDeath()) {
                    numDead++;
                }
            }

            if(numDead == 4) {
                System.out.println("Everybody is dead");
            }
            System.out.println();

            // 8. Increment the count of days and continue until everyone is dead or the society reaches 365 days
            dayCount++;
            
            // 9. Report the number of days 
            if(numDead == 4) {
                System.out.println(dayCount-1 + " days");
                break;
            }
        }

        return dayCount;
    }
}
