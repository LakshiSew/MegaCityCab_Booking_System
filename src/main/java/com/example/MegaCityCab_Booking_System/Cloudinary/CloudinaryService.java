package com.example.MegaCityCab_Booking_System.Cloudinary;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    // Inject config values from application.properties using @Value
    public CloudinaryService(
        @Value("${cloudinary.cloud-name}") String cloudName,
        @Value("${cloudinary.api-key}") String apiKey,
        @Value("${cloudinary.api-secret}") String apiSecret
    ) {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret,
            "secure", true
        ));
    }

    // Upload method accepts a MultipartFile (uploaded file) and returns image URL
    public String uploadImage(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(
            file.getBytes(),
            ObjectUtils.asMap(
              "resource_type", "image",
              "chunk_size", 6000000
            )
        );
        return uploadResult.get("secure_url").toString();  // URL of the uploaded image
    }
}
