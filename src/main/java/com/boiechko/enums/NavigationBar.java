package com.boiechko.enums;

public enum NavigationBar {
    PROFILE(1), ORDERS(2), PERSON_INFO(3), PERSON_CHANGE_PASSWORD(4),
    ADDRESSES_OF_PERSON(5), UNDEFINED(-1);

    int numberOfSection;

    NavigationBar(int numberOfSection) {
        this.numberOfSection = numberOfSection;
    }

    public int getNumberOfSection() {
        return numberOfSection;
    }
}
