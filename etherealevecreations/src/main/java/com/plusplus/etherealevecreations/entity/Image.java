package com.plusplus.etherealevecreations.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.sql.SQLException;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileType;

    @Lob
    @JsonIgnore // Exclude this field from serialization
    private Blob image;

    private String downloadUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties({"images"}) // Avoid circular references
    private Product product;

    public Image(String fileName, String fileType, Blob image, String downloadUrl) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.image = image;
        this.downloadUrl = downloadUrl;
    }

    // Add this method for serialization
    @JsonProperty("imageData") // Include a new field in the response
    public byte[] getImageData() {
        if (image != null) {
            try {
                return image.getBytes(1, (int) image.length());
            } catch (SQLException e) {
                throw new RuntimeException("Error reading image Blob", e);
            }
        }
        return null;
    }
}
