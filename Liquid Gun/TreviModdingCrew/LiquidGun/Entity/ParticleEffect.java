package TreviModdingCrew.LiquidGun.Entity;

import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.world.World;

public class ParticleEffect extends EntitySmokeFX
{

    public ParticleEffect(World World, double Par2, double Par4, double Par6, double Par8, double Par10, double Par12, float Red, float Green, float Blue)
    {
        super(World, Par2, Par4, Par6, Par8, Par10, Par12);

        particleRed = Red;
        particleGreen = Green;
        particleBlue = Blue;
    }
}