package com.application.tedallal_app.Scenarios.ScenarioChat.Controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioChat.Model.ChatItem;
import com.application.tedallal_app.Scenarios.ScenarioChat.Pattrens.Chat_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHelpFragment.Controller.Help_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class Chat_fragment extends Fragment implements IFOnBackPressed {

    private View view;
    TinyDB tinyDB;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageView imgback;
    RecyclerView recyclerView;
    private Chat_Adapter adapter;
    Button btnSend;
    EditText editchatSend;
    //    String chatID;
    String event_id;
    String group;
    String name;
    String image;
    int Id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_chat, container, false);
        tinyDB = new TinyDB(getContext());
        recyclerView = view.findViewById(R.id.recyclerChat);
        editchatSend = view.findViewById(R.id.editChatSend);
        btnSend = view.findViewById(R.id.btnSend);

        Id = Integer.parseInt(saved_data.get_user_id(Objects.requireNonNull(getContext())));
        name = saved_data.get_user_name(getContext());
        image = saved_data.get_image_url(getContext());


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editchatSend.getText().toString().equals("")) {

                    Toasty.error(Objects.requireNonNull(getContext()), R.string.please_enter_your_message, Toast.LENGTH_LONG).show();


                } else {

                    sendMessage(editchatSend.getText().toString(), name, Id);
                    btnSend.setEnabled(false);
                }


            }
        });

        setUpRecyclerView(String.valueOf(Id));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void setUpRecyclerView(String group_id) {
        MainActivity.loading.setVisibility(View.GONE);
//        chatID = "1526";
        Query query = db.collection("Conversations").document(group_id).collection("Messages").orderBy("timeStamp", Query.Direction.ASCENDING);
//        db.collection("Conversations").document(chatID).collection("Messages").orderBy("timeStamp", Query.Direction.ASCENDING);
//        db.collection("Conversations").document(chatID).collection("Messages").whereArrayContains("chatMembers", "5").orderBy("timeStamp").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                if (task.isSuccessful()){
//
//                    for (QueryDocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())){
//
//
//
//
//                    }
//
//
//                }
//
//
//            }
//        });


//        Log.e("rrrrrrr",data);


        FirestoreRecyclerOptions<ChatItem> options = new FirestoreRecyclerOptions.Builder<ChatItem>()
                .setQuery(query, ChatItem.class)
                .build();

//        if (options.getSnapshots().size() > 0) {
//
//            Log.e("setUpRecyclerView", "palwdplw");
//            updateChat(options.getSnapshots().get(options.getSnapshots().size()).message);
//        }

        Log.e("setUpRecyclerView", String.valueOf(options.getSnapshots().size()));

        adapter = new Chat_Adapter(options, getContext());

        recyclerView = view.findViewById(R.id.recyclerChat);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);

                int friendlyMessageCount = adapter.getItemCount();
                int lastVisiblePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();

                // If the recycler view is initially being loaded or the
                // user is at the bottom of the list, scroll to the bottom
                // of the list to show the newly added message.
                if (lastVisiblePosition == -1 || (positionStart >= (friendlyMessageCount - 1) && lastVisiblePosition == (positionStart - 1))) {
                    recyclerView.scrollToPosition(positionStart);

                }

            }
        });


    }


//    private void setUpRecyclerView111() {
//
//        Query query3 = db.collection("Conversations").document("allaallallalla").collection("Messages").orderBy("timeStamp", Query.Direction.ASCENDING);
//
//
//        FirestoreRecyclerOptions<ChatMessage> options = new FirestoreRecyclerOptions.Builder<ChatMessage>()
//                .setQuery(query3, ChatMessage.class)
//                .build();
//
//        Log.e("setUpRecyclerView", String.valueOf(options.getSnapshots().size()));
//
//        adapter = new ChatAdapterForAyman(options,Chat_Activity.this);
//
//        recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setStackFromEnd(true);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(adapter);
//    }
//


    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    public void sendMessage(String message, String myname, int myId) {
//        List<Integer> seenArray = new ArrayList<>();
//        seenArray.add(myId); // Your ID
//        seenArray.add("5"); // Ride Owner ID

        ChatItem newMessage = new ChatItem();
        newMessage.setMessage(message);
        newMessage.setSender(myId);
        newMessage.setTimeStamp(Calendar.getInstance().getTime());



        db.collection("Conversations").document(String.valueOf(myId)).collection("Messages").document().set(newMessage).addOnSuccessListener(new OnSuccessListener<Void>() {

            //        db.collection("Conversations").document(chatID).collection("Messages").document().set(newMessage).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                btnSend.setEnabled(true);
                recyclerView.scrollToPosition(adapter.getItemCount() - 1);

                Log.e("last", editchatSend.getText().toString());
                updateChat(editchatSend.getText().toString(), Calendar.getInstance().getTime());
                Log.e("success", "success");
                editchatSend.setText("");
//                setUpRecyclerView();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                btnSend.setEnabled(true);

                Log.e("failde", "faild");

            }
        });

    }


    public void updateChat(String last, Date date) {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        firestore.setFirestoreSettings(settings);


        Map<String, Object> updates = new HashMap<>();
        updates.put("lastMessage", last);

        db.collection("Conversations").document(String.valueOf(Id)).update(updates);


        Map<String, Object> updatesDates = new HashMap<>();
        updatesDates.put("timeStamp", date);

        db.collection("Conversations").document(String.valueOf(Id)).update(updatesDates);

    }


    @Override
    public boolean onBackPressed() {

        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Help_Fragment());
        fr.addToBackStack(null);
        fr.commit();
//        MainActivity.navigation.setSelectedItemId(R.id.home_nav);
        return true;
    }


}
