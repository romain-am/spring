package main.com.dragonsoft.utils;

import java.io.File;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomFileUtil {
	/*
	 * Finds the first directory in tree
	 */
	
private Set<String> results = new HashSet<String>();
	
	public String findDir(File root, String name)
	{
		
	    if (root.getName().equals(name))
	    {
	        results.add(root.getAbsolutePath());
	    }

	    File[] files = root.listFiles();

	    if(files != null)
	    {
	        for (File f : files)  
	        {
	            if(f.isDirectory())
	            {   
	                String myResult = findDir(f, name);
	                //this just means this branch of the
	                //recursion reached the end of the
	                //directory tree without results, but
	                //we don't want to cut it short here,
	                //we still need to check the other
	                //directories, so continue the for loop
	                if (myResult == null) {
	                    continue;
	                }
	                //we found a result so return!
	                else {
	                	results.add(myResult);
	                }
	            }
	        }
	    }

	    //we don't actually need to change this. It just means we reached
	    //the end of the directory tree (there are no more sub-directories
	    //in this directory) and didn't find the result
	    return null;
	}

	public String getResults() {
		List<String> resultsList = results.stream().sorted(Comparator.comparingInt(String::length))
		.collect(Collectors.toList());
		return resultsList.get(0);
	}

	public void setResults(Set<String> results) {
		this.results = results;
	}

}
