package com.dayasolusi.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Status {
    SUCCESS(0),
    FAILED(1);

    private final int id;

    Status(int id){
        this.id = id;
    }

    @JsonValue
    public int getId(){
        return id;
    }

    @JsonCreator  // ✅ Deserialize from JSON (0 → SUCCESS, 1 → FAILED)
    public static Status fromId(int id) {
        return Arrays.stream(Status.values())
                .filter(s -> s.id == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown status ID: " + id));
        }
    }
