package example.mamak.cryptocurrency20;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CurrencyListFragment extends Fragment {

    private RecyclerView mCurrencyRecyclerView;
    private CurrencyAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency_list, container, false);

        mCurrencyRecyclerView = (RecyclerView) view.findViewById(R.id.currency_recycler_view);
        mCurrencyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {

        CurrencyFeed currencyFeed = CurrencyFeed.get(getActivity());
        List<Currency> currencies = currencyFeed.getCurrencies();
        if (mAdapter == null) {
            mAdapter = new CurrencyAdapter(currencies);
            mCurrencyRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class CurrencyHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Currency mCurrency;
        private TextView mRankTextView;
        private TextView mNameTextView;
        private TextView mPriceUsdTextView;

        public CurrencyHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_currency, parent, false));
            itemView.setOnClickListener(this);

            mRankTextView = (TextView) itemView.findViewById(R.id.currency_rank);
            mNameTextView = (TextView) itemView.findViewById(R.id.currency_name);
            mPriceUsdTextView = (TextView) itemView.findViewById(R.id.currency_price_usd);
        }

        public void bind(Currency currency) {
            mCurrency = currency;
            mRankTextView.setText(String.valueOf(mCurrency.getRank()) + ".");
            mNameTextView.setText(mCurrency.getName());
            mPriceUsdTextView.setText(String.valueOf(mCurrency.getPriceUsd()) + " $");
        }

        @Override
        public void onClick(View v) {
            Intent intent = CurrencyActivity.newIntent(getActivity(), mCurrency.getRank());
            startActivity(intent);
        }
    }

    private class CurrencyAdapter extends RecyclerView.Adapter<CurrencyHolder> {

        private List<Currency> mCurrencies;

        public CurrencyAdapter(List<Currency> currencies) {
            mCurrencies = currencies;
        }

        @Override
        public CurrencyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new CurrencyHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CurrencyHolder holder, int position) {
            Currency currency = mCurrencies.get(position);
            holder.bind(currency);
        }

        @Override
        public int getItemCount() {
            return mCurrencies.size();
        }
    }
}
