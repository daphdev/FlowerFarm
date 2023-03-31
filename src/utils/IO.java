package utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Supplier;

public class IO {

    public IO() {}

    public static <T> List<T> readFile(Supplier<T> supp, String path) {
        // Tao folder rieng cho tung user khi moi dang ky
        if (path.endsWith("user.csv")) {
            createFolder(path.substring(0, path.length() - 9));
        }
        createFile(path);
        List<T> list = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader()
                     .withSkipHeaderRecord().withIgnoreHeaderCase().withTrim())) {
            for (CSVRecord csvRecord : csvParser.getRecords()) {
                T t = supp.get();
                for (Entry<String, String> entry : csvRecord.toMap().entrySet()) {
                    BeanUtils.setProperty(t, entry.getKey(), entry.getValue());
                }
                list.add(t);
            }
        } catch (IOException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static <T> void writeFile(List<T> list, String path, String[] header) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(header))) {
            for (T t : list) {
                Object[] values = new Object[header.length];
                for (int i = 0; i < header.length; i++) {
                    values[i] = FieldUtils.readField(t, header[i], true);
                }
                csvPrinter.printRecord(values);
            }
            csvPrinter.flush();
        } catch (IOException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void createFolder(String path) {
        try {
            Files.createDirectories(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFile(String path) {
        try {
            if (!Files.exists(Paths.get(path))) {
                Files.createFile(Paths.get(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
