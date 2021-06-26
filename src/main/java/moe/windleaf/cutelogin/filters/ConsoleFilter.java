package moe.windleaf.cutelogin.filters;

import moe.windleaf.cutelogin.utils.FilterUtil;
import moe.windleaf.cutelogin.utils.StringUtil;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class ConsoleFilter implements Filter {

    public ConsoleFilter() { }

    @Override
    public boolean isLoggable(LogRecord record) {
        if (record == null || record.getMessage() == null) { return true; }

        if (FilterUtil.isCuteLoginCommand(record.getMessage())) {
            String playerName = record.getMessage().split(" ")[0];
            record.setMessage(playerName + StringUtil.formatColor(" &a使用了一个插件指令"));
        }
        return true;
    }

}
