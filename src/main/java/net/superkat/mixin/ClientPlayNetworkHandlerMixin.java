package net.superkat.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.listener.TickablePacketListener;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.superkat.PostmortalMain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin implements TickablePacketListener, ClientPlayPacketListener {

	private final ClientWorld world;
	private final MinecraftClient client;
	public ClientPlayNetworkHandlerMixin(ClientWorld world, ClientWorld world1, MinecraftClient client) {
		super();
		this.world = world1;
		this.client = client;
	}

	@Redirect(method = "onEntityStatus", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/ParticleManager;addEmitter(Lnet/minecraft/entity/Entity;Lnet/minecraft/particle/ParticleEffect;I)V"))
	private void init(ParticleManager instance, Entity entity, ParticleEffect parameters, int maxAge) {
//		Entity entity = packet.getEntity(this.world);
//		Entity entity = packet.getEntity(this.world);
		this.world.addParticle(PostmortalMain.VORTEX, entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		this.client.particleManager.addEmitter(entity, PostmortalMain.SPARKLE, 100);
		this.client.particleManager.addEmitter(entity, PostmortalMain.EXPLOSION, 10);
		this.client.particleManager.addEmitter(entity, ParticleTypes.TOTEM_OF_UNDYING, 30);
//		this.client.particleManager.addParticle(PostmortalMain.PORTAL, entity.getX(), );
//		this.client.particleManager.addEmitter(entity, ParticleTypes.END_ROD, 30);
	}
}