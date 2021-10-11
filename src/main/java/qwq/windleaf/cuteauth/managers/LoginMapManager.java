package qwq.windleaf.cuteauth.managers;

import org.bukkit.entity.Player;
import qwq.windleaf.cuteauth.CuteAuth;

public class LoginMapManager {

    public LoginMapManager() { }

    public Boolean isLogin(Player player) {
        if (CuteAuth.loginMap.get(player.getUniqueId().toString()) == null) return false;
        else return CuteAuth.loginMap.get(player.getUniqueId().toString());
    }

    public void remove(Player player) {
        CuteAuth.loginMap.remove(player.getUniqueId().toString());
        CuteAuth.loginMap.put(player.getUniqueId().toString(), false);
    }

    public void add(Player player) {
        CuteAuth.loginMap.remove(player.getUniqueId().toString());
        CuteAuth.loginMap.put(player.getUniqueId().toString(), true);
    }

}
