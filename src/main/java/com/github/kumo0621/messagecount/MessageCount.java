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

        score = getOrRegisterObjective(sb, "chatcount", "合計チャット文字数");
        score2 = getOrRegisterObjective(sb, "chattotal", "合計チャット回数");
        score3 = getOrRegisterObjective(sb, "wwwchat", "合計wを打った回数");
        score4 = getOrRegisterObjective(sb, "kunkitechat", "KUNさんを呼んだ回数");
        score5 = getOrRegisterObjective(sb, "bokugachat", "僕が作ったって言った回数");
        score6 = getOrRegisterObjective(sb, "e.matte.tyo.chat", "え。待って。ちょを言った回数");
        score7 = getOrRegisterObjective(sb, "ggchat", "ggを言った回数");
        score8 = getOrRegisterObjective(sb, "konnitihachat", "こんにちはを言った回数");
        score9 = getOrRegisterObjective(sb, "moichat", "moiを言った回数");
        score10 = getOrRegisterObjective(sb, "nassychat", "なっしーって言った回数");
        score11 = getOrRegisterObjective(sb, "konochat", "このさんって言った回数");
        score12 = getOrRegisterObjective(sb, "nyachat", "にゃーを言った回数");

        getServer().getPluginManager().registerEvents(this, this);
    }

    private Objective getOrRegisterObjective(Scoreboard sb, String name, String displayName) {
        Objective sc = sb.getObjective(name);
        if (sc == null) {
            sc = sb.registerNewObjective(name, "dummy", displayName);
        }
        return sc;
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
            Player player = e.getPlayer();
            Score sc = score.getScore(player.getName());
            Score sa = score2.getScore(player.getName());
            sc.setScore(sc.getScore() + chat.length());
            int a = 1;
            sa.setScore(sa.getScore() + a);
            incrementIfPlayerChat(score3, player, "w|笑|ｗ|W", chat);
            incrementIfPlayerChat(score4, player, "KUNkite|kunkite|くんkite|KUNきて|kunきて|くんきて|KUN来て|kun来て|くん来て|KUNキテ|kunキテ|くんキテ|KUNcome|kuncome|くんcome", chat);
            incrementIfPlayerChat(score5, player, "僕が作った", chat);
            incrementIfPlayerChat(score6, player, "e|tyo|matte", chat);
            incrementIfPlayerChat(score7, player, "gg|GG|g", chat);
            incrementIfPlayerChat(score8, player, "こんにちは", chat);
            incrementIfPlayerChat(score9, player, "moi", chat);
            incrementIfPlayerChat(score10, player, "なっしー", chat);
            incrementIfPlayerChat(score11, player, "この", chat);
            incrementIfPlayerChat(score12, player, "にゃ", chat);
        });
    }

    private void incrementIfPlayerChat(Objective score, Player player, String pattern, String chat) {
        Score s1 = score.getScore(player.getName());
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(chat);
        int keisoku = 0;
        while (m.find()) {
            keisoku++;
        }
        s1.setScore(s1.getScore() + keisoku);
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
