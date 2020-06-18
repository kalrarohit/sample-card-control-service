package com.cts.cardcontrol;

public enum RegistrationStatus {

	register("Open"),deregister("Close");
	
	private final String value;

    private RegistrationStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }



}
