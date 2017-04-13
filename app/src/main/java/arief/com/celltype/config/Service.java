package arief.com.celltype.config;

import arief.com.celltype.model.Result;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Originally written by Author Name Arief Maffrudin A N, 13/04/17
 */

public class Service {

    public static final String ENDPOINT = "https://hwang.run/";

    public static Retrofit create(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder b = new OkHttpClient.Builder();
        b.addInterceptor(logging);

        OkHttpClient okHttpClient = b.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public interface Api{
        @GET("jp_main.json")
        Call<Result> getData();
    }
}
