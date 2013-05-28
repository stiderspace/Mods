package TreviModdingCrew.Voidcraft.Handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemSwapHandler
{
    public void SwapItem(Item Consumed, ItemStack Replacement, EntityPlayer EntityPlayer)
    {
        if(EntityPlayer.inventory.hasItem(Consumed.itemID))
        {
            EntityPlayer.inventory.consumeInventoryItem(Consumed.itemID);
            EntityPlayer.inventory.addItemStackToInventory(Replacement);
        }
    }
}
