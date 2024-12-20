package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class ZombieToast extends Enemy {
    public static final double DEFAULT_HEALTH = 5.0;
    public static final double DEFAULT_ATTACK = 6.0;

    public ZombieToast(Position position, double health, double attack) {
        super(position, health, attack);
    }

    @Override
    public void move(Game game) {

        if (getSwampTileTime() > 0) {
            setSwampTileTime(getSwampTileTime() - 1);
        } else {
            GameMap map = game.getMap();
            if (map.getPlayer().getEffectivePotion() instanceof InvincibilityPotion) {
                setStrategy(new InvincibilityStrategy());
            } else {
                setStrategy(new RandomStrategy());
            }
            getStrategy().performMovement(game, this);

        }
    }

}
