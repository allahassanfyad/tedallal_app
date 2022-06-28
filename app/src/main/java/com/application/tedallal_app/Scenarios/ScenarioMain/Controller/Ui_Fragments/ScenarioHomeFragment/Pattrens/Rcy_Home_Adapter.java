package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Pattrens;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.Home_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Controller.Home_Details_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Model.ModelAllCategoryResponse;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class Rcy_Home_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ModelAllCategoryResponse> rcyHomelList;
    private Context context;
    private TinyDB tinyDB;
    private int selected_position = -1;
    public static int edit_car_brand = 0;
    ArrayList<String> images = new ArrayList<String>();


    public Rcy_Home_Adapter(List<ModelAllCategoryResponse> rcypreviouslList, Context context) {

        this.context = context;
        this.rcyHomelList = rcypreviouslList;

    }


    @NonNull
    @Override
    public notificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_fragment_item, parent, false);
        notificationHolder holder = new notificationHolder(ads);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        final ModelAllCategoryResponse songs = rcyHomelList.get(position);

        tinyDB = new TinyDB(context);

        notificationHolder itemHome = (notificationHolder) holder;

        if (saved_data.get_lang_num(context).equals("ar")) {

            itemHome.txthome.setText(songs.getName());

        } else if (saved_data.get_lang_num(context).equals("en")) {


            itemHome.txthome.setText(songs.getNameEn());


        }


        Glide.with(context)
                .load(songs.getImg())
                .placeholder(R.drawable.holder_png)
                .into(itemHome.imgHome);


//
//
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Home_Fragment.timer.cancel();
                String id_user = saved_data.get_user_id(context);

//                MainActivity.loading.setVisibility(View.VISIBLE);
//                new Apicalls(context,Rcy_Home_Adapter.this).get_All_Product_By_Category(songs.getName(),id_user);


                if (saved_data.get_lang_num(context).equals("ar")) {

                    tinyDB.putString("CategoryName", String.valueOf(rcyHomelList.get(position).getName()));

                } else if (saved_data.get_lang_num(context).equals("en")) {


                    tinyDB.putString("CategoryName", String.valueOf(rcyHomelList.get(position).getNameEn()));


                }


//                tinyDB.putString("CategoryName", String.valueOf(rcyHomelList.get(position).getName()));
                tinyDB.putString("CategoryIdd", String.valueOf(rcyHomelList.get(position).getId()));


//                images.add(songs.getImg1());
//                images.add(songs.getImg2());
//                images.add(songs.getImg3());
//                images.add(songs.getSlider());

//                tinyDB.putListString("listImage", images);

                FragmentTransaction fr = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Home_Details_Fragment());
                fr.addToBackStack(null);
                fr.commit();


//                tinyDB.putString("Brand_Id", String.valueOf(rcyHomelList.get(position).getId()));


//                context.startActivity(new Intent(context, Order_Details.class));


            }
        });


//             EnglishSongHolder songHolder =(EnglishSongHolder)Holder;

    }


    @Override
    public int getItemCount() {
        return rcyHomelList.size();
    }


    public static class notificationHolder extends RecyclerView.ViewHolder {

        ImageView imgHome;
        TextView txthome;
//        LinearLayout linearupcoming;
//        ShimmerFrameLayout container;


        private notificationHolder(@NonNull View itemView) {
            super(itemView);

            imgHome = itemView.findViewById(R.id.imgHome);

            txthome = itemView.findViewById(R.id.txtHome);
//            linearupcoming = itemView.findViewById(R.id.linearUpComing);

//            container = itemView.findViewById(R.id.shimmer_view_container);
//            container.startShimmerAnimation();

        }
    }
}