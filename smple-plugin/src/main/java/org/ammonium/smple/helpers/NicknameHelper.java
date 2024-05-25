package org.ammonium.smple.helpers;

import org.bukkit.entity.Player;

import java.util.UUID;

public class NicknameHelper {
    
    
    public static void setNickname(Player player, String nickname, Boolean silent) {
        
        // set player nickname to nickname
        
        if (!silent) player.sendMessage("Changed your nickname to %s".formatted(nickname));
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public static String getNickname(Player player) {
        
        // get current player nickname from database
        
        player.sendMessage("Your nickname is %s".formatted("nickname"));
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public static String[] getNicknames(Player player, Boolean silent) {
        
        // get player nicknames from database
        
        if (!silent) player.sendMessage("Your nicknames are %s".formatted("nicknames"));
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public static void removeNickname(Player player, Boolean silent) {
        
        
        // if player has nickname
        
        // set player nickname to their username
        
        if (!silent) player.sendMessage("Removed your nickname");
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
}
