package fr.nil.eronya.spigot.games.uhc.gameManager;

import fr.nil.eronya.spigot.games.uhc.task.AttackTask;
import org.bukkit.Bukkit;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class GameStart {

       public static void startGame(){
        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            PlayerManager.clearPlayers(p);
            PlayerManager.giveEffect(p);
            PlayerManager.counter(p);
            AttackTask.startPVP(p);
        }
    }
}
