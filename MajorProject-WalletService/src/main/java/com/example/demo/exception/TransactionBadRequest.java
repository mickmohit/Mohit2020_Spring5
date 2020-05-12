package com.example.demo.exception;

public class TransactionBadRequest extends RuntimeException {

    public TransactionBadRequest(){
        super("TransactionBadRequest");
    }
}
