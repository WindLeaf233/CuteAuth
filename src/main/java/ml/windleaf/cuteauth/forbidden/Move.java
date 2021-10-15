package ml.windleaf.cuteauth.forbidden;

import ml.windleaf.cuteauth.CuteAuth;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Move implements Listener {

    @EventHandler
    public void handler(PlayerMoveEvent e) {
        if (!CuteAuth.loginMapManager.isLogin(e.getPlayer())) e.setCancelled(true);
    }

}
