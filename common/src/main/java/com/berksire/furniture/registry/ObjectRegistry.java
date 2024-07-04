package com.berksire.furniture.registry;

import com.berksire.furniture.Furniture;
import com.berksire.furniture.block.*;
import com.berksire.furniture.item.CanvasItem;
import com.berksire.furniture.item.PellsSpawnItem;
import com.berksire.furniture.item.TrashBagItem;
import com.berksire.furniture.util.FurnitureIdentifier;
import com.berksire.furniture.util.FurnitureUtil;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.berksire.furniture.block.StreetLanternBlock.LIT;

public class ObjectRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Furniture.MODID, Registries.ITEM);
    public static final Registrar<Item> ITEM_REGISTRAR = ITEMS.getRegistrar();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Furniture.MODID, Registries.BLOCK);
    public static final Registrar<Block> BLOCK_REGISTRAR = BLOCKS.getRegistrar();

    public static final Map<String, RegistrySupplier<Block>> SOFAS = new HashMap<>();
    public static final Map<String, RegistrySupplier<Block>> POUFFE = new HashMap<>();
    public static final Map<String, RegistrySupplier<Block>> LAMPS = new HashMap<>();
    public static final Map<String, RegistrySupplier<Block>> CURTAINS = new HashMap<>();
    public static final Map<String, RegistrySupplier<Block>> CABINETS = new HashMap<>();
    public static final Map<String, RegistrySupplier<Block>> GRANDFATHER_CLOCKS = new HashMap<>();
    public static final Map<String, RegistrySupplier<Block>> CLOCKS = new HashMap<>();
    public static final Map<String, RegistrySupplier<Block>> BENCHES = new HashMap<>();
    public static final Map<String, RegistrySupplier<Block>> MIRRORS = new HashMap<>();
    public static final RegistrySupplier<Block> GRAMOPHONE = registerWithItem("gramophone", () -> new GramophoneBlock(BlockBehaviour.Properties.copy(Blocks.JUKEBOX)));
    public static final RegistrySupplier<Block> TELESCOPE = registerWithItem("telescope", () -> new TelescopeBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistrySupplier<Block> COFFER = registerWithItem("coffer", () -> new CofferBlock(BlockBehaviour.Properties.copy(Blocks.RED_WOOL).pushReaction(PushReaction.DESTROY)));
    public static final RegistrySupplier<Block> EXPLORERS_BOX = registerWithItem("explorers_box", () -> new ExplorersBoxBlock(BlockBehaviour.Properties.copy(Blocks.CARTOGRAPHY_TABLE)));
    public static final RegistrySupplier<Block> CASH_REGISTER = registerWithItem("cash_register", () -> new CashRegisterBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK)));
    public static final RegistrySupplier<Block> TOOL_BOX = registerWithItem("tool_box", () -> new ToolBoxBlock(BlockBehaviour.Properties.copy(Blocks.SMITHING_TABLE).pushReaction(PushReaction.DESTROY)));
    public static final RegistrySupplier<Block> BLUEPRINTS = registerWithItem("blueprints", () -> new BlueprintsBlock(BlockBehaviour.Properties.copy(Blocks.CAKE).instabreak()));
    public static final RegistrySupplier<Block> SEWING_KIT = registerWithItem("sewing_kit", () -> new SewingKitBlock(BlockBehaviour.Properties.copy(Blocks.LOOM)));
    public static final RegistrySupplier<Item> CANVAS = registerItem("canvas", () -> new CanvasItem(new Item.Properties(), CanvasRegistry.LONELY_DAISY, TagRegistry.PAINTINGS));
    public static final RegistrySupplier<Block> BIN = registerWithItem("bin", () -> new BinBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistrySupplier<Item> TRASH_BAG = registerItem("trash_bag", () -> new TrashBagItem(getSettings()));
    public static final RegistrySupplier<Block> STEAM_VENT = registerWithItem("steam_vent", () -> new SteamVentBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistrySupplier<Block> FISH_TANK = registerWithItem("fish_tank", () -> new FishTankBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK)));
    public static final RegistrySupplier<Block> BRICK_CHIMNEY = registerWithItem("brick_chimney", () -> new ChimneyBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistrySupplier<Block> STONE_BRICKS_CHIMNEY = registerWithItem("stone_bricks_chimney", () -> new ChimneyBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistrySupplier<Block> COPPER_CHIMNEY = registerWithItem("copper_chimney", () -> new CopperChimneyBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK)));
    public static final RegistrySupplier<Block> BOAT_IN_A_JAR = registerWithItem("boat_in_a_jar", () -> new BoatInAJarBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static final RegistrySupplier<Block> STREET_LANTERN = registerWithoutItem("street_lantern", () -> new StreetLanternBlock(BlockBehaviour.Properties.copy(Blocks.DECORATED_POT).lightLevel((state) -> state.getValue(LIT) ? 15 : 0)));
    public static final RegistrySupplier<Block> STREET_WALL_LANTERN = registerWithoutItem("street_lantern_wall", () -> new StreetLanternWallBlock(BlockBehaviour.Properties.copy(Blocks.DECORATED_POT).lightLevel((state) -> state.getValue(LIT) ? 15 : 0)));
    public static final RegistrySupplier<Item> STREET_LANTERN_ITEM = registerItem("street_lantern_item", () -> new StandingAndWallBlockItem(ObjectRegistry.STREET_LANTERN.get(), ObjectRegistry.STREET_WALL_LANTERN.get(), new Item.Properties(), Direction.DOWN));
    public static final RegistrySupplier<Item> PELLS = registerItem("pells", () -> new PellsSpawnItem(getSettings()));

    public static final String[] colors = {
            "white", "light_gray", "gray", "black", "red", "orange", "yellow", "lime", "green", "cyan", "light_blue", "blue", "purple", "magenta", "pink", "brown"
    };

    public static final String[] woodTypes = {
            "oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "mangrove", "cherry"
    };

    static {
        for (String woodType : woodTypes) {
            BENCHES.put(woodType, registerWithItem(woodType + "_bench", () -> new BenchBlock(BlockBehaviour.Properties.copy(getCorrespondingPlank(woodType)).pushReaction(PushReaction.IGNORE))));
            CABINETS.put(woodType, registerWithItem(woodType + "_cabinet", () -> new CabinetBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD), SoundRegistry.CABINET_OPEN, SoundRegistry.CABINET_CLOSE)));
            CLOCKS.put(woodType, registerWithItem(woodType + "_clock", () -> new ClockBlock(BlockBehaviour.Properties.copy(getCorrespondingPlank(woodType)).pushReaction(PushReaction.IGNORE), ClockBlock.WoodType.valueOf(woodType.toUpperCase()))));
            GRANDFATHER_CLOCKS.put(woodType, registerWithItem(woodType + "_grandfather_clock", () -> new GrandfatherClockBlock(BlockBehaviour.Properties.copy(getCorrespondingPlank(woodType)).pushReaction(PushReaction.IGNORE), GrandfatherClockBlock.WoodType.valueOf(woodType.toUpperCase()))));
            MIRRORS.put(woodType, registerWithItem(woodType + "_mirror", () -> new MirrorBlock(BlockBehaviour.Properties.copy(getCorrespondingPlank(woodType)).pushReaction(PushReaction.IGNORE))));
        }
        for (String color : colors) {
            SOFAS.put(color, registerWithItem("sofa_" + color, () -> new SofaBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).pushReaction(PushReaction.DESTROY))));
            POUFFE.put(color, registerWithItem("pouffe_" + color, () -> new PouffeBlock(BlockBehaviour.Properties.copy(Blocks.RED_WOOL).pushReaction(PushReaction.NORMAL))));
            LAMPS.put(color, registerWithItem("lamp_" + color, () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).lightLevel(state -> state.getValue(LampBlock.LIT) ? 15 : 0).pushReaction(PushReaction.DESTROY))));
            CURTAINS.put(color, registerWithItem("curtain_" + color, () -> new CurtainBlock(BlockBehaviour.Properties.copy(Blocks.RED_WOOL).pushReaction(PushReaction.DESTROY))));
        }

        BLOCKS.register();
        ITEMS.register();
    }

    private static Block getCorrespondingPlank(String woodType) {
        return switch (woodType) {
            case "spruce" -> Blocks.SPRUCE_PLANKS;
            case "birch" -> Blocks.BIRCH_PLANKS;
            case "jungle" -> Blocks.JUNGLE_PLANKS;
            case "acacia" -> Blocks.ACACIA_PLANKS;
            case "dark_oak" -> Blocks.DARK_OAK_PLANKS;
            case "mangrove" -> Blocks.MANGROVE_PLANKS;
            case "cherry" -> Blocks.CHERRY_PLANKS;
            default -> Blocks.OAK_PLANKS;
        };
    }


    private static Item.Properties getSettings(Consumer<Item.Properties> consumer) {
        Item.Properties settings = new Item.Properties();
        consumer.accept(settings);
        return settings;
    }

    static Item.Properties getSettings() {
        return getSettings((settings) -> {
        });
    }

    public static <T extends Block> RegistrySupplier<T> registerWithItem(String name, Supplier<T> block) {
        return FurnitureUtil.registerWithItem(BLOCKS, BLOCK_REGISTRAR, ITEMS, ITEM_REGISTRAR, new FurnitureIdentifier(name), block);
    }

    public static <T extends Block> RegistrySupplier<T> registerWithoutItem(String path, Supplier<T> block) {
        return FurnitureUtil.registerWithoutItem(BLOCKS, BLOCK_REGISTRAR, new FurnitureIdentifier(path), block);
    }

    public static <T extends Item> RegistrySupplier<T> registerItem(String path, Supplier<T> itemSupplier) {
        return FurnitureUtil.registerItem(ITEMS, ITEM_REGISTRAR, new FurnitureIdentifier(path), itemSupplier);
    }
}