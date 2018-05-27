package example.mamak.cryptocurrency20;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<Currency, Void, String> {

    private static final String TAG = "BackgroundWorker";

    @Override
    protected String doInBackground(Currency... params) {
        Currency currency = params[0];
        String insert_url = "http://192.168.0.108/insertcurrency.php";
        try {
            String name = currency.getName();
            String symbol = currency.getSymbol();
            String rank = String.valueOf(currency.getRank());
            String price_usd = String.valueOf(currency.getPriceUsd());
            String price_btc = String.valueOf(currency.getPriceBtc());
            String market_cap_usd = String.valueOf(currency.getMarketCapUsd());
            String percent_change_1h = String.valueOf(currency.getPercentChange1h());
            String percent_change_24h = String.valueOf(currency.getPriceChange24h());
            String percent_change_7d = String.valueOf(currency.getPercentChange7d());
            URL url = new URL(insert_url);
            HttpURLConnection httpURLConnection =
                    (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream =
                    httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter =
                    new BufferedWriter(new OutputStreamWriter(outputStream,
                            "UTF-8"));
            String post_data = URLEncoder.encode("name", "UTF-8")
                    + "=" + URLEncoder.encode(name, "UTF-8")
                    + "&" + URLEncoder.encode("symbol", "UTF-8")
                    + "=" + URLEncoder.encode(symbol, "UTF-8")
                    + "&" + URLEncoder.encode("rank", "UTF-8")
                    + "=" + URLEncoder.encode(rank, "UTF-8")
                    + "&" + URLEncoder.encode("price_usd", "UTF-8")
                    + "=" + URLEncoder.encode(price_usd, "UTF-8")
                    + "&" + URLEncoder.encode("price_btc", "UTF-8")
                    + "=" + URLEncoder.encode(price_btc, "UTF-8")
                    + "&" + URLEncoder.encode("market_cap_usd", "UTF-8")
                    + "=" + URLEncoder.encode(market_cap_usd, "UTF-8")
                    + "&" + URLEncoder.encode("percent_change_1h", "UTF-8")
                    + "=" + URLEncoder.encode(percent_change_1h, "UTF-8")
                    + "&" + URLEncoder.encode("percent_change_24h", "UTF-8")
                    + "=" + URLEncoder.encode(percent_change_24h, "UTF-8")
                    + "&" + URLEncoder.encode("percent_change_7d", "UTF-8")
                    + "=" + URLEncoder.encode(percent_change_7d, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(inputStream,
                            "iso-8859-1"));
            String result = "";
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("XXX", "MATI");
        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null)
            Log.d(TAG, result);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
