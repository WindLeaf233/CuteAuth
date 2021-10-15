package ml.windleaf.cuteauth.filters;

import ml.windleaf.cuteauth.utils.Utils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;

public class Log4jFilter extends AbstractFilter {

    private static Result validateMessage(Message message) {
        if (message == null) return Result.NEUTRAL;
        return validateMessage(message.getFormattedMessage());
    }
    
    private static Result validateMessage(String message) {
        return Utils.isPluginCommand(message) ? Result.DENY : Result.NEUTRAL;
    }

    @Override
    public Result filter(LogEvent e) {
        Message m = null;
        if (e != null) m = e.getMessage();
        return validateMessage(m);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Message msg, Throwable t) {
        return validateMessage(msg);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object... params) {
        return validateMessage(msg);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Object msg, Throwable t) {
        String m = null;
        if (msg != null) m = msg.toString();
        return validateMessage(m);
    }

}
