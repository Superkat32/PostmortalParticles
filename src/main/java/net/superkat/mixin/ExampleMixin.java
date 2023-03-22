package net.superkat.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.listener.TickablePacketListener;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.superkat.PostmortalMain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ExampleMixin implements TickablePacketListener, ClientPlayPacketListener {

	private final ClientWorld world;
	private final MinecraftClient client;
	public ExampleMixin(ClientWorld world, ClientWorld world1, MinecraftClient client) {
		super();
		this.world = world1;
		this.client = client;
	}

	@Inject(method = "onEntityStatus", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/ParticleManager;addEmitter(Lnet/minecraft/entity/Entity;Lnet/minecraft/particle/ParticleEffect;I)V"))
	private void init(EntityStatusS2CPacket packet, CallbackInfo ci) {
//		Entity entity = packet.getEntity(this.world);
		Entity entity = packet.getEntity(this.world);
		this.world.addParticle(PostmortalMain.PORTAL, entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		this.client.particleManager.addEmitter(entity, PostmortalMain.SPARKLE, 70);
//		this.client.particleManager.addParticle(PostmortalMain.PORTAL, entity.getX(), );
//		this.client.particleManager.addEmitter(entity, ParticleTypes.END_ROD, 30);
	}
}