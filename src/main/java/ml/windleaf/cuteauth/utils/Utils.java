package ml.windleaf.cuteauth.utils;

import ml.windleaf.cuteauth.Log;
import ml.windleaf.cuteauth.CuteAuth;

import java.io.*;
import java.util.HashMap;

public class Utils {

    public static String formatColor(String string) {
        return progressString(string).replace("&", "§");
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
                @SuppressWarnings("unused") boolean w1 = file.getParentFile().mkdirs();
                @SuppressWarnings("unused") boolean w2 = file.createNewFile();
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
        return s.replace("[", "&f[")
                .replace("]", "]&r&b")
                .replace("<", "&f<&n")
                .replace(">", "&r&f>&r&b");
    }

}
