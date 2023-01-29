package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Pattrens;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Controller.Shop_Product_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Models.Model_Shops;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Rcy_Shops_Home_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Model_Shops> rcyHomelList;
    private Context context;
    private TinyDB tinyDB;
    private String language;
    private int lastPosition = -1;
    public static int shops_Home = 0;

    public Rcy_Shops_Home_Adapter(List<Model_Shops> rcypreviouslList, Context context) {

        this.context = context;
        this.rcyHomelList = rcypreviouslList;

    }


    @NonNull
    @Override
    public ItemHome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_home_item, parent, false);
        ItemHome holder = new ItemHome(ads);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        final Model_Shops songs = rcyHomelList.get(position);

        tinyDB = new TinyDB(context);

        ItemHome itemHome = (ItemHome) holder;
        // Here you apply the animation when the view is bound
        setAnimation(holder.itemView, position);

        itemHome.txtcloseTime.setText("" + songs.getClosingTime());
        itemHome.txtopentime.setText("" + songs.getOpeningTime());
//        itemHome.txtmoney.setText(songs.getPrices());
        itemHome.txtopenstate.setText("" + songs.getStatusSuppliers());


        if (saved_data.get_lang_num(context).equals("ar")) {

            itemHome.txttitle.setText(songs.getName_company_ar());

        } else if (saved_data.get_lang_num(context).equals("en")) {

            itemHome.txttitle.setText(songs.getNameCompany());

        }

        Glide.with(context)
                .load(songs.getCover_suppliers())
                .placeholder(R.drawable.gray_img)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (e != null) {
                            Log.e("error_Load_Imag", e.toString());
                        }

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }

                })
                .into(itemHome.imgcove);



        Glide.with(context)
                .load(songs.getImg())
                .placeholder(R.drawable.gray_img)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (e != null) {
                            Log.e("error_Load_Imag", e.toString());
                        }

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }

                })
                .into(itemHome.imglogo);


        if (songs.getTypeShop().equals("Opening")) {

            itemHome.txtopenstate.setTextColor(Color.parseColor("#0b9471"));
            itemHome.txtopenstate.setText(R.string.open);

        } else {

            itemHome.txtopenstate.setTextColor(Color.parseColor("#f52403"));
            itemHome.txtopenstate.setText(R.string.close);


        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tinyDB.putString("Id_Supplier", String.valueOf(songs.getId()));

                tinyDB.putString("Type_Shop", String.valueOf(songs.getTypeShop()));
                tinyDB.putString("Company_Name", String.valueOf(songs.getNameCompany()));
                tinyDB.putString("Closing_Time", String.valueOf(songs.getClosingTime()));
                tinyDB.putString("Opening_Time", String.valueOf(songs.getOpeningTime()));
                tinyDB.putString("Shop_Logo", String.valueOf(songs.getImg()));
                tinyDB.putString("cover_suppliers", String.valueOf(songs.getCover_suppliers()));
                tinyDB.putString("whatsapp", String.valueOf(songs.getWhatsapp()));
                tinyDB.putString("instagram", String.valueOf(songs.getInstagram()));
                tinyDB.putString("snapchat", String.valueOf(songs.getSnapchat()));

                shops_Home = 1;

                FragmentTransaction fr = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Shop_Product_Fragment());
                fr.addToBackStack(null);
                fr.commit();

//                context.startActivity(new Intent(context, Order_Details.class));


            }
        });


//             EnglishSongHolder songHolder =(EnglishSongHolder)Holder;

    }


    @Override
    public int getItemCount() {
        return rcyHomelList.size();
    }


    public static class ItemHome extends RecyclerView.ViewHolder {

        ImageView imgcove, imglogo;
        TextView txttitle, txtopenstate, txtopentime, txtcloseTime;

        private ItemHome(@NonNull View itemView) {
            super(itemView);
            txttitle = itemView.findViewById(R.id.txtTitle);
            txtopenstate = itemView.findViewById(R.id.txtStatus);
            txtopentime = itemView.findViewById(R.id.txtOpenTime);
            txtcloseTime = itemView.findViewById(R.id.txtClosedTime);
            imgcove = itemView.findViewById(R.id.imgCover);
            imglogo = itemView.findViewById(R.id.imgLogo);


        }
    }

//    public static int getScreenWidth(Context context) {
//        WindowManager wm= (WindowManager) context
//                .getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics dm = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(dm);
//        return dm.widthPixels;
//    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.bounce);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}