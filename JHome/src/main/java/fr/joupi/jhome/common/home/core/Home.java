package fr.joupi.jhome.common.home.core;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

@Getter
public class Home {

    private final String name;
    private final Location location;
    @Setter private Material icon;

    public Home(String name, Location location) {
        this(name, location, Material.ENDER_PEARL);
    }

    public Home(String name, Location location, String icon) {
        this(name, location, Material.getMaterial(icon));
    }

    public Home(String name, Location location, Material icon) {
        this.name = name;
        this.icon = icon;
        this.location = location;
    }

    public double getX() {
        return getLocation().getX();
    }

    public double getY() {
        return getLocation().getY();
    }

    public double getZ() {
        return getLocation().getZ();
    }

    public World getWorld() {
        return getLocation().getWorld();
    }

    public void teleport(Player player) {
        player.teleport(getLocation());
    }

}
