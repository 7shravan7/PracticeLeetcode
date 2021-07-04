package interviewQuestions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* **Hard**(PREMIUM Content)   588. Design In-Memory File System
 * Design a data structure that simulates an in-memory file system.
	Implement the FileSystem class:
	
		FileSystem() Initializes the object of the system.
		
		List<String> ls(String path)
			If path is a file path, returns a list that only contains this file's name.
			If path is a directory path, returns the list of file and directory names in this directory.
			The answer should in lexicographic order.
			
		void mkdir(String path) 
			Makes a new directory according to the given path. The given directory path does not exist. 
			If the middle directories in the path do not exist, you should create them as well.
			
		void addContentToFile(String filePath, String content)
			If filePath does not exist, creates that file containing given content.
			If filePath already exists, appends the given content to original content.
			
		String readContentFromFile(String filePath) Returns the content in the file at filePath.
 
	Example 1:
	Input
		["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
		[[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
	Output
		[null, [], null, null, ["a"], "hello"]
	Explanation
		FileSystem fileSystem = new FileSystem();
		fileSystem.ls("/");                         // return []
		fileSystem.mkdir("/a/b/c");
		fileSystem.addContentToFile("/a/b/c/d", "hello");
		fileSystem.ls("/");                         // return ["a"]
		fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"
 
	Constraints:
		1 <= path.length, filePath.length <= 100
		path and filePath are absolute paths which begin with '/' and do not end with '/' except that the path is just "/".
		You can assume that all directory names and file names only contain lowercase letters, and the same names will
		not exist in the same directory.
		You can assume that all operations will be passed valid parameters, and users will not attempt to retrieve file
		content or list a directory or file that does not exist.
		1 <= content.length <= 50
		At most 300 calls will be made to ls, mkdir, addContentToFile, and readContentFromFile.
 */
public class InMemoryFileSystem {
	
	FileNode root;
	
	public InMemoryFileSystem() {
		root = new FileNode("");
	}

	public List<String> ls(String path) {
		return getFileNode(path).getFilesList();
	}

	public void mkdir(String path) {
		getFileNode(path);
	}

	public void addContentToFile(String filePath, String content) {
		FileNode currFileNode = getFileNode(filePath);
		currFileNode.addContent(content);
		currFileNode.setDirectory(false);
	}

	public String readContentFromFile(String filePath) {
		return getFileNode(filePath).getFileContent();
	}
	
	private FileNode getFileNode(String filePath) {
		String[] pathArr = filePath.split("/");
		FileNode fileNode = root;
		for(String path: pathArr) {
			if(path.equals("")) {
				continue;
			}
			FileNode childNode = fileNode.children.get(path);
			if(childNode == null) {
				childNode = new FileNode(path);
				fileNode.children.put(path, childNode);
			}
			fileNode = childNode;
		}
		return fileNode;
	}

	public static void main(String[] args) {
		/*Output : [null, [], null, null, ["a"], "hello"] */
		InMemoryFileSystem fileSystem = new InMemoryFileSystem();
		String[] inputOps = {"FileSystem", "ls", "mkdir", "addContentToFile", "addContentToFile", "ls",   "readContentFromFile"};
		String[] input =    {"",            "/", "/a/b/c", "/a/b/c/d#hello",   "/d/b/c/dd#hello" ,  "/",     "/a/b/c/d"};
		for(int i=0;i<inputOps.length;i++) {
			String ops = inputOps[i];
			switch(ops) {
				case "FileSystem":
					System.out.println("null");
					break;
				case "ls":
					List<String> fileList = fileSystem.ls(input[i]);
					System.out.println("-------ls command --------");
					for(String file: fileList) {
						System.out.print(file+",");
					}
					System.out.println();
					break;
				case "mkdir":
					fileSystem.mkdir(input[i]);
					System.out.println("null");
					break;
				case "addContentToFile":
					String[] inputArr = input[i].split("#");
					fileSystem.addContentToFile(inputArr[0], inputArr[1]);
					break;
				case "readContentFromFile":
					String fileContent = fileSystem.readContentFromFile(input[i]);
					System.out.println(fileContent);
					break;
			}
		}
	}
}

class FileNode {
	
	String fileName;
	
	StringBuilder fileContentBuilder;
	
	Map<String, FileNode> children;
	
	boolean isDirectory;
	
	public FileNode(String fileName) {
		this.fileName = fileName;
		fileContentBuilder = new StringBuilder();
		children = new TreeMap<>();
		isDirectory = true;
	}
	
	public String getName() {
		return fileName;
	}
	
	public String getFileContent() {
		return fileContentBuilder.toString();
	}
	
	public void addContent(String content) {
		fileContentBuilder.append(content);
	}
	
	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}
	
	public boolean isDirectory() {
		return isDirectory;
	}
	
	public List<String> getFilesList() {
		List<String> filesList = new ArrayList<>();
		if(isDirectory) {
			filesList.addAll(children.keySet());
		} else {
			filesList.add(getName());
		}
		return filesList;
	}
	
}
