package ga.baoproject.underworld.abc;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public enum ElementShape {
    CUBE_SHAPE,
    ARROW_SHAPE;

    public static @Nullable ElementShape get(@NotNull String name) {
        return switch (name.toLowerCase(Locale.ROOT)) {
            case "cube_shape" -> CUBE_SHAPE;
            case "arrow_shape" -> ARROW_SHAPE;
            default -> null;
        };
    }
}
