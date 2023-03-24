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

    @ConfigEntry public boolean myBoolean = true;

    public static Screen makeScreen(Screen parent) {
        return YetAnotherConfigLib.create(INSTANCE, (defaults, config, builder) -> {
            var categoryBuilder = ConfigCategory.createBuilder()
                    .name(Text.literal("Particles"));
            var group = OptionGroup.createBuilder()
                    .name(Text.literal("Vortex"))
                    .tooltip(Text.literal("All config options related to the vortex particle"));
            var myBoolean = Option.createBuilder(boolean.class)
                    .name(Text.literal("Vortex Particle"))
                    .tooltip(Text.literal("Should the vortex particle be shown"))
                    .binding(
                            defaults.myBoolean,
                            () -> config.myBoolean,
                            val -> config.myBoolean = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            group.option(myBoolean);
            categoryBuilder.group(group.build());

            return builder
                    .title(Text.literal("Postmortal Particles Config"))
                    .category(categoryBuilder.build());
        }).generateScreen(parent);
    }
}