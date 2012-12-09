
public class dircr {

	/**
	 * @param args Filename to create directory from
	 */
	public static void main(String[] args) {
		if(args.length < 1) {
			WriteHelp();
		}
		else {
			String filename = args[0];
			
			DircrEngine engine = new DircrEngine(filename);
			engine.Run();
		}
	}

	private static void WriteHelp() {
		System.out.println("Please enter a file name as an argument.");
		System.out.println("File structure should be:");
		System.out.println(" Root node");
		System.out.println("  Sub folder #1 to root");
		System.out.println("  Sub folder #2 to root");
		System.out.println("   Sub folder #1 to Sub folder #2");
	}
	
}
