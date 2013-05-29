package TreviModdingCrew.LiquidGun.Items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFluid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.liquids.IBlockLiquid;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

import org.lwjgl.input.Keyboard;

import TreviModdingCrew.LiquidGun.Entity.EntityLiquidBullet;

public class ItemLiquidGun extends Item
{
    public ItemLiquidGun(int Par1)
    {
        super(Par1);
        
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabCombat); 
        setNoRepair();
        setUnlocalizedName("Aqua Gun");
        setMaxDamage(1000);
    }

    @Override
    public void addInformation(ItemStack ItemStack, EntityPlayer EntityPlayer, List List, boolean Par4)
    {
        LiquidStack LiquidStacks;
        
        String Liquid = "None";
        
        try
        {
            LiquidStacks = LiquidStack.loadLiquidStackFromNBT((NBTTagCompound) ItemStack.getTagCompound().getTag("LiquidData"));
            Liquid = Block.blocksList[LiquidStacks.itemID].getLocalizedName();
        }
        
        catch(Exception Exception)
        {
            LiquidStacks = null;
        }
        
        List.add("Current Liquid: " + Liquid);
           
        if(Keyboard.isKeyDown(Keyboard.KEY_M))
        {
            List.clear();
            
            List.add("Liquid Gun");
            
            List.add("Hold right click to charge up the gun.");
            List.add("To charge it you need to click it");
            List.add("a water or lava source block.");
            List.add("We have not tested this out with");
            List.add("other mods, it might crash you.");
        }
    }

    @Override
    public EnumAction getItemUseAction(ItemStack ItemStack)
    {
        return EnumAction.bow;
    }

    @Override
    public ItemStack onEaten(ItemStack ItemStack, World World, EntityPlayer EntityPlayer)
    {
        return ItemStack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack ItemStack)
    {
        return 36000;
    }

    @Override
    public int getItemEnchantability()
    {
        return 0; 
    }

    @Override
    public ItemStack onItemRightClick(ItemStack ItemStack, World World, EntityPlayer EntityPlayer)
    {
        Block Block = null;
        LiquidStack LiquidStack = null;
        MovingObjectPosition MovingObjectPosition = null;
        
        MovingObjectPosition = getMovingObjectPositionFromPlayer(World, EntityPlayer, true);
        
        if(MovingObjectPosition != null && MovingObjectPosition.typeOfHit == EnumMovingObjectType.TILE)
        {
            Block = Block.blocksList[World.getBlockId(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ)];
        }
        
        try
        {
            LiquidStack = LiquidStack.loadLiquidStackFromNBT(ItemStack.getTagCompound().getCompoundTag("LiquidData"));
        }
        
        catch(Exception Exception)
        {
            if(Block != null && (Block instanceof IBlockLiquid || Block instanceof BlockFluid))
            {
                if(Block instanceof IBlockLiquid)
                {
                    LiquidStack = LiquidDictionary.getLiquid(((IBlockLiquid) Block).getLiquidProperties().getName(), 0);
                }
                
                else
                {
                    LiquidStack = LiquidDictionary.getLiquid(Block.blockID == Block.waterStill.blockID || Block.blockID == Block.waterMoving.blockID ? "Water" : "Lava", 0);       
                }
                
                NBTTagCompound NBTTagCompound = new NBTTagCompound();

                LiquidStack.writeToNBT(NBTTagCompound);

                if(ItemStack.getTagCompound() == null) 
                {
                    ItemStack.stackTagCompound = new NBTTagCompound();
                }

                ItemStack.getTagCompound().setTag("LiquidData", NBTTagCompound);
            }
            
            else
            {
                LiquidStack = null;
            }
        }

        if(Block != null && (Block instanceof IBlockLiquid || Block instanceof BlockFluid))
        {
            if(LiquidStack.amount < 1000)
            {
                if(Block.blockID == LiquidStack.itemID)
                {
                    LiquidStack.amount = 1000;
                   
                    if(!World.isRemote) 
                    {
                        World.setBlock(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ, 0);
                    }
                }
                
                else if(LiquidStack.amount == 0)
                {
                    if(Block instanceof IBlockLiquid)
                    {
                        LiquidStack = LiquidDictionary.getLiquid(((IBlockLiquid) Block).getLiquidProperties().getName(), 1000);
                    }
                        
                    else
                    {
                        LiquidStack = LiquidDictionary.getLiquid(Block.blockID == Block.waterStill.blockID || Block.blockID == Block.waterMoving.blockID ? "Water" : "Lava", 1000);        
                    }
                    
                    World.setBlock(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ, 0);
                }

                NBTTagCompound NBTTagCompound = new NBTTagCompound();

                LiquidStack.writeToNBT(NBTTagCompound);

                if(ItemStack.getTagCompound() == null)
                {
                    ItemStack.stackTagCompound = new NBTTagCompound();
                }

                ItemStack.getTagCompound().setTag("LiquidData", NBTTagCompound);
            }
            
            else
            {
                if(EntityPlayer.capabilities.isCreativeMode || (LiquidStack != null && LiquidStack.amount > 0))
                {
                    EntityPlayer.setItemInUse(ItemStack, getMaxItemUseDuration(ItemStack));
                }
            }
        }
        
        else
        {
            if(EntityPlayer.capabilities.isCreativeMode || (LiquidStack != null && LiquidStack.amount > 0))
            {
                EntityPlayer.setItemInUse(ItemStack, getMaxItemUseDuration(ItemStack));
            }
        }

        return ItemStack;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack ItemStack, World World, EntityPlayer EntityPlayer, int Par4)
    {
        if(!World.isRemote)
        {
            boolean Flag = EntityPlayer.capabilities.isCreativeMode;

            LiquidStack Par8;

            try
            {
                Par8 = LiquidStack.loadLiquidStackFromNBT(ItemStack.getTagCompound().getCompoundTag("LiquidData"));
            }
            catch(Exception e)
            {
                Par8 = null;
            }


            int Par5 = getMaxItemUseDuration(ItemStack) - Par4;
            float Par6 = (Par5 / 20) + 1;
            
            if(Par8 != null && (Flag || Par8.amount - Math.floor(10 * Par6) >= 0))
            {
                float Par7 = (float) Par5 / 20.0F; 
                
                Par7 = (Par7 * Par7 + Par7 * 2.0F) / 3.0F;
    
                if((double) Par7 < 0.1D)
                {
                    return;
                }
    
                if(Par7 > 1.0F)
                {
                    Par7 = 1.0F;
                }
                
                if(!Flag)
                {
                    Par8.amount -= (int) Math.floor(10 * Par6);
                }
                
                LiquidStack Par9 = Par8.copy();
                Par9.amount = (int) Math.floor(10 * Par6);
                EntityLiquidBullet bullet = new EntityLiquidBullet(World, EntityPlayer, Par7 * 2.0F, Par9, Par6);
                World.spawnEntityInWorld(bullet);
            }
            
            if(Par8 != null)
            {
                NBTTagCompound NBTTagCompound = new NBTTagCompound();

                Par8.writeToNBT(NBTTagCompound);

                if(ItemStack.getTagCompound() == null) ItemStack.stackTagCompound = new NBTTagCompound();

                ItemStack.getTagCompound().setTag("LiquidData", NBTTagCompound);
            }
        }
    }
}
