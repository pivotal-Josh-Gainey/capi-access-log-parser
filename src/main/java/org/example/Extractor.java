package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Extractor {

    private File file;
    private Scanner scanner;

    ArrayList<AccessLogEntry> accessLogEntries = new ArrayList<>();

    public Extractor(String fileName){
        //reference the logs
        file = new File("" + fileName);

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        parse();
    }

    public void parse(){
        do{
            String line = scanner.nextLine();

            int firstSpaceLocation=line.indexOf(" ");
            String host = line.substring(0,firstSpaceLocation).trim();

            int locationOfFirstDoubleQuote = line.indexOf("\"");
            int locationOfSecondDoubleQuote = line.indexOf("\"", locationOfFirstDoubleQuote + 1);

            String pathFullWithQuotes = line.substring(locationOfFirstDoubleQuote,locationOfSecondDoubleQuote).trim();

            int locationOfFirstSpace = pathFullWithQuotes.indexOf(" ");
            int locationOfSecondSpace = pathFullWithQuotes.indexOf(" ", locationOfFirstSpace + 1);

            String path = pathFullWithQuotes.substring(locationOfFirstSpace,locationOfSecondSpace).trim();

            int lastSpaceLocation=line.indexOf("response_time:");
            float responseTimeFloat = Float.parseFloat(line.substring(lastSpaceLocation + 14,line.length()));

            BigDecimal responseTime = new BigDecimal(responseTimeFloat);

            AccessLogEntry accessLogEntry = new AccessLogEntry();
            accessLogEntry.setHost(host);
            accessLogEntry.setPath(path);
            accessLogEntry.setResponseTime(responseTime);
            accessLogEntry.setFullUrl(host+path);

            accessLogEntries.add(accessLogEntry);

        }while(scanner.hasNext());
    }

    public ArrayList<AccessLogEntry> getAccessLogEntriesList(){
        return accessLogEntries;
    }

}
