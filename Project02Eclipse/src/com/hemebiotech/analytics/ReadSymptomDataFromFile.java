package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Simple brute force implementation
 *
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	private String filepath;

	/**
	 * @param filepath a full or partial path to file with symptom strings in it, one per line
	 */
	public ReadSymptomDataFromFile(String filepath) {
		this.filepath = filepath;
	}

	/**
	 *
	 * Simple implementation of {@link ISymptomReader#GetSymptoms()}
	 * @return a map that contains the analytics
	 */
	@Override
	public Map<String, Integer> GetSymptoms() {
		SortedMap<String, Integer> result = new TreeMap<>();

		if (filepath != null) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(filepath));
				String line = reader.readLine();

				while (line != null) {
					result.putIfAbsent(line, 0);
					result.put(line, result.get(line) + 1);
					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * Saves the content of the analytics in a file.
	 *
	 * This simple implementation uses a FileWriter to write line by line each analytic in the file.
	 *
	 * @param data map that contains the analytics
	 */
	public void saver(Map<String, Integer> data) {

		Set<SortedMap.Entry<String, Integer>> result = data.entrySet();

		for (SortedMap.Entry<String, Integer> it : result) {
			System.out.println(String.format(" le mot %s a %s occurences", it.getKey(), it.getValue()));
		}


		try (FileWriter writer = new FileWriter("result.out")) {


			for (SortedMap.Entry<String, Integer> occurence : result) {
				String line = String.format(" le mot %s a %s occurences%s", occurence.getKey(), occurence.getValue(), System.lineSeparator());
				System.out.println(line);
				writer.write(line);
			}


		} catch (IOException e) {

		}
	}
}
