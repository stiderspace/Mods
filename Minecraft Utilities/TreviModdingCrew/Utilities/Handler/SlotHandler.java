package TreviModdingCrew.Utilities.Handler;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import TreviModdingCrew.Utilities.Recipes.RecipeRockGrinder;

public class SlotHandler extends Slot
{
    // Declarations
    
    private EntityPlayer Player;
    
    private int Slot;

    public SlotHandler(EntityPlayer EntityPlayer, IInventory IInventory, int Par3, int Par4, int Par5)
    {
        super(IInventory, Par3, Par4, Par5);
        Player = EntityPlayer;
    }

    
    // Can This Item Go In This Slot?
    
    public boolean isItemValid(ItemStack ItemStack)
    {
        return false;
    }


    // Stack Decrement
    
    public ItemStack decrStackSize(int Par1)
    {
        if (getHasStack())
        {
            Slot += Math.min(Par1, getStack().stackSize);
        }

        return super.decrStackSize(Par1);
    }


    // Player Gets Item Out Of Slot
    
    public void func_82870_a(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
    {
        onCrafting(par2ItemStack);
        onPickupFromSlot(par1EntityPlayer, par2ItemStack);
    }


    // Increasing Existing Stacks
    
    protected void onCrafting(ItemStack ItemStack, int Par2)
    {
        Slot = Par2;
        onCrafting(ItemStack);
    }

    
    // On Crafting
    
    protected void onCrafting(ItemStack ItemStack)
    {
        ItemStack.onCrafting(Player.worldObj, Player, Slot);

        if (!Player.worldObj.isRemote)
        {
            int Var4;
            int Var2 = Slot;
           
            float Var3 = RecipeRockGrinder.Grinding().getExperience(ItemStack.itemID);

            if (Var3 == 0.0F)
            {
                Var2 = 0;
            }
            else if (Var3 < 1.0F)
            {
                Var4 = MathHelper.floor_float((float)Var2 * Var3);

                if (Var4 < MathHelper.ceiling_float_int((float)Var2 * Var3) && (float)Math.random() < (float)Var2 * Var3 - (float)Var4)
                {
                    ++Var4;
                }

                Var2 = Var4;
            }

            while (Var2 > 0)
            {
                Var4 = EntityXPOrb.getXPSplit(Var2);
                Var2 = Var4;
                Player.worldObj.spawnEntityInWorld(new EntityXPOrb(Player.worldObj, Player.posX, Player.posY + 0.5D, Player.posZ + 0.5D, Var4));
            }
        }
        
        Slot = 0;
    }
}
