package fr.nil.eronya.spigot.games.uhc.commands;

import fr.nil.eronya.spigot.games.uhc.CoreClass;
import fr.nil.eronya.spigot.games.uhc.gameManager.GameStart;
import fr.nil.eronya.spigot.games.uhc.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStart implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int online = Bukkit.getServer().getOnlinePlayers().size();
        int maxplayer = Bukkit.getServer().getMaxPlayers();
        if(label.equalsIgnoreCase("start")) {
            if (CoreClass.isGame() == true) {
                sender.sendMessage(Messages.UHC_GAMEALREADYLAUNCHED.getMessage());
            } else {
                CoreClass.setGame(true);
            GameStart.startGame();
                Player p = (Player)sender;
                p.setTotalExperience(100000);
        }
        }

        return false;
    }

}
