package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Pattrens;

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
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Models.Model_Shops;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Patterns.Rcy_Shops_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Controller.Home_Details_Fragment;
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

public class Rcy_Sub_Category_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements NetworkInterface {

    private List<ModelSubCategoryResponse> rcyHomelList;
    List<Model_Shops> shops_list = new ArrayList<>();
    private Context context;
    RecyclerView rcyhomedetails;
    private TinyDB tinyDB;
    private int selected_position = -1;
    private int lastPosition = -1;
    Model_Shops[] model_shops;

    public Rcy_Sub_Category_Adapter(List<ModelSubCategoryResponse> rcypreviouslList, Context context, RecyclerView rcyhomedetails) {

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
        final ModelSubCategoryResponse songs = rcyHomelList.get(position);

        tinyDB = new TinyDB(context);

        ItemWasherServices itemHome = (ItemWasherServices) holder;

        // Here you apply the animation when the view is bound
        setAnimation(holder.itemView, position);


        if (saved_data.get_lang_num(Objects.requireNonNull(context)).equals("ar")) {

            itemHome.textservicesname.setText(songs.getNamesub());

        } else if (saved_data.get_lang_num(context).equals("en")) {

            itemHome.textservicesname.setText(songs.getNamesub_en());

        }


//        Glide.with(context)
//                .load(songs.getImage())
//                .placeholder(R.drawable.logo_bmw)
//                .into(itemHome.imgServices);


        Glide.with(context)
                .load(songs.getImgsub())
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
                MainActivity.loading.setVisibility(View.VISIBLE);
                new Apicalls(context, Rcy_Sub_Category_Adapter.this).get_All_Sub_Category_Shops(subcategoryID);

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
        shops_list.clear();

        Gson gson = new Gson();
        model_shops = gson.fromJson(model.getResponse(), Model_Shops[].class);

        if (model_shops != null) {
            Log.e("respone", model.getResponse().toString());
            for (int i = 0; i < model_shops.length; i++) {

                Model_Shops shop = new Model_Shops();
                shop.setId(model_shops[i].getId());
                shop.setIdSuppliers(model_shops[i].getIdSuppliers());
                shop.setIdAdmin(model_shops[i].getIdAdmin());
                shop.setNameSuppliers(model_shops[i].getNameSuppliers());
                shop.setAddress(model_shops[i].getAddress());
                shop.setNameCompany(model_shops[i].getNameCompany());
                shop.setPhone(model_shops[i].getPhone());
                shop.setEmail(model_shops[i].getEmail());
                shop.setPassword(model_shops[i].getPassword());
                shop.setImg(model_shops[i].getImg());
                shop.setDatee(model_shops[i].getDatee());
                shop.setPercent(model_shops[i].getPercent());
                shop.setTypeSuppliers(model_shops[i].getTypeSuppliers());
                shop.setNameEmploy(model_shops[i].getNameEmploy());
                shop.setDateeStart(model_shops[i].getDateeStart());
                shop.setDateeEnd(model_shops[i].getDateeEnd());
                shop.setStatusSuppliers(model_shops[i].getStatusSuppliers());
                shop.setTypeShop(model_shops[i].getTypeShop());
                shop.setClosingTime(model_shops[i].getClosingTime());
                shop.setOpeningTime(model_shops[i].getOpeningTime());
                shop.setWhatsapp(model_shops[i].getWhatsapp());
                shop.setInstagram(model_shops[i].getInstagram());
                shop.setSnapchat(model_shops[i].getSnapchat());
                shops_list.add(shop);

            }

            rcyhomedetails.setHasFixedSize(true);
            rcyhomedetails.setLayoutManager(new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false));
            rcyhomedetails.setAdapter(new Rcy_Shops_Adapter(shops_list, context));


        }
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