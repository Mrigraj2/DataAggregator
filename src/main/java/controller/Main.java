package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.DataFetcher;
import model.Decoder;
import model.JDBC;
import model.JsonExtractor;
import java.lang.reflect.Type;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

    public static void main(String args[]) throws Exception {
        DataFetcher df = new DataFetcher();
        Decoder decode = new Decoder();
        ObjectMapper objectMapper = new ObjectMapper();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type listType = new TypeToken<List<JsonExtractor>>() {}.getType();
        List<JsonExtractor> jeList = gson.fromJson(df.getData(), listType);
        List<JsonExtractor> jsonExtractorList = objectMapper.readValue(df.getData(), new TypeReference<List<JsonExtractor>>() {});

//        if (!jeList.isEmpty()) {
//            JsonExtractor je = jeList.get(0);
//            System.out.println(je.getMail_id());
//            System.out.println(je.getMail_body_base64());
//            System.out.println(decode.decoder(je.getMail_body_base64()));
//        }

        if(!jsonExtractorList.isEmpty()){
            JsonExtractor je = jsonExtractorList.get(0);
            System.out.println(je.getMail_id());
            System.out.println(je.getMail_body_base64());
            System.out.println(decode.decoder(je.getMail_body_base64()));
        }

    // Pehle Query chalegi to list all of mail_id
        JDBC jdbc = new JDBC();
        jdbc.arrayInsertion(); //Array me saara data chala gya
        System.out.println(JDBC.mail_id); //Print array for verification

    // Now call API using every element of mail_id vector[array] and decode it simultaneously
        for(int i=0;i<JDBC.mail_id.size();i++){
            df.setMail_id(JDBC.mail_id.get(i).toString());

        }




    }

}
