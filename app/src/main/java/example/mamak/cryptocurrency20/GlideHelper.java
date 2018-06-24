package example.mamak.cryptocurrency20;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideHelper {
    private static final String TAG = "GlideHelper";
    private static final String CONST_URL =
            "https://raw.githubusercontent.com/cjdowner/cryptocurrency-icons/master/128/icon/";

    public static void downloadImage(Context context, ImageView imageView, String symbol) {
        Log.d(TAG, "Downloading image...");

        String newSymbol = symbol.toLowerCase();
        String newUrl = CONST_URL + newSymbol + ".png";
        Glide
                .with(context)
                .load(newUrl)
                .into(imageView);
    }
}
