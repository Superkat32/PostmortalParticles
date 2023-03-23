package net.superkat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl.api.YetAnotherConfigLib;
import net.minecraft.text.Text;

public class ModMenuInter implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> GuiTest::makeScreen;
    }
}
