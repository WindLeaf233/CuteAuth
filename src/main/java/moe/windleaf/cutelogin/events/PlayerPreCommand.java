package moe.windleaf.cutelogin.events;

import moe.windleaf.cutelogin.CuteLogin;
import moe.windleaf.cutelogin.Executor;
import moe.windleaf.cutelogin.utils.LogUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerPreCommand implements Listener {

    @EventHandler
    public void handler(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();
        String[] b = e.getMessage().split(" ");
        ArrayList<String> args = new ArrayList<>(Arrays.asList(b));
        String command = args.get(0);
        args.remove(0);

        Runnable login = () -> {
            if (args.size() == 1) {
                if (CuteLogin.loginMapManager.isLogin(player)) {
                    LogUtil.logPlayer(player, "&c你已经登录过了哦...");
                } else {
                    if (CuteLogin.dataManager.isRegistered(player)) {
                        if (CuteLogin.dataManager.getSalt(player) == null) { LogUtil.logPlayer(player, "&c你还没注册呢, 快去注册一个账号再来登录叭 |·ω·`)"); }
                        if (CuteLogin.dataManager.canPass(player, args.get(0))) {
                            CuteLogin.loginMapManager.add(player);
                            CuteLogin.threadSchedule.interrupt();
                            LogUtil.logPlayer(player, "&a哇, 看样子登录成功了呢! 快去愉快地玩耍吧 •ᴗ•");
                        } else { LogUtil.logPlayer(player, "&c噢... 你的密码不对诶, 你是不是记错了 (¬◡¬)✧"); }
                    } else { LogUtil.logPlayer(player, "&c你还没注册呢, 快去注册一个账号再来登录叭 |·ω·`)"); }
                }
            } else { LogUtil.logPlayer(player, "&c你的参数不对喔, 正确用法是 &b/login <密码> &c哦, 别忘了~"); }
        };

        Runnable register = () -> {
            if (CuteLogin.dataManager.isRegistered(player)) {
                LogUtil.logPlayer(player, "&c嗯... 你似乎注册过了诶!");
            } else {
                if (args.size() == 2) {
                    if (args.get(0).equals(args.get(1))) {
                        ArrayList<String> list = CuteLogin.dataManager.encryptPassword(args.get(0));
                        CuteLogin.dataManager.add(player, list.get(0), list.get(1));
                        CuteLogin.dataManager.save();
                        CuteLogin.threadSchedule.interrupt();
                        LogUtil.logPlayer(player, "&a叮~ 你的账号创建成功! 快去 HAPPY 吧 (๑•ᴗ•๑)♡");
                    } else { LogUtil.logPlayer(player, "&c呃, 看样子你两次输入的密码不相同哦! 再试试吧 ๐·°(৹˃̵﹏˂̵৹)°·๐"); }
                } else { LogUtil.logPlayer(player, "&c你的参数不对喔, 正确用法是 &b/register <密码> <确认密码> &c哦, 别忘了~"); }
            }
        };

        Runnable logout = () -> {
            if (CuteLogin.dataManager.isRegistered(player)) {
                switch (args.size()) {
                    case 0:
                        if (CuteLogin.logoutMapManager.getTimes(player) < 1) {
                            LogUtil.logPlayer(player, "&c注销账号后一切都会消失的, 你确定真的要离开嘛...");
                            LogUtil.logPlayer(player, "&c如果确定的话, 再输入一次 &b/logout &c叭...");
                            LogUtil.logPlayer(player, "&a回心转意也可以哒, 只需输入 &b/logout cancel &a就行啦~");
                        }
                        CuteLogin.logoutMapManager.add(player);
                        break;
                    case 1:
                        if (args.get(0).equals("cancel")) {
                            CuteLogin.logoutMapManager.remove(player);
                            LogUtil.logPlayer(player, "&a我就知道你会继续留下来哒 =D");
                        } else {
                            LogUtil.logPlayer(player, "&c你的参数不对喔, 正确用法是 &b/logout [cancel] &c哦, 别忘了~");
                        }
                        break;
                    default:
                        LogUtil.logPlayer(player, "&c你的参数不对喔, 正确用法是 &b/logout [cancel] &c哦, 别忘了~");
                        break;
                }
            } else {
                LogUtil.logPlayer(player, "&c你还没注册呢, 咋注销呢~");
            }
        };

        new Executor(command, "/login", login).run();
        new Executor(command, "/l", login).run();
        new Executor(command, "/register", register).run();
        new Executor(command, "/reg", register).run();
        new Executor(command, "/logout", logout).run();
        new Executor(command, "/lout", logout).run();
    }

}
