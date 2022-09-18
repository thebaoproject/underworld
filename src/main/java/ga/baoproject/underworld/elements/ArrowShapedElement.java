package ga.baoproject.underworld.elements;

import ga.baoproject.theseed.abc.CustomEntity;
import ga.baoproject.theseed.abc.DebugLogger;
import ga.baoproject.theseed.utils.EntityUtils;
import ga.baoproject.underworld.abc.Element;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class ArrowShapedElement extends Element {
    @Override
    public void dischargeFrom(@NotNull Location location) {
        Vector direction = location.getDirection();
        Arrow a = location.getWorld().spawnArrow(location.add(0, 1, 0), direction, 0.6F, 0);
        a.setVelocity(direction.multiply(2.0D));
        EntityUtils.writeTo(a, "isSacredArt", 1);
        EntityUtils.writeTo(a, "casterName", getCaster().getBase().getName());
        EntityUtils.writeTo(a, "type", getType().toString());
    }

    @Override
    public void adhereTo(@NotNull Location location) {
    }

    @Override
    public void onHitEntity(CustomEntity e) {
        DebugLogger.debug("Entity" + e.getName() + "is hit!");
    }

    @Override
    public void onHitBlock(Block block) {
        DebugLogger.debug("Block" + block.getType() + "is hit!");
    }
}
