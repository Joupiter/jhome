package fr.joupi.jhome.common.home.adapter;

import com.google.gson.*;
import fr.joupi.jhome.common.home.core.Home;
import fr.joupi.jhome.utils.file.json.IAdapter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class HomeAdapter implements IAdapter<Home> {

    @Override
    public JsonElement serialize(Home home) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("name", home.getName());
        jsonObject.addProperty("icon", home.getIcon().name());

        jsonObject.addProperty("world", home.getWorld().getName());

        jsonObject.addProperty("x", home.getX());
        jsonObject.addProperty("y", home.getY());
        jsonObject.addProperty("z", home.getZ());
        jsonObject.addProperty("yaw", home.getLocation().getYaw());
        jsonObject.addProperty("pitch", home.getLocation().getPitch());

        return jsonObject;
    }

    @Override
    public Home deserialize(JsonElement jsonElement) throws JsonParseException {
        final JsonObject jsonObject = jsonElement.getAsJsonObject();
        final Location homeLocation = new Location(Bukkit.getWorld(jsonObject.get("world").getAsString()), jsonObject.get("x").getAsDouble(), jsonObject.get("y").getAsDouble(), jsonObject.get("z").getAsDouble(), jsonObject.get("yaw").getAsFloat(), jsonObject.get("pitch").getAsFloat());

        return new Home(jsonObject.get("name").getAsString(), homeLocation, jsonObject.get("icon").getAsString());
    }

}