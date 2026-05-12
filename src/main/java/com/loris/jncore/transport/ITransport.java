package com.loris.jncore.transport;

public interface ITransport {
    boolean connect() throws Exception;
    void disconnect();
    void send(String data) throws Exception;
    String readLine() throws Exception;
    boolean isConnected();
}