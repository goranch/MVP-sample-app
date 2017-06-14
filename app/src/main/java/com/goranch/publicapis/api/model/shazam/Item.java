
package com.goranch.publicapis.api.model.shazam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Item implements Serializable {

    @SerializedName("heading")
    @Expose
    private Heading heading;
    @SerializedName("images")
    @Expose
    private Images images;

    @SerializedName("url")
    @Expose
    private String url;

    /**
     * 
     * @return
     *     The heading
     */
    public Heading getHeading() {
        return heading;
    }


    /**
     * 
     * @return
     *     The images
     */
    public Images getImages() {
        return images;
    }


    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }



}
