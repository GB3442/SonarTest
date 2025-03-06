package com.tdit.ExceptionHandeling;

public class SellerNotFoundException extends RuntimeException{
   public SellerNotFoundException(String msg){
        super(msg);
    }
}
