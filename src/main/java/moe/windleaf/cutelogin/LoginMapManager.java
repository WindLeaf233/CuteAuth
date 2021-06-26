package moe.windleaf.cutelogin;

import org.bukkit.entity.Player;

public class LoginMapManager {

    public LoginMapManager() { }

    public Boolean isLogin(Player player) {
        if (CuteLogin.loginMap.get(player.getUniqueId().toString()) == null) { return false; } else {
            return CuteLogin.loginMap.get(player.getUniqueId().toString());
        }
    }

    public void remove(Player player) {
        CuteLogin.loginMap.remove(player.getUniqueId().toString());
        CuteLogin.loginMap.put(player.getUniqueId().toString(), false);
    }

    public void add(Player player) {
        CuteLogin.loginMap.remove(player.getUniqueId().toString());
        CuteLogin.loginMap.put(player.getUniqueId().toString(), true);
    }

}
