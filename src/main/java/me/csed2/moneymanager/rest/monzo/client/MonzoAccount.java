package me.csed2.moneymanager.rest.monzo.client;

import com.google.gson.JsonObject;
import lombok.Getter;
import me.csed2.moneymanager.rest.Account;
import me.csed2.moneymanager.rest.JsonWrapped;

import java.time.Instant;
import java.util.Date;

/**
 *
 * Basic (ish)? POJO for accounts pulled by Monzo.
 *
 * The most common things are stored here for simplicity; the most useful thing here is the ID which can then be used
 * in a request back to Monzo to get either the Balance or the Transaction details.
 *
 * In addition to the three things listed here, the JsonObject stores the following things:
 *
 * id (str), closed (bool), created (date/str), description (str), type (str), currency (str), country code (str),
 * owners (array): {user_id (str), preferred name (str), preferred first name (str)}, account_number (str),
 * sort_code (str), payment_details (array): {locale_uk (array) {account_number (str), sort_code (str)}}.
 *
 * To get any of these, call the following method: wrappedObject.get(KEY).getAs(String / Array).
 *
 * @author Ollie
 * @since 26/03/20
 */
@Getter
public class MonzoAccount implements Account, JsonWrapped {

    private JsonObject wrappedObject;

    private String id;

    private String description;

    private Date created;

    public MonzoAccount(JsonObject object) {
        this.wrappedObject = object;
        this.id = object.get("id").getAsString();
        this.description = object.get("description").getAsString();
        this.created = Date.from(Instant.parse(object.get("created").getAsString()));
    }

    public String toFormattedString() {
        return "ID: " + id + "\n" +
                "  Description: " + id + "\n"
                + "  Created: " + created.toString() + "\n"
                + "  Account Number: " + wrappedObject.get("account_number").getAsInt() + "\n"
                + "  Sort Code: " + wrappedObject.get("sort_code").getAsInt();
    }
}
