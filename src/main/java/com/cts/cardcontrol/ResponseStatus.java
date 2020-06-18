package com.cts.cardcontrol;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResponseStatus {
	SUCCESS("Success"),FAILURE("Failure");
	
	private final String value;

    private ResponseStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    @JsonValue
    public String toString() {
        return getValue();
    }

}
