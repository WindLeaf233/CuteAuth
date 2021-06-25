package moe.windleaf.cutelogin;

import moe.windleaf.cutelogin.utils.StringUtil;

public class Logger {

    protected static String PREFIX;

    public Logger(String prefix) { PREFIX = prefix; }

    public void log(String message) { System.out.println(StringUtil.formatColor(PREFIX + message)); }

}