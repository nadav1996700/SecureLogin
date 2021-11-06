package Utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class My_images {
    private static Utils.My_images instance;
    private Context context;

    private My_images(Context context) {
        this.context = context;
    }

    public static Utils.My_images getInstance() {
        return instance;
    }

    public static Utils.My_images initHelper(Context context) {
        if (instance == null)
            instance = new Utils.My_images(context);
        return instance;
    }

    private void setImageByUri(Uri uri, ImageView imageView) {
        Glide.with(context)
                .load(uri)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    /* set images using glide library (by drawable) */
    public void setImage(Drawable photo, ImageView imageView) {
        //ImageView imageView = activity.findViewById(download_placeholder);
        Glide.with(context)
                .load(photo)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }
}
