package com.example.user.nettydemo.test;

public class Data {


    /**
     * host : 192.168.1.124
     * wsport : 6988
     * s : 8rkfm6
     * sid : 5b6d47c4091e270e51f8b1a2
     */

    private String host;
    private int wsport;
    private String s;
    private String sid;

    private int tcpport;

    public int getTcpport() {

        return tcpport;
    }

    public void setTcpport(int tcpport) {
        this.tcpport = tcpport;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getWsport() {
        return wsport;
    }

    public void setWsport(int wsport) {
        this.wsport = wsport;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }


    @Override
    public String toString() {
        return "Data{" +
                "host='" + host + '\'' +
                ", wsport=" + wsport +
                ", s='" + s + '\'' +
                ", sid='" + sid + '\'' +
                '}';
    }
}
