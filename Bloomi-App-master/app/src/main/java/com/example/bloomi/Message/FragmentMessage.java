package com.example.bloomi.Message;

import static com.example.bloomi.MainActivity.user_login;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloomi.MemoryData;
import com.example.bloomi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentMessage extends Fragment {
    private  final List<MessagesList> messagesLists = new ArrayList<>();
    private String mobile;
    private String email;
    private String name;

    private boolean dataSet = false;
    private RecyclerView messagesRecycleView;
    private MessagesAdapter messagesAdapter;


    private int unseenMessages = 0;
    private String lastMessage = "";
    private String chatKey="";

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bloomi-a2ac4-default-rtdb.firebaseio.com/");



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        
        View view= inflater.inflate(R.layout.fragment_message, container, false);
        final CircleImageView userProfilePic = view.findViewById(R.id.userProfilePic);
        messagesRecycleView = view.findViewById(R.id.messagesRecyclerView);

        // get intent data from Register.class activity
        mobile = user_login.getAccout().getPhone();
        name = user_login.getAccout().getFirstName()+user_login.getAccout().getLastName();
        email = user_login.getAccout().getEmail();

        messagesRecycleView.setHasFixedSize(true);
        messagesRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // set adapter to recyclerview
        messagesAdapter = new MessagesAdapter(messagesLists, getActivity());
        messagesRecycleView.setAdapter(messagesAdapter);

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        // get profile pic from firebase database
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String profilePicUrl = snapshot.child("id_user").child(mobile).child("profile_pic").getValue(String.class);

                if(!profilePicUrl.isEmpty()){
                    // set profile pic to circle image view
                    Picasso.get().load(profilePicUrl).into(userProfilePic);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();

            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                messagesLists.clear();
                unseenMessages=0;
                lastMessage="";
                chatKey = "";

                for(DataSnapshot dataSnapshot : snapshot.child("ID_Account").getChildren()){
                    final String getMobile = dataSnapshot.getKey();

                    dataSet = false;
                    if(!getMobile.equals(mobile)){
                        final String getName = dataSnapshot.child("name").getValue(String.class);
                        final String getProfilePic = dataSnapshot.child("profile_pic").getValue(String.class);

                        databaseReference.child("chat").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                int getChatCounts = (int)snapshot.getChildrenCount();

                                if(getChatCounts>0  ){
                                    for(DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                                        final String getKey = dataSnapshot1.getKey();
                                        chatKey = getKey;

                                        if(dataSnapshot1.hasChild("user_1") && dataSnapshot1.hasChild("user_2") && dataSnapshot1.hasChild("messages")){
                                            final String getUserOne = dataSnapshot1.child("user_1").getValue(String.class);
                                            final String getUserTwo = dataSnapshot1.child("user_2").getValue(String.class);

                                            if((getUserOne.equals(getMobile) && getUserTwo.equals(mobile)) || (getUserOne.equals(mobile) && getUserTwo.equals(getMobile))){
                                                for(DataSnapshot chatDataSnapShot : dataSnapshot1.child("messages").getChildren()){

                                                    final long getMessageKey = Long.parseLong(chatDataSnapShot.getKey());
                                                    final long getLastSeenMessage = Long.parseLong(MemoryData.getLastMsgTS(getActivity(), getKey));

                                                    lastMessage = chatDataSnapShot.child("msg").getValue(String.class);
                                                    if(getMessageKey > getLastSeenMessage){
                                                        unseenMessages++;
                                                    }
                                                }
                                            }
                                        }


                                    }
                                }

                                if (!dataSet){
                                    dataSet=true;
                                    MessagesList messagesList = new MessagesList(getName, getMobile, lastMessage, getProfilePic, unseenMessages, chatKey);
                                    messagesLists.add(messagesList);
                                    messagesAdapter.updateData(messagesLists);
                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}