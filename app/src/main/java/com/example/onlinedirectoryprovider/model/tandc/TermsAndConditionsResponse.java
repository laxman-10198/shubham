
package com.example.onlinedirectoryprovider.model.tandc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TermsAndConditionsResponse {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("result")
    @Expose
    private Result result;

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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
