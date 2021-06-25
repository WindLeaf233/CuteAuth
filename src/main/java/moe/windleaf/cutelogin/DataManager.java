package moe.windleaf.cutelogin;

import moe.windleaf.cutelogin.utils.FileUtil;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;


public class DataManager {

    public DataManager() { }

    public ArrayList<String> encryptPassword(String password) {
        String salt = UUID.randomUUID().toString();
        ArrayList<String> list = new ArrayList<>();
        list.add(new Sha256Hash(password, salt).toString());
        list.add(salt);
        return list;
    }

    public Boolean canPass(Player player, String password) {
        return CuteLogin.INSTANCE.data.get(player.getUniqueId().toString()).get(0).equals(new Sha256Hash(password, this.getSalt(player)).toString());
    }

    public Boolean isRegistered(Player player) {
        return CuteLogin.INSTANCE.data.get(player.getUniqueId().toString()) != null;
    }

    public String getSalt(Player player) {
        return CuteLogin.INSTANCE.data.get(player.getUniqueId().toString()).get(1);
    }

    public void add(Player player, String encryptedPassword, String salt) {
        ArrayList<String> t = new ArrayList<>();
        t.add(encryptedPassword);
        t.add(salt);
        CuteLogin.INSTANCE.data.put(player.getUniqueId().toString(), t);
    }

    public void remove(Player player) {
        CuteLogin.INSTANCE.data.remove(player.getUniqueId().toString());
    }

    public void save() {
        FileUtil.saveHashMap(CuteLogin.INSTANCE.data, CuteLogin.INSTANCE.path);
    }

}
