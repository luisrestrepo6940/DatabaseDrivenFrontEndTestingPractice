package co.com.certification.practiceautomatedtesting.utils.various;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Slf4j
public class CsvReader {
    public static List<String[]> getCsvData(String csvPath) throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(csvPath));
        List<String[]> listData = null;
        try {
            listData = csvReader.readAll();
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        csvReader.close();
        return listData;
    }
}
