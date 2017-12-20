package com.example.mithilesh.hellodoc.data.remote;

import com.example.mithilesh.hellodoc.mvp.model.Feeds;
import com.example.mithilesh.hellodoc.mvp.model.ResponseSearch;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by mithilesh on 8/22/16.
 */
public class ApiClient {

    public static class ServiceType {
        public static final String BASE_URL = "http://hn.algolia.com/api/v1";

        public static final String SEARCH = BASE_URL + "/search";
    }

    public static class HttpErrorCode {
        public static final Integer NO_CODE = 000;
        public static final Integer FAILED = 401;
    }

    public interface APICalls {

        @GET(ServiceType.SEARCH)
        Call<ResponseSearch> search(
                @Query("query") String query);

    }

}
