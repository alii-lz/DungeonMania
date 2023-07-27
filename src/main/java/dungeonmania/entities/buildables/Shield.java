package dungeonmania.entities.buildables;

public class Shield extends Buildable {
    public Shield(int durability, double defence) {
        super(null, new ShieldBattleBuffStrategy(defence));
        setDurability(durability);
    }
}
