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

    private ScoreCounter score;
    private ScoreCounter score2;
    private ScoreCounter score3;
    private ScoreCounter score4;
    private ScoreCounter score5;
    private ScoreCounter score6;
    private ScoreCounter score7;
    private ScoreCounter score8;
    private ScoreCounter score9;
    private ScoreCounter score10;
    private ScoreCounter score11;
    private ScoreCounter score12;

    @Override
    public void onEnable() {
        score = new ScoreCounter("chatcount", "合計チャット文字数", "");
        score2 = new ScoreCounter("chattotal", "合計チャット回数", "");
        score3 = new ScoreCounter("wwwchat", "合計wを打った回数", "w|笑|ｗ|W");
        score4 = new ScoreCounter("kunkitechat", "KUNさんを呼んだ回数", "KUNkite|kunkite|くんkite|KUNきて|kunきて|くんきて|KUN来て|kun来て|くん来て|KUNキテ|kunキテ|くんキテ|KUNcome|kuncome|くんcome");
        score5 = new ScoreCounter("bokugachat", "僕が作ったって言った回数", "僕が作った");
        score6 = new ScoreCounter("e.matte.tyo.chat", "え。待って。ちょを言った回数", "e|tyo|matte");
        score7 = new ScoreCounter("ggchat", "ggを言った回数", "gg|GG|g");
        score8 = new ScoreCounter("konnitihachat", "こんにちはを言った回数", "こんにちは");
        score9 = new ScoreCounter("moichat", "moiを言った回数", "moi");
        score10 = new ScoreCounter("nassychat", "なっしーって言った回数", "なっしー");
        score11 = new ScoreCounter("konochat", "このさんって言った回数", "この");
        score12 = new ScoreCounter("nyachat", "にゃーを言った回数", "にゃ");

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
            score.addScore(player, chat.length());
            score2.addScore(player, 1);
            score3.addScore(player, score3.getWordCount(chat));
            score4.addScore(player, score4.getWordCount(chat));
            score5.addScore(player, score5.getWordCount(chat));
            score6.addScore(player, score6.getWordCount(chat));
            score7.addScore(player, score7.getWordCount(chat));
            score8.addScore(player, score8.getWordCount(chat));
            score9.addScore(player, score9.getWordCount(chat));
            score10.addScore(player, score10.getWordCount(chat));
            score11.addScore(player, score11.getWordCount(chat));
            score12.addScore(player, score12.getWordCount(chat));
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
                            score.objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
                            score2.objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);
                            break;
                        case "hide":
                            count = false;
                            sender.sendMessage("チャット計測を非表示にします");
                            score.objective.setDisplaySlot(null);
                            score2.objective.setDisplaySlot(null);
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
