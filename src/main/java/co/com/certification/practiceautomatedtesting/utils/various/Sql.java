package co.com.certification.practiceautomatedtesting.utils.various;

public class Sql {

    public static String getAllData(String columns, String table) {
        return String.format("SELECT %s FROM %s", columns, table);
    }
}
