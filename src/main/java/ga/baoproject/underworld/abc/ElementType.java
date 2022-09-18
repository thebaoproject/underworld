package ga.baoproject.underworld.abc;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public enum ElementType {
    AQUEOUS_ELEMENT,
    AERIAL_ELEMENT,
    LUMINOUS_ELEMENT,
    METALLIC_ELEMENT,
    THERMAL_ELEMENT,
    UMBRAL_ELEMENT,
    CRYSTALLINE_ELEMENT;

    public static @Nullable ElementType get(@NotNull String name) {
        return switch (name.toLowerCase(Locale.ROOT)) {
            case "aqueous_element" -> AQUEOUS_ELEMENT;
            case "aerial_element" -> AERIAL_ELEMENT;
            case "luminous_element" -> LUMINOUS_ELEMENT;
            case "metallic_element" -> METALLIC_ELEMENT;
            case "thermal_element" -> THERMAL_ELEMENT;
            case "umbral_element" -> UMBRAL_ELEMENT;
            case "crystalline_element" -> CRYSTALLINE_ELEMENT;
            default -> null;
        };
    }
}
