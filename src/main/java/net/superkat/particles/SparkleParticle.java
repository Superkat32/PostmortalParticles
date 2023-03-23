package net.superkat.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class SparkleParticle extends AnimatedParticle {
    private final SpriteProvider spriteProvider;

    //This boolean is used as a workaround
    //If enabled, only a few of all particles spawned will actually spawn
    //This is here because in ClientPlayNetworkHandlerMixin, there is a particle emitter which spawns this exact particle
    //The thing is, I don't want a lot of sparkle particles to spawn, but I want to use the emitter function
    //So this boolean is the workaround
    boolean shouldReduceAmountThatSpawn = true;
    boolean reducing = true;

    SparkleParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z, spriteProvider, 1.25F);
        this.velocityMultiplier = 0.6F;
        this.spriteProvider = spriteProvider;
        this.maxAge = 30;
        this.scale = 0.001F;
        this.velocityX = 0;
        this.velocityY = 0;
        this.velocityZ = 0;
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
            if(this.age == 1) {
                if(shouldReduceAmountThatSpawn) {
                    if(reducing) {
                        int shouldSpawn = this.random.nextBetween(1, 30);
                        if(shouldSpawn == 1) {
                            this.scale = 0.01F;
                        } else {
                            reducing = false;
                            this.markDead();
                        }
                    }
                }
            }
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
            return new SparkleParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }
}
