package net.superkat;

import dev.isxander.yacl.api.YetAnotherConfigLib;
import dev.isxander.yacl.gui.YACLScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.superkat.particles.SparkleExplosionParticle;
import net.superkat.particles.SparkleParticle;
import net.superkat.particles.VortexParticle;

public class PostmortalClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ExampleConfig.INSTANCE.load();

        ParticleFactoryRegistry.getInstance().register(PostmortalMain.VORTEX, VortexParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(PostmortalMain.SPARKLE, SparkleParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(PostmortalMain.EXPLOSION, SparkleExplosionParticle.Factory::new);
    }
}
