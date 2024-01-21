package pl.mmazur.secrets;

public class TrelloSecrets {

    private TrelloSecrets() {

    }

    private static final String KEY = "616ae19a7c06bb54c643bda8716af5ce";
    private static final String TOKEN = "ATTA92d66891168ad006998aa1a680fc77852ac92b3f483cdf091fba2fb9b8ff207eCDBF510B";

    public static String getKey() {
        return KEY;
    }

    public static String getToken() {
        return TOKEN;
    }
}
