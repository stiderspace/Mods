package TreviModdingCrew.Utilities.Entity;

import java.util.Calendar;
import java.util.Date;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBomb extends EntityThrowable
{
	// Declarations
	
	int Fuse;
	float BounceFactor;
	boolean Exploded;
	
	
	// Getting The Texture File

	public String getTextureFile()
	{
	    return "/TreviModdingCrew/Utilities/Textures/Item.png";
	}

  
	// Telling The Computer Where To Throw The Entity
  
	public EntityBomb(World World)
	{
	    super(World);
	  
	    setSize(0.5F, 0.5F);
	    yOffset = (height / -0.6F);
	    BounceFactor = 0.75F;
	    Exploded = false;
	    Fuse = 0;
	}
  
  
	// Making It A Entity
  
	public EntityBomb(World World, EntityLiving EntityLiving)
	{
	    super(World, EntityLiving);
	}

  
	// Letting It Render From All Sides
	
	public EntityBomb(World World, float Par1, float Par2, float Par3)
	{
	    super(World, Par1, Par2, Par3);
	}

  
	// Set How It Acts In The Air & Ground
  
	public void onUpdate()
	{
	    prevPosX = posX;
	    prevPosY = posY;
	    prevPosZ = posZ;
	    motionY -= 0.03999999910593033D;
	  
	    moveEntity(motionX, motionY, motionZ);
	  
	    motionX *= 0.9800000190734863F;
	    motionY *= 0.9800000190734863F;
	    motionZ *= 0.9800000190734863F;
	  
	    if (onGround)
	    {
	        motionX *= 0.699999988079071F;
	        motionZ *= 0.699999988079071F;
	        motionY *= -0.5F;
	    }

	    if (Fuse++ >= 50)
	    {
	        setDead();
    	
	        if (!worldObj.isRemote)
	        {
	            Explode();
	        }
	    }
	  
	    else
	    {
	        Calendar Timer = Calendar.getInstance();
	        Timer.setTime(new Date());
	        
	        if (Timer.get(2) + 1 == 2 && Timer.get(5) == 14)
	        {
	            worldObj.spawnParticle("heart", posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
	        }
	        
	        else
	        {
	            worldObj.spawnParticle("smoke", posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
	        }
	    }
	}

  
	// Setting How Big The Explosion Is
  
	private void Explode()
  	{
	  	float Var1 = 2.5F;
	
	  	worldObj.createExplosion((Entity)null, posX, posY, posZ, Var1, true);
  	}

  	
  	// Writing The Tag
  	
  	public void writeEntityToNBT(NBTTagCompound NBTTagCompound)
  	{
  		super.writeEntityToNBT(NBTTagCompound);
  		NBTTagCompound.setByte("Fuse", (byte)Fuse);
  	}

  	
  	// Reading The Tag
  	
  	public void readEntityFromNBT(NBTTagCompound NBTTagCompound)
  	{
	  	super.readEntityFromNBT(NBTTagCompound);
	  	Fuse = NBTTagCompound.getByte("Fuse");
  	}

  	
  	// If It Hits Something
  	
    @Override
    protected void onImpact(MovingObjectPosition MovingObjectPosition)
    {
        
    }
}