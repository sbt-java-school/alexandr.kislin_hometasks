package sbt.kislin.hw3_multimap_generics;

/**
 * Created by Angel on 17.08.2016.
 */
public class Truck<T> {
    private long mId;
    private T mModel;
    private int mCapacity;

    public Truck(long mId, T mModel, int mCapacity) {
        this.mId = mId;
        this.mModel = mModel;
        this.mCapacity = mCapacity;
    }

    public long getId() {
        return mId;
    }

    public T getModel() {
        return mModel;
    }

    public int getCapacity() {
        return mCapacity;
    }

    @Override
    public String toString() {
        return "Truck id is " + getId() + ", Model is " + getModel() + ", Capacity is " + getCapacity() +"\n";
    }
}
