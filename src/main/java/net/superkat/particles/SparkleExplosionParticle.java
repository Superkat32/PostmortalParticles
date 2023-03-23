package net.superkat.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class SparkleExplosionParticle extends AnimatedParticle {
    private final SpriteProvider spriteProvider;
    SparkleExplosionParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z, spriteProvider, 1.25F);
        this.velocityMultiplier = 0.6F;
        this.spriteProvider = spriteProvider;
        this.maxAge = 30;
        this.scale = 0.001F;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.x = x + this.random.nextFloat() * 2 * this.random.nextBetween(-1, 1);
        this.y = y + this.random.nextFloat() * 2;
        this.z = z + this.random.nextFloat() * 2 * this.random.nextBetween(-1, 1);
        this.angle = random.nextFloat() * (float)(2 * Math.PI);
//        this.angle = 1.2F;
//        this.setBoundingBoxSpacing(0.02F, 0.02F);
//        this.velocityX = this.random.nextFloat() + 0.07;
//        this.velocityY = 0;
//        this.velocityZ = this.random.nextFloat() + 0.07;
//        this.startX = x;
//        this.startY = y;
//        this.startZ = z;
        this.collidesWithWorld = false;
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
            if (this.age <= 7) {
                this.scale += 0.05;
            } else if (this.age <= 15) {
                this.scale -= 0.04;
            } else if (this.age <= 19) {
                this.scale += 0.07;
            } else if (this.age <= 30){
                this.scale -= 0.07;
            }
            this.angle = this.prevAngle + 0.05F;
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
            return new SparkleExplosionParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }
}
