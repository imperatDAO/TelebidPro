package com.registrationform.util;

public class Pair {
    private int status;
    private String code;

    public Pair(int status,String code){
        this.status = status;
        this.code = code;
    }

    public int getStatus(){
        return status;
    }
    public String getCode(){
        return code;
    }
}
