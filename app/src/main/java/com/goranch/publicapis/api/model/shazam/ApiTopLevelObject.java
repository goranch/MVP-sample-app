
package com.goranch.publicapis.api.model.shazam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class ApiTopLevelObject implements Serializable {

    @SerializedName("chart")
    @Expose
    private ArrayList<Item> chart = new ArrayList<Item>();

    /**
     * 
     * @return
     *     The chart
     */
    public ArrayList<Item> getChart() {
        return chart;
    }


}