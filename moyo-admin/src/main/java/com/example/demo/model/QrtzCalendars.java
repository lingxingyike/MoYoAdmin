package com.example.demo.model;

public class QrtzCalendars extends QrtzCalendarsKey {
    private byte[] calendar;

    public byte[] getCalendar() {
        return calendar;
    }

    public void setCalendar(byte[] calendar) {
        this.calendar = calendar;
    }
}