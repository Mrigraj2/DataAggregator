package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.DataFetcher;
import model.Decoder;
import model.JsonExtractor;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Main {

    public static void main(String args[]) throws Exception {
        DataFetcher df = new DataFetcher();
        Decoder decode = new Decoder();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<JsonExtractor>>() {}.getType();
        List<JsonExtractor> jeList = gson.fromJson(df.getData(), listType);

        if (!jeList.isEmpty()) {
            JsonExtractor je = jeList.get(0);
            System.out.println(je.getMail_id());
            System.out.println(je.getMail_body_base64());
            System.out.println(decode.decoder(je.getMail_body_base64()));;
        }

    }

}
