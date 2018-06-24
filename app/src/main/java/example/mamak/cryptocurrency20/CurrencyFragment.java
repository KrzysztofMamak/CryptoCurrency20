package example.mamak.cryptocurrency20;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

public class CurrencyFragment extends Fragment {
    private static final String ARG_CURRENCY_SYMBOL = "currency_symbol";

    private Currency mCurrency;
    private TextView mNameTextView;
    private TextView mPriceUsdTextView;
    private TextView mPriceBtcTextView;
    private TextView mMarketCapTextView;
    private TextView mChange1hTextView;
    private TextView mChange24hTextView;
    private TextView mChange7dTextView;
    private ImageView mLogoImageView;
    private GraphView mGraphView;

    public static CurrencyFragment newInstance(String symbol) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CURRENCY_SYMBOL, symbol);
        CurrencyFragment fragment = new CurrencyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrency = CurrencyLab
                .get()
                .getCurrency(String.valueOf(getArguments().get(ARG_CURRENCY_SYMBOL)));
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_currency, container, false);

        mLogoImageView = (ImageView) v.findViewById(R.id.logo_image_view);
        mNameTextView = (TextView) v.findViewById(R.id.name_text_view);
        mPriceUsdTextView = (TextView) v.findViewById(R.id.price_usd_text_view);
        mPriceBtcTextView = (TextView) v.findViewById(R.id.price_btc_text_view);
        mMarketCapTextView = (TextView) v.findViewById(R.id.market_cap_text_view);
        mChange1hTextView = (TextView) v.findViewById(R.id.percent_1h_text_view);
        mChange24hTextView = (TextView) v.findViewById(R.id.percent_24h_text_view);
        mChange7dTextView = (TextView) v.findViewById(R.id.percent_7d_text_view);
        mGraphView = (GraphView) v.findViewById(R.id.graph);
        new HistoryMarketTask(mCurrency.getSymbol()).execute();

        GlideHelper.downloadImage(getActivity(), mLogoImageView, mCurrency.getSymbol());
        mNameTextView.setText(mCurrency.getName() + "(" + mCurrency.getSymbol() + ")");
        mPriceUsdTextView.setText(String.valueOf(mCurrency.getPriceUsd()) + "$");
        mPriceBtcTextView.setText(String.valueOf(mCurrency.getPriceBtc()));
        mMarketCapTextView.setText(String.format("%.3f", mCurrency.getMarketCapUsd() / 1000000000.0) + "B$");
        mChange1hTextView.setText(String.valueOf(mCurrency.getPercentChange1h()) + "%");
        mChange24hTextView.setText(String.valueOf(mCurrency.getPriceChange24h()) + "%");
        mChange7dTextView.setText(String.valueOf(mCurrency.getPercentChange7d()) + "%");

        return v;
    }

    private void setupGraph(List<HistoryItem> historyItems) {
        int itemCount = historyItems.size();
        DataPoint[] points = new DataPoint[itemCount];
        for (int i = 0; i < itemCount; i++) {
            points[i] = new DataPoint(i, historyItems.get(i).getHigh());
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
        series.setColor(R.color.colorPrimaryDark);
        series.setBackgroundColor(R.color.colorPrimaryDark);
        series.setDrawAsPath(true);
        mGraphView.addSeries(series);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_currency, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.insert_record:
                RecordUploader recordUploader =
                        new RecordUploader();
                recordUploader.execute(mCurrency);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class HistoryMarketTask extends AsyncTask<Void,Void, List<HistoryItem>> {
        private String mSymbol;

        public HistoryMarketTask(String symbol) {
            mSymbol = symbol;
        }

        @Override
        protected List<HistoryItem> doInBackground(Void... voids) {
            return new HistoryMarket().getHistoryBySymbol(mSymbol);
        }

        @Override
        protected void onPostExecute(List<HistoryItem> historyItems) {
            setupGraph(historyItems);
        }
    }
}
