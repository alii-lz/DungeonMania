package dungeonmania.entities.playerState;

import dungeonmania.entities.Player;
import java.util.Queue;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.entities.collectables.potions.Potion;

public class BaseState extends PlayerState {
    public BaseState(Player player) {
        super(player);
    }

    @Override
    public void transitionToNextPotion(int currentTick) {
        Player player = getPlayer();
        Queue<Potion> queue = player.getQueue();

        if (queue.isEmpty()) {
            player.setInEffective(null);
            return;
        }

        Potion nextPotion = queue.remove();
        player.setInEffective(nextPotion);

        if (player.getEffectivePotion() instanceof InvincibilityPotion) {
            player.changeState(new InvincibleState(player));
        } else {
            player.changeState(new InvisibleState(player));
        }

        player.setNextTrigger(currentTick + nextPotion.getDuration());
    }

}
