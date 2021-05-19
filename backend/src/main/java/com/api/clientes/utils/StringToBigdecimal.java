package com.api.clientes.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StringToBigdecimal {

    public BigDecimal convert(String value){
        value = value.replace(",","").replace(",",".");
        if(value == null){
            return null;
        }
        return new BigDecimal(value);
    }
}
