package net.superkat;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.superkat.particles.*;

public class PostmortalClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PostmortalConfig.INSTANCE.load();

        ParticleFactoryRegistry.getInstance().register(PostmortalMain.VORTEX, VortexParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(PostmortalMain.SPARKLE, SparkleParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(PostmortalMain.EXPLOSION, SparkleExplosionParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(PostmortalMain.TOTEM, TotemParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(PostmortalMain.SHATTERED, ShatteredParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(PostmortalMain.GLITTER, GlitterParticle.Factory::new);
    }
}
