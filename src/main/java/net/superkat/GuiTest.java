package net.superkat;

import dev.isxander.yacl.api.*;
import dev.isxander.yacl.config.ConfigEntry;
import dev.isxander.yacl.config.ConfigInstance;
import dev.isxander.yacl.config.GsonConfigInstance;
import dev.isxander.yacl.gui.controllers.ActionController;
import dev.isxander.yacl.gui.controllers.BooleanController;
import dev.isxander.yacl.gui.controllers.TickBoxController;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.nio.file.Path;

public class GuiTest {
    public static final ConfigInstance<GuiTest> INSTANCE = new GsonConfigInstance<>(GuiTest.class, Path.of("./config/yacl-test.json"));

    @ConfigEntry public boolean myBoolean = true;

//    public static void save() { /* save your config! */ }

//    public Screen createGui(Screen parent) {
//        YetAnotherConfigLib.createBuilder()
//                .title(Text.of("Mod Name"))
//                .category(ConfigCategory.createBuilder()
//                        .name(Text.of("My Category"))
//                        .tooltip(Text.of("This displays when you hover over a category button")) // optional
//                        .option(Option.createBuilder(boolean.class)
//                                .name(Text.of("My Boolean Option"))
//                                .tooltip(Text.of("This option displays the basic capabilities of YetAnotherConfigLib")) // optional
//                                .binding(
//                                        true, // default
//                                        () -> this.booleanToggle, // getter
//                                        newValue -> this.booleanToggle = newValue // setter
//                                )
//                                .controller(BooleanController::new)
//                                .build())
//                        .build())
//                .save(GuiTest::save)
//                .build()
//                .generateScreen(parent);
//    }

//    public static Screen getModConfigScreenFactory(Screen parent) {
//        return YetAnotherConfigLib.create(ExampleConfig.INSTANCE, (defaults, config, builder) -> builder
//                        .title(Text.literal("Wiki"))
//                        .category(ConfigCategory.createBuilder()
////                                .name(Text.literal("Suites"))
////                                .option(ButtonOption.createBuilder()
////                                        .name(Text.literal("Full Test Suite"))
////                                        .controller(ActionController::new)
//////                                        .action((screen, opt) -> Minecraft.getInstance().setScreen(getFullTestSuite(screen)))
////                                        .build())
////                                .group(OptionGroup.createBuilder()
//                                    .name(Text.literal("Wiki"))
//                                    .option(ButtonOption.createBuilder()
//                                            .name(Text.literal("Get Started"))
//                                            .controller(ActionController::new)
//                                            .action((screen, opt) -> MinecraftClient.getInstance().setScreen(getWikiStarted((screen))))
////                                                .action((yaclScreen, buttonOption) -> MinecraftClient.getInstance().setScreen(getWikiStarted(yaclScreen)))
//                                            .build())
////                                        .build())
//                                .build())
//                )
//                .generateScreen(parent);
//    }
    public static Screen makeScreen(Screen parent) {
        return YetAnotherConfigLib.create(INSTANCE, (defaults, config, builder) -> {
            var categoryBuilder = ConfigCategory.createBuilder()
                    .name(Text.literal("category"));

            var group = OptionGroup.createBuilder()
                    .name(Text.literal("group"))
                    .tooltip(Text.literal("group tooltip"));
            var myBoolean = Option.createBuilder(boolean.class)
                    .name(Text.literal("My Boolean"))
                    .tooltip(Text.literal("PLEASE work"))
                    .binding(
                            defaults.myBoolean,
                            () -> config.myBoolean,
                            val -> config.myBoolean = val
                    )
                    .controller(TickBoxController::new)
                    .build();
            group.option(myBoolean);
            categoryBuilder.group(group.build());

            return builder
                    .title(Text.literal("Title"))
                    .category(categoryBuilder.build());
        }).generateScreen(parent);
    }


//    private static boolean myBooleanOption = true;
//    private static Screen getWikiStarted(Screen parent) {
//        return YetAnotherConfigLib.createBuilder()
//                .title(Text.literal("Title"))
//                .category(ConfigCategory.createBuilder()
//                        .name(Text.literal("category"))
//                        .tooltip(Text.literal("tooltip"))
//                        .group(OptionGroup.createBuilder()
//                                .name(Text.literal("group"))
//                                .tooltip(Text.literal("group tooltip"))
//                                .option(Option.createBuilder(boolean.class)
//                                        .name(Text.literal("Boolean Option"))
//                                        .tooltip(Text.literal("Boolean tooltip"))
//                                        .binding()
//                                        .controller(TickBoxController::new)
//                                        .build()
//                                ).build()
//                        ).build()
//                ).build()
//                .generateScreen(parent);
//    }

}