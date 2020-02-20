package com.comze.sanman00.expotions.util;

import com.comze.sanman00.expotions.Main;

public class ModUtil {
    public static final String EXPLOSIVE_POTION_NAME = "potion_explosive";
    public static final String SPICY_EXPLOSIVE_POTION_NAME = "potion_explosive_spicy";
    public static final String THROWABLE_EXPLOSIVE_POTION_NAME = "potion_explosive_throwable";
    public static final String SPICY_THROWABLE_EXPLOSIVE_POTION_NAME = "potion_explosive_throwable_spicy";

    private ModUtil() {
        
    }
    public static String qualify(String name) {
        return qualify(Main.MOD_ID, name);
    }

    public static String qualify(String qualifier, String name) {
        return qualifier + ":" + name;
    }
}
