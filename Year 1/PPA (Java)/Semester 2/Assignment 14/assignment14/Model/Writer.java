package assignment14.Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

	private String fileName;
	private String line;
	
	public Writer(String fileName) {
		this.fileName = fileName;
	}
	
	/*
	 * writes a new line to text file 
	 */
	public void writeLine(String line) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName,true))) {

			bw.write(line);
			bw.newLine();
			
		} catch (IOException e) {

			e.printStackTrace();

		}
	}
	/*
	 * readsLine from text file and returns all lines
	 */
	public String[] readLine() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		line = null;
		String lines[] = new String[50];
		int count = 0;
		while (br.ready()) {
			line = br.readLine();
				lines[count] = line;
				count++;
		}
		br.close();
		return lines;
		}
		
	/*
	 * removes line from text file
	 */
	public void RemoveLine(String lineToRemove) throws IOException {
		File inputFile = new File(fileName);
		File tempFile = new File("../Storage/Copy of Calendar.txt");

		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		String currentLine;

		while((currentLine = reader.readLine()) != null) {
		    String trimmedLine = currentLine.trim();
		    if(trimmedLine.equals(lineToRemove)) continue;
		    writer.write(currentLine + System.getProperty("line.separator"));
		}
		writer.close(); 
		reader.close(); 
		tempFile.renameTo(inputFile);
	}
	
}
