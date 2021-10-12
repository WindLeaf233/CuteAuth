package qwq.windleaf.cuteauth.managers;

import qwq.windleaf.cuteauth.CuteAuth;
import org.bukkit.entity.Player;
import qwq.windleaf.cuteauth.utils.Utils;

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
