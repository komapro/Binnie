package binnie.genetics.modules;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fml.common.registry.GameRegistry;

import forestry.api.modules.ForestryModule;
import forestry.api.recipes.RecipeManagers;

import binnie.core.Binnie;
import binnie.core.Constants;
import binnie.core.Mods;
import binnie.core.features.FeatureItem;
import binnie.core.item.ItemMisc;
import binnie.core.liquid.FluidContainerType;
import binnie.core.liquid.ManagerLiquid;
import binnie.core.modules.BinnieModule;
import binnie.core.modules.GeneticsModuleUIDs;
import binnie.core.util.OreDictUtils;
import binnie.core.util.RecipeUtil;
import binnie.genetics.item.GeneticLiquid;
import binnie.genetics.item.GeneticsItems;
import binnie.genetics.modules.features.GeneticItems;
import binnie.genetics.recipes.RegistryRecipe;

@ForestryModule(moduleID = GeneticsModuleUIDs.CORE, containerID = Constants.GENETICS_MOD_ID, name = "Core", unlocalizedDescription = "genetics.module.core", coreModule = true)
public class ModuleCore extends BinnieModule {

	public ModuleCore() {
		super("forestry", "core");
	}

	@Override
	public void registerItemsAndBlocks() {
		GeneticLiquid.values();
		//Binnie.LIQUID.createLiquids(GeneticLiquid.values());
	}

	@Override
	public void doInit() {
		FeatureItem<ItemMisc<GeneticsItems>> geneticsItem = GeneticItems.GENETICS;
		RecipeUtil recipeUtil = new RecipeUtil(Constants.GENETICS_MOD_ID);
		recipeUtil.addShapelessRecipe("dna_dye_from_glowstone", GeneticsItems.DNADye.get(8),
			Items.GLOWSTONE_DUST, new ItemStack(Items.DYE, 1, 5)
		);
		recipeUtil.addRecipe("laboratory_casing", geneticsItem.stack(GeneticsItems.LaboratoryCasing),
			"iii",
			"iYi",
			"iii",
			'i', OreDictUtils.INGOT_IRON, 'Y', Mods.Forestry.item("sturdy_machine")
		);
		recipeUtil.addShapelessRecipe("dna_dye", geneticsItem.stack(2, GeneticsItems.DNADye), "dyePurple", "dyeMagenta", "dyePink");
		recipeUtil.addShapelessRecipe("fluorescent_dye", geneticsItem.stack(2, GeneticsItems.FluorescentDye), "dyeOrange", "dyeYellow", "dustGlowstone");
		recipeUtil.addShapelessRecipe("growth_medium", geneticsItem.stack(2, GeneticsItems.GrowthMedium), new ItemStack(Items.DYE, 1, 15), Items.SUGAR);
		recipeUtil.addRecipe("empty_sequencer", geneticsItem.stack(GeneticsItems.EmptySequencer),
			" p ",
			"iGi",
			" p ",
			'i', "ingotGold", 'G', Blocks.GLASS_PANE, 'p', Items.PAPER
		);
		recipeUtil.addRecipe("empty_serum", geneticsItem.stack(GeneticsItems.EMPTY_SERUM),
			" g ",
			" G ",
			"GGG",
			'g', "ingotGold", 'G', Blocks.GLASS_PANE
		);
		recipeUtil.addRecipe("empty_genome", geneticsItem.stack(GeneticsItems.EMPTY_GENOME),
			"sss",
			"sss",
			"sss",
			's', geneticsItem.stack(GeneticsItems.EMPTY_SERUM)
		);
		recipeUtil.addRecipe("cylinder", FluidContainerType.CYLINDER.get(8),
			" g ",
			"g g",
			"   ",
			'g', Blocks.GLASS_PANE
		);
		recipeUtil.addRecipe("integrated_circuit", geneticsItem.stack(GeneticsItems.IntegratedCircuit),
			"l g",
			" c ",
			"g l",
			'c', Mods.Forestry.stack("chipsets", 1, 1), 'l', new ItemStack(Items.DYE, 1, 4), 'g', "dustGlowstone"
		);
		recipeUtil.addRecipe("integrated_casing", geneticsItem.stack(GeneticsItems.IntegratedCasing),
			"ccc",
			"cdc",
			"ccc",
			'c', geneticsItem.stack(GeneticsItems.IntegratedCircuit), 'd', geneticsItem.stack(GeneticsItems.LaboratoryCasing)
		);
		recipeUtil.addRecipe("integrated_cpu", geneticsItem.stack(GeneticsItems.IntegratedCPU),
			"ccc",
			"cdc",
			"ccc",
			'c', geneticsItem.stack(GeneticsItems.IntegratedCircuit), 'd', Items.DIAMOND
		);


		RecipeManagers.carpenterManager.addRecipe(100, Binnie.LIQUID.getFluidStack(ManagerLiquid.WATER, 2000), ItemStack.EMPTY,
			GeneticItems.DATABASE.stack(),
			"X#X",
			"YEY",
			"RDR",
			'#', Blocks.GLASS_PANE, 'X', Items.DIAMOND, 'Y', Items.DIAMOND, 'R', Items.REDSTONE, 'D', Items.ENDER_EYE, 'E', Blocks.OBSIDIAN
		);
		GameRegistry.addSmelting(GeneticItems.SEQUENCE.item(), geneticsItem.stack(GeneticsItems.EmptySequencer), 0.0f);
		GameRegistry.addSmelting(GeneticItems.SERUM.item(), geneticsItem.stack(GeneticsItems.EMPTY_SERUM), 0.0f);
		GameRegistry.addSmelting(GeneticItems.SERUM_ARRAY.item(), geneticsItem.stack(GeneticsItems.EMPTY_GENOME), 0.0f);

		recipeUtil.addRecipe("analyst",
			GeneticItems.ANALYST.stack(),
			" c ",
			"cac",
			" d ",
			'c', geneticsItem.stack(GeneticsItems.IntegratedCircuit), 'a', Mods.Forestry.item("portable_alyzer"), 'd', new ItemStack(Items.DIAMOND)
		);

		RegistryRecipe recipe = RegistryRecipe.create(GeneticItems.REGISTRY.item(), GeneticItems.GENETICS.item());
		recipeUtil.addRecipe("registry", recipe);
	}

	@Override
	public void postInit() {

	}
}