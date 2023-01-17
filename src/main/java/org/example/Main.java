package org.example;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<AccessLogEntry> accessLogEntries = new ArrayList<>();

        //find all cloud controller access logs, then print the filename + filepath
        File capiLogBaseDir = new File("/Users/jgainey/Documents/325709/repro/log-parse-scratch/cf-7a96c80103a678e21512.cloud_controller.87431aff-776d-454f-a08d-35a2157fd668-20230116-092323-879675/cloud_controller_ng");
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

        for (AccessLogEntry entry:accessLogEntries) {
            System.out.println(entry.getFullUrl());
        }

    }
}