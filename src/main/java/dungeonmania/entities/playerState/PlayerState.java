package dungeonmania.entities.playerState;

import dungeonmania.entities.Player;

public abstract class PlayerState {
    private Player player;

    PlayerState(Player player) {
        this.player = player;

    }

    public Player getPlayer() {
        return player;
    }

    public abstract void transitionToNextPotion(int currentTick);

}
