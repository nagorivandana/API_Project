package com.cfs.util;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
/**
 * To define all the common methods
 * @author
 *
 */
public class Utilities {
	/**
	 * Convert Response object to JsonPath object
	 * @param resp
	 * @return JsonPath object
	 */
	public static JsonPath rawToJson(Response resp) {
		String respString = resp.asString();
		JsonPath js = new JsonPath(respString);
		return js;
	}

}
