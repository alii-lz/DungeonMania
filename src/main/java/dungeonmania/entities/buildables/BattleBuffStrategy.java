package dungeonmania.entities.buildables;

import dungeonmania.battles.BattleStatistics;

public interface BattleBuffStrategy {
    BattleStatistics applyBuff(BattleStatistics origin);
}
