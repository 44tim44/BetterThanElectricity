package com.bte.mod.capability;

/**
 * Created by Timeout on 2017-08-23.
 */
public class Charge implements ICharge {

    private int charge;
    private int maxCharge;

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
    }

    public int getMaxCharge(){
        return this.maxCharge;
    }

    public void decreaseCharge(){
        if(this.charge > 0)
        {
            this.charge--;
        }
    }

    public void increaseCharge(){
        if(this.charge < this.maxCharge)
        {
            this.charge++;
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
    }

    public int getCharge(){
        return this.charge;
    }
}
