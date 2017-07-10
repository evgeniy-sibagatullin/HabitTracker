package com.database.android.habittracker.model;

public class Habit {

    private final String title;
    private final int ageWeeks;
    private final int usefulness;
    private final String note;

    public Habit(String title, int ageWeeks) {
        this(title, ageWeeks, 2, "");
    }

    public Habit(String title, int ageWeeks, int usefulness) {
        this(title, ageWeeks, usefulness, "");
    }

    public Habit(String title, int ageWeeks, String note) {
        this(title, ageWeeks, 2, note);
    }

    public Habit(String title, int ageWeeks, int usefulness, String note) {
        this.title = title;
        this.ageWeeks = ageWeeks;
        this.usefulness = usefulness;
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public int getAgeWeeks() {
        return ageWeeks;
    }

    public int getUsefulness() {
        return usefulness;
    }

    public String getNote() {
        return note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Habit habit = (Habit) o;

        return ageWeeks == habit.ageWeeks &&
                usefulness == habit.usefulness &&
                title.equals(habit.title) &&
                (note != null ? note.equals(habit.note) : habit.note == null);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + ageWeeks;
        result = 31 * result + usefulness;
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Habit{" +
                "title='" + title + '\'' +
                ", ageWeeks=" + ageWeeks +
                ", usefulness=" + usefulness +
                ", note='" + note + '\'' +
                '}';
    }
}
