package net.superkat.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class ShatteredParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;
    private final boolean direction = this.random.nextBoolean();
    private boolean rotate = true;

    ShatteredParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
//        this.velocityMultiplier = 0.6F;
        this.spriteProvider = spriteProvider;
        this.maxAge = 200;
        this.scale = 0.1F + this.random.nextFloat() / 32;
        this.velocityX = 0.25 * this.random.nextBetween(-1, 1) + this.random.nextFloat() / this.random.nextBetween(7, 20);
        this.velocityY = 0.12 + this.random.nextFloat() / this.random.nextBetween(8, 20);
        this.velocityZ = 0.25 * this.random.nextBetween(-1, 1) + this.random.nextFloat() / this.random.nextBetween(7, 20);
        this.x = x + this.random.nextFloat();
        this.y = y + this.random.nextFloat();
        this.z = z + this.random.nextFloat();
        this.angle = 0F;
//        this.angle = prevAngle = random.nextFloat() * (float) (2 * Math.PI);
//        this.angle = 0.30F;
//        this.setBoundingBoxSpacing(0.02F, 0.02F);
//        this.velocityX = this.random.nextFloat() + 0.07;
//        this.velocityY = 0;
//        this.velocityZ = this.random.nextFloat() + 0.07;
//        this.startX = x;
//        this.startY = y;
//        this.startZ = z;
        this.collidesWithWorld = true;
        this.setSpriteForAge(spriteProvider);
    }

    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.age++ >= this.maxAge || this.scale <= 0) {
            this.markDead();
        } else {
            if(rotate) {
                if (direction) {
                    this.angle = this.prevAngle -= 0.07F;
                } else {
                    this.angle = this.prevAngle += 0.07F;
                }
            }
            int extraTime = this.random.nextBetween(1, 5);
            if (this.age < 7 + extraTime) {
                this.scale += 0.03;
            } else if (this.age > 7 + extraTime) {
                if (this.velocityY >= -0.50) {
                    this.velocityY -= 0.07;
                }
                if (this.age > 185 + extraTime) {
                    this.scale -= 0.035;
                }
            }
            if(this.onGround) {
                rotate = false;
            }
//            this.setSpriteForAge(this.spriteProvider);
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
            ShatteredParticle shatteredParticle = new ShatteredParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
            shatteredParticle.setSprite(this.spriteProvider);
            return shatteredParticle;
        }
    }
}