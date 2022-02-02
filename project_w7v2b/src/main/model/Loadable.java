package model;

import java.io.IOException;

public interface Loadable {
    String load(String filename) throws IOException;
}
