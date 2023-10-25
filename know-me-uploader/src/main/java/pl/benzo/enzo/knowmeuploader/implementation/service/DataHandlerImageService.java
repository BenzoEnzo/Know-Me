package pl.benzo.enzo.knowmeuploader.implementation.service;


import org.springframework.web.multipart.MultipartFile;

import jakarta.activation.DataHandler;
import java.io.IOException;
import java.io.InputStream;

public class DataHandlerImageService implements MultipartFile {

    private final DataHandler dataHandler;
    private final String originalFilename;

    public DataHandlerImageService(DataHandler dataHandler, String originalFilename) {
        this.dataHandler = dataHandler;
        this.originalFilename = originalFilename;
    }

    @Override
    public String getName() {
        return originalFilename;
    }

    @Override
    public String getOriginalFilename() {
        return originalFilename;
    }

    @Override
    public String getContentType() {
        return dataHandler.getContentType();
    }

    @Override
    public boolean isEmpty() {
        try {
            return dataHandler.getInputStream().available() == 0;
        } catch (IOException e) {
            return true;
        }
    }

    @Override
    public long getSize() {
        try {
            return dataHandler.getInputStream().available();
        } catch (IOException e) {
            return 0L;
        }
    }

    @Override
    public byte[] getBytes() throws IOException {
        return dataHandler.getInputStream().readAllBytes();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return dataHandler.getInputStream();
    }

    @Override
    public void transferTo(java.io.File dest)  {
    }
}
