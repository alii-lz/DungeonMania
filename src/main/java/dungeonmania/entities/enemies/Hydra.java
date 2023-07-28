package dungeonmania.entities.enemies;

import dungeonmania.util.Position;
import java.util.Random;

public class Hydra extends ZombieToast {
    private double previousHealth;
    private Random random;

    public Hydra(Position position, double health, double attack) {
        super(position, health, attack);
        this.previousHealth = health;
        // current time as seed for the random number generator
        this.random = new Random(System.currentTimeMillis());
    }

    public void updateHealth() {
        // Check if Hydra has been attacked
        double currentHealth = getBattleStatistics().getHealth();
        if (currentHealth < previousHealth) {
            // increase health
            if (random.nextInt(100) < 50) { // 50% chance to gain health
                double newHealth = DEFAULT_HEALTH + currentHealth;
                getBattleStatistics().setHealth(newHealth);
            }
        }
        this.previousHealth = getBattleStatistics().getHealth();
    }
}
