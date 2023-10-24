package com.formation.urlshortener.personalexception;

import java.io.Serial;

public class InvalidUrlException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;
    private String method;
    // private Socket socket;
    // private SocketAddress clientUrl;
    // private InetAddress clientIp;
    // private String erreurType;
    static final String MESSAGE = "invalid url";

    public InvalidUrlException() {
        super(MESSAGE);
        // this.method = getClass().getEnclosingMethod().getName();
        // this.clientUrl = socket.getLocalSocketAddress();
        // this.clientIp = socket.getInetAddress();
        // this.erreurType = getClass().getName();
    }

    // public Socket getSocket() {
    // return this.socket;
    // }

    // public String getMethod() {
    // return this.method;
    // }

    // public SocketAddress getClientUrl() {
    // return this.clientUrl;
    // }

    // public InetAddress getClientIp() {
    // return this.clientIp;
    // }

    // public String getErreurType() {
    // return this.erreurType;
    // }

    // public void SendToClient() {
    // System.out.println(this);
    // ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MESSAGE);
    // }

    // public String personalMessage() {
    // return getMethod() + ' ' + getClientUrl() + " from " + getClientIp() + ", " +
    // getErreurType() + '\n'
    // + MESSAGE + "(<fichier source> => <ligne de code>)";
    // }
}
