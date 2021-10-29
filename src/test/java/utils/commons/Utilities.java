package utils.commons;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Utilities {

	public static void copyTestFolder(String originalSources , String destinationResources)  {
		File sourceFolder = new File(originalSources);
		File destinationFolder = new File(destinationResources);
		// Check if sourceFolder is a directory or file
		// If sourceFolder is file; then copy the file directly to new location
		// Verify if destinationFolder is already present; If not then create it
		if (!destinationFolder.exists()) {
			destinationFolder.mkdir();
		}

		// Get all files from source directory
		String files[] = sourceFolder.list();
		// Iterate over all files and copy them to destinationFolder one by one
		for (String file : files) {
			
			File srcFile = new File(sourceFolder, file);
			File destFile = new File(destinationFolder, file);
			try{
				Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				fail("Error loadig data");
			}
			// Recursive function call
		}
	}
	
	public static void copyFile(File srcFile , String destinationResources){
		File destinationFolder = new File(destinationResources);
		// Check if sourceFolder is a directory or file
		// If sourceFolder is file; then copy the file directly to new location
		// Verify if destinationFolder is already present; If not then create it
		if (!destinationFolder.exists()) {
			destinationFolder.mkdir();
		}
		File destFile = new File(destinationFolder+File.separator+srcFile.getName());
		try{
			Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			fail("Error loadig data");
		}
		// Recursive function call
	}

}
