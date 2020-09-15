package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.util.Map;


public class AnalyticsCounter {


	public static void main(String args[]) throws Exception {

		ISymptomReader r = new ReadSymptomDataFromFile("symptoms.txt");

		Map<String,Integer> symptoms = r.GetSymptoms();
		r.saver(symptoms);


	}
}
