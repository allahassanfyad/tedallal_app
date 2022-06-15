package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioOrders.Pattrens;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Model.Rcy_Cart_Model;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioOrders.Model.ModelOrdersDetailsResponse;
import com.application.tedallal_app.Utils.Realm_adapter;
import com.application.tedallal_app.local_data.saved_data;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class Rcy_Order_Product_Adapter extends RecyclerView.Adapter<Rcy_Order_Product_Adapter.ItemeCartHolder> {


    List<ModelOrdersDetailsResponse> rcyHomelList;

    Realm realm;
    Realm_adapter adapter;
    Context context;
    int num = 1;
    public static int order_back = 0;
    ArrayList<Rcy_Cart_Model> cartModels = new ArrayList<>();

    public Rcy_Order_Product_Adapter(List<ModelOrdersDetailsResponse> rcyHomelList, Context context) {

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
        final ModelOrdersDetailsResponse songs = rcyHomelList.get(position);


        ItemeCartHolder itemeCartHolder = (ItemeCartHolder) holder;

        Glide.with(context)
                .load(songs.getImg1())
                .placeholder(R.drawable.gray_img)
                .into(itemeCartHolder.imgitemhome);

        if (saved_data.get_lang_num(context).equals("ar")) {


            itemeCartHolder.txttitle.setText(songs.getTitle());


        } else if (saved_data.get_lang_num(context).equals("en")) {


            itemeCartHolder.txttitle.setText(songs.getTitleEn());


        }
        itemeCartHolder.txtquntity.setText(songs.getItemNumber());
        itemeCartHolder.txtsize.setText(songs.getItemSize());
        itemeCartHolder.txtprice.setText(songs.getPrice());
        if (songs.getItemLength().equals("none")){

            itemeCartHolder.txtlength.setText("");

        }else {

            itemeCartHolder.txtlength.setText(context.getString(R.string.customize)+" "+songs.getItemLength());

        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                order_back = 1;


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