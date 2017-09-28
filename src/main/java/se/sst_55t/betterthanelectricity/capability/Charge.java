package se.sst_55t.betterthanelectricity.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import se.sst_55t.betterthanelectricity.BTEMod;
import se.sst_55t.betterthanelectricity.network.PacketUpdateCharge;

/**
 * Created by Timeout on 2017-08-23.
 */
public class Charge implements ICharge {

    private int charge;
    private int maxCharge;
    private boolean dirty = true;

    public Charge(){
        this.charge = 0;
        this.maxCharge = 640;
    }

    public void setMaxCharge(int value){
        if(value < 1)
        {
            this.maxCharge = 1;
        }
        else
        {
            this.maxCharge = value;
        }
        dirty = true;
    }

    public int getMaxCharge(){
        return this.maxCharge;
    }

    public void decreaseCharge(){
        if(this.charge > 0)
        {
            this.charge--;
            dirty = true;
        }
    }

    public void increaseCharge(){
        if(this.charge < this.maxCharge)
        {
            this.charge++;
            dirty = true;
        }
    }

    public void setCharge(int value)
    {
        if(value > this.maxCharge)
        {
            this.charge = this.maxCharge;
        }
        else if(value < 0)
        {
            this.charge = 0;
        }
        else
        {
            this.charge = value;
        }
        dirty = true;
    }

    public int getCharge(){
        return this.charge;
    }

    public void updateClient(EntityPlayer player) {
        if(!player.getEntityWorld().isRemote)
            BTEMod.network.sendToAll(new PacketUpdateCharge(this.charge,this.maxCharge));
        dirty = false;
    }
}
