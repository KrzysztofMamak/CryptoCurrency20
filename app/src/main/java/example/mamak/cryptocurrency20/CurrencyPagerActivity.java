package example.mamak.cryptocurrency20;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class CurrencyPagerActivity extends AppCompatActivity {
    private static final String EXTRA_SYMBOL =
            "example.mamak.cryptocurrency20.symbol";

    private ViewPager mViewPager;
    private List<Currency> mCurrencies;

    public static Intent newIntent(Context packageContext, String symbol) {
        Intent intent = new Intent(packageContext, CurrencyPagerActivity.class);
        intent.putExtra(EXTRA_SYMBOL, symbol);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_pager);

        String currencySymbol = getIntent().getStringExtra(EXTRA_SYMBOL);

        mViewPager = (ViewPager) findViewById(R.id.currency_view_pager);

        mCurrencies = CurrencyLab.get().getCurrencies();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Currency currency = mCurrencies.get(position);
                return CurrencyFragment.newInstance(currency.getSymbol());
            }

            @Override
            public int getCount() {
                return mCurrencies.size();
            }
        });

        for (int i = 0; i < mCurrencies.size(); i++) {
            if (mCurrencies.get(i).getSymbol().equals(currencySymbol)) {
                mViewPager.setCurrentItem(i);
                break;
            }

        }
    }
}