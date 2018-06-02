package fr.nil.eronya.spigot.games.uhc.utils;

import fr.nil.eronya.spigot.games.uhc.CoreClass;
import sun.plugin2.message.Message;

public enum Messages {



    UHC_RELOG(CoreClass.prefix + ""),
    UHC_GAMEALREADYLAUNCHED(CoreClass.prefix + "L'UHC à déjà démarrer inutile de relance une autre partie !"),
UHC_PVPOFF(CoreClass.prefix + "Le PVP n'est pas encore activé !"),
    UHC_PVPENABLED(CoreClass.prefix + "Le PVP est maintenant activé, bonne chance !"),
    UHC_DEATH(CoreClass.prefix +"Vous êtes mort bien joué , vous aurez plus de chance la prochaine fois ! "),

UHC_GAMESTART(CoreClass.prefix + "L'UHC vas bientôt démarrer !"),
    UHC_ALREADYSTART("L'UHC à déjà démarrer , vous ne pouvez pas le rejoindre");




    private final String msg;
private static int num;
    Messages(String message){
        this.msg = message;
    }

    public String getMessage() {
        return msg;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

