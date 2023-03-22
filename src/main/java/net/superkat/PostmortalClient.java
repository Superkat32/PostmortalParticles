package net.superkat;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.superkat.particles.PortalParticle;
import net.superkat.particles.SparkleParticle;

public class PostmortalClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(PostmortalMain.PORTAL, PortalParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(PostmortalMain.SPARKLE, SparkleParticle.Factory::new);
    }
}
