/*
 * Copyright (c) 2022 the Block Art Online Project contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package ga.baoproject.theseed.events;

import ga.baoproject.theseed.abc.CustomBoss;
import ga.baoproject.theseed.abc.CustomEntity;
import ga.baoproject.theseed.exceptions.InvalidEntityData;
import ga.baoproject.theseed.exceptions.InvalidEntityID;
import ga.baoproject.theseed.utils.EntityUtils;
import ga.baoproject.theseed.utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityEventHandler {

    public static void onDeath(EntityDeathEvent e) {
        try {
            CustomEntity victim = CustomEntity.fromEntity(e.getEntity());
            if (EntityUtils.get(victim.getID()) instanceof CustomBoss b) {
                Bukkit.broadcast(Component.text(Utils.color("&6Một người chơi đã tiêu diệt boss &c" + b.getName() + "&6!")));
                b.onDeath(e);
                e.setCancelled(false);
            }
        } catch (InvalidEntityData | InvalidEntityID ignored) {
        }
    }

}
