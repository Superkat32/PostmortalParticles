package net.superkat;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostmortalMain implements ModInitializer {
	public static final String MOD_ID = "postmortalparticles";
	public static final Logger LOGGER = LoggerFactory.getLogger("postmortalparticles");

	public static final DefaultParticleType VORTEX = FabricParticleTypes.simple();
	public static final DefaultParticleType SPARKLE = FabricParticleTypes.simple();
	public static final DefaultParticleType EXPLOSION = FabricParticleTypes.simple();
	public static final DefaultParticleType TOTEM = FabricParticleTypes.simple();
	public static final DefaultParticleType SHATTERED = FabricParticleTypes.simple();

	@Override
	public void onInitialize() {

		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "vortex"), VORTEX);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "sparkle"), SPARKLE);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "explosion"), EXPLOSION);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "totem"), TOTEM);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "shattered"), SHATTERED);
		LOGGER.info("Hello Fabric world!");

	}
}