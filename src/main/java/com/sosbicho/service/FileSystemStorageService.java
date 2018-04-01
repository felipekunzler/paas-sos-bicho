package com.sosbicho.service;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileSystemStorageService implements StorageService {

    private Path rootLocation = Paths.get("upload-dir");

    @Override
    public String store(MultipartFile file, long bichoId) {
        String filename = bichoId + "-" + StringUtils.cleanPath(file.getOriginalFilename());
        if (filename.contains("..")) {
            throw new RuntimeException("Cannot store file with relative path outside current directory " + filename);
        }
        try {
            Files.createDirectories(rootLocation);
            Files.copy(file.getInputStream(), rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + filename, e);
        }
    }

    @Override
    public Path getRootLocation() {
        return rootLocation;
    }

}
