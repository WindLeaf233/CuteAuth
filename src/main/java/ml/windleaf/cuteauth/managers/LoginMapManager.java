package ml.windleaf.cuteauth.managers;

import ml.windleaf.cuteauth.CuteAuth;
import org.bukkit.entity.Player;

public class LoginMapManager {

    public LoginMapManager() {

    }

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
