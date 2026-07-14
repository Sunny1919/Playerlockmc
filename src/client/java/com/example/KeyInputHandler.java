package com.example;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {

    public static KeyBinding toggleAim;
    public static KeyBinding openSettings;

    public static final String MOD_CATEGORY = "key.category.playerlock.general";

    public static void register() {
        toggleAim = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.example.toggleaim",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                MOD_CATEGORY
        ));

        openSettings = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.example.opensettings",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                MOD_CATEGORY
        ));
    }
}