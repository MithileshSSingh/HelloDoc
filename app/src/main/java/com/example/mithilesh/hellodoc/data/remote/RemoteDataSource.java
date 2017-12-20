package com.example.mithilesh.hellodoc.data.remote;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.mithilesh.hellodoc.data.DataSource;
import com.example.mithilesh.hellodoc.mvp.model.Feeds;
import com.example.mithilesh.hellodoc.mvp.model.ResponseSearch;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by mithilesh on 8/19/16.
 */
public class RemoteDataSource implements DataSource {


    private static RemoteDataSource INSTANCE = null;

    private Context mContext;

    private RemoteDataSource() {

    }

    private RemoteDataSource(Context context) {
        mContext = context;
    }

    public static RemoteDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(context);
        }

        return INSTANCE;
    }

    private static Retrofit retrofit;
    private static ApiClient.APICalls apiCalls;

    private class ApiError {
        public int errorCode;
        public String msgError;

        @Override
        public String toString() {
            return "ApiError{" +
                    "errorCode=" + errorCode +
                    ", msgError=" + msgError +
                    '}';
        }
    }

    public synchronized ApiClient.APICalls getApiClient() {
        if (apiCalls == null) {
            apiCalls = getRetrofitInstance().create(ApiClient.APICalls.class);
        }

        return apiCalls;
    }

    private synchronized Retrofit getRetrofitInstance() {
        //XXX
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient();
            client.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request request = original;

                        request = original.newBuilder()
                                .method(original.method(), original.body())
                                .build();
                    //}

                    // Customize or return the response
                    return chain.proceed(request);
                }
            });
            client.setReadTimeout(10, TimeUnit.SECONDS);
            client.setWriteTimeout(10, TimeUnit.SECONDS);
            client.setConnectTimeout(10, TimeUnit.SECONDS);

            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiClient.ServiceType.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(
                            new ErrorHandlingExecutorCallAdapterFactory(
                                    new ErrorHandlingExecutorCallAdapterFactory.MainThreadExecutor()))
                    .build();
        }

        return retrofit;
    }

    private ApiError getApiError(Throwable t) {
        RetrofitException exception = (RetrofitException) t;
        ApiError apiError = new ApiError();

        if (exception.getErrorResponse() != null) {
            ApiErrorResponse apiErrorResponse = exception.getErrorResponse();
            if (apiErrorResponse != null) {

                apiError.errorCode = apiErrorResponse.getStatusCode();
                apiError.msgError = apiErrorResponse.getMessage();

            } else {

                apiError.errorCode = ApiClient.HttpErrorCode.NO_CODE;
                apiError.msgError = "Error";

            }
        } else {

            apiError.errorCode = ApiClient.HttpErrorCode.NO_CODE;
            apiError.msgError = "Error";

        }

        return apiError;
    }


    @Override
    public void search(
            String query,
            final QueryCallBack callback) {

        if (!NetworkUtils.isNetworkAvailable(mContext)) {

            callback.failed(
                    ApiClient.HttpErrorCode.NO_CODE,
                    "No Network"
            );
            return;
        }

        Call<ResponseSearch> response = getApiClient().search(
                query
        );

        response.enqueue(new Callback<ResponseSearch>() {
            @Override
            public void onResponse(retrofit.Response<ResponseSearch> response, Retrofit retrofit) {

                ArrayList<Feeds> feeds = new ArrayList<>();

                try {

                    ResponseSearch jsonObject = response.body();

                    Log.v("Data", jsonObject.toString());

                    for (Feeds feed : jsonObject.getFeeds()){

                        feeds.add(feed);
                    }

                    callback.success(feeds);

                }catch (NullPointerException e){
                  e.printStackTrace();
                  callback.failed(ApiClient.HttpErrorCode.NO_CODE,
                            "Exception");

                } 
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                ApiError apiError = getApiError(t);
                callback.failed(apiError.errorCode, apiError.msgError);
            }
        });

    }
}
