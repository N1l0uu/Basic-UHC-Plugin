package fr.nil.eronya.spigot.games.uhc.commands;

import fr.nil.eronya.spigot.games.uhc.CoreClass;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPVP implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if (label.equalsIgnoreCase("pvp")) {
            if (CoreClass.isGame()) {

                if (CoreClass.isPvp() == false) {

                    CoreClass.setPvp(true);
                    Bukkit.getServer().broadcastMessage(CoreClass.prefix + "Le PVP à été activé de force par " + p.getName());

                } else if (CoreClass.isPvp() == true) {
                    CoreClass.setPvp(false);
                    Bukkit.getServer().broadcastMessage(CoreClass.prefix + "Le PVP à été désactivé par " + p.getName());
                }
                    {

                    }
                }else {
                p.sendMessage(CoreClass.prefix + "Vous ne pouvez pas modifier le PVP , la partie n'est pas lancé !");


            }
        }
        return false;

    }
}
