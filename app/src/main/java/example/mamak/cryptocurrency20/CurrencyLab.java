package example.mamak.cryptocurrency20;

import java.util.ArrayList;
import java.util.List;

public class CurrencyLab {
    private static CurrencyLab sCurrencyLab;
    private List<Currency> mCurrencies;
    public static CurrencyLab get() {
        if (sCurrencyLab == null) {
            sCurrencyLab = new CurrencyLab();
        }
        return sCurrencyLab;
    }

    private CurrencyLab() {
        mCurrencies = new ArrayList<>();
    }

    public List<Currency> getCurrencies() {
        return mCurrencies;
    }

    public Currency getCurrency(String symbol) {
        for (Currency currency : mCurrencies) {
            if (currency.getSymbol().equals(symbol)) {
                return currency;
            }
        }
        return null;
    }

    public void addAll(List<Currency> list) {
        mCurrencies.addAll(list);
    }
}
