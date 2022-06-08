package fr.joupi.jhome.common.teleport;

import com.google.common.collect.Lists;
import fr.joupi.jhome.JHome;
import fr.joupi.jhome.utils.manager.AbstractManager;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class TeleportationManager extends AbstractManager<JHome> {

    private final List<UUID> playersInTeleportation;

    public TeleportationManager(JHome plugin) {
        super(plugin);
        this.playersInTeleportation = Lists.newLinkedList();
    }


}
