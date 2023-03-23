package net.superkat;

import dev.isxander.yacl.api.*;
import dev.isxander.yacl.gui.RequireRestartScreen;
import dev.isxander.yacl.gui.controllers.ActionController;
import dev.isxander.yacl.gui.controllers.BooleanController;
import dev.isxander.yacl.gui.controllers.ColorController;
import dev.isxander.yacl.gui.controllers.TickBoxController;
import dev.isxander.yacl.gui.controllers.cycling.EnumController;
import dev.isxander.yacl.gui.controllers.slider.DoubleSliderController;
import dev.isxander.yacl.gui.controllers.slider.FloatSliderController;
import dev.isxander.yacl.gui.controllers.slider.IntegerSliderController;
import dev.isxander.yacl.gui.controllers.slider.LongSliderController;
import dev.isxander.yacl.gui.controllers.string.StringController;
import dev.isxander.yacl.gui.controllers.string.number.DoubleFieldController;
import dev.isxander.yacl.gui.controllers.string.number.FloatFieldController;
import dev.isxander.yacl.gui.controllers.string.number.IntegerFieldController;
import dev.isxander.yacl.gui.controllers.string.number.LongFieldController;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.awt.*;

public class GuiTest {
    public static Screen getModConfigScreenFactory(Screen parent) {
        return YetAnotherConfigLib.create(ExampleConfig.INSTANCE, (defaults, config, builder) -> builder
                        .title(Text.literal("Test Suites"))
                        .category(ConfigCategory.createBuilder()
                                .name(Text.literal("Suites"))
                                .option(ButtonOption.createBuilder()
                                        .name(Text.literal("Full Test Suite"))
                                        .controller(ActionController::new)
//                                        .action((screen, opt) -> Minecraft.getInstance().setScreen(getFullTestSuite(screen)))
                                        .build())
                                .group(OptionGroup.createBuilder()
                                        .name(Text.literal("Wiki"))
                                        .option(ButtonOption.createBuilder()
                                                .name(Text.literal("Get Started"))
                                                .controller(ActionController::new)
//                                                .action((screen, opt) -> Minecraft.getInstance().setScreen(getWikiGetStarted(screen)))
                                                .build())
                                        .build())
                                .build())
                )
                .generateScreen(parent);
    }

    private static Screen getFullTestSuite(Screen parent) {
        return YetAnotherConfigLib.create(ExampleConfig.INSTANCE, (defaults, config, builder) -> builder
                        .title(Text.literal("Test GUI"))
                        .category(ConfigCategory.createBuilder()
                                .name(Text.literal("Control Examples"))
                                .tooltip(Text.literal("Example Category Description"))
                                .group(OptionGroup.createBuilder()
                                        .name(Text.literal("Boolean Controllers"))
                                        .tooltip(Text.literal("Test!"))
                                        .collapsed(true)
                                        .option(Option.createBuilder(boolean.class)
                                                .name(Text.literal("Boolean Toggle"))
                                                .tooltip(value -> Text.literal("A simple toggle button that contains the value '" + value + "'"))
                                                .binding(
                                                        defaults.booleanToggle,
                                                        () -> config.booleanToggle,
                                                        (value) -> config.booleanToggle = value
                                                )
                                                .controller(BooleanController::new)
                                                .flag(OptionFlag.GAME_RESTART)
                                                .build())
                                        .option(Option.createBuilder(boolean.class)
                                                .name(Text.literal("Custom Boolean Toggle"))
                                                .tooltip(Text.literal("You can customize these controllers like this!"))
                                                .binding(
                                                        defaults.customBooleanToggle,
                                                        () -> config.customBooleanToggle,
                                                        (value) -> config.customBooleanToggle = value
                                                )
                                                .controller(opt -> new BooleanController(opt, state -> state ? Text.literal("Amazing") : Text.literal("Not Amazing"), true))
                                                .build())
                                        .option(Option.createBuilder(boolean.class)
                                                .name(Text.literal("Tick Box"))
                                                .tooltip(Text.literal("There are even alternate methods of displaying the same data type!"))
                                                .binding(
                                                        defaults.tickbox,
                                                        () -> config.tickbox,
                                                        (value) -> config.tickbox = value
                                                )
                                                .controller(TickBoxController::new)
                                                .build())
                                        .build())
                                .group(OptionGroup.createBuilder()
                                        .name(Text.literal("Slider Controllers"))
                                        .option(Option.createBuilder(int.class)
                                                .name(Text.literal("Int Slider"))
                                                .instant(true)
                                                .binding(
                                                        defaults.intSlider,
                                                        () -> config.intSlider,
                                                        value -> config.intSlider = value

                                                )
                                                .controller(opt -> new IntegerSliderController(opt, 0, 3, 1))
                                                .build())
                                        .option(Option.createBuilder(double.class)
                                                .name(Text.literal("Double Slider"))
                                                .binding(
                                                        defaults.doubleSlider,
                                                        () -> config.doubleSlider,
                                                        (value) -> config.doubleSlider = value
                                                )
                                                .controller(opt -> new DoubleSliderController(opt, 0, 3, 0.05))
                                                .build())
                                        .option(Option.createBuilder(float.class)
                                                .name(Text.literal("Float Slider"))
                                                .binding(
                                                        defaults.floatSlider,
                                                        () -> config.floatSlider,
                                                        (value) -> config.floatSlider = value
                                                )
                                                .controller(opt -> new FloatSliderController(opt, 0, 3, 0.1f))
                                                .build())
                                        .option(Option.createBuilder(long.class)
                                                .name(Text.literal("Long Slider"))
                                                .binding(
                                                        defaults.longSlider,
                                                        () -> config.longSlider,
                                                        (value) -> config.longSlider = value
                                                )
                                                .controller(opt -> new LongSliderController(opt, 0, 1_000_000, 100))
                                                .build())
                                        .build())
                                .group(OptionGroup.createBuilder()
                                        .name(Text.literal("Input Field Controllers"))
                                        .option(Option.createBuilder(String.class)
                                                .name(Text.literal("Component Option"))
                                                .binding(
                                                        defaults.textField,
                                                        () -> config.textField,
                                                        value -> config.textField = value
                                                )
                                                .controller(StringController::new)
                                                .build())
                                        .option(Option.createBuilder(Color.class)
                                                .name(Text.literal("Color Option"))
                                                .binding(
                                                        defaults.colorOption,
                                                        () -> config.colorOption,
                                                        value -> config.colorOption = value
                                                )
                                                .controller(ColorController::new)
                                                .build())
                                        .build())
                                .group(OptionGroup.createBuilder()
                                        .name(Text.literal("Number Fields"))
                                        .option(Option.createBuilder(double.class)
                                                .name(Text.literal("Double Field"))
                                                .binding(
                                                        defaults.doubleField,
                                                        () -> config.doubleField,
                                                        value -> config.doubleField = value
                                                )
                                                .controller(DoubleFieldController::new)
                                                .build())
                                        .option(Option.createBuilder(float.class)
                                                .name(Text.literal("Float Field"))
                                                .binding(
                                                        defaults.floatField,
                                                        () -> config.floatField,
                                                        value -> config.floatField = value
                                                )
                                                .controller(FloatFieldController::new)
                                                .build())
                                        .option(Option.createBuilder(int.class)
                                                .name(Text.literal("Integer Field"))
                                                .binding(
                                                        defaults.intField,
                                                        () -> config.intField,
                                                        value -> config.intField = value
                                                )
                                                .controller(IntegerFieldController::new)
                                                .build())
                                        .option(Option.createBuilder(long.class)
                                                .name(Text.literal("Long Field"))
                                                .binding(
                                                        defaults.longField,
                                                        () -> config.longField,
                                                        value -> config.longField = value
                                                )
                                                .controller(LongFieldController::new)
                                                .build())
                                        .build())
                                .group(OptionGroup.createBuilder()
                                        .name(Text.literal("Enum Controllers"))
                                        .option(Option.createBuilder(ExampleConfig.Alphabet.class)
                                                .name(Text.literal("Enum Cycler"))
                                                .binding(
                                                        defaults.enumOption,
                                                        () -> config.enumOption,
                                                        (value) -> config.enumOption = value
                                                )
                                                .controller(EnumController::new)
                                                .build())
                                        .build())
                                .group(OptionGroup.createBuilder()
                                        .name(Text.literal("Options that aren't really options"))
                                        .option(ButtonOption.createBuilder()
                                                .name(Text.literal("Button \"Option\""))
//                                                .action((screen, opt) -> SystemToast.add(Minecraft.getInstance().getToasts(), SystemToast.SystemToastIds.TUTORIAL_HINT, Text.literal("Button Pressed"), Text.literal("Button option was invoked!")))
                                                .controller(ActionController::new)
                                                .build())
//                                        .option(LabelOption.create(
//                                                Component.empty()
//                                                        .append(Text.literal("a").withStyle(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("a")))))
//                                                        .append(Text.literal("b").withStyle(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("b")))))
//                                                        .append(Text.literal("c c c c c c c c c c c c c c c c c c c c c c c c c c c c c c c c c c c c c c").withStyle(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("c")))))
//                                                        .append(Text.literal("e").withStyle(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("e")))))
//                                                        .withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://isxander.dev"))))
//                                        )
                                        .build())
//                                .group(OptionGroup.createBuilder()
//                                        .name(Text.literal("Minecraft Bindings"))
//                                        .tooltip(Text.literal("YACL can also bind Minecraft options!"))
//                                        .option(Option.createBuilder(boolean.class)
//                                                .name(Text.literal("Minecraft AutoJump"))
//                                                .tooltip(Text.literal("You can even bind minecraft options!"))
//                                                .binding(Binding.minecraft(Minecraft.getInstance().options.autoJump()))
//                                                .controller(TickBoxController::new)
//                                                .build())
//                                        .option(Option.createBuilder(GraphicsStatus.class)
//                                                .name(Text.literal("Minecraft Graphics Mode"))
//                                                .binding(Binding.minecraft(Minecraft.getInstance().options.graphicsMode()))
//                                                .controller(EnumController::new)
//                                                .build())
//                                        .build())
                                .build())
                        .category(ConfigCategory.createBuilder()
                                .name(Text.literal("List Test"))
                                .group(ListOption.createBuilder(String.class)
                                        .name(Text.literal("String List"))
                                        .binding(
                                                defaults.stringList,
                                                () -> config.stringList,
                                                val -> config.stringList = val
                                        )
                                        .controller(StringController::new)
                                        .initial("")
                                        .build())
                                .group(ListOption.createBuilder(Integer.class)
                                        .name(Text.literal("Slider List"))
                                        .binding(
                                                defaults.intList,
                                                () -> config.intList,
                                                val -> config.intList = val
                                        )
                                        .controller(opt -> new IntegerSliderController(opt, 0, 10, 1))
                                        .initial(0)
                                        .available(false)
                                        .build())
//                                .group(ListOption.createBuilder(Component.class)
//                                        .name(Text.literal("Useless Label List"))
//                                        .binding(Binding.immutable(List.of(Text.literal("It's quite impressive that literally every single controller works, without problem."))))
//                                        .controller(LabelController::new)
//                                        .initial(Text.literal("Initial label"))
//                                        .build())
                                .build())
                        .category(PlaceholderCategory.createBuilder()
                                .name(Text.literal("Placeholder Category"))
                                .screen((client, yaclScreen) -> new RequireRestartScreen(yaclScreen))
                                .build())
                        .category(ConfigCategory.createBuilder()
                                .name(Text.literal("Group Test"))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Root Test"))
                                        .binding(
                                                defaults.groupTestRoot,
                                                () -> config.groupTestRoot,
                                                value -> config.groupTestRoot = value
                                        )
                                        .controller(TickBoxController::new)
                                        .build())
                                .group(OptionGroup.createBuilder()
                                        .name(Text.literal("First Group"))
                                        .option(Option.createBuilder(boolean.class)
                                                .name(Text.literal("First Group Test 1"))
                                                .binding(
                                                        defaults.groupTestFirstGroup,
                                                        () -> config.groupTestFirstGroup,
                                                        value -> config.groupTestFirstGroup = value
                                                )
                                                .controller(TickBoxController::new)
                                                .build())
                                        .option(Option.createBuilder(boolean.class)
                                                .name(Text.literal("First Group Test 2"))
                                                .binding(
                                                        defaults.groupTestFirstGroup2,
                                                        () -> config.groupTestFirstGroup2,
                                                        value -> config.groupTestFirstGroup2 = value
                                                )
                                                .controller(TickBoxController::new)
                                                .build())
                                        .build())
//                                .group(OptionGroup.createBuilder()
//                                        .name(Component.empty())
//                                        .option(Option.createBuilder(boolean.class)
//                                                .name(Text.literal("Second Group Test"))
//                                                .binding(
//                                                        defaults.groupTestSecondGroup,
//                                                        () -> config.groupTestSecondGroup,
//                                                        value -> config.groupTestSecondGroup = value
//                                                )
//                                                .controller(TickBoxController::new)
//                                                .build())
//                                        .build())
                                .build())
                        .category(ConfigCategory.createBuilder()
                                .name(Text.literal("Scroll Test"))
                                .option(Option.createBuilder(int.class)
                                        .name(Text.literal("Int Slider that is cut off because the slider"))
                                        .binding(
                                                defaults.scrollingSlider,
                                                () -> config.scrollingSlider,
                                                (value) -> config.scrollingSlider = value
                                        )
                                        .controller(opt -> new IntegerSliderController(opt, 0, 10, 1))
                                        .build())
                                .option(ButtonOption.createBuilder()
                                        .name(Text.literal("Option"))
                                        .action((screen, opt) -> {})
                                        .controller(ActionController::new)
                                        .build())
                                .option(ButtonOption.createBuilder()
                                        .name(Text.literal("Option"))
                                        .action((screen, opt) -> {})
                                        .controller(ActionController::new)
                                        .build())
                                .option(ButtonOption.createBuilder()
                                        .name(Text.literal("Option"))
                                        .action((screen, opt) -> {})
                                        .controller(ActionController::new)
                                        .build())
                                .option(ButtonOption.createBuilder()
                                        .name(Text.literal("Option"))
                                        .action((screen, opt) -> {})
                                        .controller(ActionController::new)
                                        .build())
                                .option(ButtonOption.createBuilder()
                                        .name(Text.literal("Option"))
                                        .action((screen, opt) -> {})
                                        .controller(ActionController::new)
                                        .build())
                                .option(ButtonOption.createBuilder()
                                        .name(Text.literal("Option"))
                                        .action((screen, opt) -> {})
                                        .controller(ActionController::new)
                                        .build())
                                .option(ButtonOption.createBuilder()
                                        .name(Text.literal("Option"))
                                        .action((screen, opt) -> {})
                                        .controller(ActionController::new)
                                        .build())
                                .option(ButtonOption.createBuilder()
                                        .name(Text.literal("Option"))
                                        .action((screen, opt) -> {})
                                        .controller(ActionController::new)
                                        .build())
                                .option(ButtonOption.createBuilder()
                                        .name(Text.literal("Option"))
                                        .action((screen, opt) -> {})
                                        .controller(ActionController::new)
                                        .build())
                                .option(ButtonOption.createBuilder()
                                        .name(Text.literal("Option"))
                                        .action((screen, opt) -> {})
                                        .controller(ActionController::new)
                                        .build())
                                .option(ButtonOption.createBuilder()
                                        .name(Text.literal("Option"))
                                        .action((screen, opt) -> {})
                                        .controller(ActionController::new)
                                        .build())
                                .option(ButtonOption.createBuilder()
                                        .name(Text.literal("Option"))
                                        .action((screen, opt) -> {})
                                        .controller(ActionController::new)
                                        .build())
                                .option(ButtonOption.createBuilder()
                                        .name(Text.literal("Option"))
                                        .action((screen, opt) -> {})
                                        .controller(ActionController::new)
                                        .build())
                                .build())
                        .category(ConfigCategory.createBuilder()
                                .name(Text.literal("Category Test"))
                                .build())
                        .category(ConfigCategory.createBuilder()
                                .name(Text.literal("Category Test"))
                                .build())
                        .category(ConfigCategory.createBuilder()
                                .name(Text.literal("Category Test"))
                                .build())
                        .category(ConfigCategory.createBuilder()
                                .name(Text.literal("Category Test"))
                                .build())
                        .category(ConfigCategory.createBuilder()
                                .name(Text.literal("Category Test"))
                                .build())
                        .category(ConfigCategory.createBuilder()
                                .name(Text.literal("Category Test"))
                                .build())
                        .category(ConfigCategory.createBuilder()
                                .name(Text.literal("Category Test"))
                                .build())
                        .category(ConfigCategory.createBuilder()
                                .name(Text.literal("Category Test"))
                                .build())
                        .save(() -> {
//                            Minecraft.getInstance().options.save();
                            ExampleConfig.INSTANCE.save();
                        })
                )
                .generateScreen(parent);
    }

    private static boolean myBooleanOption = true;

    private static Screen getWikiGetStarted(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.literal("Used for narration. Could be used to render a title in the future."))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Name of the category"))
                        .tooltip(Text.literal("This Component will appear as a tooltip when you hover or focus the button with Tab. There is no need to add \n to wrap as YACL will do it for you."))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Name of the group"))
                                .tooltip(Text.literal("This Component will appear when you hover over the name or focus on the collapse button with Tab."))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Boolean Option"))
                                        .tooltip(Text.literal("This Component will appear as a tooltip when you hover over the option."))
                                        .binding(true, () -> myBooleanOption, newVal -> myBooleanOption = newVal)
                                        .controller(TickBoxController::new)
                                        .build())
                                .build())
                        .build())
                .build()
                .generateScreen(parent);
    }
}