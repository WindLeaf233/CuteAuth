package ml.windleaf.cuteauth.managers;

import ml.windleaf.cuteauth.CuteAuth;
import ml.windleaf.cuteauth.utils.Utils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class DataManager {

    public DatabaseManager databaseManager = new DatabaseManager();

    public DataManager() {
        if (DatabaseManager.useDatabase) databaseManager.loadDatabase();
    }

    public ArrayList<String> encryptPassword(String password) {
        String salt = UUID.randomUUID().toString();
        ArrayList<String> list = new ArrayList<>();
        list.add(new Sha256Hash(password, salt).toString());
        list.add(salt);
        return list;
    }

    public Boolean canPass(Player player, String password) {
        if (DatabaseManager.useDatabase) {
            Sha256Hash hash = new Sha256Hash(password, databaseManager.get(player.getUniqueId().toString(), "Salt"));
            String encryptedPassword = databaseManager.get(player.getUniqueId().toString(), "EncryptedPassword");
            return encryptedPassword.equals(hash.toString());
        } else {
            Sha256Hash hash = new Sha256Hash(password, this.getSalt(player));
            return CuteAuth.data.get(player.getUniqueId().toString()).get(0).equals(hash.toString());
        }
    }

    public Boolean isRegistered(Player player) {
        if (DatabaseManager.useDatabase) return databaseManager.isRegistered(player.getUniqueId().toString());
        else return CuteAuth.data.get(player.getUniqueId().toString()) != null;
    }

    public String getSalt(Player player) {
        if (DatabaseManager.useDatabase) return databaseManager.get(player.getUniqueId().toString(), "Salt");
        else return CuteAuth.data.get(player.getUniqueId().toString()).get(1);
    }

    public void add(Player player, String encryptedPassword, String salt) {
        if (DatabaseManager.useDatabase) databaseManager.registerPlayer(player.getUniqueId().toString(), encryptedPassword, salt);
        else {
            ArrayList<String> t = new ArrayList<>();
            t.add(encryptedPassword);
            t.add(salt);
            CuteAuth.data.put(player.getUniqueId().toString(), t);
        }
    }

    public void remove(Player player) {
        if (DatabaseManager.useDatabase) databaseManager.removePlayer(player.getUniqueId().toString());
        else CuteAuth.data.remove(player.getUniqueId().toString());
    }

    public void save() {
        if (!DatabaseManager.useDatabase) Utils.saveHashMap(CuteAuth.data, CuteAuth.instance.path);
    }

    public HashMap<String, ArrayList<String>> loadData() {
        if (!DatabaseManager.useDatabase) {
            @SuppressWarnings("unchecked")
            HashMap<String, ArrayList<String>> data = (HashMap<String, ArrayList<String>>) Utils.loadHashMap(CuteAuth.instance.path);
            return data;
        }
        return null;
    }

}
