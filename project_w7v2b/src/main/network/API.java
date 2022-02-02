package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class API {
    public static BufferedReader br = null;

    public static void main(String[] args) throws IOException {
        try {
            apiData();
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    //effects: append by each \n and print out new lines
    public static void apiData() throws MalformedURLException, IOException {
        String apikey = "fbacc6bdeb6208654f516bcefe2e52df"; //fill this in with the API key they email you
        String londonweatherquery = "https://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=";
        String theURL = londonweatherquery + apikey;
        URL url = new URL(theURL);
        br = new BufferedReader(new InputStreamReader(url.openStream()));
        String lines;
        StringBuilder sb = new StringBuilder();
        while ((lines = br.readLine()) != null) {
            String s = lines.substring(145, 158);
            String s2 = lines.substring(159, 174);
            String s3 = lines.substring(175, 188);
            String s4 = lines.substring(189, 206);
            sb.append(s + "\n");
            sb.append(s2 + "\n");
            sb.append(s3 + "\n");
            sb.append(s4 + "\n");
        }

        System.out.println(sb);
    }



}
