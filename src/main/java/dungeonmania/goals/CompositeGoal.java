package dungeonmania.goals;

import dungeonmania.Game;

public abstract class CompositeGoal implements Goal {
    private Goal goal1;
    private Goal goal2;

    public CompositeGoal(Goal goal1, Goal goal2) {
        this.goal1 = goal1;
        this.goal2 = goal2;
    }

    @Override
    public abstract boolean achieved(Game game);

    @Override
    public abstract String toString(Game game);

    public Goal getGoal1() {
        return goal1;
    }

    public void setGoal1(Goal goal1) {
        this.goal1 = goal1;
    }

    public Goal getGoal2() {
        return goal2;
    }

    public void setGoal2(Goal goal2) {
        this.goal2 = goal2;
    }

}
