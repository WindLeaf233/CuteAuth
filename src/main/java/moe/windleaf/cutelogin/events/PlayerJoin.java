package moe.windleaf.cutelogin.events;

import moe.windleaf.cutelogin.CuteLogin;
import moe.windleaf.cutelogin.schedule.ThreadSchedule;
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
                CuteLogin.threadSchedule = new ThreadSchedule(player, "&a输入 &b/login <密码> &a登录哦 (๑•.•๑)",
                        CuteLogin.INSTANCE.getConfig().getInt("log.intervals-time") * 1000,
                        CuteLogin.INSTANCE.getConfig().getInt("log.limit-time"));
                CuteLogin.threadSchedule.start();
            }
        } else {
            LogUtil.logPlayer(player, String.format("&a你好吖 %s, 欢迎来到这个服务器! ヾ(=^▽^=)ノ", player.getName()));
            LogUtil.logPlayer(player, "&a我在这里等了很久了呢, 快来成为我们的一员吧!");
            CuteLogin.threadSchedule = new ThreadSchedule(player, "&a输入 &b/register <密码> <确认密码> &c来注册~",
                    CuteLogin.INSTANCE.getConfig().getInt("log.intervals-time") * 1000,
                    CuteLogin.INSTANCE.getConfig().getInt("log.limit-time"));
            CuteLogin.threadSchedule.start();
        }
    }

}
