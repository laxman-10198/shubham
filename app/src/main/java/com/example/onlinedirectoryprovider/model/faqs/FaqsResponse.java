
package com.example.onlinedirectoryprovider.model.faqs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class FaqsResponse {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

}
