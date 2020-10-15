package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class WriteSymptomsInToFile implements ISymptomWriter {

    /**
     * Saves the content of the analytics in a file.
     *
     * This simple implementation uses a FileWriter to write line by line each analytic in the file.
     *
     * @param data map that contains the analytics
     */

    @Override
    public void saver(Map<String, Integer>data) {

        Set<SortedMap.Entry<String, Integer>> result = data.entrySet();

        for(SortedMap.Entry<String, Integer> it :result){

            System.out.println(String.format(" le mot %s a %s occurences", it.getKey(), it.getValue()));
        }

        String filename = "result.out";

        try(FileWriter writer = new FileWriter(filename)){

            for (SortedMap.Entry<String, Integer> occurence : result) {
                String line = String.format(" le mot %s a %s occurences%s", occurence.getKey(), occurence.getValue(), System.lineSeparator());
                System.out.print(line);
                writer.write(line);
            }

        } catch(IOException e){
            System.err.println(String.format("An error occured when saving in file %s ",filename));

        }
    }
}