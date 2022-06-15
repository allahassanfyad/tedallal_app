package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHelpFragment.Controller;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioChat.Controller.Chat_fragment;
import com.application.tedallal_app.Scenarios.ScenarioChat.Model.Conversation;
import com.application.tedallal_app.Scenarios.ScenarioHome.Controller.SignIn;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.Home_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Help_Fragment extends Fragment implements IFOnBackPressed {

    private View view;
    //    ImageView imgwhatsapp, imggmail;
    TinyDB tinyDB;
    LinearLayout linearChat, linearwhatsapp, linearmail, linearcall, linearfacebool, linearinstagram, linearsnapchat;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    String lastMessage;
    //    String namee = "";
//    String image = "";
//    String id = "";
    String language;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.help_fragment, container, false);

        tinyDB = new TinyDB(getContext());
//        imgwhatsapp = view.findViewById(R.id.imgWhatsApp);
//        imggmail = view.findViewById(R.id.imgGmail);
        linearChat = view.findViewById(R.id.linearChat);
        linearwhatsapp = view.findViewById(R.id.linearWhatsApp);
        linearfacebool = view.findViewById(R.id.linearFacebook);
        linearinstagram = view.findViewById(R.id.linearInstgram);
        linearsnapchat = view.findViewById(R.id.linearSnapChat);
        linearcall = view.findViewById(R.id.linearCall);
        linearmail = view.findViewById(R.id.linearMail);
//        linearfaq = view.findViewById(R.id.linearFaq);


        linearcall.setOnClickListener(v -> {

            String phone = "+96565173953";

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phone));
            startActivity(intent);

        });

//        linearfaq.setOnClickListener(v -> {
//
//            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
//            fr.replace(R.id.fragment_container, new FAQ_WebView_Fragment());
//            fr.addToBackStack(null);
//            fr.commit();
//
////            Objects.requireNonNull(getActivity()).startActivity(new Intent(getActivity(), Webview_Faq.class));
//
//
//        });

        linearwhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "++96565173953";


                String url = "https://api.whatsapp.com/send?phone=" + phone;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);


            }
        });


        linearmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailAddress = "tadallal@gmail.com";
                Intent email = new Intent(Intent.ACTION_SENDTO);
                email.setData(Uri.parse("mailto:" + emailAddress));
                startActivity(email);


            }
        });

        linearChat.setOnClickListener(v -> {

            if (saved_data.get_user_id(Objects.requireNonNull(getContext())).equals("") || saved_data.get_user_id(Objects.requireNonNull(getContext())).equals("0")) {
                Objects.requireNonNull(getActivity()).finish();

                Objects.requireNonNull(getActivity()).startActivity(new Intent(getContext(), SignIn.class));

            } else {

                MainActivity.loading.setVisibility(View.VISIBLE);


                String name = saved_data.get_user_name(getContext());
                int Id = Integer.parseInt(saved_data.get_user_id(Objects.requireNonNull(getContext())));
                String image = saved_data.get_image_url(getContext());
                addChat(image, name, Id);


            }


        });

        linearfacebool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(requireActivity());
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);

            }
        });

        linearsnapchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String snapchatId = "tadallal.app";

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add/" + snapchatId));
                    intent.setPackage("com.snapchat.android");
                    startActivity(intent);
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add/" + snapchatId)));
                }
            }
        });

        linearinstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Uri uri = Uri.parse("http://instagram.com/_u/tadallalkwt");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/tadallalkwt")));
                }

            }
        });

        language = saved_data.get_lang_num(Objects.requireNonNull(getContext()));
        Log.e("lang", Locale.getDefault().getLanguage());

        return view;
    }

    @Override
    public void onResume() {
        language = saved_data.get_lang_num(Objects.requireNonNull(getContext()));

        MainActivity.changeLang222(getContext(), language);
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public boolean onBackPressed() {

        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Home_Fragment());
        fr.addToBackStack(null);
        fr.commit();
        MainActivity.navigation.setSelectedItemId(R.id.home_nav);
        return true;
    }


    public void addChat(String image, String myname, int myId) {

        Log.e("gropu", image);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        firestore.setFirestoreSettings(settings);


//        chatID = "111222333444";
//        List<Passenger> chatMembers = new ArrayList<>();
        List<Integer> users = new ArrayList<>();

        users.add(Integer.valueOf(myId));

//        Passenger passenger = new Passenger();
//        passenger = new Passenger(Integer.parseInt(id), image, namee);
//        passenger = new Passenger(Integer.parseInt(id), image, namee);

        int idAlshal = 55667788;
        String imageAlshal = "Picture";
        String nameAlshal = "Alshal";

//        users.add(myId);
//        chatMembers.add(new Passenger(myId, image, myname)); // Your Object
//
//        chatMembers.add(new Passenger(idAlshal, imageAlshal, nameAlshal)); // other gay Object
        users.add(idAlshal);


        lastMessage = ""; // the Current Message Wrote for the Booking

//        if (lastMessage.equals("")) {
//            lastMessage = "In this chat you can ask for more information about this booking";
//        }

//        int groupID = Integer.parseInt(id);
        Conversation conversation = new Conversation();
        conversation.setLastMessage(lastMessage);
        conversation.setSenderName(myname);
        conversation.setStatus("Active");
        conversation.setUsers(users);
        conversation.setTimeStamp(Calendar.getInstance().getTime());


        db.collection("Conversations").document(String.valueOf(myId)).set(conversation).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

//                sendMessageInitial("In this chat you can ask for more information about this booking", event_id);

//                chat = 1;
//                Objects.requireNonNull(getActivity()).startActivity(new Intent(getContext(), Chat_Activity.class));
                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Chat_fragment());
                fr.addToBackStack(null);
                fr.commit();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.e("addChat", e.toString());
            }
        });

    }


    public static String FACEBOOK_URL = "https://www.facebook.com/Tadallalapplication-103562839033572";
    public static String FACEBOOK_PAGE_ID = "Tadallalapplication-103562839033572";

    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }
}
