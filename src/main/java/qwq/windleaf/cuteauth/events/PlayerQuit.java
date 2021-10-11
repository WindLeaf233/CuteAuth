package qwq.windleaf.cuteauth.events;

import qwq.windleaf.cuteauth.CuteAuth;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import qwq.windleaf.cuteauth.schedules.autologin.ThreadSchedule;

public class PlayerQuit implements Listener {

    @EventHandler
    public void handler(PlayerQuitEvent e) {
        if (CuteAuth.loginMapManager.isLogin(e.getPlayer())) {
            CuteAuth.autoLoginThreadSchedule = new ThreadSchedule(
                    CuteAuth.instance.getConfig().getInt("auto-login.time"), e.getPlayer()
            );
            CuteAuth.autoLoginThreadSchedule.start();
        }
    }

}
