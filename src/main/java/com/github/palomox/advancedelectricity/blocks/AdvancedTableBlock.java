package com.github.palomox.advancedelectricity.blocks;

import javax.annotation.Nullable;

import com.github.palomox.advancedelectricity.tiles.AdvancedTableTile;

//import com.github.palomox.advancedelectricity.tiles.AdvancedTableTile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class AdvancedTableBlock extends Block {
	
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
	        if (!world.isRemote) {
	            TileEntity tileEntity = world.getTileEntity(pos);
	            if (tileEntity instanceof INamedContainerProvider) {
	                NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
	            } else {
	                throw new IllegalStateException("Our named container provider is missing!");
	            }
	            return true;
	        }
	        return super.onBlockActivated(state, world, pos, player, hand, result);
	    }
	 

}
