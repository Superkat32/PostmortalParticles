package net.superkat;

import dev.isxander.yacl.api.ConfigCategory;
import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.api.OptionGroup;
import dev.isxander.yacl.api.YetAnotherConfigLib;
import dev.isxander.yacl.config.ConfigEntry;
import dev.isxander.yacl.config.ConfigInstance;
import dev.isxander.yacl.config.GsonConfigInstance;
import dev.isxander.yacl.gui.controllers.BooleanController;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.nio.file.Path;

public class PostmortalConfig {
    public static final ConfigInstance<PostmortalConfig> INSTANCE = new GsonConfigInstance<>(PostmortalConfig.class, Path.of("./config/postmortal-config.json"));

    @ConfigEntry public boolean vortexParticle = true;

    public static Screen makeScreen(Screen parent) {
        return YetAnotherConfigLib.create(INSTANCE, (defaults, config, builder) -> {
            var particlesCategoryBuilder = ConfigCategory.createBuilder()
                    .name(Text.literal("Particles"));
            var vortexGroup = OptionGroup.createBuilder()
                    .name(Text.literal("Vortex"))
                    .tooltip(Text.literal("All config options related to the vortex particle"));
            var vortex = Option.createBuilder(boolean.class)
                    .name(Text.literal("Vortex Particle"))
                    .tooltip(Text.literal("Should the vortex particle be shown"))
                    .binding(
                            defaults.vortexParticle,
                            () -> config.vortexParticle,
                            val -> config.vortexParticle = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            vortexGroup.option(vortex);
            particlesCategoryBuilder.group(vortexGroup.build());

            return builder
                    .title(Text.literal("Postmortal Particles Config"))
                    .category(particlesCategoryBuilder.build());
        }).generateScreen(parent);
    }
}