
package com.goranch.publicapis.api.model.food.nutrition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tags {

    @SerializedName("item")
    @Expose
    private String item;
    @SerializedName("measure")
    @Expose
    private Object measure;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("tag_id")
    @Expose
    private Integer tagId;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Object getMeasure() {
        return measure;
    }

    public void setMeasure(Object measure) {
        this.measure = measure;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

}
