package fr.joupi.jhome.common.user;

import fr.joupi.jhome.common.home.core.Home;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class User {

    private final UUID uuid;

    private final List<Home> homes;

    @Setter private int maxHomes;

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public void sendMessage(String... messages) {
        Arrays.asList(messages)
                .forEach(message -> getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', message)));
    }

}
