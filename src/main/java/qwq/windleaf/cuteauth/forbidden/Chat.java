package qwq.windleaf.cuteauth.forbidden;

import qwq.windleaf.cuteauth.CuteAuth;
import qwq.windleaf.cuteauth.utils.LogUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {

    @EventHandler
    public void handler(AsyncPlayerChatEvent e) {
        if (CuteAuth.instance.getConfig().getBoolean("forbidden.chat")) {
            if (!CuteAuth.loginMapManager.isLogin(e.getPlayer())) {
                LogUtil.logPlayer(e.getPlayer(), "&c嘿, 你在登录之前还不能聊天哦!");
                e.setCancelled(true);
            }
        }
    }

}
