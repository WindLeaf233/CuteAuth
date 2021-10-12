package qwq.windleaf.cuteauth.forbidden;

import qwq.windleaf.cuteauth.CuteAuth;
import qwq.windleaf.cuteauth.Log;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import qwq.windleaf.cuteauth.utils.Utils;

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
