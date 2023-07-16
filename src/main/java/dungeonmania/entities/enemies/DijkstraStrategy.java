package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.entities.Player;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class DijkstraStrategy implements MoveStrategy {
    @Override
    public void perform(Game game, Enemy enemy) {
        Player player = game.getPlayer();
        GameMap map = game.getMap();
        Position nextPos = map.dijkstraPathFind(enemy.getPosition(), player.getPosition(), enemy);
        map.moveTo(enemy, nextPos);
    }

}
