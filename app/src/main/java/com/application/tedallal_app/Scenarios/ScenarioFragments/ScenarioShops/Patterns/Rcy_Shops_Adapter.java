package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Patterns;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.application.tedallal_app.NetworkLayer.Apicalls;
import com.application.tedallal_app.NetworkLayer.NetworkInterface;
import com.application.tedallal_app.NetworkLayer.ResponseModel;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Controller.Add_Cart_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Controller.Shop_Product_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Models.Model_Shops;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Model.ModelAllProductByCategoreyResponse;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Model.ModelSubCategoryResponse;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Pattrens.Rcy_Home_Details_Adapter;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rcy_Shops_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Model_Shops> rcyHomelList;
    private Context context;
    private TinyDB tinyDB;
    private int selected_position = -1;
    private int lastPosition = -1;

    public Rcy_Shops_Adapter(List<Model_Shops> rcypreviouslList, Context context) {

        this.context = context;
        this.rcyHomelList = rcypreviouslList;

    }


    @NonNull
    @Override
    public ItemWasherServices onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.shops_item, parent, false);
        ItemWasherServices holder = new ItemWasherServices(ads);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        final Model_Shops songs = rcyHomelList.get(position);

        tinyDB = new TinyDB(context);

        ItemWasherServices itemHome = (ItemWasherServices) holder;

        // Here you apply the animation when the view is bound
        setAnimation(holder.itemView, position);


        itemHome.textservicesname.setText(songs.getNameCompany());
        if (songs.getTypeShop().equals("Opening")) {

            final int sdk = android.os.Build.VERSION.SDK_INT;

            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {

                itemHome.relativeStatus.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circle_background_green_small));

            } else {

                itemHome.relativeStatus.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_background_green_small));
            }


        } else if (songs.getTypeShop().equals("Closed")) {

            final int sdk = android.os.Build.VERSION.SDK_INT;

            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {

                itemHome.relativeStatus.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circle_background_gray_small));

            } else {

                itemHome.relativeStatus.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_background_gray_small));
            }

        }


//        Glide.with(context)
//                .load(songs.getImage())
//                .placeholder(R.drawable.logo_bmw)
//                .into(itemHome.imgServices);


        Glide.with(context)
                .load(songs.getImg())
                .placeholder(R.drawable.holder_png)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                        assert e != null;
                        Log.e("photoFaild", e.toString());

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        itemHome.container.stopShimmerAnimation();
                        return false;
                    }

                })
                .into(itemHome.imgServices);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tinyDB.putString("Id_Supplier", String.valueOf(songs.getId()));

                tinyDB.putString("Type_Shop", String.valueOf(songs.getTypeShop()));
                tinyDB.putString("Company_Name", String.valueOf(songs.getNameCompany()));
                tinyDB.putString("Closing_Time", String.valueOf(songs.getClosingTime()));
                tinyDB.putString("Opening_Time", String.valueOf(songs.getOpeningTime()));
                tinyDB.putString("Shop_Logo", String.valueOf(songs.getImg()));

                FragmentTransaction fr = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Shop_Product_Fragment());
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


    public static class ItemWasherServices extends RecyclerView.ViewHolder {

        ImageView imgServices;
        TextView textservicesname, txtservicesPrice;
        RelativeLayout relativeStatus;
        ShimmerFrameLayout container;


        private ItemWasherServices(@NonNull View itemView) {
            super(itemView);

            imgServices = itemView.findViewById(R.id.imgServices);
            textservicesname = itemView.findViewById(R.id.txtSubName);
            relativeStatus = itemView.findViewById(R.id.relativeStatus);

            container = itemView.findViewById(R.id.shimmer_view_container);
            container.startShimmerAnimation();


        }
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}