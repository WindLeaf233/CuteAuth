package moe.windleaf.cutelogin.forbidden;

import moe.windleaf.cutelogin.CuteLogin;
import moe.windleaf.cutelogin.utils.FilterUtil;
import moe.windleaf.cutelogin.utils.LogUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ExecuteCommand implements Listener {

    @EventHandler
    public void handler(PlayerCommandPreprocessEvent e) {
        if (CuteLogin.INSTANCE.getConfig().getBoolean("forbidden.execute-command")) {
            if (!CuteLogin.loginMapManager.isLogin(e.getPlayer())) {
                if (!FilterUtil.isCuteLoginCommand(e.getMessage())) {
                    LogUtil.logPlayer(e.getPlayer(), "&cEmmm, 你在登录之前还不能执行命令哦!");
                    e.setCancelled(true);
                }
            }
        }
    }

}
