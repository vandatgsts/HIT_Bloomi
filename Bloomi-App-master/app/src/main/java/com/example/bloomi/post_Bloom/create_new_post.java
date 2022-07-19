package com.example.bloomi.post_Bloom;

import static android.app.Activity.RESULT_OK;

import static com.example.bloomi.MainActivity.user_login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bloomi.CallAPI.Call_API;
import com.example.bloomi.R;
import com.example.bloomi.uses_manage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;

import java.io.IOException;
import java.util.UUID;

public class create_new_post extends Fragment {
    private static final int PICK_IMAGE_REQUEST =100 ;
    ImageButton imagebutton;
    ImageView setImage,Cancel;
    EditText content;
    uses_manage account= user_login.getAccout();
    Button post;
    // firebase
    FirebaseStorage storage;
    StorageReference storageReference;
    // anh
    DatabaseReference databaseReference;
    Uri filePath;
    String idImage;


    public static oneNewPost NewPost=new oneNewPost();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_create_new_post, container, false);
        // up image len firebase
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("ID Account: " +user_login.getAccout().getId()+"");
        //databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("gs://bloomi-a2ac4.appspot.com");

        //

        imagebutton=view.findViewById(R.id.f_createPost_addImage);
        setImage=view.findViewById(R.id.f_createPost_image);
        content=view.findViewById(R.id.f_createPost_content);
        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImageFromGallery();
            }
        });

        post=view.findViewById(R.id.f_createPost_post);
        Call_API call=new Call_API(this.getActivity());
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    NewPost.setAccountId(account.getId());
                    NewPost.setContent(content.getText().toString());
                   // System.out.println("jyfhjkgfkjh"+content.getText().toString());
                    upToFireBase();
                    //getURLFireBase();
                    call.call_Api_CreateNewPost(NewPost);


                    getFragmentManager().beginTransaction().remove(create_new_post.this).commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        Cancel=view.findViewById(R.id.f_createPost_cancel);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().remove(create_new_post.this).commit();
            }
        });


    return  view;
    }

    private void chooseImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {       filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.requireActivity().getContentResolver(), filePath);
                setImage.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    public void upToFireBase()
    {
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            idImage=UUID.randomUUID().toString();
            System.out.println(idImage);
            StorageReference ref = storageReference.child("images/"+idImage);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            getURLFireBase();
                            //Toast.makeText(getActivity(), "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            //Toast.makeText(getActivity(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
            // lay URL anh

//            System.out.println("check url ");
//            //String location="/images/"+idImage;
//           // StorageReference re=storageReference.child(location);
//            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                @Override
//                public void onSuccess(Uri uri) {
//                    NewPost.setUrl_image(uri.toString());
//                    System.out.println(uri.toString());
//                    // Got the download URL for 'users/me/profile.png'
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception exception) {
//                    // Handle any errors
//                }
//            });


        }
    }
    public void getURLFireBase()
    {
        String location="/images/"+idImage;
        StorageReference re=storageReference.child(location);
        System.out.println(idImage);
        re.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                NewPost.setUrl_image(uri.toString());
                System.out.println(uri.toString());
                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }

}