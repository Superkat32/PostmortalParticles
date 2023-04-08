package net.superkat.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.Entity;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.listener.TickablePacketListener;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.superkat.PostmortalMain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.superkat.PostmortalConfig.INSTANCE;
import static net.superkat.PostmortalMain.LOGGER;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin implements TickablePacketListener, ClientPlayPacketListener {
//	private final ClientWorld world;
//	private final MinecraftClient client;
//	public ClientPlayNetworkHandlerMixin(ClientWorld world, MinecraftClient client) {
//		super();
//		this.world = world;
//		this.client = client;
//	}

//	@Redirect(method = "onEntityStatus", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/ParticleManager;addEmitter(Lnet/minecraft/entity/Entity;Lnet/minecraft/particle/ParticleEffect;I)V"))
	//TODO - Check if using "instance.addParticle" is better than "world.addParticle"
	@Redirect(method = "onEntityStatus", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/ParticleManager;addEmitter(Lnet/minecraft/entity/Entity;Lnet/minecraft/particle/ParticleEffect;I)V"))
	public void onEntityStatus(ParticleManager instance, Entity entity, ParticleEffect parameters, int maxAge) {
		if(INSTANCE.getConfig().modEnabled) {
//			World world = entity.getWorld();
			if(INSTANCE.getConfig().spamLogs) {
				LOGGER.info("Showing particles!");
				logDebugInfo(entity);
			}
			if(INSTANCE.getConfig().vortexParticle) {
				instance.addParticle(PostmortalMain.VORTEX, entity.getX(), entity.getBodyY(1.0), entity.getZ(), 0, 0, 0);
			}
			if(INSTANCE.getConfig().sparkleParticle) {
				//Multiply the number by 20, to change the time from seconds to Minecraft ticks
				float timer = INSTANCE.getConfig().sparkleTimer * 20;
				instance.addEmitter(entity, PostmortalMain.SPARKLE, (int) timer);
			}
			if(INSTANCE.getConfig().sparkleExplosionParticle) {
				instance.addEmitter(entity, PostmortalMain.EXPLOSION, 10);
			}
			if(INSTANCE.getConfig().totemParticle) {
				instance.addParticle(PostmortalMain.TOTEM, entity.getX(), entity.getBodyY(1.0), entity.getZ(), 0, 0, 0);
			}
			if(INSTANCE.getConfig().shatteredParticle) {
				for(int amount = INSTANCE.getConfig().shatteredAmount; amount >= 1; amount--) {
					instance.addParticle(PostmortalMain.SHATTERED, entity.getX(), entity.getBodyY(1.0), entity.getZ(), 0, 0, 0);
				}
			}
			if(INSTANCE.getConfig().defaultParticles) {
				float timer = INSTANCE.getConfig().defaultTimer * 20;
				instance.addEmitter(entity, ParticleTypes.TOTEM_OF_UNDYING, (int) timer);
			}
			if(INSTANCE.getConfig().beamParticle) {
				float timer = INSTANCE.getConfig().beamTime * 20;
				instance.addEmitter(entity, PostmortalMain.BEAM, (int) timer);
			}
			if(INSTANCE.getConfig().trailParticle) {
				float timer = INSTANCE.getConfig().trailTime * 20;
				instance.addEmitter(entity, PostmortalMain.TRAIL, (int) timer);
			}
		} else {
			if(INSTANCE.getConfig().spamLogs) {
				LOGGER.info("Not showing any particles beyond original");
				LOGGER.info("Mod enabled/disabled status: " + INSTANCE.getConfig().modEnabled);
			}
			instance.addEmitter(entity, ParticleTypes.TOTEM_OF_UNDYING, 30);
		}
	}

	public void logDebugInfo(Entity entity) {
		LOGGER.info("Entity: " + entity.getType());
		LOGGER.info("Uuid: " + entity.getUuidAsString());
		LOGGER.info("X: " + entity.getX());
		LOGGER.info("Y: " + entity.getY());
		LOGGER.info("Z: " + entity.getZ());
	}
}