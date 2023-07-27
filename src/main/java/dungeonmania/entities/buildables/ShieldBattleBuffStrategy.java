package dungeonmania.entities.buildables;

import dungeonmania.battles.BattleStatistics;

public class ShieldBattleBuffStrategy implements BattleBuffStrategy {
    private double defence;

    public ShieldBattleBuffStrategy(double defence) {
        this.defence = defence;
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, 0, defence, 1, 1));
    }

}
