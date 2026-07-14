package com.example;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {

    public static KeyBinding toggleAim;
    public static KeyBinding openSettings;

    // Wir erstellen eine echte Category-Instanz mit einem Identifier
    // Da deine Klasse 'KeyBinding.Category' ist, nutzen wir die create-Methode:
    public static final KeyBinding.Category MOD_CATEGORY = KeyBinding.Category.register(
            Identifier.of("playerlock", "general")
    );

    public static void register() {
        toggleAim = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.example.toggleaim",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                MOD_CATEGORY // Jetzt ist es vom Typ 'Category'
        ));

        openSettings = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.example.opensettings",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                MOD_CATEGORY
        ));
    }
}