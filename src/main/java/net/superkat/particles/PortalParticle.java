package net.superkat.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class PortalParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    PortalParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
//        this.velocityMultiplier = 0.6F;
        this.spriteProvider = spriteProvider;
        this.maxAge = 40;
        this.scale = 1.1F + this.random.nextFloat() / 2;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.x = x + this.random.nextFloat();
        this.y = y + this.random.nextFloat();
        this.z = z + this.random.nextFloat();
//        this.angle = random.nextFloat() * (float)(2 * Math.PI);
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
            int extraTime = this.random.nextBetween(1, 5);
            if (this.age <= 4) {
                this.scale += 0.03;
            } else if (this.age - extraTime > 7) {
//                this.angle -= 0.06;
//                if (this.angle > 0) {
//                } else if (this.angle < 0) {
//                    SparkyStrikes.LOGGER.info("angle set to 0!");
//                    this.angle = 0;
//                }
//                this.scale -= 0.07;
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
            return new PortalParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }
}
