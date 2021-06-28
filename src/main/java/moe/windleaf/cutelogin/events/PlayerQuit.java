package moe.windleaf.cutelogin.events;

import moe.windleaf.cutelogin.CuteLogin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void handler(PlayerQuitEvent e) {
        if (CuteLogin.loginMapManager.isLogin(e.getPlayer())) {
            CuteLogin.autoLoginThreadSchedule = new moe.windleaf.cutelogin.schedule.autologin.ThreadSchedule(
                    CuteLogin.INSTANCE.getConfig().getInt("auto-login.time"), e.getPlayer()
            );
            CuteLogin.autoLoginThreadSchedule.start();
        }
    }

}
