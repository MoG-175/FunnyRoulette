package net.mog.funnyroulette;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.world.World;
import java.util.Random;

public class FunnyRoulette implements ModInitializer {
    private static final Random rn = new Random();
    private static final int defaultchance = 1_000_000;
    private static int chance = defaultchance;
    
	@Override
	public void onInitialize() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            if (rn.nextInt(0, chance) == 0) {
                player.damage(player.getDamageSources().explosion(null), 99999F);
                world.createExplosion(null, player.getX(), player.getY(), player.getZ(), 4.0F, false, World.ExplosionSourceType.TNT);
                chance = defaultchance;
            } else {
                chance -= 1;
            }
        });
	}
}