package moe.windleaf.cutelogin.utils;

import moe.windleaf.cutelogin.CuteLogin;

public class FilterUtil {

    public static Boolean isCuteLoginCommand(String command) {
        for (String i : CuteLogin.commands) { if (command.contains(i)) { return true; } }
        return false;
    }

}
