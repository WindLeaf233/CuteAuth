package qwq.windleaf.cuteauth.forbidden;

import qwq.windleaf.cuteauth.CuteAuth;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import qwq.windleaf.cuteauth.Log;

public class Chat implements Listener {

    @EventHandler
    public void handler(AsyncPlayerChatEvent e) {
        if (CuteAuth.instance.getConfig().getBoolean("forbidden.chat")) {
            if (!CuteAuth.loginMapManager.isLogin(e.getPlayer())) {
                Log.send(e.getPlayer(), "&c呃, 你在登录之前还不能聊天哦!");
                e.setCancelled(true);
            }
        }
    }

}
