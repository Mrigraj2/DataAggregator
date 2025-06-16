package model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DataFetcher {

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://helpdesk.c-zentrix.com/encode_mail_body.php?mail_id=3335111&file_path=/var/www/html/Support_CRM/mail_body/localhost.localdomain/2025_06/mail_body_3335111.txt"))
            .GET()
            .build();

    public String getData() throws Exception{
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}
