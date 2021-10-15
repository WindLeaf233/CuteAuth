package ml.windleaf.cuteauth.schedules.autologin;

import org.bukkit.entity.Player;

import java.util.Timer;

public class ThreadSchedule extends Thread {

    private final Integer time;
    private final Player player;

    public Timer timer = new Timer();
    public Integer counter = 0;

    public ThreadSchedule(Integer time, Player player) {
        this.time = time;
        this.player = player;
    }

    @Override
    public void start() {
        this.timer.schedule(new Task(this.time, this.player), 0, 1000);
    }

}
