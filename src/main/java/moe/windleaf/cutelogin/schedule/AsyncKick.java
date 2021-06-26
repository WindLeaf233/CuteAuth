package moe.windleaf.cutelogin.schedule;

import moe.windleaf.cutelogin.CuteLogin;
import moe.windleaf.cutelogin.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class AsyncKick {

    private final Player player;
    private final Integer limitTime;

    public AsyncKick(Player player, Integer limitTime) {
        this.player = player;
        this.limitTime = limitTime;
    }

    public void run() {
        if (CuteLogin.INSTANCE.isEnabled()) {
            BukkitTask task = Bukkit.getScheduler().runTask(CuteLogin.INSTANCE, () -> this.player.kickPlayer(
                    StringUtil.formatColor(String.format("&c哎, 你超过了限制时间来登录或注册: &b%s 秒&c, 下次输入密码快点哦~", this.limitTime))
            ));
            if (CuteLogin.loginMapManager.isLogin(this.player) == null) { task.cancel(); }
        }
    }

}
