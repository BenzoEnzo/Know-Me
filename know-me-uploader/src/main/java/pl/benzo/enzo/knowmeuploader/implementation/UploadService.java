package pl.benzo.enzo.knowmeuploader.implementation;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;


public class UploadService {

    /**
     * Store the provided file in the specified upload directory.
     *
     * @param file the file to store
     * @param filename the name of the stored file
     * @param uploadDirectory the directory to store the file in
     * @throws IOException if an error occurs during file storage
     */
    public void storeFile(MultipartFile file, String filename, String uploadDirectory) throws IOException {
        Path directoryPath = Paths.get(uploadDirectory);
        ensureDirectoryExists(directoryPath);

        Path filePath = directoryPath.resolve(filename);
        System.out.println("Attempting to write to: " + filePath.toString());

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Load a file from the specified upload directory.
     *
     * @param filename the name of the file to load
     * @param uploadDirectory the directory to load the file from
     * @return the loaded file as a Resource
     * @throws FileNotFoundException if the file is not found
     */
    public Resource loadFile(String filename, String uploadDirectory) throws FileNotFoundException {
        Path filePath = Paths.get(uploadDirectory).resolve(filename);
        System.out.println("Attempting to read from: " + filePath.toString());

        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found: " + filename);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found: " + filename);
        }
    }

    /**
     * Ensure that the provided directory exists.
     *
     * @param directoryPath the directory to check or create
     * @throws FileAlreadyExistsException if the path exists but is not a directory
     * @throws IOException if an error occurs while creating the directory
     */
    private void ensureDirectoryExists(Path directoryPath) throws IOException {
        if (Files.exists(directoryPath)) {
            if (!Files.isDirectory(directoryPath)) {
                throw new FileAlreadyExistsException(directoryPath.toString(), null, "Path exists but is not a directory");
            }
        } else {
            Files.createDirectories(directoryPath);
        }
    }
}