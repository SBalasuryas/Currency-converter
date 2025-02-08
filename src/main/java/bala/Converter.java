package bala;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Converter {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Type currency to convert from:");
        String convertFrom = scanner.nextLine().toUpperCase();

        System.out.println("Type currency to convert to:");
        String convertTo = scanner.nextLine().toUpperCase();

        System.out.println("Type quantity to convert:");
        BigDecimal quantity = scanner.nextBigDecimal();

        String accessKey = "b06ce225c319b072e77ad2b083f5eebe";
        String urlString = "https://api.exchangerate.host/convert?access_key=" + accessKey + "&from=" + convertFrom + "&to=" + convertTo + "&amount=" + quantity;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlString)
                .get()
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            System.out.println("API Error: " + response.code());
            return;
        }

        String stringResponse = response.body().string();
//        System.out.println("API Response: " + stringResponse);

        JSONObject jsonObject = new JSONObject(stringResponse);


        if (!jsonObject.has("result")) {
            System.out.println("Error: 'result' key not found in API response.");
            return;
        }

        BigDecimal result = jsonObject.getBigDecimal("result");

        System.out.println("Converted Amount: " + result);
    }
}
