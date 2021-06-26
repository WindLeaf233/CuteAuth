package moe.windleaf.cutelogin.forbidden;

import moe.windleaf.cutelogin.CuteLogin;
import moe.windleaf.cutelogin.utils.LogUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {

    @EventHandler
    public void handler(AsyncPlayerChatEvent e) {
        if (CuteLogin.INSTANCE.getConfig().getBoolean("forbidden.chat")) {
            if (!CuteLogin.loginMapManager.isLogin(e.getPlayer())) {
                LogUtil.logPlayer(e.getPlayer(), "&c嘿, 你在登录之前还不能聊天哦!");
                e.setCancelled(true);
            }
        }
    }

}
