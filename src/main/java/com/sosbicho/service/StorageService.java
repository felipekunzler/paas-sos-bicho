package com.sosbicho.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {

    String store(MultipartFile file, long bichoId);

    Path getRootLocation();

}
