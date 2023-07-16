package dungeonmania.entities.enemies;

import dungeonmania.Game;

public interface MoveStrategy {
    public void perform(Game game, Enemy enemy);
}
