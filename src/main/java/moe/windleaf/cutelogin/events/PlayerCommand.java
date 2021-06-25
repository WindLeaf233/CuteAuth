package moe.windleaf.cutelogin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class PlayerCommand implements Listener {
    @EventHandler
    public void handler(PlayerCommandSendEvent e) {
        e.getHandlers().bake();
    }
}
