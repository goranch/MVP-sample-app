
package com.goranch.shazammvp.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Images implements Serializable{

    @SerializedName("default")
    @Expose
    private String _default;

    /**
     * 
     * @return
     *     The default image
     */
    public String getDefault() {
        return _default;
    }

}
