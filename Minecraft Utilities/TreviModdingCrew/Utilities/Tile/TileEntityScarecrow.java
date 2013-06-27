package TreviModdingCrew.Utilities.Tile;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityScarecrow extends TileEntity
{
    public void updateEntity() 
    {  
        if(!worldObj.isRemote)
        {
            AxisAlignedBB Axis = AxisAlignedBB.getBoundingBox(xCoord - 5, yCoord, zCoord - 5, xCoord + 5, yCoord + 1, zCoord + 5);
            
            List Entities = worldObj.getEntitiesWithinAABB(EntityLiving.class, Axis);
            
            for(int Size = 0; Size < Entities.size(); Size++)
            {
                EntityLiving CurrentEntity = (EntityLiving) Entities.get(Size);
                
                if(CurrentEntity instanceof EntityPig)
                {
                    double xVel = 0;
                    double zVel = 0;

                    xVel = (CurrentEntity.posX - xCoord) / 5;
                    zVel = (CurrentEntity.posZ - zCoord) / 5;
                    
                    CurrentEntity.setVelocity(xVel, 0.1, zVel);
                }
                
                else if(CurrentEntity instanceof EntityCow)
                {
                    double xVel = 0;
                    double zVel = 0;

                    xVel = (CurrentEntity.posX - xCoord) / 5;
                    zVel = (CurrentEntity.posZ - zCoord) / 5;
                    
                    CurrentEntity.setVelocity(xVel, 0.1, zVel);
                }
                
                else if(CurrentEntity instanceof EntityChicken)
                {
                    double xVel = 0;
                    double zVel = 0;

                    xVel = (CurrentEntity.posX - xCoord) / 5;
                    zVel = (CurrentEntity.posZ - zCoord) / 5;
                    
                    CurrentEntity.setVelocity(xVel, 0.1, zVel);              
                }
                
                else if(CurrentEntity instanceof EntitySheep)
                {
                    double xVel = 0;
                    double zVel = 0;

                    xVel = (CurrentEntity.posX - xCoord) / 5;
                    zVel = (CurrentEntity.posZ - zCoord) / 5;
                    
                    CurrentEntity.setVelocity(xVel, 0.05, zVel);
                }
                
                else if(CurrentEntity instanceof EntityOcelot)
                {
                    double xVel = 0;
                    double zVel = 0;

                    xVel = (CurrentEntity.posX - xCoord) / 5;
                    zVel = (CurrentEntity.posZ - zCoord) / 5;
                    
                    CurrentEntity.setVelocity(xVel, 0.05, zVel);
                }
                
                else if(CurrentEntity instanceof EntityMooshroom)
                {
                    double xVel = 0;
                    double zVel = 0;

                    xVel = (CurrentEntity.posX - xCoord) / 5;
                    zVel = (CurrentEntity.posZ - zCoord) / 5;
                    
                    CurrentEntity.setVelocity(xVel, 0.05, zVel);
                }
            }
        }
    }
}