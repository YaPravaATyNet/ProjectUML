package com.example.projectuml;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Target implements Serializable {
    private String name;
    private TargetType type;
    private int quantity;
    private int hours;
    private int days;
    private GregorianCalendar start;
    private int progress = 0;
    private TargetState state;

    public Target(String name, TargetType type, int quantity, int hours, int day, int progress, TargetState state) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.hours = hours;
        this.days = day;
        this.start = new GregorianCalendar();
        this.progress = progress;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TargetType getType() {
        return type;
    }

    public void setType(TargetType type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public GregorianCalendar getStart() {
        return start;
    }

    public void setStart(GregorianCalendar start) {
        //this.start = start;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getIdea() {
        String formDay = " дней, ";
        String formHour = " часов ";
        String formType = " юнитов за ";
        if (days < 5 && days > 1)
            formDay = " дня, ";
        if (hours < 5 && hours > 1)
            formHour = " часа ";
        if (type == TargetType.WORDS) {
            formType = " слов за ";
        }
        return quantity + formType + days + formDay + hours + formHour;
    }

    @Override
    public String toString() {
        return name + '\n' + getIdea();
    }
}
