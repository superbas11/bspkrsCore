package bspkrs.testmod;

import static bspkrs.util.config.Configuration.CATEGORY_GENERAL;

import java.io.File;
import java.util.Arrays;
import java.util.regex.Pattern;

import bspkrs.util.config.Configuration;
import bspkrs.util.config.Property;

public class TestModSimpleConfig
{
    private final static boolean   enabledDefault                   = true;
    public static boolean          enabled                          = enabledDefault;
    private final static boolean[] fixedBooleanListDefault          = new boolean[] { true, true, true, false, false, false };
    public static boolean[]        fixedBooleanList                 = Arrays.copyOf(fixedBooleanListDefault, fixedBooleanListDefault.length);
    private final static String[]  variablePatternStringListDefault = new String[] { "minecraft:stone_pickaxe,1,0", "minecraft:stone_shovel,1,0",
                                                                    "minecraft:stone_sword,1,0,{RepairCost:2,display:{Name:\"Rapier\",},}",
                                                                    "minecraft:stone_axe,1,0", "minecraft:apple,16,0", "minecraft:torch,16,0",
                                                                    "minecraft:enchanted_book,1,0,{StoredEnchantments:[0:{id:3s,lvl:4s,},],}",
                                                                    "minecraft:enchanted_book,1,0,{StoredEnchantments:[0:{id:51s,lvl:1s,},],}",
                                                                    "minecraft:anvil,1,0" };
    private final static Pattern   variablePatternStringListPattern = Pattern.compile("[A-Za-z]+:[A-Za-z_]+,[0-9]+,[0-9]+($|,\\{.+?\\}$)");
    public static String[]         variablePatternStringList        = Arrays.copyOf(variablePatternStringListDefault, variablePatternStringListDefault.length);
    private final static String    regularStringDefault             = "This is a regular string, anything goes.";
    public static String           regularString                    = regularStringDefault;
    private final static String    patternStringDefault             = "Only, comma, separated, words, can, be, entered, in, this, box";
    public static String           patternString                    = patternStringDefault;
    private final static Pattern   patternStringPattern             = Pattern.compile("([A-Za-z]+((,){1}( )*|$))+?");
    private final static String    selectStringDefault              = "This";
    public static String           selectString                     = selectStringDefault;
    private final static String[]  selectStringValues               = new String[] { "This", "property", "cycles", "through", "a", "list", "of", "valid", "choices." };
    private final static int       unboundedIntegerDefault          = 25;
    public static int              unboundedInteger                 = unboundedIntegerDefault;
    private final static int       boundedIntegerDefault            = 100;
    public static int              boundedInteger                   = boundedIntegerDefault;
    private final static float     unboundedFloatDefault            = 25.0F;
    public static float            unboundedFloat                   = unboundedFloatDefault;
    private final static float     boundedFloatDefault              = 100.0F;
    public static float            boundedFloat                     = boundedFloatDefault;
    
    private static Configuration   config;
    
    public static Configuration getConfig()
    {
        return config;
    }
    
    public static void initConfig(File file)
    {
        config = new Configuration(file);
        syncConfig();
    }
    
    public static void syncConfig()
    {
        Property temp;
        
        config.load();
        
        temp = config.get(CATEGORY_GENERAL, "enabled", enabledDefault, "Enables or disables this mod.");
        //temp.setLanguageKey("bspkrs.testmod.configgui." + temp.getName());
        enabled = temp.getBoolean();
        
        temp = config.get(CATEGORY_GENERAL, "fixedBooleanList", fixedBooleanListDefault, "", true);
        //temp.setLanguageKey("bspkrs.testmod.configgui." + temp.getName());
        fixedBooleanList = temp.getBooleanList();
        
        temp = config.get(CATEGORY_GENERAL, "variablePatternStringList", variablePatternStringListDefault, "", false, variablePatternStringListPattern);
        //temp.setLanguageKey("bspkrs.testmod.configgui." + temp.getName());
        variablePatternStringList = temp.getStringList();
        
        config.save();
    }
}
