# CuteLogin
一个简易高效且安全的 Minecraft Spigot 1.16.x 登录插件.

# Feature
1. 抛弃 `MYSQL` 数据库的繁琐, 使用 **`DATABASE`** 保存;
2. 不用担心任何 `MYSQL` 引发的各种问题, 无需配置数据库;
3. 使用 ***Sha256Hash***+***RandomUUID***+***Salt*** **`三层加密`** 保存, 安全可靠;
4. 使用 双层 ***Console***+***Log4j*** **`筛选器`**, 不明文显示密码;
5. 在 `PlayerCommandPreprocessEvent` 事件使用全新方法对命令进行定义与运行, 有效地避免了传统定义命令可能会出现的 **`NullPointerException`**, 保证 **线程安全**.

# Other
- 命令定义方法:
  ```Java
  String commandPlayerTyped = "command";
  Runnable command = () -> {
    // Some Code Here
  };
  (new Executor(commandPlayerTyped, "/command", command)).run();
  ```
 
