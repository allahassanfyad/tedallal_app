package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Pattrens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Model.mode_introduction;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioPreviewImage.Controller.Preview_Image_Fragment;
import com.application.tedallal_app.Utils.TinyDB;
import com.bumptech.glide.Glide;

import java.util.List;

public class Rcy_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<mode_introduction> rcyHomelList;
    Context context;
    TinyDB tinyDB;

    public Rcy_adapter(List<mode_introduction> rcyHomelList, Context context) {

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
                .into(itemHomeHolder.imgitemhome);

        itemHomeHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tinyDB.putString("imageSelected",songs.getImghome());

                FragmentTransaction fr = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
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