package ml.windleaf.cuteauth.forbidden;

import ml.windleaf.cuteauth.CuteAuth;
import ml.windleaf.cuteauth.Log;
import ml.windleaf.cuteauth.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ExecuteCommand implements Listener {

    @EventHandler
    public void handler(PlayerCommandPreprocessEvent e) {
        if (CuteAuth.instance.getConfig().getBoolean("forbidden.execute-command")) {
            if (!CuteAuth.loginMapManager.isLogin(e.getPlayer())) {
                if (!Utils.isPluginCommand(e.getMessage())) {
                    Log.send(e.getPlayer(), "&c呃, 你在登录之前还不能执行命令哦!");
                    e.setCancelled(true);
                }
            }
        }
    }

}
