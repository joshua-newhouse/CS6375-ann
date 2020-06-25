package edu.utdallas.cs6375.ann.data;

public class DataException extends Exception {
    public DataException(String msg) {
        super(msg);
    }

    public DataException(Exception e) {
        super(e);
    }
}
