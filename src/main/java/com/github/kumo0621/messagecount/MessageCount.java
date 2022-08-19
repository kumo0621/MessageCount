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
    private Objective score4;
    private Objective score5;
    private Objective score6;
    private Objective score7;
    private Objective score8;
    private Objective score9;
    private Objective score10;
    private Objective score11;
    private Objective score12;

    @Override
    public void onEnable() {
        Scoreboard sb = getServer().getScoreboardManager().getMainScoreboard();
        Objective sc = sb.getObjective("chatcount");
        Objective sa = sb.getObjective("chattotal");
        Objective s1 = sb.getObjective("wwwchat");
        Objective s2 = sb.getObjective("kunkitechat");
        Objective s3 = sb.getObjective("bokugachat");
        Objective s4 = sb.getObjective("e.matte.tyo.chat");
        Objective s5 = sb.getObjective("ggchat");
        Objective s6 = sb.getObjective("konnitihachat");
        Objective s7 = sb.getObjective("moimoichat");
        Objective s8 = sb.getObjective("naltusichat");
        Objective s9 = sb.getObjective("konochat");
        Objective s10 = sb.getObjective("nyachat");
        if (sc == null) {
            sc = sb.registerNewObjective("chatcount", "dummy", "合計チャット文字数");
        }
        if (sa == null) {
            sa = sb.registerNewObjective("chattotal", "dummy", "合計チャット回数");
        }
        if (s1 == null) {
            s1 = sb.registerNewObjective("wwwchat", "dummy", "合計wを打った回数");
        }
        if (s2 == null) {
            s2 = sb.registerNewObjective("kunkitechat", "dummy", "KUNさんを呼んだ回数");
        }
        if (s3 == null) {
            s3 = sb.registerNewObjective("bokugachat", "dummy", "僕が作ったって言った回数");
        }
        if (s4 == null) {
            s4 = sb.registerNewObjective("e.matte.tyo.chat", "dummy", "え。待って。ちょを言った回数");
        }
        if (s5 == null) {
            s5 = sb.registerNewObjective("ggchat", "dummy", "ggを言った回数");
        }
        if (s6 == null) {
            s6 = sb.registerNewObjective("konnitihachat", "dummy", "こんにちはを言った回数");
        }
        if (s7 == null) {
            s7 = sb.registerNewObjective("moimoichat", "dummy", "moimoiを言った回数");
        }
        if (s8 == null) {
            s8 = sb.registerNewObjective("naltusichat", "dummy", "なっしーって言った回数");
        }
        if (s9 == null) {
            s9 = sb.registerNewObjective("konochat", "dummy", "このさんって言った回数");
        }
        if (s10 == null) {
            s10 = sb.registerNewObjective("nyachat", "dummy", "にゃーを言った回数");
        }
        score = sc;
        score2 = sa;
        score3 = s1;
        score4 = s2;
        score5 = s3;
        score6 = s4;
        score7 = s5;
        score8 = s6;
        score9 = s7;
        score10 = s8;
        score11 = s9;
        score12 = s10;


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
            Score s2 = score4.getScore(e.getPlayer().getName());
            Score s3 = score5.getScore(e.getPlayer().getName());
            Score s4 = score6.getScore(e.getPlayer().getName());
            Score s5 = score7.getScore(e.getPlayer().getName());
            Score s6 = score8.getScore(e.getPlayer().getName());
            Score s7 = score9.getScore(e.getPlayer().getName());
            Score s8 = score10.getScore(e.getPlayer().getName());
            Score s9 = score11.getScore(e.getPlayer().getName());
            Score s10 = score12.getScore(e.getPlayer().getName());
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

            Pattern p1 = Pattern.compile("KUNkite|kunkite|くんkite|KUNきて|kunきて|くんきて|KUN来て|kun来て|くん来て|KUNキテ|kunキテ|くんキテ|KUNcome|kuncome|くんcome");
            Matcher m1 = p1.matcher(chat);
            int keisoku1 = 0;
            while (m1.find()) {
                keisoku1++;
            }
            s2.setScore(s2.getScore() + keisoku1);

            Pattern p2 = Pattern.compile("僕が作った");
            Matcher m2 = p2.matcher(chat);
            int keisoku2 = 0;
            while (m2.find()) {
                keisoku2++;
            }
            s3.setScore(s3.getScore() + keisoku2);

            Pattern p3 = Pattern.compile("e|tyo|matte");
            Matcher m3 = p3.matcher(chat);
            int keisoku3 = 0;
            while (m3.find()) {
                keisoku3++;
            }
            s4.setScore(s4.getScore() + keisoku3);

            Pattern p4 = Pattern.compile("gg|GG|g");
            Matcher m4 = p4.matcher(chat);
            int keisoku4 = 0;
            while (m4.find()) {
                keisoku4++;
            }
            s5.setScore(s5.getScore() + keisoku4);

            Pattern p5 = Pattern.compile("こんにちは");
            Matcher m5 = p5.matcher(chat);
            int keisoku5 = 0;
            while (m5.find()) {
                keisoku5++;
            }
            s6.setScore(s6.getScore() + keisoku5);

            Pattern p6 = Pattern.compile("moi");
            Matcher m6 = p6.matcher(chat);
            int keisoku6 = 0;
            while (m6.find()) {
                keisoku6++;
            }
            s7.setScore(s7.getScore() + keisoku6);

            Pattern p7 = Pattern.compile("なっしー");
            Matcher m7 = p7.matcher(chat);
            int keisoku7 = 0;
            while (m7.find()) {
                keisoku7++;
            }
            s8.setScore(s8.getScore() + keisoku7);

            Pattern p8 = Pattern.compile("この");
            Matcher m8 = p8.matcher(chat);
            int keisoku8 = 0;
            while (m8.find()) {
                keisoku8++;
            }
            s9.setScore(s9.getScore() + keisoku8);

            Pattern p9 = Pattern.compile("にゃ");
            Matcher m9 = p9.matcher(chat);
            int keisoku9 = 0;
            while (m9.find()) {
                keisoku9++;
            }
            s10.setScore(s10.getScore() + keisoku9);
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
                            break;
                        case "hide":
                            count = false;
                            sender.sendMessage("チャット計測を非表示にします");
                            score.setDisplaySlot(null);
                            score2.setDisplaySlot(null);
                            break;
                        case "help":
                            sender.sendMessage("ヘルプを表示します。");
                            sender.sendMessage("/mc show  :チャット計測を表示する");
                            sender.sendMessage("/mc hide  :チャット計測を非表示にする");
                            sender.sendMessage("各値の結果を表示したい場合はscoreboardのsidebarで表示する。");
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
