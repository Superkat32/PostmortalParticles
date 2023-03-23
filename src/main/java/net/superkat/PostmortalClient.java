package net.superkat;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.superkat.particles.SparkleExplosionParticle;
import net.superkat.particles.SparkleParticle;
import net.superkat.particles.VortexParticle;

public class PostmortalClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        GuiTest.INSTANCE.load();
        GuiTest.INSTANCE.getDefaults();

        ParticleFactoryRegistry.getInstance().register(PostmortalMain.VORTEX, VortexParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(PostmortalMain.SPARKLE, SparkleParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(PostmortalMain.EXPLOSION, SparkleExplosionParticle.Factory::new);
    }
}
