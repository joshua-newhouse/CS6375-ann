package edu.utdallas.cs6375.ann.network;

public class ANNException extends Exception {
    public ANNException(String msg) {
        super(msg);
    }

    public ANNException(Exception e) {
        super(e);
    }
}
