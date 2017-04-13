package arief.com.celltype.config;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import arief.com.celltype.R;

/**
 * All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Originally written by Author Name Arief Maffrudin A N, 13/04/17
 */

public class ImageUtils {

    public static void displayImage(Context context, String str, ImageView img){
        Picasso.with(context)
                .load(str)
                .placeholder(R.color.colorAccent)
                .error(R.color.colorAccent)
                .into(img);
    }
}
