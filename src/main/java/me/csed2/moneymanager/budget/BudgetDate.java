package me.csed2.moneymanager.budget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor
public class BudgetDate {

    public static final BudgetDate CURRENT_DATE = new BudgetDate(LocalDate.now().getYear(), Month.of(LocalDate.now()));

    private int year;
    private Month month;

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

        String name;
        int id;

        private static int SIZE = 12;
        private static Month[] values = values();

        Month(String name, int id) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public static Month of(LocalDate date) {
            return values[date.getMonthValue() - 1];
        }

        public Month previousMonth() {
            return values[((this.ordinal() - 1) + SIZE) % SIZE];
        }

        public Month nextMonth() {
            return values[(this.ordinal() + 1) % SIZE];
        }
    }
}
