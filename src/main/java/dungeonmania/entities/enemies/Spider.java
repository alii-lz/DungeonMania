package dungeonmania.entities.enemies;

import java.util.List;

import dungeonmania.Game;
import dungeonmania.entities.Entity;
import dungeonmania.util.Position;

public class Spider extends Enemy {
    private List<Position> movementTrajectory;
    private int nextPositionElement;
    private boolean forward;

    public static final int DEFAULT_SPAWN_RATE = 0;
    public static final double DEFAULT_ATTACK = 5;
    public static final double DEFAULT_HEALTH = 10;

    public Spider(Position position, double health, double attack) {
        super(position.asLayer(Entity.DOOR_LAYER + 1), health, attack);
        /**
         * Establish spider movement trajectory Spider moves as follows:
         *  8 1 2       10/12  1/9  2/8
         *  7 S 3       11     S    3/7
         *  6 5 4       B      5    4/6
         */
        movementTrajectory = position.getAdjacentPositions();
        nextPositionElement = 1;
        forward = true;
    };

    public void updateNextPosition() {
        if (forward) {
            nextPositionElement++;
            if (nextPositionElement == 8) {
                nextPositionElement = 0;
            }
        } else {
            nextPositionElement--;
            if (nextPositionElement == -1) {
                nextPositionElement = 7;
            }
        }
    }

    @Override
    public void move(Game game) {

        if (getSwampTileTime() > 0) {
            setSwampTileTime(getSwampTileTime() - 1);
        } else {
            setStrategy(new SpiderStrategy());
            getStrategy().performMovement(game, this);
        }
    }

    public List<Position> getMovementTrajectory() {
        return movementTrajectory;
    }

    public void setMovementTrajectory(List<Position> movementTrajectory) {
        this.movementTrajectory = movementTrajectory;
    }

    public int getNextPositionElement() {
        return nextPositionElement;
    }

    public void setNextPositionElement(int nextPositionElement) {
        this.nextPositionElement = nextPositionElement;
    }

    public boolean isForward() {
        return forward;
    }

    public void setForward(boolean forward) {
        this.forward = forward;
    }

    public static int getDefaultSpawnRate() {
        return DEFAULT_SPAWN_RATE;
    }

    public static double getDefaultAttack() {
        return DEFAULT_ATTACK;
    }

    public static double getDefaultHealth() {
        return DEFAULT_HEALTH;
    }
}
