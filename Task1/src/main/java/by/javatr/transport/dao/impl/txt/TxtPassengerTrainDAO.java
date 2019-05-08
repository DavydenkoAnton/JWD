package by.javatr.transport.dao.impl.txt;

import by.javatr.transport.dao.PassengerDAO;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class TxtPassengerDAO implements PassengerDAO {


    public void addPassenger(String name, int baggageCount, int trainID, int trainCarID, int seat) {
        List<String> lines = Arrays.asList("The first line", "The second line");
        Path file = Paths.get("src/main/java/files/txt/the-file-name.txt");
        //Files.write(file, lines, Charset.forName("UTF-8"));
        //Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
    }

    @Override
    public void create() {

    }

    @Override
    public void read() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
