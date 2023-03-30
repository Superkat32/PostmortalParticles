package net.superkat.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class TrailParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    //This boolean is used as a workaround
    //If enabled, only a few of all particles spawned will actually spawn
    //This is here because in ClientPlayNetworkHandlerMixin, there is a particle emitter which spawns this exact particle
    //The thing is, I don't want a lot of sparkle particles to spawn, but I want to use the emitter function
    //So this boolean is the workaround
    boolean shouldReduceAmountThatSpawn = true;
    boolean reducing = true;
    public double velX = 0;
    public double velZ = 0;

    TrailParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
        this.velocityMultiplier = 0.6F;
        this.spriteProvider = spriteProvider;
        this.maxAge = 100;
        this.scale = 0.001F;
        this.velocityX = velocityX * 1.2;
        this.velocityY = velocityY / 15;
        this.velocityZ = velocityZ * 1.2;
        velX = velocityX;
        velZ = velocityZ;
        this.x = x;
        this.y = y;
        this.z = z;
//        this.angle = random.nextFloat() * (float)(2 * Math.PI);
//        this.angle = 1.2F;
//        this.setBoundingBoxSpacing(0.02F, 0.02F);
//        this.velocityX = this.random.nextFloat() + 0.07;
//        this.velocityY = 0;
//        this.velocityZ = this.random.nextFloat() + 0.07;
//        this.startX = x;
//        this.startY = y;
//        this.startZ = z;
        if (this.random.nextInt(4) == 0) {
            this.setColor(0.6F + this.random.nextFloat() * 0.2F, 0.6F + this.random.nextFloat() * 0.3F, this.random.nextFloat() * 0.2F);
        } else {
            this.setColor(0.1F + this.random.nextFloat() * 0.2F, 0.4F + this.random.nextFloat() * 0.3F, this.random.nextFloat() * 0.2F);
        }
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
            if(this.age == 1) {
                if(shouldReduceAmountThatSpawn) {
                    if(reducing) {
                        int shouldSpawn = this.random.nextBetween(1, 7);
                        if(shouldSpawn == 1) {
                            this.scale = 0.45F;
                        } else {
                            reducing = false;
                            this.markDead();
                        }
                    }
                }
                if(this.velocityY < 0) {
                    this.velocityY *= -1;
                }
            }
            if(this.age == 2) {
                this.velocityX = 0;
//                this.velocityY *= -1.02;
                this.velocityZ = 0;
            }
            if(this.age == 2) {
                this.velocityY *= -1;
            }
            if(this.age >= 5) {
                this.velocityY *= 1.05;
            }
            if(this.age >= 75) {
                //There is actually a bug here that causes the particle to flicker, however, I liked it. So I decided to keep it
                this.alpha -= 0.1;
            }
//            this.velocityY *= 1.0;
//            this.angle = this.prevAngle + this.random.nextFloat() / 32;
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
            return new TrailParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }
}
