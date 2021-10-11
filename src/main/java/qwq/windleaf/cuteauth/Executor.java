package qwq.windleaf.cuteauth;

public class Executor implements Runnable {

    private final Runnable target;
    private final String cmd;
    private final String command;

    public Executor(String cmd, String command, Runnable target) {
        this.cmd = cmd;
        this.command = command;
        this.target = target;
    }

    @Override
    public void run() {
        if (this.cmd.equalsIgnoreCase(this.command)) { target.run(); }
    }

}
