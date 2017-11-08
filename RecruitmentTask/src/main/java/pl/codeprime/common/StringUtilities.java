package pl.codeprime.common;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class StringUtilities {
	
	public static boolean isNotEmpty(String... values) {
		
		 boolean empty = Arrays.asList(values)
				 					.stream()
				 						.map(StringUtilities::isNotEmpty)
				 							.collect(Collectors.toList()).contains(false);
		 
		 return !empty;
	}
	
	public static boolean isNotEmpty(String  value) {
		
		boolean notEmpty = StringUtils.isNotEmpty(value) && !"null".equalsIgnoreCase(value);
		
		return notEmpty;
	}

}
