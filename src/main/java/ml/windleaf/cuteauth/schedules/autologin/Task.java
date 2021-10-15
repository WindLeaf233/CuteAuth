package ml.windleaf.cuteauth.schedules.autologin;

import ml.windleaf.cuteauth.CuteAuth;
import org.bukkit.entity.Player;

import java.util.TimerTask;

public class Task extends TimerTask {

    private final Integer time;
    private final Player player;

    public Task(Integer time, Player player) {
        this.time = time;
        this.player = player;
    }

    @Override
    public void run() {
        if (CuteAuth.autoLoginThreadSchedule.counter >= this.time) {
            CuteAuth.loginMapManager.remove(this.player);
            CuteAuth.autoLoginThreadSchedule.interrupt();
            CuteAuth.autoLoginThreadSchedule.timer.cancel();
            this.cancel();
        } else CuteAuth.autoLoginThreadSchedule.counter++;
    }

}
