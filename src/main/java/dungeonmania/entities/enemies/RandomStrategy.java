package dungeonmania.entities.enemies;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import dungeonmania.util.Position;

import dungeonmania.Game;
import dungeonmania.entities.Entity;
import dungeonmania.map.GameMap;

public class RandomStrategy implements MoveStrategy {
    private Random randGen = new Random();

    @Override
    public void performMovement(Game game, Enemy enemy) {
        Entity zombieToast = (Entity) enemy;
        Position nextPos;
        GameMap map = game.getMap();
        List<Position> pos = zombieToast.getPosition().getCardinallyAdjacentPositions();
        pos = pos.stream().filter(p -> map.canMoveTo(zombieToast, p)).collect(Collectors.toList());
        if (pos.size() == 0) {
            nextPos = zombieToast.getPosition();
        } else {
            nextPos = pos.get(randGen.nextInt(pos.size()));
        }
        map.moveTo(enemy, nextPos);
    }

}
