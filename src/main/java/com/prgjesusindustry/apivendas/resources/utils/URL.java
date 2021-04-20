package com.prgjesusindustry.apivendas.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
	
	public static String decodeParam(String str) {
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	
	public static List<Integer> decodIntList(String str) {
		return Arrays.asList(str.split(","))
				.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
	}
}
