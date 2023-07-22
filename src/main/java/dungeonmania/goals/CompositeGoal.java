package dungeonmania.goals;

import dungeonmania.Game;

public abstract class CompositeGoal implements Goal {
    protected Goal goal1;
    protected Goal goal2;

    public CompositeGoal(Goal goal1, Goal goal2) {
        this.goal1 = goal1;
        this.goal2 = goal2;
    }

    @Override
    public abstract boolean achieved(Game game);

    @Override
    public abstract String toString(Game game);

}
