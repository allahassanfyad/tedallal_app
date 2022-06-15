package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioSearchFragment.Pattrens;

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

import com.android.volley.VolleyError;

import com.application.tedallal_app.NetworkLayer.NetworkInterface;
import com.application.tedallal_app.NetworkLayer.ResponseModel;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Controller.Add_Cart_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Model.ModelAllProductByCategoreyResponse;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class Rcy_Search_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements NetworkInterface {

    private List<ModelAllProductByCategoreyResponse> rcyHomelList;
    private Context context;
    private TinyDB tinyDB;
    private int selected_position = -1;
    public static int edit_car_brand = 0;
    ArrayList<String> images = new ArrayList<String>();


    public Rcy_Search_Adapter(List<ModelAllProductByCategoreyResponse> rcypreviouslList, Context context) {

        this.context = context;
        this.rcyHomelList = rcypreviouslList;

    }


    @NonNull
    @Override
    public notificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_details_item, parent, false);
        notificationHolder holder = new notificationHolder(ads);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        final ModelAllProductByCategoreyResponse songs = rcyHomelList.get(position);

        tinyDB = new TinyDB(context);

        notificationHolder itemHome = (notificationHolder) holder;
        if (saved_data.get_lang_num(context).equals("ar")) {

            itemHome.txttitle.setText(songs.getTitle());

        } else if (saved_data.get_lang_num(context).equals("en")) {


            itemHome.txttitle.setText(songs.getTitleEn());


        }

        itemHome.txtprice.setText(songs.getPrice());


        Glide.with(context)
                .load(songs.getImg1())
                .placeholder(R.drawable.logo_tedallal)
                .into(itemHome.imgphoto);

        if (songs.getFavouret() == true) {
            itemHome.imgFav.setImageResource(R.drawable.heart_selected_png);
            songs.setFavouret(true);
        } else if (songs.getFavouret() == false) {
            itemHome.imgFav.setImageResource(R.drawable.heart_unselected_png);

        }


//        itemHome.imgFav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (saved_data.get_user_id(context).equals("0") || saved_data.get_switch_checked(context) == false) {
//
//                    MainActivity.login = 1;
//                    context.startActivity(new Intent(context, SignIn.class));
//
//
//                } else {
//
//
//                    if (songs.getIsFav() == false) {
////                        x = 1;
//                        selected_position = position;
//                        songs.setIsFav(true);
//                        String event_id = String.valueOf(songs.getId());
//                        itemHome.imgFav.setImageResource(R.drawable.heart_selected_svg);
//                        MainActivity.loading.setVisibility(View.VISIBLE);
//                        new Apicalls(context, Rcy_Home_Details_Adapter.this).insert_Fav(saved_data.get_user_id(context), String.valueOf(songs.getId()));
//                        notifyItemChanged(position);
//
//                    } else {
//                        selected_position = position;
//                        songs.setIsFav(false);
//                        String event_id = String.valueOf(songs.getId());
//                        itemHome.imgFav.setImageResource(R.drawable.heart_unselected_svg);
//                        MainActivity.loading.setVisibility(View.VISIBLE);
//                        new Apicalls(context, Rcy_Home_Details_Adapter.this).insert_Fav(saved_data.get_user_id(context), String.valueOf(songs.getId()));
//                        notifyItemChanged(position);
////                        Toasty.error(Objects.requireNonNull(context), R.string.this_product_already_in_fav, Toast.LENGTH_LONG).show();
//
//
//                    }
//
//
////                    MainActivity.loading.setVisibility(View.VISIBLE);
////                    new Apicalls(context,Rcy_Home_Details_Adapter.this).insert_Fav(saved_data.get_user_id(context), String.valueOf(songs.getId()));
//
//                }
//
//
//            }
//        });

//        itemHome.imgphoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                FragmentTransaction fr = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
//                fr.replace(R.id.fragment_container, new Add_Cart_Fragment());
//                fr.addToBackStack(null);
//                fr.commit();
//
//
//            }
//        });


//        Glide.with(context)
//                .load(songs.getLogo())
//                .placeholder(R.drawable.logo_bmw)
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//
//                        itemHome.container.stopShimmerAnimation();
//
//                        return false;
//                    }
//                })
//                .into(itemHome.imgcarbrand);

//        Glide.with(context)
//                .load(rcyHomelList.get(position).getLogo())
//                .placeholder(R.drawable.gray_img)
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//
//                        assert e != null;
//                        Log.e("error", e.toString());
//
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//
//                        itemHome.container.stopShimmerAnimation();
//                        return false;
//                    }
//                })
//                .into(itemHome.imgcarbrand);

//
//        if (edit_car_brand == rcyHomelList.get(position).getId()) {
//
//            Log.e("car_brand", String.valueOf(edit_car_brand));
//            edit_car_brand = 0;
//
//            selected_position = position;
//            final int sdk = android.os.Build.VERSION.SDK_INT;
//
//            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                itemHome.linearcarbrand.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.raduis_all_with_strok_item_selected));
//            } else {
//                itemHome.linearcarbrand.setBackground(ContextCompat.getDrawable(context, R.drawable.raduis_all_with_strok_item_selected));
//            }
//
//        }

//
//        if (selected_position == position) {
//            // do your stuff here like
//            //Change selected item background color and Show sub item views
//
////            tinyDB.putString("Brand_Id", String.valueOf(rcyHomelList.get(position).getId()));
//
//            final int sdk = android.os.Build.VERSION.SDK_INT;
//
//            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                itemHome.linearupcoming.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.raduis_all_with_strok_upcoming_selected));
//            } else {
//                itemHome.linearupcoming.setBackground(ContextCompat.getDrawable(context, R.drawable.raduis_all_with_strok_upcoming_selected));
//            }
//
//        } else {
//            // do your stuff here like
//            //Change  unselected item background color and Hide sub item views
//
//            final int sdk = android.os.Build.VERSION.SDK_INT;
//
//            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                itemHome.linearupcoming.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.raduis_all_with_upcoming_unselected));
//            } else {
//                itemHome.linearupcoming.setBackground(ContextCompat.getDrawable(context, R.drawable.raduis_all_with_upcoming_unselected));
//            }
//        }
//
//
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (selected_position == position) {
//                    selected_position = -1;
//                    notifyDataSetChanged();
//                    return;
//                }
//                selected_position = position;
//                notifyDataSetChanged();

                tinyDB.putString("ProductId", String.valueOf(rcyHomelList.get(position).getId()));
                tinyDB.putString("ProductTitle", String.valueOf(rcyHomelList.get(position).getId()));
                tinyDB.putString("ProductPrice", String.valueOf(rcyHomelList.get(position).getId()));


                images.add(songs.getImg1());
                images.add(songs.getImg2());
                images.add(songs.getImg3());
                images.add(songs.getSlider());

                tinyDB.putListString("listImage", images);
                tinyDB.putBoolean("isfav", songs.getFavouret());
                FragmentTransaction fr = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Add_Cart_Fragment());
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

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("response", model.getResponse());
        Toasty.success(Objects.requireNonNull(context), R.string.product_addd_to_favourite, Toast.LENGTH_LONG).show();

    }

    @Override
    public void OnError(VolleyError error) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("error", error.toString());

    }


    public static class notificationHolder extends RecyclerView.ViewHolder {

        ImageView imgphoto, imgFav;
        TextView txttitle, txtprice;
//        LinearLayout linearupcoming;
//        ShimmerFrameLayout container;


        private notificationHolder(@NonNull View itemView) {
            super(itemView);

            imgphoto = itemView.findViewById(R.id.imgPhoto);
            imgFav = itemView.findViewById(R.id.imgFav);
            txtprice = itemView.findViewById(R.id.txtPrice);
            txttitle = itemView.findViewById(R.id.txtTitle);
//            linearupcoming = itemView.findViewById(R.id.linearUpComing);

//            container = itemView.findViewById(R.id.shimmer_view_container);
//            container.startShimmerAnimation();

        }
    }
}