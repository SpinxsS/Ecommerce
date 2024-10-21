package com.ecommerce.util;

import java.util.HashMap;
import java.util.Map;

public class Utilidades {
	
	public Map<String, String> parseSpecifications(String specifications) {
	    Map<String, String> specsMap = new HashMap<>();

	    // Eliminar llaves y espacios
	    specifications = specifications.trim().replaceAll("[{}]", "");

	    // Separar por comas
	    String[] entries = specifications.split(", ");

	    for (String entry : entries) {
	        // Separar clave y valor por el signo '='
	        String[] keyValue = entry.split("=");
	        if (keyValue.length == 2) {
	            String key = keyValue[0].trim();
	            String value = keyValue[1].trim();
	            specsMap.put(key, value);
	        }
	    }

	    return specsMap;
	}



}
