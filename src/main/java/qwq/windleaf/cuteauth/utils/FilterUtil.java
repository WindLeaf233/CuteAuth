package qwq.windleaf.cuteauth.utils;

import qwq.windleaf.cuteauth.CuteAuth;

public class FilterUtil {

    public static Boolean isCuteLoginCommand(String command) {
        for (String i : CuteAuth.commands) if (command.contains(i)) return true;
        return false;
    }

}
