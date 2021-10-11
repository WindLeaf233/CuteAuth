package qwq.windleaf.cuteauth;

import qwq.windleaf.cuteauth.utils.StringUtil;

public class Logger {

    protected static String PREFIX;

    public Logger(String prefix) { PREFIX = prefix; }

    public void log(String message) { CuteAuth.instance.getLogger().info(StringUtil.formatColor(message)); }

}