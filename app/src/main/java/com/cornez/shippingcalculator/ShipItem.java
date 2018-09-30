package com.cornez.shippingcalculator;

/**
 * Created by trishcornez on 6/29/14.
 */
public class ShipItem {

    // SHIPPING CONSTANTS
    static final Double BASE = 3.00;
    static final Double ADDED = .50;
    static final int BASE_WEIGHT = 16;
    static final double EXTRA_OUNCES = 4.0;

    // DATA MEMBERS
    private Integer mWeight;
    private Double mBaseCost;
    private Double mAddedCost;
    private Double mTotalCost;
    // STAN'S ADDITIONS
    private Integer mLength;
    private Integer mWidth;
    private Integer mHeight;

    public ShipItem() {
        mWeight = 0;
        mAddedCost = 0.0;
        mBaseCost = BASE;
        mTotalCost = 0.0;
        //STAN'S ADDITIONS
        mLength = 0;
        mWidth = 0;
        mHeight = 0;
    }

    public void setWeight (int weight){
        mWeight = weight;
        computeCosts();
    }

    // STAN'S ADDITIONS
    public void setLength (int length){
        mLength = length;
        computeCosts();
    }

    public void setWidth (int width){
        mWidth = width;
        computeCosts();
    }

    public void setHeight (int height){
        mHeight = height;
        computeCosts();
    }

    private void computeCosts() {
        mAddedCost = 0.0;
        mBaseCost = BASE;

        if (mWeight <= 0)
            mBaseCost = 0.0;
        else if (mWeight > BASE_WEIGHT)
            mAddedCost = Math.ceil((double) (mWeight - BASE_WEIGHT) / EXTRA_OUNCES) * ADDED;

        mTotalCost = mBaseCost + mAddedCost;
    }

    public Double getBaseCost() {
        return mBaseCost;
    }

    public Double getAddedCost() {
        return mAddedCost;
    }

    public Double getTotalCost() {
        return mTotalCost;
    }

}
