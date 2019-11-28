package com.example.service.exception;

public class TransferException extends Exception{
    public TransferException(String reason) {
        super(reason);
    }
    public TransferException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
