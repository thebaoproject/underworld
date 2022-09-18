/*
 * Copyright (c) 2022 the Block Art Online Project contributors.
 *
 * This work is free. It comes without any warranty, to the extent permitted
 * by applicable law. You can redistribute it and/or modify it under the terms
 * of the Do What The Fuck You Want To Public License, Version 2.
 * See the LICENSE file for more details.
 */

package ga.baoproject.underworld;

import ga.baoproject.theseed.abc.CustomPlayer;
import ga.baoproject.theseed.abc.DebugLogger;
import ga.baoproject.theseed.exceptions.InvalidEntityData;
import ga.baoproject.theseed.i18n.Localized;
import ga.baoproject.underworld.abc.Element;
import ga.baoproject.underworld.abc.ElementType;
import ga.baoproject.underworld.elements.ArrowShapedElement;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SacredArts implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player p)) {
            sender.sendMessage("Console cannot execute this command!");
            return true;
        }
        CustomPlayer cp;
        try {
            cp = CustomPlayer.fromPlayer(p);
        } catch (InvalidEntityData e) {
            throw new RuntimeException(e);
        }
        if (args.length != 3) {
            sender.sendMessage(new Localized("Bạn chưa đưa ra đủ tham số!", "plugin.error.notEnoughArgs").render(cp.getLocale()));
            return true;
        }
        ElementType type = ElementType.get(args[0]);
        if (type != null) {
            Element e;
            if (args[1].equalsIgnoreCase("arrow_shape")) {
                e = new ArrowShapedElement();
            } else if (args[1].equalsIgnoreCase("cube_shape")) {
                // TODO - Implement cube-shaped element
                DebugLogger.debug("CUBE_SHAPE: NOT IMPLEMENTED.");
                return true;
            } else {
                sender.sendMessage(new Localized("Tham số mà bạn đưa ra không hợp lệ!", "plugin.error.invalidArg").render(cp.getLocale()));
                return true;
            }
            e.setCaster(cp);
            e.setType(type);
            if (args[2].equalsIgnoreCase("discharge")) {
                e.dischargeFrom(p.getLocation());
                p.sendMessage(ChatColor.GOLD + new Localized("Vù!", "plugin.item.abilityUse").render(cp.getLocale()));
            }

        } else {
            sender.sendMessage(new Localized("Tham số mà bạn đưa ra không hợp lệ!", "plugin.error.invalidArg").render(cp.getLocale()));
            return true;
        }
        return true;
    }
}
