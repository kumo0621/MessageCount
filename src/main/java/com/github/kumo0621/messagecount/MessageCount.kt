package com.github.kumo0621.messagecount

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scoreboard.DisplaySlot

class MessageCount : JavaPlugin(), Listener {
    private lateinit var score: ScoreCounter
    private lateinit var score2: ScoreCounter
    private lateinit var scoreList: MutableList<ScoreCounter>
    override fun onEnable() {
        score = ScoreCounter("chatcount", "合計チャット文字数", "")
        score2 = ScoreCounter("chattotal", "合計チャット回数", "")
        scoreList = ArrayList()
        saveDefaultConfig()
        val counters = config.getMapList("counters")
        for (counter in counters) {
            val name = counter["name"] as String?
            val displayName = counter["displayName"] as String?
            val pattern = counter["pattern"] as String?
            if (name != null && displayName != null && pattern != null) {
                scoreList.add(ScoreCounter(name, displayName, pattern))
            }
        }
        server.pluginManager.registerEvents(this, this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    fun onPlayerchat(e: AsyncPlayerChatEvent) {
        Bukkit.getScheduler().runTask(this, Runnable {
            val chat: String
            chat = e.message
            val player = e.player
            score.addScore(player, chat.length)
            score2.addScore(player, 1)
            for (s in scoreList) {
                s.addScore(player, s.getWordCount(chat))
            }
        })
    }

    var count = false
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (command.name == "MessageCount") {
            if (sender is Player) {
                if (args.size == 0) {
                    sender.sendMessage("引数を指定してください。")
                } else {
                    when (args[0]) {
                        "show" -> {
                            count = true
                            sender.sendMessage("チャット計測を表示にします")
                            score.objective.displaySlot = DisplaySlot.BELOW_NAME
                            score2.objective.displaySlot = DisplaySlot.PLAYER_LIST
                        }
                        "hide" -> {
                            count = false
                            sender.sendMessage("チャット計測を非表示にします")
                            score.objective.displaySlot = null
                            score2.objective.displaySlot = null
                        }
                        "help" -> {
                            sender.sendMessage("ヘルプを表示します。")
                            sender.sendMessage("/mc show  :チャット計測を表示する")
                            sender.sendMessage("/mc hide  :チャット計測を非表示にする")
                            sender.sendMessage("各値の結果を表示したい場合はscoreboardのsidebarで表示する。")
                        }
                        else -> sender.sendMessage("不明なコマンドです。")
                    }
                }
            }
        }
        return super.onCommand(sender, command, label, args)
    }
}