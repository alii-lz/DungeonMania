package dungeonmania.goals;

import dungeonmania.Game;
import dungeonmania.entities.enemies.ZombieToastSpawner;

public class EnemyGoal implements Goal {
    private int enemiesToKill;

    public EnemyGoal(int enemiesToKill) {
        this.enemiesToKill = enemiesToKill;
    }

    @Override
    public boolean achieved(Game game) {
        int kills = game.getKills();
        return game.getMap().getEntities(ZombieToastSpawner.class).size() == 0 && kills >= enemiesToKill;
    }

    @Override
    public String toString(Game game) {
        if (achieved(game)) {
            return "";
        } else {
            return ":enemy";
        }
    }

    public int getEnemiesToKill() {
        return enemiesToKill;
    }

    public void setEnemiesToKill(int enemiesToKill) {
        this.enemiesToKill = enemiesToKill;
    }

}
