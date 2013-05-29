package TreviModdingCrew.Voidcraft.Entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityGreenPowderPrimed extends Entity
{
    public int Fuse;
    
    private EntityLiving PlacedBy;

    public EntityGreenPowderPrimed(World World)
    {
        super(World);
        
        Fuse = 0;
        preventEntitySpawning = true;
        yOffset = height / 2.0F;
        
        setSize(0.5F, 0.5F);
    }

    public EntityGreenPowderPrimed(World World, double Par2, double Par3, double Par4, EntityLiving EntityLiving)
    {
        this(World);
        float Par5 = (float)(Math.random() * Math.PI * 2.0D);
        
        setPosition(Par2, Par3, Par4);
       
        motionX = (double)(-((float)Math.sin((double)Par5)) * 0.02F);
        motionY = 0.20000000298023224D;
        motionZ = (double)(-((float)Math.cos((double)Par5)) * 0.02F);
        
        Fuse = 0;
        
        prevPosX = Par2;
        prevPosY = Par3;
        prevPosZ = Par4;
        PlacedBy = EntityLiving;
    }

    protected void entityInit()
    {
        
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }

    public boolean canBeCollidedWith()
    {
        return !isDead;
    }

    public void onUpdate()
    {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
       
        motionY -= 0.03999999910593033D;
       
        moveEntity(motionX, motionY, motionZ);
        
        motionX *= 0.9800000190734863D;
        motionY *= 0.9800000190734863D;
        motionZ *= 0.9800000190734863D;

        if (onGround)
        {
            motionX *= 0.699999988079071D;
            motionZ *= 0.699999988079071D;
            motionY *= -0.5D;
        }

        if (Fuse-- <= 0)
        {
            setDead();

            if (!worldObj.isRemote)
            {
                explode();
            }
        }
        
        else
        {
            worldObj.spawnParticle("smoke", posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    private void explode()
    {
        float f = 8.0F;
        worldObj.createExplosion(this, posX, posY, posZ, f, true);
    }

    protected void writeEntityToNBT(NBTTagCompound NBTTagCompound)
    {
        NBTTagCompound.setByte("Fuse", (byte)Fuse);
    }

    protected void readEntityFromNBT(NBTTagCompound NBTTagCompound)
    {
        Fuse = NBTTagCompound.getByte("Fuse");
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }

    public EntityLiving getTntPlacedBy()
    {
        return PlacedBy;
    }
}
