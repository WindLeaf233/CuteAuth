package moe.windleaf.cutelogin;

import moe.windleaf.cutelogin.utils.StringUtil;
import org.bukkit.entity.Player;

public class LogoutMapManager {

    public LogoutMapManager() { }

    public void add(Player player) {
        int times = this.getTimes(player);
        if (times == 1) {
            this.remove(player);
            CuteLogin.loginMapManager.remove(player);
            CuteLogin.dataManager.remove(player);
            player.kickPlayer(StringUtil.formatColor("&c你注销了该服务器的账号, 有缘再会 (˃̣̣̥ o ˂̣̣̥)"));
        } else {
            CuteLogin.logoutMap.remove(player);
            CuteLogin.logoutMap.put(player, times+1);
        }
    }

    public Integer getTimes(Player player) {
        return CuteLogin.logoutMap.get(player) == null ? 0 : CuteLogin.logoutMap.get(player);

    }
    public void remove(Player player) {
        CuteLogin.logoutMap.remove(player);
    }

}
