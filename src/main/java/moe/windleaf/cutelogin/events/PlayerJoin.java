package moe.windleaf.cutelogin.events;

import moe.windleaf.cutelogin.CuteLogin;
import moe.windleaf.cutelogin.utils.LogUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void handler(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (CuteLogin.dataManager.isRegistered(player)) {
            if (CuteLogin.loginMapManager.isLogin(player)) {
                LogUtil.logPlayer(player, String.format("&aNya~ %s 回来啦, 已经自动帮你登录好了哦 ฅ(_•◡•_)ฅ", player.getName()));
            } else {
                LogUtil.logPlayer(player, "&a输入 &b/login <密码> &a登录哦 (๑•.•๑)");
            }
        } else {
            LogUtil.logPlayer(player, String.format("&a你好吖 %s, 欢迎来到这个服务器! ヾ(=^▽^=)ノ", player.getName()));
            LogUtil.logPlayer(player, "&a我在这里等了很久了呢, 输入 &b/register <密码> <确认密码> &a成为我们的一员吧!");
        }

    }
}
