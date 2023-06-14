package com.example.bloomi.CallAPI;

import static com.example.bloomi.homePage.MainNav.list;
import static com.example.bloomi.homePage.MainNav.listnoti;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bloomi.Login.LogIn;
import com.example.bloomi.Login.SharedPrefManager;
import com.example.bloomi.Login.User_login;
import com.example.bloomi.MainActivity;
import com.example.bloomi.Notification.Notification;
import com.example.bloomi.Register.User;
import com.example.bloomi.homePage.MainNav;
import com.example.bloomi.post_Bloom.OnePost;
import com.example.bloomi.post_Bloom.oneNewPost;
import com.example.bloomi.uses_manage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Call_API {


    public Context context;

    public Call_API(Context context) {
        this.context = context;
    }

    static String url = "http://18.218.39.66:8080";

    private FirebaseAuth auth;
    public void call_API_SignUp(User user) {
        RequestQueue referenceQueue = Volley.newRequestQueue(context);
        String url_signup=url+"/api/v1/auth/signup";
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, url_signup, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject= new JSONObject(response);
//                    System.out.println("NGuyenVanDat");
                    System.out.println(response);
                    if(jsonObject.getString("status").equals("SUCCESS"))
                    {
                        Toast.makeText(context,"successful registration",Toast.LENGTH_SHORT).show();
                        //Intent intent=new Intent(context, LogIn.class);
                        //context.startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(context,"Password must be at least 8 characters, must contain at least 1 uppercase, lowercase, alphanumeric, and special characters!",Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Not Found Account", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();

                param.put("birthday", user.getBirthDay().toString());
                param.put("email", user.getEmail());
                param.put("firstName", user.getFirstName().toString());
                param.put("gender", user.getSex().toString());
                param.put("lastName", user.getLastName().toString());
                param.put("password", user.getPassword().toString());
              //  param.put("phone", user.getPhone().toString());
                return param;
            }
        };
        referenceQueue.add(jsonArrayRequest);
    }

    public void callAPISignIn(String name, String pass) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url_signin=url+"/api/v1/auth/signin";
        //System.out.println(name+pass);
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, url_signin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    System.out.println(jsonObject.getString("status"));
                    if (!jsonObject.getString("status").equals("SUCCESS")) {
                        Toast.makeText(context, "asdfsd", Toast.LENGTH_SHORT).show();
                    } else {
                        JSONObject userJson = jsonObject.getJSONObject("data");

                        //creating a new user object
                        User_login user = new User_login(
                                userJson.getString("token"),new uses_manage(
                                userJson.getJSONObject("account").getInt("id"),
                                userJson.getJSONObject("account").getBoolean("activeFlag"),
                                userJson.getJSONObject("account").getBoolean("deleteFlag"),
                                userJson.getJSONObject("account").getString("createdDate"),
                                userJson.getJSONObject("account").getString("email"),
                                userJson.getJSONObject("account").getString("phone"),
                                userJson.getJSONObject("account").getString("firstName"),
                                userJson.getJSONObject("account").getString("lastName"),
                                userJson.getJSONObject("account").getString("gender"),
                                userJson.getJSONObject("account").getString("birthday"),
                                userJson.getJSONObject("account").getString("avatarUrl"),
                                userJson.getJSONObject("account").getString("coverImageUrl"),
                                userJson.getJSONObject("account").getString("address"),
                                userJson.getJSONObject("account").getString("authProvider")
                        )
                        );

                        //storing the user in shared preferences
                        SharedPrefManager.getInstance(context).userLogin(user);
                        MainActivity.user_login=SharedPrefManager.getInstance(context).getUser();
                        Intent goToHomeFromLogIn = new Intent(context, MainNav.class);
                        //System.out.println("Nguyen Van Dat");
                        //loginFirebase(name,pass);
                        context.startActivity(goToHomeFromLogIn);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Not found Account", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                System.out.println(name);
                param.put("emailOrPhone",name.toLowerCase());
                param.put("password",pass);
                return param;}
        };
        requestQueue.add(jsonArrayRequest);
    }
    public void call_Api_CreateNewPost(oneNewPost newPost) throws JSONException {
        String Url_new_Post=url+"/api/v1/post/create/";
        RequestQueue requestQueue=Volley.newRequestQueue(context);
        JSONObject jsonObject=new JSONObject();
        JSONObject jsonObject2=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        jsonObject.put("content",newPost.getContent().toString());
        jsonObject.put("displayMode",newPost.getDisplayMode());
        //
        System.out.println(newPost.getUrl_image());
        jsonObject2.put("type","image");
        jsonObject2.put("url",newPost.getUrl_image());
        jsonArray.put(jsonObject2);
        //
        jsonObject.put("imageVideos",jsonArray);
        final String requestBody=jsonObject.toString();
        StringRequest jsonArrayRequest=new StringRequest(Request.Method.POST, Url_new_Post+newPost.getAccountId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject1 = new JSONObject(response);
                    System.out.println(jsonObject1);
                    if (jsonObject1.getString("status").equals("SUCCESS"))
                        Toast.makeText(context, "POST SUCCESS", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            //            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//               Map<String,String > param=new HashMap<>();
//               //param.put("", newPost.getAccountId()+"");
//               param.put("content",newPost.getContent());
//               param.put("displayMode",newPost.getDisplayMode()+"");
//               return param;
//            }

        };
       requestQueue.add(jsonArrayRequest);
    }
    public void call_Api_Get_Post(int ID)
    {
        String Url_get_Post=url+"/api/v1/post/";
        RequestQueue requestQueue=Volley.newRequestQueue(context);
        StringRequest jsonArrayRequest=new StringRequest(Request.Method.GET, Url_get_Post+ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject  jsonObject = new JSONObject(response);

                    System.out.println(jsonObject);
                    if(!jsonObject.getString("status").equals("SUCCESS"))
                    {
                        Toast.makeText(context, "No POST", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {   // lay mang data
                        JSONArray jsonArray=jsonObject.getJSONArray("data");
                        // tao list chua magn

                        for(int i=0;i<jsonArray.length();i++)
                        {
                            OnePost onePost=new OnePost(
                                    jsonArray.getJSONObject(i).getJSONObject("post").getInt("id"),
                                    jsonArray.getJSONObject(i).getJSONObject("post").getString("author"),
                                    jsonArray.getJSONObject(i).getJSONObject("post").getString("avtAuthorUrl"),
                                    jsonArray.getJSONObject(i).getJSONObject("post").getString("content"),
                                    jsonArray.getJSONObject(i).getInt("numberOfLikes"),
                                    jsonArray.getJSONObject(i).getInt("numberOfComments"));
                            if(jsonArray.getJSONObject(i).getJSONObject("post").getJSONArray("imageVideos").length()!=0)
                                onePost.setImage( jsonArray.getJSONObject(i).getJSONObject("post").getJSONArray("imageVideos").getJSONObject(0).getString("url"));
                           // System.out.println( jsonArray.getJSONObject(i).getJSONObject("post").getString("content"));
                            list.add(onePost);
                           // System.out.println(jsonArray.getJSONObject(i).getString("content"));
                        }


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"vui long load lai",Toast.LENGTH_SHORT).show();
            }
        }){

        };
        requestQueue.add(jsonArrayRequest);
    }
    public void loginFirebase(String mail,String pass) {
       // DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://chatapplication-677a2-default-rtdb.firebaseio.com/");
        auth.createUserWithEmailAndPassword(mail,pass)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(context, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        //progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(context, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void postCodeToAPI(String code)
    {
        String urlPostCode=url+"/api/v1/auth/signup/verify?token=";
        RequestQueue requestQueue=Volley.newRequestQueue(context);
        StringRequest jsonArrayRequest=new StringRequest(Request.Method.GET, urlPostCode+code, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject  jsonObject = new JSONObject(response);

                    if(!jsonObject.getString("status").equals("SUCCESS"))
                    {
                        Toast.makeText(context,"Code errol!",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {   // lay mang data
                        Toast.makeText(context,"Register Concuragtion",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(context,LogIn.class);
                        context.startActivity(intent);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"vui long load lai",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);

    }
    // NOTI
    public void callApigetNoti(int idAc)
    {    RequestQueue requestQueue=Volley.newRequestQueue(context);
        String urlNoti= url+"/api/v1/notification/";
        StringRequest jsonArrayRequest=new StringRequest(Request.Method.GET, urlNoti + idAc, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.getString("status").equals("SUCCESS"))
                    {
                        JSONArray jsonArray=jsonObject.getJSONArray("data");
                        for(int i=0;i<jsonArray.length();i++)
                        {
                            Notification notification=new Notification(
                                                                jsonArray.getJSONObject(i).getString("content"),
                                                    jsonArray.getJSONObject(i).getJSONObject("account").getString("firstName")+
                                                                jsonArray.getJSONObject(i).getJSONObject("account").getString("lastName"));
                            if(jsonArray.getJSONObject(i).getInt("type")==3)
                                notification.setNoti_action("liked your post");
                            listnoti.add(notification);

                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        requestQueue.add(jsonArrayRequest);

    }
    public void callAPIcreateNoTi(int idAcountRe,int idsent,int type,int idPost)
    {
        RequestQueue requestQueue=Volley.newRequestQueue(context);
        String urlRactPost= url+"/api/v1/notification/create";
        StringRequest jsonArrayRequest=new StringRequest(Request.Method.POST, urlRactPost, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if(!jsonObject.getJSONArray("status").equals("SUCCESS"))
                    {
                        Toast.makeText(context,"vui long thu lai",Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param=new HashMap<>();
                param.put("idPost",idPost+"");
                param.put("idReceive",idsent+"");
                param.put("idSender",idAcountRe+"");
                param.put("type",type+"");
                return param;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }

    //
    public void callApiReactionPost(int idAcount,int idPost)
    {    RequestQueue requestQueue=Volley.newRequestQueue(context);
        String urlRactPost= url+"/api/v1/react/create/";
        StringRequest jsonArrayRequest=new StringRequest(Request.Method.GET, urlRactPost + idPost+"/"+idAcount, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if(!jsonObject.getJSONArray("status").equals("SUCCESS"))
                    {
                        Toast.makeText(context,"vui long thu lai",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

    }
 public void callApiDeleteReactionPost(int idAcount,int idPost)
    {    RequestQueue requestQueue=Volley.newRequestQueue(context);
        String urlRactPost= url+"/api/v1/react/delete/";
        StringRequest jsonArrayRequest=new StringRequest(Request.Method.GET, urlRactPost + idPost+"/"+idAcount, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    System.out.println(jsonObject.getJSONArray("status"));
                    if(!jsonObject.getJSONArray("status").equals("SUCCESS"))
                    {
                        Toast.makeText(context,"vui long thu lai",Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

    }
    // ADDFRIEND
    public void call_Api_AddFriend(int idAcc,int idFriend)
    {
       String urlaf=url+"/api/v1/account/friend/addfriend/"+idAcc+"/"+idFriend;
       StringRequest JsonArrayrequest=new StringRequest(Request.Method.POST, urlaf, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               try {
                   JSONObject jsonObject=new JSONObject(response);
                   if(!jsonObject.getString("status").equals("SUCCESS"))
                   {
                       Toast.makeText(context,"vui long thu lai",Toast.LENGTH_SHORT).show();
                   }
               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       });
    }
}
