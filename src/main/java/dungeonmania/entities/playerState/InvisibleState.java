package dungeonmania.entities.playerState;

import java.util.Queue;
import dungeonmania.entities.Player;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.entities.collectables.potions.Potion;

public class InvisibleState extends PlayerState {
    public InvisibleState(Player player) {
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

        if (player.getEffectivePotion() instanceof InvincibilityPotion) {
            player.changeState(new InvincibleState(player));
        }

        player.setNextTrigger(currentTick + nextPotion.getDuration());
    }
}
