package net.superkat;

import dev.isxander.yacl.api.*;
import dev.isxander.yacl.gui.controllers.TickBoxController;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class GuiTest {
    private boolean myBooleanOption = true;
    public Screen createGui(Screen parent) {
         YetAnotherConfigLib.createBuilder()
            .title(Text.literal("Used for narration. Could be used to render a title in the future."))
            .category(ConfigCategory.createBuilder()
                    .name(Text.literal("Name of the category"))
                    .tooltip(Text.literal("This text will appear as a tooltip when you hover or focus the button with Tab. There is no need to add \n to wrap as YACL will do it for you."))
                    .group(OptionGroup.createBuilder()
                            .name(Text.literal("Name of the group"))
                            .tooltip(Text.literal("This text will appear when you hover over the name or focus on the collapse button with Tab."))
                            .option(Option.createBuilder(boolean.class)
                                    .name(Text.literal("Boolean Option"))
                                    .tooltip(Text.literal("This text will appear as a tooltip when you hover over the option."))
                                    .binding(true, () -> this.myBooleanOption, newVal -> this.myBooleanOption = newVal)
                                    .controller(TickBoxController::new)
                                    .build())
                            .build())
                    .build())
            .build()
            .generateScreen(parent);
}