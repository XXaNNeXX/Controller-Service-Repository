package com.example.productrepository;

import java.util.UUID;

public class IdService {

    public String randomUUID() {
        return UUID.randomUUID().toString();
    }
}
