package qwq.windleaf.cuteauth.forbidden;

import qwq.windleaf.cuteauth.CuteAuth;
import qwq.windleaf.cuteauth.utils.FilterUtil;
import qwq.windleaf.cuteauth.utils.LogUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ExecuteCommand implements Listener {

    @EventHandler
    public void handler(PlayerCommandPreprocessEvent e) {
        if (CuteAuth.instance.getConfig().getBoolean("forbidden.execute-command")) {
            if (!CuteAuth.loginMapManager.isLogin(e.getPlayer())) {
                if (!FilterUtil.isCuteLoginCommand(e.getMessage())) {
                    LogUtil.logPlayer(e.getPlayer(), "&cEmmm, 你在登录之前还不能执行命令哦!");
                    e.setCancelled(true);
                }
            }
        }
    }

}
