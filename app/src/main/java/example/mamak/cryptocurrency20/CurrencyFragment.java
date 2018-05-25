package example.mamak.cryptocurrency20;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CurrencyFragment extends Fragment {

    private static final String ARG_CURRENCY_SYMBOL = "currency_symbol";

    private Currency mCurrency;
    private Button mInsertCurrencyButton;

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
