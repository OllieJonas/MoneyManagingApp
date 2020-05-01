package me.csed2.moneymanager.rest.monzo.client;

import lombok.Getter;

public enum MonzoDetails {
    GRANT_TYPE("grant_type", "authorization_code"),
    CLIENT_ID("client_id", "oauth2client_00009tCzlbhKRvzmyNTqAz"),
    CLIENT_SECRET("client_secret", "mnzpub.oOEY253Ors+PvYpBaPwWQ9wet9fLwcLtkT/eRMDpen4mw05lufj57eTq+RtZ6Uad4mKjZIekBvP+KCwa5FD6"),
    REDIRECT_URI("redirect_uri", "http://localhost:8080/oauth/callback"),
    MONZO_API("monzo_api", "https://api.monzo.com");

    public String KEY;

    public String VALUE;

    MonzoDetails(String key, String value) {
        this.KEY = key;
        this.VALUE = value;
    }
}
