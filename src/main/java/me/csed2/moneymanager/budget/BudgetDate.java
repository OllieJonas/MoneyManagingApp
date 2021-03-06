package me.csed2.moneymanager.budget;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;


@Data @AllArgsConstructor
@JsonAdapter(BudgetDate.Adapter.class)
public class BudgetDate {

    public static final BudgetDate NOW = new BudgetDate(LocalDate.now().getYear(), Month.of(LocalDate.now().getMonthValue()));

    private int year;
    private Month month;

    public static BudgetDate of(Date created) {
        return new BudgetDate(created.getYear(), Month.of(created.getMonth()));
    }

    public static BudgetDate of(LocalDate date) {
        return new BudgetDate(date.getYear(), Month.of(date.getMonthValue()));
    }

    @Override
    public String toString() {
        return month.getName() + " " + year;
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof BudgetDate) && ((BudgetDate) object).getMonth() == this.getMonth() && ((BudgetDate) object).getYear() == this.getYear();
    }


    public enum Month {
        JANUARY("January", 0),
        FEBRUARY("February", 1),
        MARCH("March", 2),
        APRIL("April", 3),
        MAY("May", 4),
        JUNE("June", 5),
        JULY("July", 6),
        AUGUST("August", 7),
        SEPTEMBER("September", 8),
        OCTOBER("October", 9),
        NOVEMBER("November", 10),
        DECEMBER("December", 11);

        private final String name;
        private final int value;

        private static final int SIZE = 12;
        private static final Month[] values = values();

        Month(String name, int value) {
            this.value = value;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }

        public static Month of(LocalDate date) {
            return values[date.getMonthValue() - 1];
        }

        public static Month of(String string) {
            return valueOf(string);
        }

        public static Month of(int date) {
            return values[date - 1];
        }

        public Month previousMonth() {
            return values[((this.ordinal() - 1) + SIZE) % SIZE];
        }

        public Month nextMonth() {
            return values[(this.ordinal() + 1) % SIZE];
        }
    }

    public static class Adapter extends TypeAdapter<BudgetDate> {

        @Override
        public void write(JsonWriter out, BudgetDate value) throws IOException {
            out.beginObject();
            out.name("month").value(value.getMonth().getName());
            out.name("year").value(value.getYear());
            out.endObject();
        }

        @Override
        public BudgetDate read(JsonReader in) throws IOException {
            Month month = null;
            int year = -1;
            in.beginObject();
            while (in.hasNext()) {
                if (in.nextName().equals("month")) {
                    month = Month.of(in.nextString());
                } else if (in.nextName().equals("year")) {
                    year = in.nextInt();
                }
            }
            return new BudgetDate(year, month);
        }
    }
}
