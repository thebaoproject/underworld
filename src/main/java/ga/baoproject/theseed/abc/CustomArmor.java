/*
 * Copyright (c) 2022 the Block Art Online Project contributors.
 *
 * This work is free. It comes without any warranty, to the extent permitted
 * by applicable law. You can redistribute it and/or modify it under the terms
 * of the Do What The Fuck You Want To Public License, Version 2.
 * See the LICENSE file for more details.
 */

package ga.baoproject.theseed.abc;

import ga.baoproject.theseed.TheSeed;
import ga.baoproject.theseed.i18n.Locale;
import ga.baoproject.theseed.i18n.Localized;
import ga.baoproject.theseed.utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A talisman can be an item that you hold in your inventory to
 * receive buff or armor, there is no difference in the effect of the two.
 * TODO: protection and knockback mitigation.
 */
public abstract class CustomArmor extends CustomItem {

    private int protection;
    private int knockbackMitigation;
    private int speedBuff;
    private int healthBuff;
    private EquipmentSlot trigger;

    public CustomArmor(Material m) {
        super(m);
    }

    @Override
    public ItemStack getItem(Locale l) {
        ItemStack item = new ItemStack(getMaterial());
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.displayName(Component.text(getRarity().toColor() + getName()));
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(Utils.color("&7" + new Localized("Bảo vệ", "plugin.item.description.protection").render(l) + ": &c" + "+" + getProtection())));
        if (knockbackMitigation > 0) {
            lore.add(Component.text(Utils.color("&7" + new Localized("Chống giật lùi", "plugin.item.description.knockback").render(l) + ": &c" + "+" + getKnockbackMitigation())));
        }
        if (healthBuff > 0) {
            lore.add(Component.text(Utils.color("&7" + new Localized("Tăng máu", "plugin.item.description.healthBuff").render(l) + ": &c" + "+" + getHealthBuff())));
        }
        if (speedBuff > 0) {
            lore.add(Component.text(Utils.color("&7" + new Localized("Tăng máu", "plugin.item.description.speedBuff").render(l) + ": &c" + "+" + getSpeedBuff())));
        }
        // TODO: Full set bonus only for armour. However it is not necessary as there is no such thing in Sword Art Online.
        lore.add(Component.space());
        lore.add(Component.text(getRarity().renderLocalizedString(l)));
        meta.lore(lore);
        meta.setUnbreakable(true);
        AttributeModifier sb = new AttributeModifier(
                UUID.randomUUID(),
                "generic.movementSpeed",
                getSpeedBuff() / 400F,
                AttributeModifier.Operation.ADD_NUMBER,
                getTrigger()
        );
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, sb);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);

        Plugin pl = TheSeed.getInstance();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(new NamespacedKey(pl, "id"), PersistentDataType.STRING, getID());
        item.setItemMeta(meta);
        return item;
    }

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public int getKnockbackMitigation() {
        return knockbackMitigation;
    }

    public void setKnockbackMitigation(int knockbackMitigation) {
        this.knockbackMitigation = knockbackMitigation;
    }

    public int getSpeedBuff() {
        return speedBuff;
    }

    public void setSpeedBuff(int speedBuff) {
        this.speedBuff = speedBuff;
    }

    public int getHealthBuff() {
        return healthBuff;
    }

    public void setHealthBuff(int healthBuff) {
        this.healthBuff = healthBuff;
    }

    public EquipmentSlot getTrigger() {
        return trigger;
    }

    public void setTrigger(EquipmentSlot trigger) {
        this.trigger = trigger;
    }
}
