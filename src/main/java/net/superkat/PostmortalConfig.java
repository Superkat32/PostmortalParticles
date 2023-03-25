package net.superkat;

import dev.isxander.yacl.api.ConfigCategory;
import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.api.OptionGroup;
import dev.isxander.yacl.api.YetAnotherConfigLib;
import dev.isxander.yacl.config.ConfigEntry;
import dev.isxander.yacl.config.ConfigInstance;
import dev.isxander.yacl.config.GsonConfigInstance;
import dev.isxander.yacl.gui.controllers.BooleanController;
import dev.isxander.yacl.gui.controllers.slider.IntegerSliderController;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.nio.file.Path;

public class PostmortalConfig {
    public static final ConfigInstance<PostmortalConfig> INSTANCE = new GsonConfigInstance<>(PostmortalConfig.class, Path.of("./config/postmortal-config.json"));

    @ConfigEntry public boolean vortexParticle = true;
    @ConfigEntry public boolean sparkleParticle = true;
    @ConfigEntry public int sparkleTimer = 5;


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
            var sparkleSlider = Option.createBuilder(Integer.class)
                    .name(Text.translatable("postmortalparticles.sparkle.enabled"))
                    .tooltip(Text.translatable("postmortalparticles.sparkle.enabled.tooltip"))
                    .binding(
                            defaults.sparkleTimer,
                            () -> config.sparkleTimer,
                            val -> config.sparkleTimer = val
                    )
                    .controller(integerOption -> new <Number>IntegerSliderController(integerOption, 1, 60, 1))
                    .build();
            sparkleGroup.option(sparkle);
            sparkleGroup.option(sparkleSlider);
            particlesCategoryBuilder.group(sparkleGroup.build());


            return builder
                    .title(Text.translatable("postmortalparticles.title"))
                    .category(particlesCategoryBuilder.build());
        }).generateScreen(parent);
    }
}