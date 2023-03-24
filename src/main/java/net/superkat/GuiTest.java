package net.superkat;

import dev.isxander.yacl.api.*;
import dev.isxander.yacl.gui.controllers.ActionController;
import dev.isxander.yacl.gui.controllers.BooleanController;
import dev.isxander.yacl.gui.controllers.TickBoxController;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class GuiTest {

    public static void save() { /* save your config! */ }

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

    public static Screen getModConfigScreenFactory(Screen parent) {
        return YetAnotherConfigLib.create(ExampleConfig.INSTANCE, (defaults, config, builder) -> builder
                        .title(Text.literal("Wiki"))
                        .category(ConfigCategory.createBuilder()
//                                .name(Text.literal("Suites"))
//                                .option(ButtonOption.createBuilder()
//                                        .name(Text.literal("Full Test Suite"))
//                                        .controller(ActionController::new)
////                                        .action((screen, opt) -> Minecraft.getInstance().setScreen(getFullTestSuite(screen)))
//                                        .build())
//                                .group(OptionGroup.createBuilder()
                                    .name(Text.literal("Wiki"))
                                    .option(ButtonOption.createBuilder()
                                            .name(Text.literal("Get Started"))
                                            .controller(ActionController::new)
                                            .action((screen, opt) -> MinecraftClient.getInstance().setScreen(getWikiStarted((screen))))
//                                                .action((yaclScreen, buttonOption) -> MinecraftClient.getInstance().setScreen(getWikiStarted(yaclScreen)))
                                            .build())
//                                        .build())
                                .build())
                )
                .generateScreen(parent);
    }


    private static boolean myBooleanOption = true;
    private static Screen getWikiStarted(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.literal("Title"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("category"))
                        .tooltip(Text.literal("tooltip"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("group"))
                                .tooltip(Text.literal("group tooltip"))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Boolean Option"))
                                        .tooltip(Text.literal("Boolean tooltip"))
                                        .binding(true, () -> myBooleanOption, newVal -> myBooleanOption = newVal)
                                        .controller(TickBoxController::new)
                                        .build()
                                ).build()
                        ).build()
                ).build()
                .generateScreen(parent);
    }

}