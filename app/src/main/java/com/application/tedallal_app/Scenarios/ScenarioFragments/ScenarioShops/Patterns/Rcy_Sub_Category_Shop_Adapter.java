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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.application.tedallal_app.NetworkLayer.Apicalls;
import com.application.tedallal_app.NetworkLayer.NetworkInterface;
import com.application.tedallal_app.NetworkLayer.ResponseModel;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Models.ModelSubCategoryShopsResponse;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Models.Model_Shops;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Model.ModelAllProductByCategoreyResponse;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Model.ModelSubCategoryResponse;
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

public class Rcy_Sub_Category_Shop_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements NetworkInterface {

    private List<ModelSubCategoryShopsResponse> rcyHomelList;
    ModelAllProductByCategoreyResponse[] homeDetailsResponses;
    List<ModelAllProductByCategoreyResponse> homeDetailsList = new ArrayList<>();
    private Context context;
    RecyclerView rcyhomedetails;
    private TinyDB tinyDB;
    private int selected_position = -1;
    private int lastPosition = -1;
    Model_Shops[] model_shops;

    public Rcy_Sub_Category_Shop_Adapter(List<ModelSubCategoryShopsResponse> rcypreviouslList, Context context, RecyclerView rcyhomedetails) {

        this.rcyhomedetails = rcyhomedetails;
        this.context = context;
        this.rcyHomelList = rcypreviouslList;

    }


    @NonNull
    @Override
    public ItemWasherServices onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_category_item, parent, false);
        ItemWasherServices holder = new ItemWasherServices(ads);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        final ModelSubCategoryShopsResponse songs = rcyHomelList.get(position);

        tinyDB = new TinyDB(context);

        ItemWasherServices itemHome = (ItemWasherServices) holder;

        // Here you apply the animation when the view is bound
        setAnimation(holder.itemView, position);


        if (saved_data.get_lang_num(Objects.requireNonNull(context)).equals("ar")) {

            itemHome.textservicesname.setText(songs.getTitle_ar());

        } else if (saved_data.get_lang_num(context).equals("en")) {

            itemHome.textservicesname.setText(songs.getTitle_en());

        }


//        Glide.with(context)
//                .load(songs.getImage())
//                .placeholder(R.drawable.logo_bmw)
//                .into(itemHome.imgServices);


        Glide.with(context)
                .load(songs.getImg_category())
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


//                tinyDB.putString("MyOrderID", String.valueOf(rcyHomelList.get(position).getId()));
//                context.startActivity(new Intent(context, Order_Details.class));

                String subcategoryID = String.valueOf(songs.getId());
                String id = saved_data.get_user_id(Objects.requireNonNull(context));
                MainActivity.loading.setVisibility(View.VISIBLE);
                new Apicalls(context, Rcy_Sub_Category_Shop_Adapter.this).selecte_product_of_category_shops(subcategoryID, id);

            }
        });


//             EnglishSongHolder songHolder =(EnglishSongHolder)Holder;

    }


    @Override
    public int getItemCount() {
        return rcyHomelList.size();
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("response", model.getResponse());
        homeDetailsList.clear();

        Gson gson = new Gson();
        homeDetailsResponses = gson.fromJson(model.getResponse(), ModelAllProductByCategoreyResponse[].class);

        for (int i = 0; i < homeDetailsResponses.length; i++) {

            ModelAllProductByCategoreyResponse homeDetailsResponse = new ModelAllProductByCategoreyResponse();

            homeDetailsResponse.setCategory(homeDetailsResponses[i].getCategory());
            homeDetailsResponse.setDatee(homeDetailsResponses[i].getDatee());
            homeDetailsResponse.setDes(homeDetailsResponses[i].getDes());
            homeDetailsResponse.setDesEn(homeDetailsResponses[i].getDesEn());
            homeDetailsResponse.setId(homeDetailsResponses[i].getId());
            homeDetailsResponse.setImg1(homeDetailsResponses[i].getImg1());
            homeDetailsResponse.setImg2(homeDetailsResponses[i].getImg2());
            homeDetailsResponse.setImg3(homeDetailsResponses[i].getImg3());
            homeDetailsResponse.setPrice(homeDetailsResponses[i].getPrice());
            homeDetailsResponse.setIsfav(homeDetailsResponses[i].getIsfav());
            homeDetailsResponse.setPriceDiscount(homeDetailsResponses[i].getPriceDiscount());
            homeDetailsResponse.setRate(homeDetailsResponses[i].getRate());
            homeDetailsResponse.setSlider(homeDetailsResponses[i].getSlider());
            homeDetailsResponse.setTitle(homeDetailsResponses[i].getTitle());
            homeDetailsResponse.setTitleEn(homeDetailsResponses[i].getTitleEn());
            homeDetailsResponse.setNumberRate(homeDetailsResponses[i].getNumberRate());
            homeDetailsResponse.setNumberStar(homeDetailsResponses[i].getNumberStar());

            if (homeDetailsResponse.getIsfav().equals("yes")) {

                homeDetailsResponse.setFavouret(true);

            }

            homeDetailsList.add(homeDetailsResponse);

        }

        rcyhomedetails.setHasFixedSize(true);
        rcyhomedetails.setLayoutManager(new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false));
        rcyhomedetails.setAdapter(new Rcy_Shops_Product_Adapter(homeDetailsList, context));
        DividerItemDecoration verticalDecoration = new DividerItemDecoration(rcyhomedetails.getContext(),
                DividerItemDecoration.HORIZONTAL);
        Drawable verticalDivider = ContextCompat.getDrawable(context, R.drawable.vertical_divider);
        verticalDecoration.setDrawable(verticalDivider);
        rcyhomedetails.addItemDecoration(verticalDecoration);

    }


    @Override
    public void OnError(VolleyError error) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("error", error.toString());
    }


    public static class ItemWasherServices extends RecyclerView.ViewHolder {

        ImageView imgServices;
        TextView textservicesname, txtservicesPrice;

        ShimmerFrameLayout container;


        private ItemWasherServices(@NonNull View itemView) {
            super(itemView);

            imgServices = itemView.findViewById(R.id.imgServices);
            textservicesname = itemView.findViewById(R.id.txtSubName);

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