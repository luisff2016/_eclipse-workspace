package exemplosJava;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CriarArquivos {

	public static void main(String[] args) {
		

		Path filePath = Paths.get("E:\\redimensionada\\teste.txt");
		
//		usar o Files.createFile(path)método para criar um novo arquivo
        try {
            // Create a file at the specified file path
            Files.createFile(filePath);
            System.out.println("File created successfully!");

        } catch (FileAlreadyExistsException e) {
            System.out.println("File already exists");
		} catch (IOException e) {
            System.out.println("An I/O error occurred: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("No permission to create file: " + e.getMessage());
        }

		File file = new File("E:\\redimensionada\\novoArquivo.txt");
		
		
//		criar diretórios pais ausentes antes de criar o arquivo.
		try {
			// Create missing parent directories
			if (filePath.getParent() != null) {
				Files.createDirectories(filePath.getParent());
			}

			// Create a file at the specified file path
			Files.createFile(filePath);
			System.out.println("File created successfully!");

		} catch (FileAlreadyExistsException e) {
			System.out.println("File already exists");
		} catch (IOException e) {
			System.out.println("An I/O error occurred: " + e.getMessage());
		} catch (SecurityException e) {
			System.out.println("No permission to create file: " + e.getMessage());
		}

        
		// Instantiate a File object with a file path	
		try {
			// Create the file in the filesystem
			boolean success = file.createNewFile();
			if (success) {
				System.out.println("File created successfully!");
			} else {
				System.out.println("File already exists!");
			}
		} catch (IOException e) {
			System.out.println("An I/O error occurred: " + e.getMessage());
		} catch (SecurityException e) {
			System.out.println("No sufficient permission to create file: " + e.getMessage());
		}
		
		// Instantiate a File object with a file path
        try {
            // Create missing parent directories
            if(file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            // Create the file
            boolean success = file.createNewFile();

            if (success) {
                System.out.println("File created successfully!");
            } else {
                System.out.println("File already exists!");
            }
        } catch (IOException e) {
            System.out.println("An I/O error occurred: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("No sufficient permission to create file: " + e.getMessage());
        }

	}

}
