package example.mamak.cryptocurrency20;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrencyFragment extends Fragment {

    private static final String ARG_CURRENCY_SYMBOL = "currency_symbol";

    private Currency mCurrency;
    private TextView mNameTextView;
    private TextView mRankTextView;
    private TextView mPriceUsdTextView;
    private TextView mPriceBtcTextView;
    private TextView mMarketCapTextView;
    private TextView mChange1hTextView;
    private TextView mChange24hTextView;
    private TextView mChange7dTextView;
    private GraphView mGraphView;

    public static CurrencyFragment newInstance(String symbol) {
        Bundle args = new Bundle();
        args.putString(ARG_CURRENCY_SYMBOL, symbol);
        CurrencyFragment fragment = new CurrencyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String symbol = getArguments().getString(ARG_CURRENCY_SYMBOL);
        mCurrency = CurrencyFeed.get(getActivity()).getCurrency(symbol);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_currency, container, false);

        mNameTextView = (TextView) v.findViewById(R.id.name_text_view);
        mRankTextView = (TextView) v.findViewById(R.id.rank_text_view);
        mPriceUsdTextView = (TextView) v.findViewById(R.id.price_usd_text_view);
        mPriceBtcTextView = (TextView) v.findViewById(R.id.price_btc_text_view);
        mMarketCapTextView = (TextView) v.findViewById(R.id.market_cap_text_view);
        mChange1hTextView = (TextView) v.findViewById(R.id.percent_1h_text_view);
        mChange24hTextView = (TextView) v.findViewById(R.id.percent_24h_text_view);
        mChange7dTextView = (TextView) v.findViewById(R.id.percent_7d_text_view);

        mNameTextView.setText(mCurrency.getName() + "(" + mCurrency.getSymbol() + ")");
        mRankTextView.setText(String.valueOf("Rank: " + mCurrency.getRank()));
        mPriceUsdTextView.setText(String.valueOf(mCurrency.getPriceUsd()) + "$");
        mPriceBtcTextView.setText(String.valueOf(mCurrency.getPriceBtc()));
        mMarketCapTextView.setText(String.valueOf(mCurrency.getMarketCapUsd()) + "$");
        mChange1hTextView.setText(String.valueOf(mCurrency.getPercentChange1h()));
        mChange24hTextView.setText(String.valueOf(mCurrency.getPriceChange24h()));
        mChange7dTextView.setText(String.valueOf(mCurrency.getPercentChange7d()));

        mGraphView = (GraphView) v.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        series.setColor(R.color.colorPrimaryDark);
        series.setBackgroundColor(R.color.colorPrimaryDark);
        series.setDrawAsPath(true);
        mGraphView.addSeries(series);

       /* mInsertCurrencyButton = (Button) v.findViewById(R.id.insert_currency_button);
        mInsertCurrencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundWorker backgroundWorker =
                        new BackgroundWorker();
                backgroundWorker.execute(mCurrency);
            }
        });*/

        return v;
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
                BackgroundWorker backgroundWorker =
                        new BackgroundWorker();
                backgroundWorker.execute(mCurrency);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
