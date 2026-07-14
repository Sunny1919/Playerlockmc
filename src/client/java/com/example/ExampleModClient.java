package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class ExampleModClient implements ClientModInitializer {

	private MinecraftClient mc;

	@Override
	public void onInitializeClient() {
		mc = MinecraftClient.getInstance();

		KeyInputHandler.register();

		ClientTickEvents.END_CLIENT_TICK.register(client -> {

			// GUI öffnen
			if (KeyInputHandler.openSettings.wasPressed()) {
				mc.setScreen(new AimGuiScreen());
			}

			// Wenn R gedrückt gehalten wird, Auto-Aim aktiv
			if (mc.player == null || mc.world == null) return;
			if (mc.currentScreen != null) return;

			if (!KeyInputHandler.toggleAim.isPressed()) return; // Nur laufen, solange Taste gedrückt

			PlayerEntity closest = null;
			double bestDist = Double.MAX_VALUE;

			for (PlayerEntity p : mc.world.getPlayers()) {
				if (p == mc.player) continue;
				double dist = mc.player.squaredDistanceTo(p);
				if (dist < bestDist && dist <= AimSettings.RANGE * AimSettings.RANGE) {
					bestDist = dist;
					closest = p;
				}
			}

			if (closest == null) return;

			Vec3d eyes = mc.player.getEyePos();
			Vec3d target = closest.getEyePos();

			double dx = target.x - eyes.x;
			double dy = target.y - eyes.y;
			double dz = target.z - eyes.z;

			double yaw = Math.toDegrees(Math.atan2(dz, dx)) - 90;
			double pitch = -Math.toDegrees(Math.atan2(dy, Math.sqrt(dx*dx + dz*dz)));

			float newYaw = (float)(mc.player.getYaw() + (yaw - mc.player.getYaw()) * AimSettings.SMOOTH);
			float newPitch = (float)(mc.player.getPitch() + (pitch - mc.player.getPitch()) * AimSettings.SMOOTH);

			mc.player.setYaw(newYaw);
			mc.player.setPitch(newPitch);
		});
	}
}
