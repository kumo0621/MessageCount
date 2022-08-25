package com.github.kumo0621.messagecount;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 指定されたワードが含まれていたらスコアを加算する。
 */
public class ScoreCounter {
    private static Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();

    /**
     * スコアの名前
     */
    private String name;
    /**
     * scoreの表示名
     */
    private String displayName;
    /**
     * 反応するチャットの正規表現
     */
    private Pattern pattern;
    /**
     * scoreの入れ物
     */
    public Objective objective;

    public ScoreCounter(String name, String displayName, String pattern) {
        this.name = name;
        this.displayName = displayName;
        this.pattern = Pattern.compile(pattern);
        this.objective = getOrRegisterObjective();
    }

    /**
     * scoreboardの作成の関数
     */

    private Objective getOrRegisterObjective() {
        Objective sc = sb.getObjective(name);
        if (sc == null) {
            sc = sb.registerNewObjective(name, "dummy", displayName);
        }
        return sc;
    }

    /**
     * チャットの中に指定された文字が含まれていないかの確認
     */
    public int getWordCount(String chat) {
        Matcher m = pattern.matcher(chat);
        int keisoku = 0;
        while (m.find()) {
            keisoku++;
        }
        return keisoku;
    }


    /**
     * チャットに指定文字が含まれているかをチェック
     */
    public void addScore(Player player, int count) {
        Score s1 = objective.getScore(player.getName());
        s1.setScore(s1.getScore() + count);
    }


}
