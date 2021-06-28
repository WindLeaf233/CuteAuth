package moe.windleaf.cutelogin.schedule.autologin;

import moe.windleaf.cutelogin.CuteLogin;
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
        if (CuteLogin.autoLoginThreadSchedule.counter >= this.time) {
            CuteLogin.loginMapManager.remove(this.player);
            CuteLogin.autoLoginThreadSchedule.interrupt();
            CuteLogin.autoLoginThreadSchedule.timer.cancel();
            this.cancel();
        } else { CuteLogin.autoLoginThreadSchedule.counter++; }
    }

}
