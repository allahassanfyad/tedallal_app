package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Pattrens;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Model.mode_introduction;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioPreviewImage.Controller.Preview_Image_Fragment;
import com.application.tedallal_app.Utils.TinyDB;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class Rcy_Slider_Home_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<mode_introduction> rcyHomelList;
    Context context;
    TinyDB tinyDB;
    public static int sliderBack = 0;

    public Rcy_Slider_Home_adapter(List<mode_introduction> rcyHomelList, Context context) {

        this.context = context;
        this.rcyHomelList = rcyHomelList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent, false);
        ItemHomeHolder holder = new ItemHomeHolder(ads);

        tinyDB = new TinyDB(context);

//        ads.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    // run scale animation and make it bigger
//                    Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_in_tv);
//                    ads.startAnimation(anim);
//                    anim.setFillAfter(true);
//                    Toast.makeText(context, "awjdwjaad", Toast.LENGTH_SHORT).show();
//                } else {
//                    // run scale animation and make it smaller
//                    Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_out_tv);
//                    ads.startAnimation(anim);
//                    anim.setFillAfter(true);
//                    Toast.makeText(context, "awjdwjaad", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder Holder, final int position) {
        int viewType = getItemViewType(position);
        final mode_introduction songs = rcyHomelList.get(position);


        ItemHomeHolder itemHomeHolder = (ItemHomeHolder) Holder;


        Glide.with(context)
                .load(songs.getImghome())
                .placeholder(R.drawable.gray_img)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                        assert e != null;
                        Log.e("photoFaild", e.toString());

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        itemHome.container.stopShimmerAnimation();
                        return false;
                    }

                })
                .into(itemHomeHolder.imgitemhome);

//        Glide.with(context)
//                .load(songs.getImghome())
//                .into(itemHomeHolder.imgitemhome);

        itemHomeHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tinyDB.putString("imageSelected", songs.getImghome());

                sliderBack = 1;
                FragmentTransaction fr = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Preview_Image_Fragment());
                fr.addToBackStack(null);
                fr.commit();


            }
        });


    }

    @Override
    public int getItemCount() {
        return rcyHomelList.size();
    }


    public class ItemHomeHolder extends RecyclerView.ViewHolder {
        ImageView imgitemhome;

        public ItemHomeHolder(@NonNull View itemView) {
            super(itemView);

            imgitemhome = itemView.findViewById(R.id.image);


        }
    }
}