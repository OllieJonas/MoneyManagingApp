package me.csed2.moneymanager.rest.monzo;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class MonzoDetails {

    public static final String CLIENT_ID = "oauth2client_00009tCzlbhKRvzmyNTqAz";
    public static final String CLIENT_SECRET = "mnzpub.oOEY253Ors+PvYpBaPwWQ9wet9fLwcLtkT/eRMDpen4mw05lufj57eTq+RtZ6Uad4mKjZIekBvP+KCwa5FD6";
    public static final String REDIRECT_URI = "http://localhost:8080/oauth/callback";

    public static final HashMap<Object, Object> MAP = new HashMap<>();

    static {
        MAP.put("CLIENT_ID", CLIENT_ID);
        MAP.put("CLIENT_SECRET", CLIENT_SECRET);
    }
}
