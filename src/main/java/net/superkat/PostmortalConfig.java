package net.superkat;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.config.ConfigEntry;
import dev.isxander.yacl3.config.ConfigInstance;
import dev.isxander.yacl3.config.GsonConfigInstance;
import dev.isxander.yacl3.gui.controllers.BooleanController;
import dev.isxander.yacl3.gui.controllers.slider.FloatSliderController;
import dev.isxander.yacl3.gui.controllers.slider.IntegerSliderController;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.nio.file.Path;

public class PostmortalConfig {
    public static final ConfigInstance<PostmortalConfig> INSTANCE = new GsonConfigInstance<>(PostmortalConfig.class, Path.of("./config/postmortal-config.json"));

    @ConfigEntry
    public boolean vortexParticle = true;
    @ConfigEntry public boolean sparkleParticle = true;
    @ConfigEntry public float sparkleTimer = 5.0F;
    @ConfigEntry public boolean sparkleExplosionParticle = true;
    @ConfigEntry public boolean totemParticle = true;
    @ConfigEntry public boolean shatteredParticle = false;
    @ConfigEntry public int shatteredAmount = 5;
    @ConfigEntry public boolean beamParticle = true;
    @ConfigEntry public float beamTime = 2.0F;
    @ConfigEntry public boolean trailParticle = true;
    @ConfigEntry public float trailTime = 7.5F;
    @ConfigEntry public boolean defaultParticles = true;
    @ConfigEntry public float defaultTimer = 1.5F;
    @ConfigEntry public boolean modEnabled = true;
    @ConfigEntry public boolean spamLogs = false;

    public static Screen makeScreen(Screen parent) {
        return YetAnotherConfigLib.create(INSTANCE, (defaults, config, builder) -> {
            //Particles category
            var particlesCategoryBuilder = ConfigCategory.createBuilder()
                    .name(Text.translatable("postmortalparticles.category.particle"));

            //Vortex particle group
            var vortexGroup = OptionGroup.createBuilder()
                    .name(Text.translatable("postmortalparticles.vortex.group"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("postmortalparticles.vortex.group.tooltip"))
                            .build());
            var vortex = Option.<Boolean>createBuilder()
                    .name(Text.translatable("postmortalparticles.vortex.enabled"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("postmortalparticles.vortex.enabled.tooltip"))
                            .build())
                    .binding(
                            defaults.vortexParticle,
                            () -> config.vortexParticle,
                            val -> config.vortexParticle = val
                    )
                    .customController(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            vortexGroup.option(vortex);
            particlesCategoryBuilder.group(vortexGroup.build());

            //Sparkle particle group
            var sparkleGroup = OptionGroup.createBuilder()
                    .name(Text.translatable("postmortalparticles.sparkle.group"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("postmortalparticles.sparkle.group.tooltip"))
                            .build());
            var sparkle = Option.<Boolean>createBuilder()
                    .name(Text.translatable("postmortalparticles.sparkle.enabled"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("postmortalparticles.sparkle.enabled.tooltip"))
                            .build())
                    .binding(
                            defaults.sparkleParticle,
                            () -> config.sparkleParticle,
                            val -> config.sparkleParticle = val
                    )
                    .customController(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var sparkleSlider = Option.<Float>createBuilder()
                    .name(Text.translatable("postmortalparticles.sparkle.time"))
                            .description(OptionDescription.createBuilder()
                                    .text(Text.translatable("postmortalparticles.sparkle.time.tooltip"))
                                    .build())
                    .binding(
                            defaults.sparkleTimer,
                            () -> config.sparkleTimer,
                            val -> config.sparkleTimer = val
                    )
                    .customController(floatOption -> new <Number>FloatSliderController(floatOption, 0, 30, 0.1F))
                    .build();
            sparkleGroup.option(sparkle);
            sparkleGroup.option(sparkleSlider);
            particlesCategoryBuilder.group(sparkleGroup.build());

            //Sparkle explosion group
            var sparkleExplosionGroup = OptionGroup.createBuilder()
                    .name(Text.translatable("postmortalparticles.explosion.group"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("postmortalparticles.explosion.group.tooltip"))
                            .build());
            var sparkleExplosion = Option.<Boolean>createBuilder()
                    .name(Text.translatable("postmortalparticles.explosion.enabled"))
                            .description(OptionDescription.createBuilder()
                                    .text(Text.translatable("postmortalparticles.explosion.enabled.tooltip"))
                                    .build())
                    .binding(
                            defaults.sparkleExplosionParticle,
                            () -> config.sparkleExplosionParticle,
                            val -> config.sparkleExplosionParticle = val
                    )
                    .customController(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            sparkleExplosionGroup.option(sparkleExplosion);
            particlesCategoryBuilder.group(sparkleExplosionGroup.build());

            //Totem particle group
            var totemGroup = OptionGroup.createBuilder()
                    .name(Text.translatable("postmortalparticles.totem.group"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("postmortalparticles.totem.group.tooltip"))
                            .build());
            var totemParticle = Option.<Boolean>createBuilder()
                    .name(Text.translatable("postmortalparticles.totem.enabled"))
                            .description(OptionDescription.createBuilder()
                                    .text(Text.translatable("postmortalparticles.totem.enabled.tooltip"))
                                    .build())
                    .binding(
                            defaults.totemParticle,
                            () -> config.totemParticle,
                            val -> config.totemParticle = val
                    )
                    .customController(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var shatteredParticle = Option.<Boolean>createBuilder()
                    .name(Text.translatable("postmortalparticles.shattered.enabled"))
                            .description(OptionDescription.createBuilder()
                                    .text(Text.translatable("postmortalparticles.shattered.enabled.tooltip"))
                                    .build())
                    .binding(
                            defaults.shatteredParticle,
                            () -> config.shatteredParticle,
                            val -> config.shatteredParticle = val
                    )
                    .customController(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var shatteredAmount = Option.<Integer>createBuilder()
                    .name(Text.translatable("postmortalparticles.shattered.amount"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("postmortalparticles.shattered.amount.tooltip"))
                            .build())
                    .binding(
                            defaults.shatteredAmount,
                            () -> config.shatteredAmount,
                            val -> config.shatteredAmount = val
                    )
                    .customController(integerOption -> new <Number>IntegerSliderController(integerOption, 0, 30, 1))
                    .build();
            totemGroup.option(totemParticle);
            totemGroup.option(shatteredParticle);
            totemGroup.option(shatteredAmount);
            particlesCategoryBuilder.group(totemGroup.build());

            //Beam particles group
            var beamGroup = OptionGroup.createBuilder()
                    .name(Text.translatable("postmortalparticles.beam.group"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("postmortalparticles.beam.group.tooltip"))
                            .build());
            var beamParticle = Option.<Boolean>createBuilder()
                    .name(Text.translatable("postmortalparticles.beam.enabled"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("postmortalparticles.beam.enabled.tooltip"))
                            .build())
                    .binding(
                            defaults.beamParticle,
                            () -> config.beamParticle,
                            val -> config.beamParticle = val
                    )
                    .customController(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var beamTime = Option.<Float>createBuilder()
                    .name(Text.translatable("postmortalparticles.beam.time"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("postmortalparticles.beam.time.tooltip"))
                            .build())
                    .binding(
                            defaults.beamTime,
                            () -> config.beamTime,
                            val -> config.beamTime = val
                    )
                    .customController(floatOption -> new <Number>FloatSliderController(floatOption, 0, 30, 0.1F))
                    .build();
            beamGroup.option(beamParticle);
            beamGroup.option(beamTime);
            particlesCategoryBuilder.group(beamGroup.build());

            //Trail particles group
            var trailGroup = OptionGroup.createBuilder()
                    .name(Text.translatable("postmortalparticles.trail.group"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("postmortalparticles.trail.group.tooltip"))
                            .build());
            var trailParticle = Option.<Boolean>createBuilder()
                    .name(Text.translatable("postmortalparticles.trail.enabled"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("postmortalparticles.trail.enabled.tooltip"))
                            .build())
                    .binding(
                            defaults.trailParticle,
                            () -> config.trailParticle,
                            val -> config.trailParticle = val
                    )
                    .customController(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var trailTime = Option.<Float>createBuilder()
                    .name(Text.translatable("postmortalparticles.trail.time"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("postmortalparticles.trail.time.tooltip"))
                            .build())
                    .binding(
                            defaults.trailTime,
                            () -> config.trailTime,
                            val -> config.trailTime = val
                    )
                    .customController(floatOption -> new <Number>FloatSliderController(floatOption, 0, 30, 0.1F))
                    .build();
            trailGroup.option(trailParticle);
            trailGroup.option(trailTime);
            particlesCategoryBuilder.group(trailGroup.build());

            //Default particles group
            var defaultGroup = OptionGroup.createBuilder()
                    .name(Text.translatable("postmortalparticles.default.group"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("postmortalparticles.default.group.tooltip"))
                            .build());
            var defaultParticles = Option.<Boolean>createBuilder()
                    .name(Text.translatable("postmortalparticles.default.enabled"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("postmortalparticles.default.enabled.tooltip"))
                            .build())
                    .binding(
                            defaults.defaultParticles,
                            () -> config.defaultParticles,
                            val -> config.defaultParticles = val
                    )
                    .customController(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var defaultSlider = Option.<Float>createBuilder()
                    .name(Text.translatable("postmortalparticles.default.time"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("postmortalparticles.default.time.tooltip"))
                            .build())
                    .binding(
                            defaults.defaultTimer,
                            () -> config.defaultTimer,
                            val -> config.defaultTimer = val
                    )
                    .customController(floatOption -> new <Number>FloatSliderController(floatOption, 0, 30, 0.1F))
                    .build();
            defaultGroup.option(defaultParticles);
            defaultGroup.option(defaultSlider);
            particlesCategoryBuilder.group(defaultGroup.build());



            //Other category
            var otherCategoryBuilder = ConfigCategory.createBuilder()
                    .name(Text.translatable("postmortalparticles.category.other"));

            //Other group
            var otherGroup = OptionGroup.createBuilder()
                    .name(Text.translatable("postmortalparticles.other.group"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("postmortalparticles.other.group.tooltip"))
                            .build());
            var modEnabled = Option.<Boolean>createBuilder()
                    .name(Text.translatable("postmortalparticles.other.modenabled"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("postmortalparticles.other.modenabled.tooltip"))
                            .build())
                    .binding(
                            defaults.modEnabled,
                            () -> config.modEnabled,
                            val -> config.modEnabled = val
                    )
                    .customController(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var spamLogs = Option.<Boolean>createBuilder()
                    .name(Text.translatable("postmortalparticles.other.spamlogs"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("postmortalparticles.other.spamlogs.tooltip"))
                            .build())
                    .binding(
                            defaults.spamLogs,
                            () -> config.spamLogs,
                            val -> config.spamLogs = val
                    )
                    .customController(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            otherGroup.option(modEnabled);
            otherGroup.option(spamLogs);
            otherCategoryBuilder.group(otherGroup.build());

            return builder
                    .title(Text.translatable("postmortalparticles.title"))
                    .category(particlesCategoryBuilder.build())
                    .category(otherCategoryBuilder.build());
        }).generateScreen(parent);
    }
}