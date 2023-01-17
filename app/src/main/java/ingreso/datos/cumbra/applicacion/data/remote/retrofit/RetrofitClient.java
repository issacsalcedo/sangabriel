package ingreso.datos.cumbra.applicacion.data.remote.retrofit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ingreso.datos.cumbra.applicacion.BuildConfig;
import ingreso.datos.cumbra.applicacion.utils.Constants;

public class  RetrofitClient {
    private static RetrofitClient instance;
    private static Retrofit retrofit;

    public RetrofitClient() {
        retrofit = generateRetrofit();
    }

    private Retrofit generateRetrofit(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        final OkHttpClient client = getOkHttpClient();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .connectTimeout(Constants.APP_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.APP_TIMEOUT,TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Content-Type","application/json;charset=UTF-8")
                        .method(original.method(), original.body());
                return chain.proceed(requestBuilder.build());
            }
        });
        return builder.build();
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {

            instance = new RetrofitClient();
        }
        return instance;
    }

    public <T> T create(Class<T> service) {

        return retrofit.create(service);
    }
}
