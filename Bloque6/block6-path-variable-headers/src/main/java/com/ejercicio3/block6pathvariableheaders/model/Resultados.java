package com.ejercicio3.block6pathvariableheaders.model;

import java.util.List;

public class Resultados {

    private String body;

    private List<String> headers;

    private List<String> requestParams;

    public Resultados(String body, List<String> headers, List<String> requestParams) {
        this.body = body;
        this.headers = headers;
        this.requestParams = requestParams;
    }

    public String getBody() {
        return body;
    }

    public void setUser(String body) {
        this.body = body;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<String> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(List<String> requestParams) {
        this.requestParams = requestParams;
    }

    @Override
    public String toString() {
        return "Resultados{" +
                "user=" + body +
                ", headers=" + headers +
                ", requestParams=" + requestParams +
                '}';
    }
}
