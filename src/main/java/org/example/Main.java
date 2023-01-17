package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ArrayList<AccessLogEntry> accessLogEntries = new ArrayList<>();

        //fourth arg is location of chromedriver
        String baseDirectory = "/Users/jgainey/Documents/325709/repro/log-parse-scratch/cf-7a96c80103a678e21512.cloud_controller.87431aff-776d-454f-a08d-35a2157fd668-20230116-092323-879675/cloud_controller_ng";


        //find all cloud controller access logs, then print the filename + filepath
        File capiLogBaseDir = new File(baseDirectory);
        File[] directoryListing = capiLogBaseDir.listFiles();
        if (directoryListing != null){
            for (File child : directoryListing){
                String fullFileNamePath = child.getAbsolutePath();
                if (fullFileNamePath.contains("nginx-access")){
                    System.out.println("Extracting: " + fullFileNamePath);
                    Extractor extractor = new Extractor(fullFileNamePath);
                    accessLogEntries.addAll(extractor.getAccessLogEntriesList());
                }
            }
        }

        System.out.println("Total Number of entries: " + accessLogEntries.size());

        saveListToAssets(accessLogEntries, "accessLogEntries.csv");

    }

    private static void saveListToAssets(List<AccessLogEntry> list, String filenameToSaveAs) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< list.size(); i++){
            sb.append(list.get(i).toString());
            sb.append("\n");
        }

        File file = new File("assets/"+filenameToSaveAs);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}