package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.ScenariosAlshalChat.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioChat.Model.Passenger;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.ScenariosAlshalChat.Model.Applied_Model;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.ScenariosAlshalChat.Model.Conversation1;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.ScenariosAlshalChat.Pattrens.Rcy_Messages_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.Utils.TinyDB;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Alshal_Messages_Fragment extends Fragment implements IFOnBackPressed {

    private View view;
    TinyDB tinyDB;
    RecyclerView recyclerView;
    List<Applied_Model> applied_list = new ArrayList<>();
    List<Conversation1> conversationList = new ArrayList<>();
    List<Passenger> passengerList = new ArrayList<>();
    List<Integer> users = new ArrayList<>();
    Rcy_Messages_Adapter adapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference citiesRef;
    ListenerRegistration registration;
    LinearLayout loading;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.alshal_message_fragment, container, false);
        tinyDB = new TinyDB(getContext());

        recyclerView = view.findViewById(R.id.rcyMessage);
        MainActivity.loading.setVisibility(View.VISIBLE);
        retrieveConversation();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public boolean onBackPressed() {

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(a);
        getActivity().finish();
        return true;
    }


    public void retrieveConversation() {

        int id = 55667788;


        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        firestore.setFirestoreSettings(settings);

        ArrayList<Integer> myId = new ArrayList<>();
        myId.add(id);
//        String [] names;

        ArrayList<String> names = new ArrayList<>();

        Map<String, String> map = new HashMap<>();

//        Log.e("map",map.toString());


        citiesRef = db.collection("Conversations");

        registration = citiesRef.orderBy("timeStamp", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {

                String nameTitle = "";
                MainActivity.loading.setVisibility(View.GONE);
//                Log.e("docmnt", Objects.requireNonNull(queryDocumentSnapshots).getDocuments().toString());
//                queryDocumentSnapshots.getDocuments();
                conversationList.clear();
                passengerList.clear();
                users.clear();
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    Conversation1 conversation1212 = doc.toObject(Conversation1.class);


//                    conversationList.add((Conversation) conversation1212.chatMembers);

//                    Log.e("wikwndiwh", String.valueOf(conversation1212.chatMembers));

                    if (doc.get("users") != null) {

                        Log.e("docmnt", String.valueOf(doc.get("users")));
                    } else {

                        Log.e("docmnt", "null");

                    }

                    Date currentTime = Calendar.getInstance().getTime();
                    Conversation1 conversation = new Conversation1();

                    conversation.setLastMessage(String.valueOf((doc.get("lastMessage"))));
                    conversation.setStatus(String.valueOf((doc.get("status"))));
//                    conversation.setChatMembers((List<Passenger>) doc.get("chatMembers"));
                    conversation.setSenderName(String.valueOf((doc.get("senderName"))));
//                    map = doc.get("chatMembers");
                    nameTitle = conversation.getSenderName();
                    names.add(nameTitle);
                    try {
                        conversation.setTimeStamp(Objects.requireNonNull(doc.getDate("timeStamp")));
                    } catch (Exception ex) {
                        conversation.setTimeStamp(currentTime);
                    }


//                    passengerList = conversation.getChatMembers();


                    users = conversation1212.getUsers();

                    for (int i = 0; i < users.size(); i++) {

//                        Passenger passenger = new Passenger();
//
//                        passenger = passengerList.get(i);
                        int user = users.get(i);

                        if (user != 55667788) {

                            conversation.setUserId(user);
                            Log.e("id", String.valueOf(user));
                        }
//                        Log.e("name", String.valueOf(passenger.getName()));


//                        map.put("name",passengerList.get(i).getName());

                    }
                    conversationList.add(conversation);
                    Log.w("name", String.valueOf(names.size()));

                }
                Log.e("name", String.valueOf(names.toString()));

//                passengerList.clear();
//                for (int i=0 ; i<conversationList.size(); i++){
//
//                    passengerList = conversationList.get(i).getChatMembers();
//
//
//                }

                Log.e("ssllsllss", String.valueOf(passengerList.toString()));

                Log.w("ListName", String.valueOf(names.size()));
                adapter = new Rcy_Messages_Adapter(conversationList, names, getContext());

                recyclerView = view.findViewById(R.id.rcyMessage);
                recyclerView.setHasFixedSize(true);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);


            }
        });


    }


}
