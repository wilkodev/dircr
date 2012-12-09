import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DircrEngine {
	private File fileToRead;
	
	public DircrEngine(String filename) {
		fileToRead = new File(filename);
	}
	
	/*
	 * Kick the tires and light the fires
	 */
	public void Run() {
		String currentDirectory = System.getProperty("user.dir").concat("/");
		ArrayList<String> lines = ReadFile();
		int previousLevel = -1;
		for(String s : lines) {
			if(isSameLevel(previousLevel, s)) {
				currentDirectory = PopNodeFromPath(currentDirectory, 1);
			}
			else if(isSubFolder(previousLevel, s)) {
				previousLevel = DepthOfDirectory(s,  0);
			}
			else {
				int diff = previousLevel - DepthOfDirectory(s, 0);
				currentDirectory = PopNodeFromPath(currentDirectory, diff+1);
				previousLevel = DepthOfDirectory(s,  0);
			}
			currentDirectory = currentDirectory.concat(FormatDirectory(s));
			CreateDirectory(currentDirectory);
		}
	}
	
	/*
	 * Recursive. Determines the depth of directory from string
	 */
	private int DepthOfDirectory(String s, int level) {
		if(s.startsWith(" ")) {
			return DepthOfDirectory(s.substring(1), ++level);
		}
		else {
			return level;
		}
	}
	
	/*
	 * Remove white space from name and add ending slash
	 */
	private String FormatDirectory(String s) {
		int spaceToRemove = DepthOfDirectory(s, 0);

		return s.substring(spaceToRemove).concat("/");
	}
	
	private boolean isSameLevel(int previousLevel, String path) {
		return (previousLevel == DepthOfDirectory(path, 0));
	}
	
	private boolean isSubFolder(int previousLevel, String path) {
		return (previousLevel < DepthOfDirectory(path, 0));
	}
	
	/*
	 * Return substring of nodes
	 */
	private String PopNodeFromPath(String path, int numberOfNodes) {
		String[] nodes = path.split("/");
		
		String higherPath = "";
		
		for(int i = 0; i < nodes.length - numberOfNodes; i++) {
			higherPath = higherPath.concat(nodes[i]);
			higherPath = higherPath.concat("/");
		}
		
		return higherPath;
	}
	
	/*
	 * Create the directory on disk
	 */
	private void CreateDirectory(String directory) {
		File folder = new File(directory);
		
		boolean success = folder.mkdir();
		
		if(!success) {
			System.out.println("Error creating: " + directory);
		}
	}
	
	/*
	 * Read file into memory
	 */
	private ArrayList<String> ReadFile() {
		ArrayList<String> lines = new ArrayList<String>();
		FileReader filereader;
		try {
			filereader = new FileReader(fileToRead);
			BufferedReader reader = new BufferedReader(filereader);
		
			String line;
			while((line = reader.readLine()) != null) {
				lines.add(line);
			}
			
			reader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("ERROR: File not found.");
		} 
		catch (IOException e) {
			System.out.println("ERROR: Unable to open file.");
		}
		
		return lines;
	}
}
