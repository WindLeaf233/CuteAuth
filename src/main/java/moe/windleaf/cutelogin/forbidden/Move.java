package moe.windleaf.cutelogin.forbidden;

import moe.windleaf.cutelogin.CuteLogin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Move implements Listener {

    @EventHandler
    public void handler(PlayerMoveEvent e) {
        if (!CuteLogin.loginMapManager.isLogin(e.getPlayer())) { e.setCancelled(true); }
    }

}
