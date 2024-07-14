package com.om.operations.storage;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.stream.Stream;


@Slf4j
@Service
public class FileSystemStorageServiceMaster implements StorageService {

    private final Path rootLocation;
    private final Path employeeImages;
    public final static String EMPLOYEEIMAGES = "employee-images";

    public final Path paymentImages;
    public final static String PAYMENTIMAGES = "payment-images";

    @Autowired
    public FileSystemStorageServiceMaster(StoragePropertiesMaster properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.employeeImages = Paths.get(properties.getEmployeeImages());
        this.paymentImages = Paths.get(properties.getPaymentImages());
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            Path destinationFile = this.rootLocation.resolve(Paths.get(Objects.requireNonNull(file.getOriginalFilename()))).normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException("Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public void store(MultipartFile file, String updatedName) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }

            Path destinationFile = this.rootLocation.resolve(Paths.get(Objects.requireNonNull(updatedName))).normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException("Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public void storeBaseImage(String baseImage, String updatedName, String fileType) throws IOException {
        byte[] byteArray = Base64.decodeBase64(baseImage);
        FileOutputStream fos = null;
        System.out.println("File type: " + fileType);
        try {
            switch (fileType) {
                case EMPLOYEEIMAGES: {
                    fos = new FileOutputStream(employeeImages + "/" + updatedName);
                    System.out.println("In Employee Switch case");
                };
                break;
                case PAYMENTIMAGES:
                    fos = new FileOutputStream(paymentImages + "/" + updatedName);
                    break;
            }
            assert fos != null;
            fos.write(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fos != null;
        fos.close();
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation)).map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Path load(String filename, String fileType) {
        System.out.println("filename " + filename);
        System.out.println("fileType " + fileType);
        switch (fileType) {
            case "employee-images":
                return employeeImages.resolve(filename);
            case "paymentImages":
                return paymentImages.resolve(filename);
        }
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public Resource loadAsResource(String filename, String type) {
        System.out.println("FileName FileName" + filename);
        System.out.println("Type Type "+ type);
        try {
            Path file = load(filename, type);
            System.out.println("file.getFileSystem()"+file.getFileSystem());
            System.out.println("File load " + file);
            Resource resource = new UrlResource(file.toUri());
            System.out.println("resource.getFile().getAbsolutePath()"+resource.getFilename());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
        FileSystemUtils.deleteRecursively(employeeImages.toFile());
    }

    @Override
    public boolean delete(String filename) {
        try {
            Path file= new File(filename).toPath();
            System.out.println("file:"+file);
            System.out.println("file deletion started....");
            boolean check=Files.deleteIfExists(file);
            if(check)
            System.out.println("file deleted....");
            else
                System.out.println("file not deleted...");
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e);
        }
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
            Files.createDirectories(employeeImages);
            Files.createDirectories(paymentImages);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
