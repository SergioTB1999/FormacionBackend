package com.bosonit.trip_backend.errors;

import java.util.Date;

public class CustomError {

    Date timestap;
    int HttpCode;
    String mensaje;

    public CustomError(Date timestap, int httpCode, String mensaje) {
        this.timestap = timestap;
        HttpCode = httpCode;
        this.mensaje = mensaje;
    }

    public Date getTimestap() {
        return timestap;
    }

    public void setTimestap(Date timestap) {
        this.timestap = timestap;
    }

    public int getHttpCode() {
        return HttpCode;
    }

    public void setHttpCode(int httpCode) {
        HttpCode = httpCode;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}