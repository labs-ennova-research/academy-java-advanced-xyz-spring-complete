package com.ennova_research.academy.xyzspring.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * @author Alberto Ielpo
 */
public class Json {

	public static String writeValuesAsString(Object value) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(value);
		} catch(Exception e) {
			return null;
		}
	}
}
