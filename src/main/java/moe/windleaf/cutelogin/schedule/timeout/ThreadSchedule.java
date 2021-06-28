package moe.windleaf.cutelogin.schedule.timeout;

import org.bukkit.entity.Player;

import java.util.Timer;

public class ThreadSchedule extends Thread {

    private final Player player;
    private final String log;
    private final Integer period;

    public Integer limitTime;
    public Integer time = 0;
    public Timer timer = new Timer();

    public ThreadSchedule(Player player, String log, Integer period, Integer limitTime) {
        this.player = player;
        this.log = log;
        this.period = period;
        this.limitTime = limitTime;
    }

    @Override
    public void start() {
        this.timer.schedule(new Task(this.player, this.log, this.period, this), 0, this.period);
    }

}
