package com.github.palomox.advancedelectricity.containers;

import java.util.Map;
import java.util.Optional;

import com.github.palomox.advancedelectricity.init.ModBlocks;
import com.github.palomox.advancedelectricity.init.ModRecipeSerializers;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class AdvancedTableContainer extends Container {
	private TileEntity tileEntity;
	private PlayerEntity playerEntity;
	private IItemHandler playerInventory;
	public CraftingInventory advTableGrid = new CraftingInventory(this, 3, 2);
	public CraftResultInventory advTableResult = new CraftResultInventory();
	private final IWorldPosCallable pos;
	
	public AdvancedTableContainer(int wId, PlayerInventory inv) {
		this(wId, inv, IWorldPosCallable.DUMMY);
	}
	public AdvancedTableContainer(int windowId, PlayerInventory playerInventory, IWorldPosCallable pos) {
		super(ModBlocks.ADVANCED_TABLE_CONTAINER, windowId);
		//tileEntity = world.getTileEntity(pos);
		this.pos = pos;
		this.playerEntity = playerInventory.player;
		this.playerInventory = new InvWrapper(playerInventory);

		this.addSlot(new CraftingResultSlot(playerInventory.player, this.advTableGrid, this.advTableResult, 0, 124, 36));
		/*tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
			//input*/
			this.addSlot(new Slot(this.advTableGrid, 1, 31, 36));
			this.addSlot(new Slot(this.advTableGrid, 2, 67, 36));
			this.addSlot(new Slot(this.advTableGrid, 3, 49, 54));
			this.addSlot(new Slot(this.advTableGrid, 4, 49, 18));
			this.addSlot(new Slot(this.advTableGrid, 5, 49, 36));
			/*output
			addSlot(new CraftingResultSlot(this.playerEntity, this.advTableGrid, this.advTableResult, 0, 124, 35));
		});*/
		layoutPlayerInventorySlots(8, 84);
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		//return isWithinUsableDistance(IWorldPosCallable.of(playerIn.world, playerIn.getPosition()), playerIn, ModBlocks.ADVANCED_TABLE);
		return true;
	}

	private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
		for (int i = 0; i < amount; i++) {
			addSlot(new SlotItemHandler(handler, index, x, y));
			x += dx;
			index++;
		}
		return index;
	}

	private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount,
			int dy) {
		for (int j = 0; j < verAmount; j++) {
			index = addSlotRange(handler, index, x, y, horAmount, dx);
			y += dy;
		}
		return index;
	}

	private void layoutPlayerInventorySlots(int leftCol, int topRow) {
		// Player inventory
		addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

		// Hotbar
		topRow += 58;
		addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
	}
	@Override
	public boolean getCanCraft(PlayerEntity player) {
		return true;
	}
	
	@Override
	public void setCanCraft(PlayerEntity player, boolean canCraft) {
		super.setCanCraft(player, true);
	}
	@Override
	public void onContainerClosed(PlayerEntity playerIn) {
		super.onContainerClosed(playerIn);	      
	    /*for(int i = 0; i < this.advTableGrid.getSizeInventory(); i++) {
	    	ItemStack stack = this.advTableGrid.getStackInSlot(i).copy();
	    	playerIn.addItemStackToInventory(stack);
	    }*/
	    this.clearContainer(playerIn, playerIn.world, this.advTableGrid);
	}
	@Override
	protected void clearContainer(PlayerEntity playerIn, World worldIn, IInventory inventoryIn) {
		advTableGrid.clear();
		advTableResult.clear();
	}
	protected void slotChangedCraftMatrix(int windowId, World world, PlayerEntity player, CraftingInventory inventory, CraftResultInventory resultInventory) {
		if(!world.isRemote) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)player;
	        ItemStack itemstack = ItemStack.EMPTY;
	        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(this.advTableGrid.getStackInSlot(0));
	        Optional<ICraftingRecipe> optional = world.getServer().getRecipeManager().getRecipe(IRecipeType.CRAFTING, inventory, world);
	        if(optional.isPresent()) {
	        	ICraftingRecipe icraftingrecipe = optional.get(); 
	            if(resultInventory.canUseRecipe(world, serverplayerentity, icraftingrecipe) && icraftingrecipe.getSerializer() == ModRecipeSerializers.advt_shapedr) {	     
	            	itemstack = icraftingrecipe.getCraftingResult(inventory);
	            }
	        }

	        resultInventory.setInventorySlotContents(0, itemstack);
	        serverplayerentity.connection.sendPacket(new SSetSlotPacket(windowId, 0, itemstack));
		}
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory inventoryIn) {
		this.detectAndSendChanges();
		slotChangedCraftMatrix(this.windowId, this.playerEntity.world, this.playerEntity, this.advTableGrid, this.advTableResult);
	}

	@Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            itemstack = stack.copy();
            if (index == 0) {
                if (!this.mergeItemStack(stack, 1, 37, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(stack, itemstack);
            } else {
                    if (!this.mergeItemStack(stack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                 else if (index < 28) {
                    if (!this.mergeItemStack(stack, 28, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 37 && !this.mergeItemStack(stack, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

	return itemstack;
}

}
