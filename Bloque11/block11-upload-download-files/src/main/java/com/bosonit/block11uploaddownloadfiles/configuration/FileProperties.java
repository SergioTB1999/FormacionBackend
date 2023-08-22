package com.bosonit.block11uploaddownloadfiles.configuration;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component

public class FileProperties {
    private String location = "./";
    public void setLocation(String location){
        this.location = location;
    }
}
