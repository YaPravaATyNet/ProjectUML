package com.example.projectuml;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Target implements Serializable {
    private int id;
    private String name;
    private TargetType type;
    private int quantity;
    private int hours;
    private int days;
    private GregorianCalendar start;
    private int progress;
    private TargetState state;

    public Target(int id, String name, TargetType type, int quantity, int hours, int day, String start, int progress, TargetState state) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.hours = hours;
        this.days = day;
        this.start = parseString(start);
        this.progress = progress;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public TargetType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getHours() {
        return hours;
    }

    public int getDays() {
        return days;
    }

    public GregorianCalendar getStart() {
        return start;
    }

    public int getProgress() {
        return progress;
    }

    public TargetState getState() {
        return state;
    }

    public static GregorianCalendar parseString(String str) {
        GregorianCalendar date = new GregorianCalendar();
        date.set(Calendar.YEAR, Integer.parseInt(str.split(" ")[0].split("-")[0]));
        date.set(Calendar.MONTH, Integer.parseInt(str.split(" ")[0].split("-")[1]) - 1);
        date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(str.split(" ")[0].split("-")[2]));
        date.set(Calendar.HOUR_OF_DAY, Integer.parseInt(str.split(" ")[2].split(":")[0]));
        date.set(Calendar.MINUTE, Integer.parseInt(str.split(" ")[2].split(":")[1]));
        date.set(Calendar.SECOND, Integer.parseInt(str.split(" ")[2].split(":")[2]));
        return date;
    }

    public String getDescription() {
        String formDay = " дней, ";
        String formHour = " часов ";
        String formType = " cлов за ";
        if (days % 10 < 5 && days % 10 > 1)
            formDay = " дня, ";
        if (days % 10 == 1)
            formDay = " день, ";
        if (hours % 10 < 5 && hours % 10 > 1)
            formHour = " часа ";
        if (hours % 10 == 1)
            formHour = " час ";
        switch (type) {
            case GRAMMAIR_UNIT:
                formType = " грамматических юнитов за ";
                break;
            case LEXIQUE_UNIT:
                formType = " лексических юнитов за ";
                break;
            case UNIT:
                formType = " юнитов за ";
                break;
        }
        return quantity + formType + days + formDay + hours + formHour;
    }

    @Override
    public String toString() {
        return name + '\n' + getDescription();
    }
}
