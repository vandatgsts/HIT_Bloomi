package com.example.bloomi.CallAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public interface APIService {
    String Url_new_Post="http://bloomi.eba-u3ypmzpm.us-east-1.elasticbeanstalk.com/api/v1/post/create/";
    Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd T HH:mm:ss").create();
    // http://bloomi.eba-u3ypmzpm.us-east-1.elasticbeanstalk.com/api/v1/post/create/1?content=afasdf&displayMode=1
    APIService API_SERVICE =new Retrofit.Builder().baseUrl("http://bloomi.eba-u3ypmzpm.us-east-1.elasticbeanstalk.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);
}
