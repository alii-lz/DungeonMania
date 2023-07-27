package dungeonmania.entities.enemies;

import dungeonmania.util.Position;
import dungeonmania.entities.Player;
import java.util.Random;

public class Assassin extends Mercenary {
    public static final double DEFAULT_ATTACK = 8.0; // double damage than mercenary
    public static final int DEFAULT_BRIBE_SUCCESS_CHANCE = 30;

    private int bribeSuccessChance = DEFAULT_BRIBE_SUCCESS_CHANCE;
    private Random random; //Random generator for success chance

    public Assassin(Position position, double health, double attack, int bribeAmount, int bribeRadius,
            double allyAttack, double allyDefence) {
        super(position, health, attack, bribeAmount, bribeRadius, allyAttack, allyDefence);
        random = new Random(System.currentTimeMillis());
    }

    public void setBribeSuccessChance(int bribeSuccessChance) {
        this.bribeSuccessChance = bribeSuccessChance;
    }

    @Override
    public boolean canBeBribed(Player player) {
        // Check enough treasures and the bribe chance succeeds
        return super.canBeBribed(player) && random.nextInt(100) < bribeSuccessChance;
    }
}
