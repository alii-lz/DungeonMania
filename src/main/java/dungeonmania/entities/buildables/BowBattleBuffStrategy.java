package dungeonmania.entities.buildables;

import dungeonmania.battles.BattleStatistics;

public class BowBattleBuffStrategy implements BattleBuffStrategy {
    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, 0, 0, 2, 1));
    }
}
