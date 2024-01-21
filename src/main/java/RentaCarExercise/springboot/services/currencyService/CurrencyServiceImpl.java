package RentaCarExercise.springboot.services.currencyService;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.text.DecimalFormat;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public int convertFromEuro(float euro, String currencyType) {
        RestClient restClient = RestClient.create();

        String currency = restClient
                .get()
                .uri("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/eur/" + currencyType + ".json")
                .retrieve()
                .body(String.class);

        if (currency == null) {
            return (int) euro;

        }
        float currencyFloat = Float.parseFloat(currency.substring(currency.lastIndexOf(":") + 1, currency.indexOf("}")));
        return (int) (euro * currencyFloat);
    }
}