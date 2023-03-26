package net.superkat.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class TotemParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    TotemParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
//        this.velocityMultiplier = 0.6F;
        this.spriteProvider = spriteProvider;
        this.maxAge = 40;
        this.scale = 0.4F;
        this.velocityX = velocityX + 0.05 * this.random.nextBetween(-1, 1);
        this.velocityY = velocityY - 0.01;
        this.velocityZ = velocityZ + 0.05 * this.random.nextBetween(-1, 1);
        this.x = x + this.random.nextFloat() * 2 * this.random.nextBetween(-1, 1);
        this.y = y + this.random.nextFloat() * 2;
        this.z = z + this.random.nextFloat() * 2 * this.random.nextBetween(-1, 1);
        this.collidesWithWorld = true;
        this.setSpriteForAge(spriteProvider);
    }

    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        this.prevAngle = this.angle;
        if (this.age++ >= this.maxAge || this.scale <= 0) {
            this.markDead();
        } else {
            this.setSpriteForAge(this.spriteProvider);
            this.move(this.velocityX, this.velocityY, this.velocityZ);
        }
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new TotemParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }
}
