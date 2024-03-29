package ml.windleaf.cuteauth.managers;

import ml.windleaf.cuteauth.CuteAuth;
import ml.windleaf.cuteauth.utils.Utils;
import org.bukkit.entity.Player;

public class LogoutMapManager {

    public LogoutMapManager() {

    }

    public void add(Player player) {
        int times = this.getTimes(player);
        if (times == 1) {
            this.remove(player);
            CuteAuth.dataManager.remove(player);
            CuteAuth.loginMapManager.remove(player);
            player.kickPlayer(Utils.formatColor("&c你注销了该服务器的账号, 有缘再见 ヾ(￣▽￣)"));
        } else {
            CuteAuth.logoutMap.remove(player);
            CuteAuth.logoutMap.put(player, times+1);
        }
    }

    public Integer getTimes(Player player) {
        return CuteAuth.logoutMap.get(player) == null ? 0 : CuteAuth.logoutMap.get(player);

    }
    public void remove(Player player) {
        CuteAuth.logoutMap.remove(player);
    }

}
