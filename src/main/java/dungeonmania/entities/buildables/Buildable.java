package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.BattleItem;
import dungeonmania.entities.Entity;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.util.Position;

public abstract class Buildable extends Entity implements InventoryItem, BattleItem {
    private int durability;
    private BattleBuffStrategy buffStrategy;

    public Buildable(Position position, BattleBuffStrategy buffStrategy) {
        super(position);
        this.buffStrategy = buffStrategy;
    }

    public void use(Game game) {
        durability--;
        if (durability <= 0) {
            game.getPlayer().remove(this);
        }
    }

    public BattleStatistics applyBuff(BattleStatistics origin) {
        return buffStrategy.applyBuff(origin);
    }

    public int getDurability() {
        return durability;
    }

    protected void setDurability(int durability) {
        this.durability = durability;
    }
}
