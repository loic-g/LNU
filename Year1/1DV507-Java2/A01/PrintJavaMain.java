package lg222sv_assign1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

public class PrintJavaMain {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		System.out.print("Please give a Directory: ");
		String Directory = sc.nextLine();
		sc.close();
		try {
			printAllJavaFiles(new File(Directory));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void printAllJavaFiles(File file) throws IOException {

		// Get all files from a directory.

		File[] fList = file.listFiles();

		if (fList != null)
			for (File f : fList) {
				try {
					if (f.isDirectory()) {
						printAllJavaFiles(f);

					} else if (f.getName().endsWith(".java")) {

						Path path = Paths.get(f.getPath());
						long size = Files.lines(path).count();
						String javafile = f.getName() + " , " + size + " Lines";
						System.out.println(javafile);
					}
				} catch (Exception e) {
					System.out.println("Error reading the file " + f.getName());
				}
			}

	}

}
