public class Person {
    private int health;
    private int food;
    private int shelter;
    private String skill;
    private boolean dead = false;

    public Person(String skill) {
        health = 10;
        food = 10;
        shelter = 10;
        this.skill = skill;
    }

    public void setHealth(int health) {
        this.health = health;
        if(this.health > 10) {
            this.health = 10;
        }
        if(this.health < 0) {
            this.health = 0;
        }
    }

    public int getHealth() {
        return health;
    }

    public void incrementHealth(int i) {
        if(!dead) {
            setHealth(health += i);
        }
    }

    public void setFood(int food) {
        this.food = food;
        if(this.food < 0) {
            this.food = 0;
        }
    }

    public int getFood() {
        return food;
    }

    public void incrementFood(int i) {
        if(!dead) {
            setFood(food += i);
        }
    }

    public void setShelter(int shelter) {
        this.shelter = shelter;
        if(this.shelter > 10) {
            this.shelter = 10;
        }
        if(this.shelter < 0) {
            this.shelter = 0;
        }
    }

    public int getShelter() {
        return shelter;
    }
    
    public void incrementShelter(int i) {
        if(!dead) {
            setShelter(shelter += i);
        }
    }

    public String getSkill() {
        return skill;
    }

    public void setDeath(boolean dead) {
        this.dead = dead;
    }

    public boolean getDeath() {
        return dead;
    }

    public String toString() {
        return skill + " : " + health + " " + food + " " + shelter;
    }
}