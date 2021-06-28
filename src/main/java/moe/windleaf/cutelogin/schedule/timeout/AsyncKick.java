package moe.windleaf.cutelogin.schedule.timeout;

import moe.windleaf.cutelogin.CuteLogin;
import moe.windleaf.cutelogin.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AsyncKick {

    private final Player player;
    private final Integer limitTime;

    public AsyncKick(Player player, Integer limitTime) {
        this.player = player;
        this.limitTime = limitTime;
    }

    public void run() {
        if (CuteLogin.INSTANCE.isEnabled()) {
            String k = CuteLogin.INSTANCE.getConfig().getString("log.kick-message");
            String kickMessage = k == null ? "" : k;

            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getScheduler().runTask(CuteLogin.INSTANCE, () -> player.kickPlayer(
                            StringUtil.formatColor(kickMessage)
                                    .replace("{time}", String.valueOf(limitTime))
                                    .replace("{player}", player.getName())
                    ));
                }
            }.run();
        }
    }

}