package example.mamak.cryptocurrency20;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CurrencyListFragment extends Fragment {
    private static final String TAG = "CurrencyListFragment";

    private RecyclerView mCurrencyRecyclerView;
    private TextView mUpdateTextView;
    private boolean mIsLoading = false;
    private ProgressBar mProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency_list, container, false);
        mUpdateTextView = (TextView) view.findViewById(R.id.update_text_view);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        mCurrencyRecyclerView = (RecyclerView) view.findViewById(R.id.currency_recycler_view);
        updateCurrencies(0);
        mCurrencyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCurrencyRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        mCurrencyRecyclerView.getLayoutManager();
                int lastPosition = layoutManager.findLastVisibleItemPosition();
                int itemCount = mCurrencyRecyclerView.getAdapter().getItemCount();

                if (mIsLoading == false && lastPosition == itemCount - 1) {
                    updateCurrencies(itemCount);
                    mIsLoading = true;
                }
            }
        });
        setupAdapter();
        changeLastUpdate();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void updateCurrencies(int start) {
        new CurrencyMarketTask(start).execute();
    }

    private void setupAdapter() {
        mCurrencyRecyclerView.setAdapter(new CurrencyAdapter(
                CurrencyLab.get().getCurrencies()));
    }

    private void changeLastUpdate() {
        Date today;
        String result;
        SimpleDateFormat formatter;

        formatter = new SimpleDateFormat("d MMM yyyy, H:mm");
        today = new Date();
        result = formatter.format(today);
        mUpdateTextView.setText("Last update: " + result);
    }

    private class CurrencyHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private Currency mCurrency;
        private TextView mNameTextView;
        private TextView mPriceUsdTextView;
        private ImageView mChangeImageView;
        private ImageView mLogoImageView;

        public CurrencyHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_currency, parent, false));
            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.currency_name);
            mPriceUsdTextView = (TextView) itemView.findViewById(R.id.currency_price_usd);
            mLogoImageView = (ImageView) itemView.findViewById(R.id.currency_logo);
            mChangeImageView = (ImageView) itemView.findViewById(R.id.change_image_view);
        }

        public void bind(Currency currency) {
            mCurrency = currency;
            mNameTextView.setText(mCurrency.getName());
            mPriceUsdTextView.setText(String.valueOf(mCurrency.getPriceUsd()) + " $");
            GlideHelper.downloadImage(getActivity(), mLogoImageView, mCurrency.getSymbol());
            if (mCurrency.getPercentChange1h() > 0) {
                mChangeImageView.setImageResource(R.drawable.ic_arrow_up);
            } else if (mCurrency.getPercentChange1h() < 0) {
                mChangeImageView.setImageResource(R.drawable.ic_arrow_down);
            } else {
                mChangeImageView.setImageResource(R.drawable.ic_line);
            }
        }

        @Override
        public void onClick(View v) {
            Intent intent = CurrencyPagerActivity.newIntent(getActivity(), mCurrency.getSymbol());
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

    private class CurrencyMarketTask extends AsyncTask<Void,Void,List<Currency>> {
        private int mStart;

        public CurrencyMarketTask(int start) {
            mStart = start;
        }

        @Override
        protected void onPreExecute() {
            mCurrencyRecyclerView.setVisibility(View.GONE);
            mUpdateTextView.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Currency> doInBackground(Void... voids) {
            return new CurrencyMarket().getCurrenciesByPosition(mStart);
        }

        @Override
        protected void onPostExecute(List<Currency> currencies) {
            CurrencyLab.get().addAll(currencies);
            mProgressBar.setVisibility(View.GONE);
            mUpdateTextView.setVisibility(View.VISIBLE);
            mCurrencyRecyclerView.setVisibility(View.VISIBLE);
            mCurrencyRecyclerView.setBackgroundColor(Color.LTGRAY);
            setupAdapter();
            mIsLoading = false;
        }
    }
}
