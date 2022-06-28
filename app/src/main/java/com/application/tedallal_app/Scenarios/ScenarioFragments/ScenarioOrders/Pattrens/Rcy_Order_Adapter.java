package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioOrders.Pattrens;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioOrders.Controller.Order_Details_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioOrders.Model.ModelAllOrdersResponse;
import com.application.tedallal_app.Utils.TinyDB;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Rcy_Order_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ModelAllOrdersResponse> rcyHomelList;
    private Context context;
    private TinyDB tinyDB;
    private int selected_position = -1;
    public static int edit_car_brand = 0;
    ArrayList<String> images = new ArrayList<String>();


    public Rcy_Order_Adapter(List<ModelAllOrdersResponse> rcypreviouslList, Context context) {

        this.context = context;
        this.rcyHomelList = rcypreviouslList;

    }


    @NonNull
    @Override
    public notificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_all_orders_item, parent, false);
        notificationHolder holder = new notificationHolder(ads);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        final ModelAllOrdersResponse songs = rcyHomelList.get(position);

        tinyDB = new TinyDB(context);

        notificationHolder itemHome = (notificationHolder) holder;

//        if (saved_data.get_lang_num(context).equals("ar")) {
//
//            itemHome.txthome.setText(songs.getName());
//
//        } else if (saved_data.get_lang_num(context).equals("en")) {
//
//
//            itemHome.txthome.setText(songs.getNameEn());
//
//
//        }


//        String datetime = songs.getDatee();
//        String[] splitingdate = datetime.split(" ");
//        String date = splitingdate[0];
//        String time = splitingdate[1];
//        Log.e("dateTime", date + "......." + time);

        itemHome.txttime.setText(songs.getTimee());
        itemHome.txtdate.setText(songs.getDateeeeee());

        itemHome.txtstaus.setText(songs.getStatus());
        itemHome.txtprice.setText(songs.getTotlePrice());
        itemHome.txtordernumber.setText("" + songs.getId());


        itemHome.btnorderdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tinyDB.putString("orderID", String.valueOf(rcyHomelList.get(position).getId()));


                FragmentTransaction fr = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Order_Details_Fragment());
                fr.addToBackStack(null);
                fr.commit();


            }
        });


//
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                String id_user = saved_data.get_user_id(context);
//
////                MainActivity.loading.setVisibility(View.VISIBLE);
////                new Apicalls(context,Rcy_Home_Adapter.this).get_All_Product_By_Category(songs.getName(),id_user);
//
//                tinyDB.putString("orderID", String.valueOf(rcyHomelList.get(position).getId()));
////                tinyDB.putString("ProductTitle", String.valueOf(rcyHomelList.get(position).getId()));
////                tinyDB.putString("ProductPrice", String.valueOf(rcyHomelList.get(position).getId()));
//
//
////                images.add(songs.getImg1());
////                images.add(songs.getImg2());
////                images.add(songs.getImg3());
////                images.add(songs.getSlider());
//
////                tinyDB.putListString("listImage", images);
//
//                FragmentTransaction fr = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
//                fr.replace(R.id.fragment_container, new Home_Details_Fragment());
//                fr.addToBackStack(null);
//                fr.commit();
//
//
////                tinyDB.putString("Brand_Id", String.valueOf(rcyHomelList.get(position).getId()));
//
//
////                context.startActivity(new Intent(context, Order_Details.class));
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


    public static class notificationHolder extends RecyclerView.ViewHolder {

        Button btnorderdetails;
        TextView txtordernumber, txtprice, txttime, txtdate, txtstaus;
//        LinearLayout linearupcoming;
//        ShimmerFrameLayout container;


        private notificationHolder(@NonNull View itemView) {
            super(itemView);

            txtdate = itemView.findViewById(R.id.txtDate);

            txtordernumber = itemView.findViewById(R.id.txtOrderNumber);
            txtprice = itemView.findViewById(R.id.txtPrice);
            txtstaus = itemView.findViewById(R.id.txtStatus);
            txttime = itemView.findViewById(R.id.txtTime);

            btnorderdetails = itemView.findViewById(R.id.btnOrderDetails);
//            linearupcoming = itemView.findViewById(R.id.linearUpComing);

//            container = itemView.findViewById(R.id.shimmer_view_container);
//            container.startShimmerAnimation();

        }
    }
}