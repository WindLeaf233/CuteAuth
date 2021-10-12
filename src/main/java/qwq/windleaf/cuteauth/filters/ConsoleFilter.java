package qwq.windleaf.cuteauth.filters;

import qwq.windleaf.cuteauth.utils.Utils;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class ConsoleFilter implements Filter {

    public ConsoleFilter() { }

    @Override
    public boolean isLoggable(LogRecord record) {
        if (record == null || record.getMessage() == null) return true;
        if (Utils.isPluginCommand(record.getMessage())) {
            String playerName = record.getMessage().split(" ")[0];
            record.setMessage(playerName + Utils.formatColor(" &a使用了一个插件指令"));
        }
        return true;
    }

}
