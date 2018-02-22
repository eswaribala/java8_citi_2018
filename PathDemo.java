package com.polaris.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Path path = Paths.get("F:\\AccentureMayBatch\\JSTLProject");

	

		System.out.format("toString: %s%n", path.toString());
		System.out.format("getFileName: %s%n", path.getFileName());
		System.out.format("getName(0): %s%n", path.getName(0));
		System.out.format("getNameCount: %d%n", path.getNameCount());
		System.out.format("subpath(0,2): %s%n", path.subpath(0,2));
		System.out.format("getParent: %s%n", path.getParent());
		System.out.format("getRoot: %s%n", path.getRoot());
		
		try {
			Files.list(new File(".").toPath())
			 .forEach(System.out::println);
			
			
			Files.walk(new File(".").toPath())
		     .filter(p -> !p.getFileName()
		                    .toString().startsWith("."))
		     .forEach(System.out::println);
			
			Files.lines(new File("./src/com/polaris/utility/PathDemo.java").toPath())
		     .map(s -> s.trim())
		     .filter(s -> !s.isEmpty())
		     .forEach(System.out::println);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//finding path and sub path into string
		 Path start = Paths.get(".");
		    int maxDepth = 5;
		    try (Stream<Path> stream = Files.find(start, maxDepth, (path2, attr) -> String.valueOf(path2).endsWith(".java"))) 
		    {
		        String joined = stream
		                .sorted()
		                .map(String::valueOf)
		                .collect(Collectors.joining("; "));
		        System.out.println("Found: " + joined);
		    } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    
		
		Path source  = Paths.get("./src/com/polaris/utility/PathDemo.java");
		Path target = Paths.get("F:/yatrabakup");
		
/*
		try {
		   // Files.copy(source, target);
		} catch(FileAlreadyExistsException fae) {
		    fae.printStackTrace();
		} catch (IOException e) {
		    // something else went wrong
		    e.printStackTrace();
		}
		*/
		try (BufferedReader reader = Files.newBufferedReader(Paths.get("f:\\yatrabakup\\EmployeeData.csv"))) {
		    reader.lines().map(String::toLowerCase).forEach(System.out::println);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
