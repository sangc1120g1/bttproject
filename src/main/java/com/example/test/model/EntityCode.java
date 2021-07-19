package com.example.test.model;

import lombok.Data;

@Data
public class EntityCode {

    public static String autoCode(String code , long codeNumber){
        return getHeaderCode(code ,codeNumber);
    }

    private static String getHeaderCode(String code ,long codeNumber){
        if (codeNumber<10){
            return code+"00"+codeNumber;
        }else if (codeNumber<100){
            return code+"0"+codeNumber;
        }else {
            return "BTT" + code+codeNumber;
        }
    }
}
