package model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DataFetcher {

    String mail_id;

    //put the mail_id no to get the corresponding data from table 2025_06
    public void setMail_id(String mail_id) {
        this.mail_id = mail_id;
    }

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://helpdesk.c-zentrix.com/encode_mail_body.php?mail_id="+mail_id+"&file_path=/var/www/html/Support_CRM/mail_body/localhost.localdomain/2025_06/mail_body_"+mail_id+".txt"))
            .GET()
            .build();

    public String getData() throws Exception{
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}
