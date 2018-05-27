package example.mamak.cryptocurrency20;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CurrencyFragment extends Fragment {

    private static final String ARG_CURRENCY_SYMBOL = "currency_symbol";

    private Currency mCurrency;
    private Button mInsertCurrencyButton;
    private TextView mNameTextView;
    private TextView mRankTextView;
    private TextView mPriceUsdTextView;
    private TextView mPriceBtcTextView;
    private TextView mMarketCapTextView;
    private TextView mChange1hTextView;
    private TextView mChange24hTextView;
    private TextView mChange7dTextView;

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
        mPriceUsdTextView.setText(String.valueOf("Price: " + mCurrency.getPriceUsd()) + "$");
        mPriceBtcTextView.setText(String.valueOf("Price(BTC): " + mCurrency.getPriceBtc()) + "%");
        mMarketCapTextView.setText(String.valueOf("Market cap: " + mCurrency.getMarketCapUsd()) + "$");
        mChange1hTextView.setText(String.valueOf("Change(1h): " + mCurrency.getPercentChange1h()) + "%");
        mChange24hTextView.setText(String.valueOf("Change(24h): " + mCurrency.getPriceChange24h()) + "%");
        mChange7dTextView.setText(String.valueOf("Change(7d): " + mCurrency.getPercentChange7d()) + "%");

        mInsertCurrencyButton = (Button) v.findViewById(R.id.insert_currency_button);
        mInsertCurrencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundWorker backgroundWorker =
                        new BackgroundWorker();
                backgroundWorker.execute(mCurrency);
            }
        });

        return v;
    }
}
