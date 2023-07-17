package dungeonmania.entities.enemies;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import dungeonmania.Game;
import dungeonmania.entities.Player;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class InvisibilityStrategy implements MoveStrategy {
    @Override
    public void performMovement(Game game, Enemy enemy) {
        Position nextPos;
        Player player = game.getPlayer();
        GameMap map = game.getMap();
        // Move random
        Random randGen = new Random();
        List<Position> pos = player.getPosition().getCardinallyAdjacentPositions();
        pos = pos.stream().filter(p -> map.canMoveTo(enemy, p)).collect(Collectors.toList());
        if (pos.size() == 0) {
            nextPos = player.getPosition();
            map.moveTo(enemy, nextPos);

        } else {
            nextPos = pos.get(randGen.nextInt(pos.size()));
            map.moveTo(enemy, nextPos);
        }
    }

}
