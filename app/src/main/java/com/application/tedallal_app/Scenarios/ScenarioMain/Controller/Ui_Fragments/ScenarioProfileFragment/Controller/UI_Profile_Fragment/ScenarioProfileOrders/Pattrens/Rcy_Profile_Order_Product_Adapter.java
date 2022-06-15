package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioProfileFragment.Controller.UI_Profile_Fragment.ScenarioProfileOrders.Pattrens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Model.Rcy_Cart_Model;
import com.application.tedallal_app.Utils.Realm_adapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class Rcy_Profile_Order_Product_Adapter extends RecyclerView.Adapter<Rcy_Profile_Order_Product_Adapter.ItemeCartHolder> {


    List<Rcy_Cart_Model> rcyHomelList;

    Realm realm;
    Realm_adapter adapter;
    Context context;
    int num = 1;
    public static int profile_order_back = 0;

    ArrayList<Rcy_Cart_Model> cartModels = new ArrayList<>();

    public Rcy_Profile_Order_Product_Adapter(List<Rcy_Cart_Model> rcyHomelList, Context context) {

        this.context = context;
        this.rcyHomelList = rcyHomelList;

    }


    @NonNull
    @Override
    public ItemeCartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_details_product_item, parent, false);
        ItemeCartHolder holder = new ItemeCartHolder(ads);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ItemeCartHolder holder, final int position) {
        int viewType = getItemViewType(position);
        final Rcy_Cart_Model songs = rcyHomelList.get(position);


        ItemeCartHolder itemeCartHolder = (ItemeCartHolder) holder;

        int price = Integer.parseInt(songs.getTxtprice());
        int number = Integer.parseInt(songs.getTxtnumberchoose());

        int totalpricebefortax = price * number;


        itemeCartHolder.txttitle.setText(songs.getTxttitle());
        itemeCartHolder.txtprice.setText("" + totalpricebefortax);
        itemeCartHolder.txtquntity.setText(songs.getTxtnumberchoose());

        Glide.with(context)
                .load(songs.getImghome())
                .placeholder(R.drawable.logo_tedallal)
                .into(itemeCartHolder.imgitemhome);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                profile_order_back = 1;

            }
        });

//        Holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                dialog_home_item dialogHomeItem = new dialog_home_item();
//                dialogHomeItem.dialog(context, R.layout.item_home_dialog, 1.05);
//
//
//            }
//        });


//             EnglishSongHolder songHolder =(EnglishSongHolder)Holder;

    }


    @Override
    public int getItemCount() {
        return rcyHomelList.size();
    }


    public static class ItemeCartHolder extends RecyclerView.ViewHolder {
        private final TextView txttitle, txtprice, txtsize, txtquntity, txtlength;
        private final ImageView imgitemhome;


        public ItemeCartHolder(@NonNull View itemView) {
            super(itemView);
            txttitle = itemView.findViewById(R.id.txtTitle);
            txtprice = itemView.findViewById(R.id.txtPrice);
            imgitemhome = itemView.findViewById(R.id.imgCart);
            txtquntity = itemView.findViewById(R.id.txtQuntity);
            txtsize = itemView.findViewById(R.id.txtSize);
            txtlength = itemView.findViewById(R.id.txtLength);


        }
    }

}