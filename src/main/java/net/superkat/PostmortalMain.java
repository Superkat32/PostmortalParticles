package net.superkat;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostmortalMain implements ModInitializer {
	public static final String MOD_ID = "postmortalparticles";
	public static final Logger LOGGER = LoggerFactory.getLogger("postmortalparticles");

	@Override
	public void onInitialize() {

		LOGGER.info("Hello Fabric world!");
	}
}