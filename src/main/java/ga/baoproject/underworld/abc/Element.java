/*
 * Copyright (c) 2022 the Block Art Online Project contributors.
 *
 * This work is free. It comes without any warranty, to the extent permitted
 * by applicable law. You can redistribute it and/or modify it under the terms
 * of the Do What The Fuck You Want To Public License, Version 2.
 * See the LICENSE file for more details.
 */

package ga.baoproject.underworld.abc;


import ga.baoproject.theseed.abc.CustomEntity;
import ga.baoproject.theseed.abc.CustomPlayer;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public abstract class Element {
    private ElementType type;
    private CustomPlayer caster;

    public abstract void dischargeFrom(@NotNull Location location);

    public abstract void adhereTo(@NotNull Location location);

    public abstract void onHitEntity(CustomEntity e);

    public abstract void onHitBlock(Block block);

    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
    }

    public CustomPlayer getCaster() {
        return caster;
    }

    public void setCaster(CustomPlayer caster) {
        this.caster = caster;
    }
}
