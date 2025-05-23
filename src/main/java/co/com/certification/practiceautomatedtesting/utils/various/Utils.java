package co.com.certification.practiceautomatedtesting.utils.various;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@NoArgsConstructor
public class Utils {
    public static int getRandomNumberAnInterval(int upperLimit) {
        return new Random().nextInt(upperLimit);
    }

    public static List<String> getInsertionOrderInADataTable() {
        List<String> stringList = new ArrayList<>();
        stringList.add("user");
        stringList.add("password");
        stringList.add("amount");
        stringList.add("firstname");
        stringList.add("lastname");
        stringList.add("postalcode");
        stringList.add("confirmationmessage");
        return stringList;
    }

    public static List<Map<String, Object>> convertResultsetToList(ResultSet resultSet) {
        final String MESSAGE_CONNECTION = "The result set %s converted to a list. ";
        List<Map<String, Object>> mapList = new ArrayList<>();
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(resultSetMetaData.getColumnLabel(i).toLowerCase(), resultSet.getObject(i));
                }
                mapList.add(map);
            }
            log.info(String.format(MESSAGE_CONNECTION, "was"));
        } catch (SQLException sqlException) {
            log.error(String.format(MESSAGE_CONNECTION, "was not").concat(String.valueOf(sqlException)));
        }
        return mapList;
    }
}
