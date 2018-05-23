package example.mamak.cryptocurrency20;

import android.support.v4.app.Fragment;

public class CurrencyListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CurrencyListFragment();
    }
}
