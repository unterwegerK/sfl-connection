package de.ku.sfl.connection.objects;

import java.util.Base64;

/**
 * An image can be used in report descriptions. Hence, it also holds a name by which it can be
 * identified.
 */
public class Image {

    private String name;
    private byte[] data;

    public Image(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }

    /**
     * Deserializes the image from the given Base64 string.
     */
    public Image(String name, String base64Data){
        this.name = name;

        data = Base64.getDecoder().decode(base64Data);
    }

    /**
     * Serializes the image to a Base64 encoded string.
     */
    public String getAsBase64() {
        return Base64.getEncoder().encodeToString(data);
    }

    public String getName() {
        return name;
    }

    public byte[] getData() {
        return data;
    }
}
