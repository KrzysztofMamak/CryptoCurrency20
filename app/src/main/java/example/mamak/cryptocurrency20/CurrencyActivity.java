package example.mamak.cryptocurrency20;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class CurrencyActivity extends SingleFragmentActivity {

    public static final String EXTRA_CURRENCY_RANK =
            "example.mamak.cyptocurrency20.currency_rank";

    public static Intent newIntent(Context packageContext, int currencyRank) {
        Intent intent = new Intent(packageContext, CurrencyActivity.class);
        intent.putExtra(EXTRA_CURRENCY_RANK, currencyRank);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new CurrencyFragment();
    }
}
