package dungeonmania.entities.buildables;

public class Bow extends Buildable {
    public Bow(int durability) {
        super(null, new BowBattleBuffStrategy());
        setDurability(durability);
    }

}
