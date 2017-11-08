package pl.codeprime.common;

import com.google.gson.Gson;

public class JSONUtils {
	
	public static <T> Object fromJson(String json, Class<T> classOfT) {
		return  new Gson().fromJson(json, classOfT);
	}
	
	public static <T> String toJson(Object objectToJson) {
		return  new Gson().toJson(objectToJson);
	}

}
