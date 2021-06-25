package moe.windleaf.cutelogin.utils;

import java.io.*;
import java.util.HashMap;

public class FileUtil {

    public static String getPath() { return System.getProperty("user.dir") + java.io.File.separator; }

    public static void saveHashMap(HashMap<?, ?> map, String path) {
        try {
            if (new java.io.File(path).exists()) {
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(path));
                os.writeObject(map);
                os.flush();
                os.close();
            } else {
                java.io.File file = new java.io.File(path);
                boolean createdDIR = file.getParentFile().mkdirs();
                boolean createdFILE = file.createNewFile();
                if (!createdDIR && !createdFILE) {
                    LogUtil.log("&c哦天哪, 是不是出了什么问题? 我好像不能存储你们的数据了... 试试重装插件吧 ('⌓')");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap<?, ?> loadHashMap(String path) {
        try {
            if (new java.io.File(path).exists()) {
                ObjectInputStream os = new ObjectInputStream(new FileInputStream(path));
                return (HashMap<?, ?>) os.readObject();
            } else {
                FileUtil.makeFile(path);
                FileUtil.saveHashMap(new HashMap<>(), path);
                return loadHashMap(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public static void makeFile(String path) {
        java.io.File file = new java.io.File(path);
        if (!file.exists()) {
            try {
                @SuppressWarnings("unused") boolean createdDIR = file.getParentFile().mkdirs();
                @SuppressWarnings("unused") boolean createdFILE = file.createNewFile();
            } catch (IOException e) {
                LogUtil.log("&c哦天哪, 是不是出了什么问题? 我好像被玩坏了诶... ('⌓')");
                e.printStackTrace();
            }
        }
    }

}
