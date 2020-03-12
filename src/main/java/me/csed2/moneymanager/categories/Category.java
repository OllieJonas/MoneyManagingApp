package me.csed2.moneymanager.categories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.transactions.Transaction;

import java.util.Date;
import java.util.List;

/**
 * Category POJO.
 *
 * Note the use of Lombok for the getters and setters.
 *
 * @author Ollie
 * @since 03/03/2020
 */
@Getter
@Setter
@AllArgsConstructor
public class Category {

    /**
     * The name of the category
     */
    private String name;

    /**
     * The associated ID with the category
     */
    private int id;

    /**
     * Date of creation
     */
    private Date created;

    /**
     * The budget the user has set for this category
     */
    private int budget;

    /**
     * The list of all transactions
     */
    private List<Transaction> transactions;

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public String toFormattedString() {
        return "category: " + name + "  " + "\n  "
                + " id: " + id + "\n  "
                + " created: " + created + "\n  "
                + " budget: £" + Math.round(budget / 100);
    }

    public Transaction getTransactionByName(String transactionName) {
        for (Transaction transaction : transactions) {
            if (transaction.getName().equalsIgnoreCase(transactionName)) {
                return transaction;
            }
        }
        return null;
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    public Transaction update(Transaction entity) {
        transactions.removeIf(transaction -> transaction.getId() == entity.getId());
        transactions.add(entity);
        return entity;
    }

    public void printTransactions() {
        for (Transaction transaction : transactions) {
            transaction.print();
        }
    }
}
