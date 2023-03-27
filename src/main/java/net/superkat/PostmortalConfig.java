package net.superkat;

import dev.isxander.yacl.api.ConfigCategory;
import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.api.OptionGroup;
import dev.isxander.yacl.api.YetAnotherConfigLib;
import dev.isxander.yacl.config.ConfigEntry;
import dev.isxander.yacl.config.ConfigInstance;
import dev.isxander.yacl.config.GsonConfigInstance;
import dev.isxander.yacl.gui.controllers.BooleanController;
import dev.isxander.yacl.gui.controllers.slider.FloatSliderController;
import dev.isxander.yacl.gui.controllers.slider.IntegerSliderController;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.nio.file.Path;

public class PostmortalConfig {
    public static final ConfigInstance<PostmortalConfig> INSTANCE = new GsonConfigInstance<>(PostmortalConfig.class, Path.of("./config/postmortal-config.json"));

    @ConfigEntry public boolean vortexParticle = true;
    @ConfigEntry public boolean sparkleParticle = true;
    @ConfigEntry public float sparkleTimer = 5.0F;
    @ConfigEntry public boolean sparkleExplosionParticle = true;
    @ConfigEntry public boolean totemParticle = true;
    @ConfigEntry public boolean shatteredParticle = false;
    @ConfigEntry public int shatteredAmount = 5;
    @ConfigEntry public boolean defaultParticles = true;
    @ConfigEntry public float defaultTimer = 1.5F;
    @ConfigEntry public boolean modEnabled = true;
    @ConfigEntry public boolean spamLogs = false;


    //TODO - Change all "Text.literal('string')" to work with the language file(e.g "Text.translatable('bleh.bleh.bleh")
    public static Screen makeScreen(Screen parent) {
        return YetAnotherConfigLib.create(INSTANCE, (defaults, config, builder) -> {
            //Particles category
            var particlesCategoryBuilder = ConfigCategory.createBuilder()
                    .name(Text.translatable("postmortalparticles.category.particle"));

            //Vortex particle group
            var vortexGroup = OptionGroup.createBuilder()
                    .name(Text.translatable("postmortalparticles.vortex.group"))
                    .tooltip(Text.translatable("postmortalparticles.vortex.group.tooltip"));
            var vortex = Option.createBuilder(boolean.class)
                    .name(Text.translatable("postmortalparticles.vortex.enabled"))
                    .tooltip(Text.translatable("postmortalparticles.vortex.enabled.tooltip"))
                    .binding(
                            defaults.vortexParticle,
                            () -> config.vortexParticle,
                            val -> config.vortexParticle = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            vortexGroup.option(vortex);
            particlesCategoryBuilder.group(vortexGroup.build());

            //Sparkle particle group
            var sparkleGroup = OptionGroup.createBuilder()
                    .name(Text.translatable("postmortalparticles.sparkle.group"))
                    .tooltip(Text.translatable("postmortalparticles.sparkle.group.tooltip"));
            var sparkle = Option.createBuilder(Boolean.class)
                    .name(Text.translatable("postmortalparticles.sparkle.enabled"))
                    .tooltip(Text.translatable("postmortalparticles.sparkle.enabled.tooltip"))
                    .binding(
                            defaults.sparkleParticle,
                            () -> config.sparkleParticle,
                            val -> config.sparkleParticle = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var sparkleSlider = Option.createBuilder(Float.class)
                    .name(Text.translatable("postmortalparticles.sparkle.time"))
                    .tooltip(Text.translatable("postmortalparticles.sparkle.time.tooltip"))
                    .binding(
                            defaults.sparkleTimer,
                            () -> config.sparkleTimer,
                            val -> config.sparkleTimer = val
                    )
                    .controller(floatOption -> new <Number>FloatSliderController(floatOption, 0, 30, 0.1F))
                    .build();
            sparkleGroup.option(sparkle);
            sparkleGroup.option(sparkleSlider);
            particlesCategoryBuilder.group(sparkleGroup.build());

            //Sparkle explosion group
            var sparkleExplosionGroup = OptionGroup.createBuilder()
                    .name(Text.translatable("postmortalparticles.explosion.group"))
                    .tooltip(Text.translatable("postmortalparticles.explosion.group.tooltip"));
            var sparkleExplosion = Option.createBuilder(boolean.class)
                    .name(Text.translatable("postmortalparticles.explosion.enabled"))
                    .tooltip(Text.translatable("postmortalparticles.explosion.enabled.tooltip"))
                    .binding(
                            defaults.sparkleExplosionParticle,
                            () -> config.sparkleExplosionParticle,
                            val -> config.sparkleExplosionParticle = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            sparkleExplosionGroup.option(sparkleExplosion);
            particlesCategoryBuilder.group(sparkleExplosionGroup.build());

            //Totem particle group
            var totemGroup = OptionGroup.createBuilder()
                    .name(Text.translatable("postmortalparticles.totem.group"))
                    .tooltip(Text.translatable("postmortalparticles.totem.group.tooltip"));
            var totemParticle = Option.createBuilder(boolean.class)
                    .name(Text.translatable("postmortalparticles.totem.enabled"))
                    .tooltip(Text.translatable("postmortalparticles.totem.enabled.tooltip"))
                    .binding(
                            defaults.totemParticle,
                            () -> config.totemParticle,
                            val -> config.totemParticle = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var shatteredParticle = Option.createBuilder(boolean.class)
                    .name(Text.translatable("postmortalparticles.shattered.enabled"))
                    .tooltip(Text.translatable("postmortalparticles.shattered.enabled.tooltip"))
                    .binding(
                            defaults.shatteredParticle,
                            () -> config.shatteredParticle,
                            val -> config.shatteredParticle = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var shatteredAmount = Option.createBuilder(Integer.class)
                    .name(Text.translatable("postmortalparticles.shattered.amount"))
                    .tooltip(Text.translatable("postmortalparticles.shattered.amount.tooltip"))
                    .binding(
                            defaults.shatteredAmount,
                            () -> config.shatteredAmount,
                            val -> config.shatteredAmount = val
                    )
                    .controller(integerOption -> new <Number>IntegerSliderController(integerOption, 0, 30, 1))
                    .build();
            totemGroup.option(totemParticle);
            totemGroup.option(shatteredParticle);
            totemGroup.option(shatteredAmount);
            particlesCategoryBuilder.group(totemGroup.build());

            //Default particles group
            var defaultGroup = OptionGroup.createBuilder()
                    .name(Text.translatable("postmortalparticles.default.group"))
                    .tooltip(Text.translatable("postmortalparticles.default.group.tooltip"));
            var defaultParticles = Option.createBuilder(boolean.class)
                    .name(Text.translatable("postmortalparticles.default.enabled"))
                    .tooltip(Text.translatable("postmortalparticles.default.enabled.tooltip"))
                    .binding(
                            defaults.defaultParticles,
                            () -> config.defaultParticles,
                            val -> config.defaultParticles = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var defaultSlider = Option.createBuilder(Float.class)
                    .name(Text.translatable("postmortalparticles.default.time"))
                    .tooltip(Text.translatable("postmortalparticles.default.time.tooltip"))
                    .binding(
                            defaults.defaultTimer,
                            () -> config.defaultTimer,
                            val -> config.defaultTimer = val
                    )
                    .controller(floatOption -> new <Number>FloatSliderController(floatOption, 0, 30, 0.1F))
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
                    .tooltip(Text.translatable("postmortalparticles.other.group.tooltip"));
            var modEnabled = Option.createBuilder(boolean.class)
                    .name(Text.translatable("postmortalparticles.other.modenabled"))
                    .tooltip(Text.translatable("postmortalparticles.other.modenabled.tooltip"))
                    .binding(
                            defaults.modEnabled,
                            () -> config.modEnabled,
                            val -> config.modEnabled = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var spamLogs = Option.createBuilder(boolean.class)
                    .name(Text.translatable("postmortalparticles.other.spamlogs"))
                    .tooltip(Text.translatable("postmortalparticles.other.spamlogs.tooltip"))
                    .binding(
                            defaults.spamLogs,
                            () -> config.spamLogs,
                            val -> config.spamLogs = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
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