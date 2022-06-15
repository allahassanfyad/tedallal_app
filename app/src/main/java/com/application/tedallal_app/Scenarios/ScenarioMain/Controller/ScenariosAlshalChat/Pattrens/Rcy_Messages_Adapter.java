package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.ScenariosAlshalChat.Pattrens;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.ScenariosAlshalChat.Controller.Alshal_Chat_fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.ScenariosAlshalChat.Model.Conversation1;
import com.application.tedallal_app.Utils.TinyDB;
import com.github.curioustechizen.ago.RelativeTimeTextView;

import java.util.ArrayList;
import java.util.List;

public class Rcy_Messages_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Conversation1> rcyHomelList;
    private Context context;
    private TinyDB tinyDB;
    private int selected_position = -1;
    public static int edit_car_brand = 0;
    private ArrayList<String> names;
    String title = "";


    public Rcy_Messages_Adapter(List<Conversation1> rcypreviouslList, ArrayList<String> names, Context context) {

        this.context = context;
        this.rcyHomelList = rcypreviouslList;
        this.names = names;

    }


    @NonNull
    @Override
    public messageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.alshal_message_item, parent, false);
        messageHolder holder = new messageHolder(ads);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        final Conversation1 songs = rcyHomelList.get(position);

        tinyDB = new TinyDB(context);

        messageHolder itemHome = (messageHolder) holder;


//        title += passengerList.get(position).name;

//        passengerList = songs.getChatMembers();

        if (songs.lastMessage == null) {

            itemHome.txtdec.setText("");

        } else {

            itemHome.txtdec.setText(songs.getLastMessage());

        }
        itemHome.txtMessageTime.setReferenceTime(songs.getTimeStamp().getTime());

        itemHome.txttitle.setText(names.get(position));

//        for (int i = 0; i<rcyHomelList.get(position).chatMembers.size(); i ++){
//
//            Passenger passenger =new Passenger();
//
//            passenger = songs.chatMembers.get(i);
//
////            names.add(passenger.getName());
//            title += passenger.getName()+" / ";
//
//            Log.e("name", String.valueOf(passenger.getName()));
//        }
//
//        itemHome.txttitle.setText(title);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tinyDB.putString("userId", String.valueOf(songs.getUserId()));
//                tinyDB.putString("Brand_Id", String.valueOf(rcyHomelList.get(position).getId()));
                Log.e("userId", String.valueOf(songs.getUserId()));

                FragmentTransaction fr = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Alshal_Chat_fragment());
                fr.addToBackStack(null);
                fr.commit();


            }
        });


//             EnglishSongHolder songHolder =(EnglishSongHolder)Holder;

    }


    @Override
    public int getItemCount() {
        return rcyHomelList.size();
    }


    public static class messageHolder extends RecyclerView.ViewHolder {


        TextView txttitle, txtdec;
        RelativeTimeTextView txtMessageTime;

//        LinearLayout linearupcoming;
//        ShimmerFrameLayout container;


        private messageHolder(@NonNull View itemView) {
            super(itemView);


            txtdec = itemView.findViewById(R.id.txtNMessageDescription);
            txttitle = itemView.findViewById(R.id.txtMessageTitle);
            txtMessageTime = itemView.findViewById(R.id.date);
//            linearupcoming = itemView.findViewById(R.id.linearUpComing);

//            container = itemView.findViewById(R.id.shimmer_view_container);
//            container.startShimmerAnimation();

        }
    }
}