package qwq.windleaf.cuteauth.utils;

import qwq.windleaf.cuteauth.CuteAuth;
import qwq.windleaf.cuteauth.Log;

import java.io.*;
import java.util.HashMap;

public class Utils {

    public static String formatColor(String string) {
        return progressString(string.replace("&", "§"));
    }

    public static Boolean isPluginCommand(String command) {
        for (String i : CuteAuth.commands) if (command.contains(i)) return true;
        return false;
    }

    public static String getPath() {
        return System.getProperty("user.dir") + File.separator;
    }

    public static void saveHashMap(HashMap<?, ?> map, String path) {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(path));
            os.writeObject(map);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap<?, ?> loadHashMap(String path) {
        try {
            if ((new File(path)).exists()) {
                ObjectInputStream os = new ObjectInputStream(new FileInputStream(path));
                return (HashMap<?, ?>) os.readObject();
            } else {
                Utils.makeFile(path);
                Utils.saveHashMap(new HashMap<>(), path);
                return new HashMap<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public static void makeFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void disableByError(String errorMessage) {
        Log.console(errorMessage);
        CuteAuth.instance.p.disablePlugin(CuteAuth.instance);
    }

    public static String progressString(String s) {
        return s.replace("[", "&7[")
                .replace("]", "]&r")
                .replace("<", "&7<&n")
                .replace(">", "&r&7>&r");
    }

}
