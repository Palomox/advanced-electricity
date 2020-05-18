package com.github.palomox.advancedelectricity.blocks;

import javax.annotation.Nullable;

import com.github.palomox.advancedelectricity.containers.AdvancedTableContainer;
import com.github.palomox.advancedelectricity.tiles.AdvancedTableTile;

//import com.github.palomox.advancedelectricity.tiles.AdvancedTableTile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class AdvancedTableBlock extends Block {
	
	public static final ITextComponent name = new TranslationTextComponent("container.electriciantable");
	public static final VoxelShape pie = Block.makeCuboidShape(1D, 0D, 1D, 15D, 1.5D, 15D);
	public static final VoxelShape pata = Block.makeCuboidShape(3D, 1.5D, 3D, 13D, 13D, 13D);
	public static final VoxelShape mesa = Block.makeCuboidShape(0D, 13D, 0D, 16D, 16D, 16D);
	
	public AdvancedTableBlock() {
		super(Properties.create(Material.WOOD));
		setRegistryName("advanced_table");
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity,
			ItemStack stack) {
		if (entity != null) {
			world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, entity)), 2);
		}
	}

	public static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity) {
		return Direction.getFacingFromVector((float) (entity.posX - clickedBlock.getX()),
				(float) (entity.posY - clickedBlock.getY()), (float) (entity.posZ - clickedBlock.getZ()));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.FACING);
	}
	
	  @Override public boolean hasTileEntity(BlockState state) { 
		  return true; 
	  }
	  @Override
		public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos position, ISelectionContext context) {
			return VoxelShapes.or(pata, pie, mesa);
		}
	  @Nullable	  
	  @Override public TileEntity createTileEntity(BlockState state, IBlockReader world) { 
		  return new AdvancedTableTile(); 
	  }
	  @SuppressWarnings("deprecation")
	@Override
	    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
		  
		  player.openContainer(state.getContainer(world, pos));
		  return true;
	        /*DEPRECATED if (!world.isRemote) {
	        	NetworkHooks.openGui((ServerPlayerEntity) player, new INamedContainerProvider() {

					@Override
					public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
						return new AdvancedTableContainer(id, world, pos, inv, player);
					}

					@Override
					public ITextComponent getDisplayName() {
						return new TranslationTextComponent("advanced_table");
					}
					
				}, pos);;
	            return true;
	        }
			return true;
	    }*/
	 


	  }
	  public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
		  return new SimpleNamedContainerProvider((wid, inv, player) -> {
			 return new AdvancedTableContainer(wid, inv, IWorldPosCallable.of(worldIn, pos));
		  }, name);
				  
				  
	  }
	  }
