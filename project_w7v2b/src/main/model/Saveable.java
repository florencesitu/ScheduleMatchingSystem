package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface Saveable {
    String save(String filename, List<String> lines) throws IOException;
}
