package dungeonmania.entities.playerState;

import java.util.Queue;
import dungeonmania.entities.Player;
import dungeonmania.entities.collectables.potions.InvisibilityPotion;
import dungeonmania.entities.collectables.potions.Potion;

public class InvincibleState extends PlayerState {
    public InvincibleState(Player player) {
        super(player);
    }

    @Override
    public void transitionToNextPotion(int currentTick) {
        Player player = getPlayer();
        Queue<Potion> queue = player.getQueue();

        if (queue.isEmpty()) {
            player.changeState(new BaseState(player));
            player.setInEffective(null);
            return;
        }

        Potion nextPotion = queue.remove();
        player.setInEffective(nextPotion);

        if (player.getEffectivePotion() instanceof InvisibilityPotion) {
            player.changeState(new InvisibleState(player));
        }

        player.setNextTrigger(currentTick + nextPotion.getDuration());
    }

}
