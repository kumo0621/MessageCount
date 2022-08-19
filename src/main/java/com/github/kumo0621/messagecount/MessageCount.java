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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MessageCount extends JavaPlugin implements org.bukkit.event.Listener {

    private Objective score;
    private Objective score2;
    private Objective score3;

    @Override
    public void onEnable() {
        Scoreboard sb = getServer().getScoreboardManager().getMainScoreboard();
        Objective sc = sb.getObjective("chatcount");
        Objective sa = sb.getObjective("chattotal");
        Objective s1 = sb.getObjective("wwwchat");
        if (sc == null) {
            sc = sb.registerNewObjective("chatcount", "dummy", "合計チャット文字数");
        }
        if (sa == null) {
            sa = sb.registerNewObjective("chattotal", "dummy", "合計チャット回数");
        }
        if (s1 == null) {
            s1 = sb.registerNewObjective("wwwchat", "dummy", "合計wを打った回数");
        }
        score = sc;
        score2 = sa;
        score3 = s1;

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
            Score s1 = score3.getScore(e.getPlayer().getName());
            sc.setScore(sc.getScore() + chat.length());
            int a = 1;
            sa.setScore(sa.getScore() + a);
            Pattern p = Pattern.compile("w|笑|ｗ|W");
            Matcher m = p.matcher(chat);
            int keisoku = 0;
            while (m.find()) {
                keisoku++;
            }
            s1.setScore(s1.getScore() + keisoku);
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
                        case "show":
                            count = true;
                            sender.sendMessage("チャット計測を表示にします");
                            score.setDisplaySlot(DisplaySlot.BELOW_NAME);
                            score2.setDisplaySlot(DisplaySlot.PLAYER_LIST);
                            score3.setDisplaySlot(DisplaySlot.SIDEBAR);
                            break;
                        case "hide":
                            count = false;
                            sender.sendMessage("チャット計測を非表示にします");
                            score.setDisplaySlot(null);
                            score2.setDisplaySlot(null);
                            score3.setDisplaySlot(null);
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
