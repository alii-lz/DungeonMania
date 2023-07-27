package dungeonmania.mvp.Task2;

import dungeonmania.entities.enemies.Assassin;
import dungeonmania.entities.Player;
import dungeonmania.entities.collectables.Treasure;
import dungeonmania.util.Position;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class AssassinTest {
    private Player player;
    private Assassin assassin;

    @BeforeEach
    void setup() {
        //create player
        player = new Player(new Position(0, 0), 100.0, 5.0);
        for (int i = 0; i < 5; i++) {
            player.pickUp(new Treasure(new Position(0, 0)));
        }
        //create assassin
        assassin = new Assassin(new Position(1, 1), 10.0, 8.0, 3, 1, 5.0, 5.0);
    }

    @Test
    void testBribePossibility() {
        // Test if player can sometimes bribe the assassin and sometimes not
        boolean bribedAtLeastOnce = false;
        boolean notBribedAtLeastOnce = false;
        for (int i = 0; i < 100; i++) {
            if (assassin.canBeBribed(player)) {
                bribedAtLeastOnce = true;
            } else {
                notBribedAtLeastOnce = true;
            }
            if (bribedAtLeastOnce && notBribedAtLeastOnce) {
                break;
            }
        }

        assertTrue(bribedAtLeastOnce, "Player able to bribe the assassin sometimes");
        assertTrue(notBribedAtLeastOnce, "Player unable to bribe the assassin sometimes");
    }

    @Test
    void testInsufficientTreasures() {
        // finish players treasure
        while (player.countEntityOfType(Treasure.class) > 0) {
            player.use(Treasure.class);
        }

        // Test if player cannot bribe the assassin due to insufficient treasures
        assertFalse(assassin.canBeBribed(player), "Player should not be able to bribe the assassin");
    }

    @Test
    void testBribeSuccessRate() {
        // Test success rate
        assassin.setBribeSuccessChance(Assassin.DEFAULT_BRIBE_SUCCESS_CHANCE);
        int numTrials = 1000;
        int numSuccesses = 0;
        for (int i = 0; i < numTrials; i++) {
            if (assassin.canBeBribed(player)) {
                numSuccesses++;
            }
        }
        // check with tollerance 0.1
        double successRate = numSuccesses / (double) numTrials;
        assertEquals(Assassin.DEFAULT_BRIBE_SUCCESS_CHANCE / 100.0, successRate, 0.1,
                "success rate around 50%, with tollerance 1%");
    }
}
