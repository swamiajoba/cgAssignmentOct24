package com.manju;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Mapping {
	public static void main(String[] args) {
		List<String> locations = Arrays.asList(new String[]{"Pune","Mumbai","Chennai","Banglore","Noida"});
		
		Stream<String> lstr=locations.stream();
		
		// Passing Function lambda to Stream map method
		Function<String,Integer> fin=(String location)->{ return location.length();};
		Stream<Integer> istr=lstr.map(fin);
		
		List<Integer> lenl=istr.toList();  // Converting Stream to List
		System.out.println(lenl);

	}
}
