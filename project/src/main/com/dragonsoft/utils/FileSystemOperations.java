package main.com.dragonsoft.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class FileSystemOperations {
	/*
	 * Class for common filesystem operations
	 */

	private Set<String> results = new HashSet<>();

	public Properties getProperties(Resource resource) {
		try {
			return PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e0) {
			System.out.println("Project missing application.properties in src/resources folder");
			System.out.println(e0.getMessage());
			System.out.println("Exiting application..");
			return null;
		}

	}

	public String getFileSystemSeparator(String option) {
		FileSystem fileSystem = FileSystems.getDefault();
		String fileSystemSeparator = fileSystem.getSeparator();

		if(option.equals("escape") && fileSystemSeparator.equals("\\")) {
			fileSystemSeparator = "\\\\";
		}

		return fileSystemSeparator;
	}

	public File getProjectPath() throws IOException {
		Resource resource = new ClassPathResource("/resources");
		File resourceFile = resource.getFile();
		String projectPath = resourceFile.getAbsolutePath().split("target")[0];
		return new File(projectPath);

	}

	public String findDirfromRoot(File root, String name)
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
					String myResult = findDirfromRoot(f, name);
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

	public String getDirectoryFound() {
		List<String> resultsList = results.stream().sorted(Comparator.comparingInt(String::length))
				.collect(Collectors.toList());
		return resultsList.get(0);
	}

	public void setResults(Set<String> results) {
		this.results = results;
	}

}
