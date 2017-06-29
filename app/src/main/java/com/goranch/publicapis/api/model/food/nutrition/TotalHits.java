package com.goranch.publicapis.api.model.food.nutrition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TotalHits {

    @SerializedName("total_hits")
    @Expose
    private Integer totalHits;
    @SerializedName("max_score")
    @Expose
    private Double maxScore;
    @SerializedName("hits")
    @Expose
    private List<Hit> hits;

    public Integer getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(Integer totalHits) {
        this.totalHits = totalHits;
    }

    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

}
