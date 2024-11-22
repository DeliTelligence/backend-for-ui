package com.DeliTelligenceBackEndService.entitymodeldto;

import lombok.Data;

import java.util.Base64;

@Data
public class ProductFetchDto {
    private String productName;
    private float standardWeight;
    private float productPrice;
    private String productImage; // This will store the Base64 encoded image string
    private String productType;


    public static String encodeImage(byte[] imageBytes) {
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    // To decode a Base64 string back to image bytes
    public static byte[] decodeImage(String base64Image) {
        return Base64.getDecoder().decode(base64Image);
    }
}
