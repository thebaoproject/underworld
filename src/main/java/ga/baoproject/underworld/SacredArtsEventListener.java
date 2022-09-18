/*
 * Copyright (c) 2022 the Block Art Online Project contributors.
 *
 * This work is free. It comes without any warranty, to the extent permitted
 * by applicable law. You can redistribute it and/or modify it under the terms
 * of the Do What The Fuck You Want To Public License, Version 2.
 * See the LICENSE file for more details.
 */

package ga.baoproject.underworld;

import ga.baoproject.theseed.abc.CustomEntity;
import ga.baoproject.theseed.abc.CustomPlayer;
import ga.baoproject.theseed.abc.DebugLogger;
import ga.baoproject.theseed.exceptions.InvalidEntityData;
import ga.baoproject.theseed.utils.EntityUtils;
import ga.baoproject.underworld.abc.ElementType;
import ga.baoproject.underworld.elements.ArrowShapedElement;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.persistence.PersistentDataType;

public class SacredArtsEventListener {
    public static void arrowHitEvent(ProjectileHitEvent e) {
        if (e.getEntity() instanceof Arrow) {
            // Checks if it is a Sacred Art.
            Integer isArt = EntityUtils.readFrom(e.getEntity(), "isSacredArt", PersistentDataType.INTEGER);
            String casterName = EntityUtils.readFrom(e.getEntity(), "casterName", PersistentDataType.STRING);
            String elementType = EntityUtils.readFrom(e.getEntity(), "type", PersistentDataType.STRING);
            DebugLogger.debug("ARROW REGISTERED art:" + isArt + " caster:" + casterName + " type:" + elementType);
            if (isArt != null && isArt == 1 && casterName != null && elementType != null) {
                ArrowShapedElement ae = new ArrowShapedElement();
                Player caster = Bukkit.getPlayer(casterName);
                if (caster != null) {
                    try {
                        ae.setCaster(CustomPlayer.fromPlayer(caster));
                        ae.setType(Enum.valueOf(ElementType.class, elementType));
                        if (e.getHitBlock() != null) {
                            ae.onHitBlock(e.getHitBlock());
                        } else if (e.getHitEntity() != null) {
                            ae.onHitEntity(CustomEntity.fromEntity((Damageable) e.getHitEntity()));
                        }
                    } catch (InvalidEntityData | ClassCastException ignored) {
                    }
                }
            }
        }
    }
}
