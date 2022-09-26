package com.github.kumo0621.messagecount

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Objective
import java.util.regex.Pattern

/**
 * 指定されたワードが含まれていたらスコアを加算する。
 */
class ScoreCounter(
        /**
         * スコアの名前
         */
        private val name: String,
        /**
         * scoreの表示名
         */
        private val displayName: String,
        pattern: String
) {
    /**
     * 反応するチャットの正規表現
     */
    private val pattern: Pattern

    /**
     * scoreの入れ物
     */
    var objective: Objective

    init {
        this.pattern = Pattern.compile(pattern)
        objective = orRegisterObjective
    }

    /**
     * scoreboardの作成の関数
     */
    private val orRegisterObjective: Objective
        private get() {
            var sc = sb.getObjective(name)
            if (sc == null) {
                sc = sb.registerNewObjective(name, "dummy", displayName)
            }
            return sc
        }

    /**
     * チャットの中に指定された文字が含まれていないかの確認
     */
    fun getWordCount(chat: String?): Int {
        val m = pattern.matcher(chat)
        var keisoku = 0
        while (m.find()) {
            keisoku++
        }
        return keisoku
    }

    /**
     * チャットに指定文字が含まれているかをチェック
     */
    fun addScore(player: Player, count: Int) {
        val s1 = objective.getScore(player.name)
        s1.score = s1.score + count
    }

    companion object {
        private val sb = Bukkit.getScoreboardManager().mainScoreboard
    }
}