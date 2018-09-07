package com.example.user.nettydemo.test;

public class LoginMessage {

    private int terminal;
    private String devicetoken;
    private String account;
    private String key;
    private String appId;
    private String service;

    public int getTerminal() {

        return terminal;
    }

    public void setTerminal(int terminal) {
        this.terminal = terminal;
    }

    public String getDevicetoken() {


        return devicetoken == null ? "" : devicetoken;
    }

    public void setDevicetoken(String devicetoken) {
        this.devicetoken = devicetoken;
    }

    public String getAccount() {


        return account == null ? "" : account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getKey() {


        return key == null ? "" : key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAppId() {


        return appId == null ? "" : appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getService() {


        return service == null ? "" : service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
