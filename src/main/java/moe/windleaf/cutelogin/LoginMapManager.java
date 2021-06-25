package moe.windleaf.cutelogin;

import org.bukkit.entity.Player;

public class LoginMapManager {

    public LoginMapManager() { }

    public Boolean isLogin(Player player) {
        return CuteLogin.loginMap.get(player) != null && CuteLogin.loginMap.get(player);
    }

    public void remove(Player player) {
        CuteLogin.loginMap.remove(player);
        CuteLogin.loginMap.put(player, false);
    }

    public void add(Player player) {
        CuteLogin.loginMap.remove(player);
        CuteLogin.loginMap.put(player, true);
    }

}
