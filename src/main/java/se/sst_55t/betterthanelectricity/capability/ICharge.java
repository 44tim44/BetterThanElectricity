package se.sst_55t.betterthanelectricity.capability;

/**
 * Created by Timeout on 2017-08-23.
 */
public interface ICharge {
    public void setMaxCharge(int value);

    public int getMaxCharge();

    public void decreaseCharge();

    public void increaseCharge();

    public void setCharge(int value);

    public int getCharge();
}