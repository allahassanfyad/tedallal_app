package com.application.tedallal_app.Scenarios.ScenarioChat.Pattrens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioChat.Model.ChatItem;
import com.application.tedallal_app.local_data.saved_data;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.google.firebase.firestore.FirebaseFirestore;


public class Chat_Adapter extends FirestoreRecyclerAdapter<ChatItem, RecyclerView.ViewHolder> {

    FirestoreRecyclerOptions<ChatItem> options;
    private Context context;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    public Chat_Adapter(FirestoreRecyclerOptions<ChatItem> options, Context context) {
        super(options);
        this.options = options;
        this.context = context;


    }


    @Override
    public int getItemViewType(int position) {
//        Passenger passenger = new Passenger();
        int sender = (int) Integer.parseInt(String.valueOf(options.getSnapshots().get(position).getSender()));
        int user_id = Integer.parseInt(saved_data.get_user_id(context));

        if (sender == user_id) {
            return 0;
        }
        return 1;
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;

        if (viewType == 0) {
            view = layoutInflater.inflate(R.layout.chat_item_right, parent, false); //Normal Chat UI
            return new MyHolder(view);
        }

        view = layoutInflater.inflate(R.layout.chat_item_left, parent, false); // Admin UI
        return new NormalHolder(view);

    }


    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, int i, ChatItem chatItem) {
        int viewType = getItemViewType(i);


        if (viewType == 0) {
            // Direction of You

            MyHolder myHolder = (MyHolder) holder;

            myHolder.message.setText(chatItem.getMessage());
            myHolder.txtMessageTime.setReferenceTime(chatItem.getTimeStamp().getTime());

        } else {
            // Direction of the Other

            NormalHolder normalHolder = (NormalHolder) holder;

            normalHolder.message.setText(chatItem.getMessage());
            normalHolder.txtMessageTime.setReferenceTime(chatItem.getTimeStamp().getTime());

//            Glide.with(context)
//                    .load(songs.getCoverImage())
//                    .placeholder(R.drawable.car_wash)
//                    .into(itemWasherPhotos.imgphotos);


        }

    }


    // View Holder for Normal Chat
    class NormalHolder extends RecyclerView.ViewHolder {

        //        LinearLayout layout;
        TextView message;
        ImageView image;
        RelativeTimeTextView txtMessageTime;

        NormalHolder(View itemView) {
            super(itemView);

            txtMessageTime = itemView.findViewById(R.id.date);
            message = itemView.findViewById(R.id.txtMessage);

        }
    }


    // View Holder for Admin Chat
    class MyHolder extends RecyclerView.ViewHolder {

        //        LinearLayout layout;
        TextView message;
        RelativeTimeTextView txtMessageTime;
//        ImageView image;

        MyHolder(View itemView) {
            super(itemView);
//            layout = itemView.findViewById(R.id.layout);


            txtMessageTime = itemView.findViewById(R.id.date);
            message = itemView.findViewById(R.id.txtMessage);
//            image = itemView.findViewById(R.id.image);
        }
    }


    public void updateChat(String last) {

        db.collection("Conversations").document("group_23").update("lastMessage", last);

    }


}