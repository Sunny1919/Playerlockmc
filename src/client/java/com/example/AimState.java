package com.example;

public class AimState {
    private static boolean enabled = false;

    public static boolean isEnabled() {
        return enabled;
    }

    public static void toggle() {
        enabled = !enabled;
    }
}
