package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.battles.Battleable;
import dungeonmania.entities.Destroy;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Overlap;
import dungeonmania.entities.Player;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public abstract class Enemy extends Entity implements Battleable, Overlap, Destroy {
    private BattleStatistics battleStatistics;
    private MoveStrategy strategy;
    private int swampTileTime;

    public Enemy(Position position, double health, double attack) {
        super(position.asLayer(Entity.CHARACTER_LAYER));
        battleStatistics = new BattleStatistics(health, attack, 0, BattleStatistics.DEFAULT_DAMAGE_MAGNIFIER,
                BattleStatistics.DEFAULT_ENEMY_DAMAGE_REDUCER);
        this.swampTileTime = 0;
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return entity instanceof Player;
    }

    @Override
    public BattleStatistics getBattleStatistics() {
        return battleStatistics;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (entity instanceof Player) {
            Player player = (Player) entity;
            map.getGame().battle(player, this);
        }
    }

    @Override
    public void onDestroy(GameMap map) {
        Game g = map.getGame();
        g.unsubscribe(getId());
    }

    public abstract void move(Game game);

    public MoveStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(MoveStrategy strategy) {
        this.strategy = strategy;
    }

    public double getHealthFromBattleStatistics() {
        return battleStatistics.getHealth();
    }

    public void setHealthFromBattleStatistics(double health) {
        this.battleStatistics.setHealth(health);
    }

    public int getSwampTileTime() {
        return swampTileTime;
    }

    public void setSwampTileTime(int swampTileTime) {
        this.swampTileTime = swampTileTime;
    }

}
