package TreviModdingCrew.LiquidGun.Entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFluid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet70GameEvent;
import net.minecraft.src.ModLoader;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.liquids.IBlockLiquid;
import net.minecraftforge.liquids.LiquidStack;

public class EntityLiquidBullet extends Entity implements IProjectile
{
    private boolean InGround = false;
    private boolean IsOre = false;
    
    public Entity ShootingEntity;
    public LiquidStack LiquidStored;
   
    private int TicksInAir = 0;
    private int KnockbackStrength;
    
    private double Damage = 0;
   
    public byte[] Particle = new byte[3];

    public EntityLiquidBullet(World World)
    {
        super(World);
        renderDistanceWeight = 10.0D;
        setSize(0.5F, 0.5F);
        Block Block = null;
       
        try
        {
            Block = (Block.blocksList[(LiquidStack.loadLiquidStackFromNBT((NBTTagCompound) ModLoader.getMinecraftInstance().thePlayer.inventory.getCurrentItem().getTagCompound().getTag("LiquidData")).itemID)]);                   
        }
        
        catch(Exception Exception)
        {
            Block = Block.waterStill;
        }
        
        if(Block instanceof IBlockLiquid)
        {
            Particle[0] = ((IBlockLiquid) Block).getLiquidRGB()[0];
            Particle[1] = ((IBlockLiquid) Block).getLiquidRGB()[1];
            Particle[2] = ((IBlockLiquid) Block).getLiquidRGB()[2];
        }
        
        else if(Block instanceof BlockFluid)
        {
            if(Block.blockID == 8 || Block.blockID == 9)
            {
                Particle[0] = 50;
                Particle[1] = 50;
                Particle[2] = (byte) 255;

            }
            else if(Block.blockID == 10 || Block.blockID == 11)
            {
                Particle[0] = (byte) 255;
                Particle[1] = 120;
                Particle[2] = 50;
            }
        }
    }

    public EntityLiquidBullet(World World, double Par2, double Par4, double Par5, LiquidStack LiquidStack)
    {
        super(World);
        setSize(0.5F, 0.5F);
        setPosition(Par2, Par4, Par5);
        renderDistanceWeight = 10.0D;
        LiquidStored = LiquidStack;
        yOffset = 0.0F;
    }

    public EntityLiquidBullet(World World, EntityLiving EntityLiving, EntityLiving Entity, float Par4, float Par5, LiquidStack LiquidStacks)    
    {
        super(World);
        
        renderDistanceWeight = 10.0D;
        ShootingEntity = EntityLiving;
        LiquidStored = LiquidStacks;
        posY = EntityLiving.posY + (double) EntityLiving.getEyeHeight() - 0.10000000149011612D;
        
        double Var1 = Entity.posX - EntityLiving.posX;
        double Var2 = Entity.boundingBox.minY + (double) (Entity.height / 3.0F) - posY;
        double Var3 = Entity.posZ - EntityLiving.posZ;
        double Var4 = (double) MathHelper.sqrt_double(Var1 * Var1 + Var3 * Var3);

        if(Var4 >= 1.0E-7D)
        {
            float f2 = (float) (Math.atan2(Var3, Var1) * 180.0D / Math.PI) - 90.0F;
            float f3 = (float) (-(Math.atan2(Var2, Var4) * 180.0D / Math.PI));
            float f4 = (float) Var4 * 0.2F;
            double d4 = Var1 / Var4;
            double d5 = Var3 / Var4;
            
            yOffset = 0.0F;
            setLocationAndAngles(EntityLiving.posX + d4, posY, EntityLiving.posZ + d5, f2, f3);
            setThrowableHeading(Var1, Var2 + (double) f4, Var3, Par4, Par5);
        }
    }

    public EntityLiquidBullet(World World, EntityLiving EntityLiving, float Par3, LiquidStack LiquidStacks, float d)
    {
        super(World);
        renderDistanceWeight = 10.0D;
        ShootingEntity = EntityLiving;
        LiquidStored = LiquidStacks;

        setSize(0.5F, 0.5F);
        setLocationAndAngles(EntityLiving.posX, EntityLiving.posY + (double) EntityLiving.getEyeHeight(),EntityLiving.posZ, EntityLiving.rotationYaw, EntityLiving.rotationPitch);
                
        posX -= (double) (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
        posY -= 0.10000000149011612D;
        posZ -= (double) (MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
        
        setPosition(posX, posY, posZ);
        
        yOffset = 0.0F;
       
        motionX = (double) (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));   
        motionZ = (double) (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
        motionY = (double) (-MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI));
        
        setThrowableHeading(motionX, motionY, motionZ, Par3 * 1.5F, 1.0F);
        
        Damage = d;
        
        switch(LiquidStacks.itemID)
        {
            case 8: Damage /= 2;
            case 9: Damage /= 2;
                 
            break;

            case 10: Damage *= 2.0D;
            case 11: Damage *= 2.0D;
                
            break;
        }
    }

    protected void entityInit()
    {
        dataWatcher.addObject(16, 0);
    }

    public void setThrowableHeading(double Par1, double Par3, double Par5, float Par7, float Par8)
    {
        float f2 = MathHelper.sqrt_double(Par1 * Par1 + Par3 * Par3 + Par5 * Par5);
        
        Par1 /= (double) f2;
        Par3 /= (double) f2;
        Par5 /= (double) f2;
        
        Par1 += rand.nextGaussian() * (double) (rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double) Par8;
        Par3 += rand.nextGaussian() * (double) (rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double) Par8;
        Par5 += rand.nextGaussian() * (double) (rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double) Par8;
        
        Par1 *= (double) Par7;
        Par3 *= (double) Par7;
        Par5 *= (double) Par7;
        
        motionX = Par1;
        motionY = Par3;
        motionZ = Par5;
        
        float f3 = MathHelper.sqrt_double(Par1 * Par1 + Par5 * Par5);
        
        prevRotationYaw = rotationYaw = (float) (Math.atan2(Par1, Par5) * 180.0D / Math.PI);
        prevRotationPitch = rotationPitch = (float) (Math.atan2(Par3, (double) f3) * 180.0D / Math.PI);
    }

    public void setPositionAndRotation2(double Par1, double Par3, double Par5, float Par7, float Par8, int Par9)
    {
        setPosition(Par1, Par3, Par5);
        setRotation(Par7, Par8);
    }

    public void setVelocity(double Par1, double Par3, double Par5)
    {
        motionX = Par1;
        motionY = Par3;
        motionZ = Par5;

        if(prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
        {
            float Buffer = MathHelper.sqrt_double(Par1 * Par1 + Par5 * Par5);
            
            prevRotationYaw = rotationYaw = (float) (Math.atan2(Par1, Par5) * 180.0D / Math.PI);
            prevRotationPitch = rotationPitch = (float) (Math.atan2(Par3, (double) Buffer) * 180.0D / Math.PI);
            prevRotationPitch = rotationPitch;
            prevRotationYaw = rotationYaw;
            
            setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
        }
    }

    public void onUpdate()
    {
        super.onUpdate();
        {
            if(!worldObj.isRemote) dataWatcher.updateObject(16, LiquidStored.itemID);

            if(prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
            {
                float Buffer = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
               
                prevRotationYaw = rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
                prevRotationPitch = rotationPitch = (float) (Math.atan2(motionY, (double) Buffer) * 180.0D / Math.PI);
            }

            if(InGround)
            {
                setDead(); 
            }
            
            else
            {
                ++TicksInAir;
               
                Vec3 Var1 = worldObj.getWorldVec3Pool().getVecFromPool(posX, posY, posZ);
                Vec3 Var2 = worldObj.getWorldVec3Pool().getVecFromPool(posX + motionX,posY + motionY, posZ + motionZ);
                        

                MovingObjectPosition MovingObjectPosition = worldObj.rayTraceBlocks_do_do(Var1, Var2, false, true);

                Var1 = worldObj.getWorldVec3Pool().getVecFromPool(posX, posY, posZ);
                Var2 = worldObj.getWorldVec3Pool().getVecFromPool(posX + motionX, posY + motionY, posZ + motionZ);

                        
                if(MovingObjectPosition != null)
                {
                    Var2 = worldObj.getWorldVec3Pool().getVecFromPool(MovingObjectPosition.hitVec.xCoord, MovingObjectPosition.hitVec.yCoord, MovingObjectPosition.hitVec.zCoord);        
                }

                Entity entity = null;
               
                List <?> List = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
                        
                double Var3 = 0.0D;
                int Var4;
                float Var5;

                for(Var4 = 0; Var4 < List.size(); ++Var4)
                {
                    Entity Entity = (Entity) List.get(Var4);

                    if(Entity.canBeCollidedWith() && (Entity != ShootingEntity || TicksInAir >= 5))
                    {
                        Var5 = 0.3F;
                        
                        AxisAlignedBB axisalignedbb1 = Entity.boundingBox.expand((double) Var5, (double) Var5, (double) Var5);
                        MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(Var1, Var2);

                        if(movingobjectposition1 != null)
                        {
                            double Var6 = Var1.distanceTo(movingobjectposition1.hitVec);

                            if(Var6 < Var3 || Var3 == 0.0D)
                            {
                                entity = Entity;
                                Var3 = Var6;
                            }
                        }
                    }
                }

                if(entity != null)
                {
                    MovingObjectPosition = new MovingObjectPosition(entity);
                }

                if(MovingObjectPosition != null && MovingObjectPosition.entityHit != null && MovingObjectPosition.entityHit instanceof EntityPlayer)    
                {
                    EntityPlayer entityplayer = (EntityPlayer) MovingObjectPosition.entityHit;

                    if(entityplayer.capabilities.disableDamage || ShootingEntity instanceof EntityPlayer && !((EntityPlayer) ShootingEntity).func_96122_a(entityplayer))    
                    {
                        MovingObjectPosition = null;
                    }
                }

                float Var6;
                float Var7;

                if(MovingObjectPosition != null)
                {
                    if(MovingObjectPosition.entityHit != null)
                    {
                        Var6 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
                                
                        int Var9 = MathHelper.ceiling_double_int((double) Var6 * Damage);

                        DamageSource Source = null;

                        if(ShootingEntity == null)
                        {
                            Source = DamageSource.causeThrownDamage(this, this);
                        }
                        
                        else
                        {
                            Source = DamageSource.causeThrownDamage(this, ShootingEntity);
                        }

                        if(!worldObj.isRemote)
                        {
                            if(MovingObjectPosition.entityHit.attackEntityFrom(Source, Var9))
                            {
                                if(MovingObjectPosition.entityHit instanceof EntityLiving)
                                {   
                                    if(KnockbackStrength > 0)
                                    {
                                        Var7 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
    
                                        if(Var7 > 0.0F)
                                        {
                                            MovingObjectPosition.entityHit.addVelocity(motionX * (double) KnockbackStrength * 0.6000000238418579D / (double) Var7, 0.1D,motionZ * (double) KnockbackStrength * 0.6000000238418579D / (double) Var7);                
                                        }
                                    }
    
                                    if(ShootingEntity != null && MovingObjectPosition.entityHit != ShootingEntity && MovingObjectPosition.entityHit instanceof EntityPlayer && ShootingEntity instanceof EntityPlayerMP)      
                                    {
                                        ((EntityPlayerMP) ShootingEntity).playerNetServerHandler.sendPacketToPlayer(new Packet70GameEvent(6, 0));      
                                    }
                                }
    
                                setDead();
    
                                switch(dataWatcher.getWatchableObjectInt(16))                    
                                {
                                    case 9: MovingObjectPosition.entityHit.extinguish();
                                    case 8: MovingObjectPosition.entityHit.extinguish();
                                       
                                    break;
    
                                    case 10: MovingObjectPosition.entityHit.setFire(5);
                                    case 11: MovingObjectPosition.entityHit.setFire(5);
                                        
                                    break;
                                }
                            }
                        }
                        
                        else
                        {
                            motionX *= -0.10000000149011612D;
                            motionY *= -0.10000000149011612D;
                            motionZ *= -0.10000000149011612D;
                            
                            rotationYaw += 180.0F;
                            prevRotationYaw += 180.0F;
                            TicksInAir = 0;
                        }
                    }
                    
                    else
                    {
                        InGround = true;
                        
                        if(!worldObj.isRemote)
                        {
                            int Var10 = MovingObjectPosition.blockX + ((MovingObjectPosition.sideHit == 5) ? 1 : ((MovingObjectPosition.sideHit == 4) ? -1 : 0));
                            int Var11 = MovingObjectPosition.blockY + ((MovingObjectPosition.sideHit == 1) ? 1 : ((MovingObjectPosition.sideHit == 0) ? -1 : 0));     
                            int Var12 = MovingObjectPosition.blockZ + ((MovingObjectPosition.sideHit == 3) ? 1 : ((MovingObjectPosition.sideHit == 2) ? -1 : 0));
    
                            int Var13 = worldObj.getBlockId(Var10, Var11, Var12);
    
                            switch(dataWatcher.getWatchableObjectInt(16))
                            {
                                case 9:
                                case 8:
                                        if(IsOre == false)
                                        {
                                            if(Var13 == Block.fire.blockID)
                                            {
                                                worldObj.setBlock(Var10, Var11, Var12, 0);
                                               
                                                if(!worldObj.isRemote)
                                                {
                                                    worldObj.playSoundEffect((double) ((float) Var10 + 0.5F), (double) ((float) Var11 + 0.5F), (double) ((float) Var12 + 0.5F), "random.fizz ", 0.5F, 2.6F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.8F);                
                                                }
                                            }
                                        }
                                        
                                break;
    
                                case 10:
                                case 11:
                                    
                                    if(worldObj.getBlockId(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ) == Block.sand.blockID)     
                                    {
                                        worldObj.setBlock(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ, Block.glass.blockID);         
                                        
                                        IsOre = true;
                                    }
                                    
                                    if(worldObj.getBlockId(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ) == Block.cobblestone.blockID)     
                                    {
                                        worldObj.setBlock(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ, Block.stone.blockID);
                                   
                                        IsOre = true;
                                    }
                                    
                                    if(worldObj.getBlockId(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ) == Block.brick.blockID)     
                                    {
                                        worldObj.setBlock(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ, Block.netherBrick.blockID);
                                    
                                        IsOre = true;
                                    }
                                    
                                    if(worldObj.getBlockId(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ) == Block.stairsBrick.blockID)     
                                    {
                                        worldObj.setBlock(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ, Block.stairsNetherBrick.blockID);
                                    
                                        IsOre = true;
                                    }
                                     
                                    if(worldObj.getBlockId(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ) == Block.oreIron.blockID)     
                                    {
                                        worldObj.setBlock(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ, 0);
                                        
                                        EntityItem Replacement = new EntityItem(worldObj, MovingObjectPosition.blockX + 0, MovingObjectPosition.blockY + 0.5, MovingObjectPosition.blockZ + 0, new ItemStack(Item.ingotIron));
                                        worldObj.spawnEntityInWorld(Replacement);
                                        
                                        IsOre = true;
                                    }
                                    
                                    if(worldObj.getBlockId(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ) == Block.blockClay.blockID)     
                                    {
                                        worldObj.setBlock(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ, 0);
                                        
                                        EntityItem Replacement = new EntityItem(worldObj, MovingObjectPosition.blockX + 0, MovingObjectPosition.blockY + 0.5, MovingObjectPosition.blockZ + 0, new ItemStack(Item.brick, 4));
                                        worldObj.spawnEntityInWorld(Replacement);
                                        
                                        IsOre = true;
                                    }
                                    
                                    if(worldObj.getBlockId(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ) == Block.oreGold.blockID)     
                                    {
                                        worldObj.setBlock(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ, 0);
                                        
                                        EntityItem Replacement = new EntityItem(worldObj, MovingObjectPosition.blockX + 0, MovingObjectPosition.blockY + 0.5, MovingObjectPosition.blockZ + 0, new ItemStack(Item.ingotGold));
                                        worldObj.spawnEntityInWorld(Replacement);
                                        
                                        IsOre = true;
                                    }
                                    
                                    if(worldObj.getBlockId(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ) == Block.wood.blockID)     
                                    {
                                        worldObj.setBlock(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ, 0);
                                        
                                        EntityItem Replacement = new EntityItem(worldObj, MovingObjectPosition.blockX + 0, MovingObjectPosition.blockY + 0.5, MovingObjectPosition.blockZ + 0, new ItemStack(Item.coal, 1, 1));
                                        worldObj.spawnEntityInWorld(Replacement);
                                        
                                        IsOre = true;
                                    }
                                    
                                    if(worldObj.getBlockId(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ) == Block.tnt.blockID)     
                                    {
                                        worldObj.setBlockToAir(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ);
                                        
                                        EntityTNTPrimed Tnt = new EntityTNTPrimed(worldObj);
                                        
                                        Tnt.setPosition(MovingObjectPosition.blockX, MovingObjectPosition.blockY, MovingObjectPosition.blockZ);
                                 
                                        worldObj.spawnEntityInWorld(Tnt);
                                        
                                        IsOre = true;
                                    }
                                    
                                    if(Var13 == 0 || Var13 == Block.vine.blockID || Var13 == Block.grass.blockID)
                                    {
                                        if(IsOre == false)
                                        {
                                            worldObj.setBlock(Var10, Var11, Var12, Block.fire.blockID);
                                        }
                                    }
                                    
                                break;
                            }
                        }
                    }
                }

                posX += motionX;
                posY += motionY;
                posZ += motionZ;
                
                Var6 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
                
                rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0D / Math.PI);

                for(rotationPitch = (float) (Math.atan2(motionY, (double) Var6) * 180.0D / Math.PI); rotationPitch - prevRotationPitch < -180.0F; prevRotationPitch -= 360.0F)
                {
                    
                }

                while(rotationPitch - prevRotationPitch >= 180.0F)
                {
                    prevRotationPitch += 360.0F;
                }

                while(rotationYaw - prevRotationYaw < -180.0F)
                {
                    prevRotationYaw -= 360.0F;
                }

                while(rotationYaw - prevRotationYaw >= 180.0F)
                {
                    prevRotationYaw += 360.0F;
                }

                rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
                rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
               
                float Var8 = 0.99F;
                
                Var5 = 0.05F;


                motionX *= (double) Var8;
                motionY *= (double) Var8;
                motionZ *= (double) Var8;
                motionY -= (double) Var5;
                
                setPosition(posX, posY, posZ);
                doBlockCollisions();
                {
                    for(int Buffer = 0; Buffer < 4; ++Buffer)
                    {
                        Var7 = 0.25F;
                        worldObj.spawnParticle("smoke", posX - motionX * (double) Var7, posY - motionY * (double) Var7, posZ - motionZ * (double) Var7, motionX, motionY,motionZ);         
                    }

                    Var8 = 0.8F;
                }
            }
        }
       
        {
            MovingObjectPosition MovingObjectPositionTwo = worldObj.rayTraceBlocks_do_do(worldObj.getWorldVec3Pool().getVecFromPool(posX, posY, posZ),worldObj.getWorldVec3Pool().getVecFromPool(posX + motionX, posY + motionY,posZ + motionZ), true, false);           
            if(MovingObjectPositionTwo != null && MovingObjectPositionTwo.entityHit == null)
            {
                if(dataWatcher.getWatchableObjectInt(16) == Block.waterMoving.blockID || dataWatcher.getWatchableObjectInt(16) == Block.waterStill.blockID)  
                {
                    if(worldObj.getBlockId(MovingObjectPositionTwo.blockX, MovingObjectPositionTwo.blockY, MovingObjectPositionTwo.blockZ) == Block.lavaStill.blockID)
                    {
                        if(!worldObj.isRemote) worldObj.setBlock(MovingObjectPositionTwo.blockX, MovingObjectPositionTwo.blockY, MovingObjectPositionTwo.blockZ, Block.obsidian.blockID);  
                        {
                            if(!worldObj.isRemote) worldObj.playSoundEffect((double) ((float) MovingObjectPositionTwo.blockX + 0.5F),(double) ((float) MovingObjectPositionTwo.blockY + 0.5F), (double) ((float) MovingObjectPositionTwo.blockZ + 0.5F), "random.fizz", 0.5F,2.6F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.8F);
                            {
                                for(int S = 0; S < 8; ++S)
                                {
                                    worldObj.spawnParticle("largesmoke", (double) MovingObjectPositionTwo.blockX + Math.random(),
                                            (double) MovingObjectPositionTwo.blockY + 1.2D, (double) MovingObjectPositionTwo.blockZ + Math.random(), 0.0D, 0.0D, 0.0D);
                                }
                                
                                setDead();
                            }
                        }
                    }
                    
                    if(worldObj.getBlockId(MovingObjectPositionTwo.blockX, MovingObjectPositionTwo.blockY, MovingObjectPositionTwo.blockZ) == Block.lavaMoving.blockID)
                    {
                        if(!worldObj.isRemote)
                        {
                            worldObj.setBlock(MovingObjectPositionTwo.blockX, MovingObjectPositionTwo.blockY, MovingObjectPositionTwo.blockZ, Block.cobblestone.blockID);
                        }

                        if(!worldObj.isRemote)
                        {
                            worldObj.playSoundEffect((double) ((float) MovingObjectPositionTwo.blockX + 0.5F),(double) ((float) MovingObjectPositionTwo.blockY + 0.5F), (double) ((float) MovingObjectPositionTwo.blockZ + 0.5F), "random.fizz", 0.5F, 2.6F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.8F);
                        }
                                 
                        for(int S = 0; S < 8; ++S)
                        {
                            worldObj.spawnParticle("largesmoke", (double) MovingObjectPositionTwo.blockX + Math.random(),(double) MovingObjectPositionTwo.blockY + 1.2D, (double) MovingObjectPositionTwo.blockZ + Math.random(), 0.0D, 0.0D, 0.0D);
                        }
                        setDead();
                    }
                }
                
                else if(dataWatcher.getWatchableObjectInt(16) == Block.lavaMoving.blockID || dataWatcher.getWatchableObjectInt(16) == Block.lavaStill.blockID)    
                {
                    if(worldObj.getBlockId(MovingObjectPositionTwo.blockX, MovingObjectPositionTwo.blockY, MovingObjectPositionTwo.blockZ) == Block.waterMoving.blockID || worldObj.getBlockId(MovingObjectPositionTwo.blockX, MovingObjectPositionTwo.blockY, MovingObjectPositionTwo.blockZ) == Block.waterStill.blockID)   
                    {
                        if(!worldObj.isRemote)
                        {
                            worldObj.setBlock(MovingObjectPositionTwo.blockX, MovingObjectPositionTwo.blockY, MovingObjectPositionTwo.blockZ, Block.stone.blockID);
                        }
                            
                        if(!worldObj.isRemote)
                        {
                            worldObj.playSoundEffect((double) ((float) MovingObjectPositionTwo.blockX + 0.5F),(double) ((float) MovingObjectPositionTwo.blockY + 0.5F), (double) ((float) MovingObjectPositionTwo.blockZ + 0.5F), "random.fizz", 0.5F,2.6F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.8F);
                        }
                               
                        for(int S = 0; S < 8; ++S)
                        {
                            worldObj.spawnParticle("largesmoke", (double) MovingObjectPositionTwo.blockX + Math.random(),  (double) MovingObjectPositionTwo.blockY + 1.2D, (double) MovingObjectPositionTwo.blockZ + Math.random(), 0.0D, 0.0D, 0.0D);       
                        }
                        
                        setDead();
                    }
                }
            }
        }
    }

    public void writeEntityToNBT(NBTTagCompound NBTTagCompound)
    {
        NBTTagCompound.setByte("InGround", (byte) (InGround ? 1 : 0));
        NBTTagCompound.setDouble("Damage", Damage);
        NBTTagCompound.setByteArray("Particle", Particle);
        NBTTagCompound Tag = new NBTTagCompound();

        LiquidStored.writeToNBT(Tag);

        NBTTagCompound.setTag("LiquidData", Tag);
    }

    public int getLiquidItemID()
    {
        return dataWatcher.getWatchableObjectInt(16);
    }

    public void readEntityFromNBT(NBTTagCompound NBTTagCompound)
    {
        InGround = NBTTagCompound.getByte("InGround") == 1;
        Particle = NBTTagCompound.getByteArray("Particle");

        if(NBTTagCompound.hasKey("Damage"))
        {
            Damage = NBTTagCompound.getDouble("Damage");
        }

        LiquidStored = LiquidStack.loadLiquidStackFromNBT((NBTTagCompound) NBTTagCompound.getTag("LiquidData"));
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }

    public float getShadowSize()
    {
        return 0.0F;
    }

    public void setDamage(double Par1)
    {
        Damage = Par1;
    }

    public double getDamage()
    {
        return Damage;
    }

    public void setKnockbackStrength(int Par1)
    {
        KnockbackStrength = Par1;
    }

    public boolean canAttackWithItem()
    {
        return false;
    }
}
