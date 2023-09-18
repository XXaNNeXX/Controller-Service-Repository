package com.example.productrepository;

import java.util.UUID;

public record IdService() {

    public String randomUUID() {
        return UUID.randomUUID().toString();
    }
}
