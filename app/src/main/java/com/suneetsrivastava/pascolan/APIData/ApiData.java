package com.suneetsrivastava.pascolan.APIData;

import com.suneetsrivastava.pascolan.Model.SampleUser;
import com.suneetsrivastava.pascolan.Model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiData {
    @GET("users.php")
    Call<SampleUser> getSampleUsers(@Query("page") int page);
}
