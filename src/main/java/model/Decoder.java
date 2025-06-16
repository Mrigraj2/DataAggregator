package model;

import java.util.Base64;

public class Decoder {

    public String decoder(String query){
        byte[] decodedBytes = Base64.getDecoder().decode(query);
        return new String(decodedBytes);
    }
}
