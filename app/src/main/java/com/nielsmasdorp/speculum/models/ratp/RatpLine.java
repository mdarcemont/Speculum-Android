package com.nielsmasdorp.speculum.models.ratp;

public class RatpLine {

    private String type;
    private String code;

    public RatpLine(String type, String code) {
        this.type = type;
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
