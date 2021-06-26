package moe.windleaf.cutelogin.schedule;

import moe.windleaf.cutelogin.utils.LogUtil;
import org.bukkit.entity.Player;

import java.util.TimerTask;

public class Task extends TimerTask {

    private final Player player;
    private final String log;
    private final Integer period;
    private final ThreadSchedule threadSchedule;

    public Task(Player player, String log, Integer period, ThreadSchedule threadSchedule) {
        this.player = player;
        this.log = log;
        this.period = period;
        this.threadSchedule = threadSchedule;
    }

    @Override
    public void run() {
        if (this.threadSchedule.time >= this.threadSchedule.limitTime) {
            (new AsyncKick(this.player, this.threadSchedule.limitTime)).run();
            this.threadSchedule.interrupt();
        } else {
            this.threadSchedule.time = this.threadSchedule.time + (this.period / 1000);
            LogUtil.logPlayer(this.player, this.log);
        }
    }

}
