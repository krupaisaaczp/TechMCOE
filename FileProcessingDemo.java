import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Custom exception for file processing errors
class FileProcessingException extends Exception {
    public FileProcessingException(String message) {
        super(message);
    }
    
    public FileProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}

class FileProcessor {
    
    // Method to read a file and return its contents as a list of strings
    public List<String> readFile(String filePath) throws FileProcessingException {
        List<String> lines = new ArrayList<>();
        
        // Try-with-resources ensures that resources are properly closed
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (FileNotFoundException e) {
            // Wrap the original exception and provide more context
            throw new FileProcessingException("File not found: " + filePath, e);
        } catch (IOException e) {
            throw new FileProcessingException("Error reading file: " + filePath, e);
        }
    }
    
    // Method to write data to a file
    public void writeFile(String filePath, List<String> lines) throws FileProcessingException {
        // Try-with-resources for writing
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new FileProcessingException("Error writing to file: " + filePath, e);
        }
    }
    
    // Method to process a file by reading it, modifying content, and writing back
    public void processFile(String inputPath, String outputPath) throws FileProcessingException {
        List<String> lines = readFile(inputPath);
        List<String> processedLines = new ArrayList<>();
        
        // Process each line (convert to uppercase for this example)
        for (String line : lines) {
            processedLines.add(line.toUpperCase());
        }
        
        writeFile(outputPath, processedLines);
        System.out.println("File processed successfully");
    }
}

class FileProcessingDemo {
    public static void main(String[] args) {
        FileProcessor processor = new FileProcessor();
        
        try {
            // Process an existing file
            processor.processFile("input.txt", "output.txt");
            
            // Try to process a non-existent file
            processor.processFile("nonexistent.txt", "output2.txt");
        } catch (FileProcessingException e) {
            System.out.println("File processing error: " + e.getMessage());
            
            // Print the original exception's message
            if (e.getCause() != null) {
                System.out.println("Original cause: " + e.getCause().getMessage());
            }
            
            // Print stack trace for debugging
            e.printStackTrace();
        }
        
        System.out.println("Program completed");
    }
}
