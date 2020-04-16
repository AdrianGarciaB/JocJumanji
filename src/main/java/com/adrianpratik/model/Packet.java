package com.adrianpratik.model;

import java.io.Serializable;

public class Packet implements Serializable{
    public static final long serialVersionUID = 42L;
    public int responseCode;

    public Packet(int responseCode, Object data) {
        this.responseCode = responseCode;
    }
}
