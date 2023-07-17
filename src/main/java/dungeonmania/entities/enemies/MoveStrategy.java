package dungeonmania.entities.enemies;

import dungeonmania.Game;

public interface MoveStrategy {
    public void performMovement(Game game, Enemy enemy);
}
