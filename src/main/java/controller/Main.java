package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.DataFetcher;
import model.Decoder;
import model.JDBC;
import model.JsonExtractor;
import java.lang.reflect.Type;
import java.util.List;

public class Main {

    public static void main(String args[]) throws Exception {
        DataFetcher df = new DataFetcher();
        Decoder decode = new Decoder();

        Gson gson = new Gson();
        Type listType = new TypeToken<List<JsonExtractor>>(){}.getType();

        //Fetch Data
        String data = df.getData();
        System.out.println(data);

        //Data extraction
        List<JsonExtractor> jse = gson.fromJson(data,listType);

        //Data printing
        for (JsonExtractor jsonExtractor: jse){
            System.out.println(jsonExtractor.getMail_body_base64());
            System.out.println(decode.decoder(jsonExtractor.getMail_body_base64()));
        }




    }

}
