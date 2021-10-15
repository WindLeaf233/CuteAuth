package ml.windleaf.cuteauth.events;

import ml.windleaf.cuteauth.CuteAuth;
import ml.windleaf.cuteauth.Log;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ml.windleaf.cuteauth.schedules.timeout.ThreadSchedule;

public class PlayerJoin implements Listener {

    @EventHandler
    public void handler(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (CuteAuth.dataManager.isRegistered(player)) {
            if (CuteAuth.loginMapManager.isLogin(player)) {
                if (CuteAuth.instance.getConfig().getBoolean("auto-login.enable")) {
                    CuteAuth.autoLoginThreadSchedule.interrupt();
                    CuteAuth.autoLoginThreadSchedule.timer.cancel();
                    Log.send(player, String.format("&a嘿~ %s 回来啦, 已经自动帮你登录好了哦 ฅ(_•◡•_)ฅ", player.getName()));
                } else {
                    CuteAuth.loginMapManager.remove(player);
                    this.handler(e);
                }
            } else {
                player.setInvulnerable(true);
                CuteAuth.threadSchedule = new ThreadSchedule(player, "&a输入 &b/login <密码> &a登录哦 (๑•.•๑)",
                        CuteAuth.instance.getConfig().getInt("log.intervals-time") * 1000,
                        CuteAuth.instance.getConfig().getInt("log.limit-time"));
                CuteAuth.threadSchedule.start();
            }
        } else {
            Log.send(player, String.format("&a你好吖 %s, 欢迎来到这个服务器! ヾ(=^▽^=)ノ", player.getName()));
            Log.send(player, "&a我在这里等了很久了呢, 快来成为我们的一员吧!");
            player.setInvulnerable(true);
            CuteAuth.threadSchedule = new ThreadSchedule(player, "&a输入 &b/register <密码> <确认密码> &a来注册~",
                    CuteAuth.instance.getConfig().getInt("log.intervals-time") * 1000,
                    CuteAuth.instance.getConfig().getInt("log.limit-time")
            );
            CuteAuth.threadSchedule.start();
        }
    }

}
