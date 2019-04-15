package oshin.tasnuva.retrofitpost.Connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnection {

//baseurl is fixed part of our url
    //retrofit builder for building retrofit client here
    //static for making is one for this whole project we can call this as many time as we want


    private static final String BASE_URL = "http://httpbin.org";
    static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    static Retrofit retrofit = builder.build();


    public static <S> S GetOurRetrofit(Class<S> ourClass) {
        return retrofit.create(ourClass);
        //client means connection estrablished now we are getting the instance
        //ourClass is the interface class
    }


}
