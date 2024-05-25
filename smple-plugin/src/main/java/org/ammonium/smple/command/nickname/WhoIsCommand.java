package org.ammonium.smple.command.nickname;

import org.ammonium.smple.SmplePlugin;
import org.ammonium.smple.helpers.NicknameHelper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class WhoIsCommand {
    
    private final SmplePlugin plugin;
    
    public WhoIsCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }
    
    @Command("whois <nickname>")
    @Permission("smple.misc.whois.run")
    @CommandDescription("Get information about a player")
    public void whois(
        final CommandSender player,
        @Argument("nickname")
        final String target
    ) {
        // String[] nicknames = NicknameHelper.getNickname(target);
        
        // Check through nicknames
        
        // if target in nicknames
        
        // return targets username
        
        // else
        
        // return "Player with nickname %s not found".formatted(target);"
        
    }
}
