import java.util.Random;

public class Obstacle {
    private String name;
    private int damage, award, healthy, maxNumber;

    public Obstacle(String name, int damage, int award, int healthy, int maxNumber) {
        this.name = name;
        this.damage = damage;
        this.award = award;
        this.healthy = healthy;
        this.maxNumber = maxNumber;

    }

    public int count() {
        Random r = new Random();
        return r.nextInt(this.maxNumber) + 1;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getAward() {
        return award;
    }

    public int getHealthy() {
        return healthy;
    }

    public void setHealthy(int healthy) {
        this.healthy = healthy;
    }
}