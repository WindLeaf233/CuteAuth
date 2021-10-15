package ml.windleaf.cuteauth.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ml.windleaf.cuteauth.CuteAuth;
import ml.windleaf.cuteauth.Log;

import java.util.ArrayList;

public class RegisterCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Log.onlyByExecutePlayer(sender, () -> {
            Player player = (Player) sender;
            if (CuteAuth.dataManager.isRegistered(player)) Log.send(player, "&c嗯... 你似乎注册过了诶!");
            else {
                if (args.length == 2) {
                    if (args[0].equals(args[1])) {
                        ArrayList<String> list = CuteAuth.dataManager.encryptPassword(args[0]);
                        CuteAuth.dataManager.add(player, list.get(0), list.get(1));
                        CuteAuth.dataManager.save();
                        CuteAuth.loginMapManager.add(player);
                        CuteAuth.threadSchedule.interrupt();
                        CuteAuth.threadSchedule.timer.cancel();
                        player.setInvulnerable(false);
                        Log.send(player, "&a叮~ 你的账号创建成功! 快去玩吧 (๑•ᴗ•๑)♡");
                    } else Log.send(player, "&c呃, 看样子你两次输入的密码不相同哦! 再试试吧 ๐·°(৹˃̵﹏˂̵৹)°·๐");
                } else Log.invalidCommand(player);
            }
        });
        return true;
    }

}
