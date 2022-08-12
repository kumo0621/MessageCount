package com.github.kumo0621.messagecount;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

public final class MessageCount extends JavaPlugin implements org.bukkit.event.Listener {

    private Objective score;
    private Objective score2;

    @Override
    public void onEnable() {
        Scoreboard sb = getServer().getScoreboardManager().getMainScoreboard();
        Objective sc = sb.getObjective("chatcount");
        Objective sa = sb.getObjective("chattotal");
        if (sc == null) {
            sc = sb.registerNewObjective("chatcount", "dummy", "合計チャット文字数");
        }
        if (sa == null) {
            sa = sb.registerNewObjective("chattotal", "dummy", "合計チャット回数数");
        }
        score = sc;
        score2 = sa;

        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerchat(AsyncPlayerChatEvent e) {
        Bukkit.getScheduler().runTask(this, () -> {

            String chat;
            chat = e.getMessage();
            System.out.println(chat);
            Score sc = score.getScore(e.getPlayer().getName());
            Score sa = score2.getScore(e.getPlayer().getName());
            sc.setScore(sc.getScore() + chat.length());
//++でやるチャット回数を計測
            int a = 1;
            sa.setScore(sa.getScore() + a);
            });
    }

    boolean count;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equals("MessageCount")) {
            if (sender instanceof Player) {
                if (args.length == 0) {
                    sender.sendMessage("引数を指定してください。");
                } else {
                    switch (args[0]) {
                        case "start":
                            count = true;
                            sender.sendMessage("チャット計測を表示にします");
                            score.setDisplaySlot(DisplaySlot.BELOW_NAME);
                            score2.setDisplaySlot(DisplaySlot.PLAYER_LIST);
                            break;
                        case "end":
                            count = false;
                            sender.sendMessage("チャット計測を非表示にします");
                            score.setDisplaySlot(null);
                            score2.setDisplaySlot(null);
                            break;
                        default:
                            sender.sendMessage("不明なコマンドです。");
                            break;
                    }
                }
            }
        }
        return super.onCommand(sender, command, label, args);
    }
}
