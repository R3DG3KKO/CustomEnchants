package com.r3dg3kko.customenchants.enums;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

public enum ItemSet {
    HELMET(Material.LEATHER_HELMET, Material.IRON_HELMET, Material.CHAINMAIL_HELMET, Material.GOLDEN_HELMET, Material.DIAMOND_HELMET),
    CHESTPLATE(Material.LEATHER_CHESTPLATE, Material.IRON_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.GOLDEN_CHESTPLATE, Material.DIAMOND_CHESTPLATE),
    LEGGINGS(Material.LEATHER_LEGGINGS, Material.IRON_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.GOLDEN_LEGGINGS, Material.DIAMOND_LEGGINGS),
    BOOTS(Material.LEATHER_BOOTS, Material.IRON_BOOTS, Material.CHAINMAIL_BOOTS, Material.GOLDEN_BOOTS, Material.DIAMOND_BOOTS),
    SWORD(Material.WOODEN_SWORD, Material.IRON_SWORD, Material.STONE_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD),
    BOW(Material.BOW),
    PICKAXE(Material.WOODEN_PICKAXE, Material.IRON_PICKAXE, Material.STONE_PICKAXE, Material.GOLDEN_PICKAXE, Material.DIAMOND_PICKAXE),
    SHOVEL(Material.WOODEN_SHOVEL, Material.IRON_SHOVEL, Material.STONE_SHOVEL, Material.GOLDEN_SHOVEL, Material.DIAMOND_SHOVEL),
    AXE(Material.WOODEN_AXE, Material.IRON_AXE, Material.STONE_AXE, Material.GOLDEN_AXE, Material.DIAMOND_AXE),
    HOE(Material.WOODEN_HOE, Material.IRON_HOE, Material.STONE_HOE, Material.GOLDEN_HOE, Material.DIAMOND_HOE),
    BLAZEROD(Material.BLAZE_ROD),
    ALL_TOOL(ItemSet.PICKAXE, ItemSet.SHOVEL, ItemSet.AXE, ItemSet.HOE),
    ALL_ARMOUR(ItemSet.HELMET, ItemSet.CHESTPLATE, ItemSet.LEGGINGS, ItemSet.BOOTS),
    ALL_WEAPON(ItemSet.SWORD, ItemSet.AXE);


    private List<Material> items = new ArrayList<>();

    ItemSet(Material... itemss) {
        for (Material item : itemss) {
            items.add(item);
        }
    }

    ItemSet(ItemSet... it) {
        for (ItemSet i : it) {
            items.addAll(i.getItems());
        }
    }

    public List<Material> getItems() {
        return items;
    }
}
