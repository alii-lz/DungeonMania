package dungeonmania.mvp.Task2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dungeonmania.entities.enemies.Hydra;
import dungeonmania.util.Position;

public class HydraTest {
    private Hydra hydra;
    private static final int TEST_RUNS = 1000;

    @BeforeEach
    public void setUp() {
        hydra = new Hydra(new Position(0, 0), 5, 6);
    }

    @Test
    public void testUpdateHealthRunRate() {
        int healthIncreaseCount = 0;

        for (int i = 0; i < TEST_RUNS; i++) {
            double initialHealth = hydra.getBattleStatistics().getHealth();

            // attack hydra
            hydra.getBattleStatistics().setHealth(initialHealth - 2);
            hydra.updateHealth();

            double afterUpdateHealth = hydra.getBattleStatistics().getHealth();

            // check health increase
            if (afterUpdateHealth > initialHealth) {
                healthIncreaseCount++;
            }
        }

        double healthIncreasePercentage = (double) healthIncreaseCount / TEST_RUNS * 100;
        System.out.println("Health increased " + healthIncreaseCount + " times out of " + TEST_RUNS
                + " test runs, percentage result " + healthIncreasePercentage + "%");

        // check health increase probability close to 50%
        assertEquals(50, healthIncreasePercentage, 5,
                "The health increase percentage should be approximately 50%, with tollerance 5%");
    }
}
