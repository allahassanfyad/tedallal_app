package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioConfirmOrder.Pattrens;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.application.tedallal_app.NetworkLayer.Apicalls;
import com.application.tedallal_app.NetworkLayer.NetworkInterface;
import com.application.tedallal_app.NetworkLayer.ResponseModel;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Controller.Add_Cart_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Model.Rcy_Cart_Model;
import com.application.tedallal_app.Scenarios.ScenarioHome.Controller.SignIn;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.Home_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Model.ModelAllProductByCategoreyResponse;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class Rcy_Confirm_Order_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Rcy_Cart_Model> rcyHomelList;
    private Context context;
    private TinyDB tinyDB;
    private int selected_position = -1;
    public static int edit_car_brand = 0;
    ArrayList<String> images = new ArrayList<String>();
    public static int getback = 0;


    public Rcy_Confirm_Order_Adapter(List<Rcy_Cart_Model> rcypreviouslList, Context context) {

        this.context = context;
        this.rcyHomelList = rcypreviouslList;

    }


    @NonNull
    @Override
    public notificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.complete_order_item, parent, false);
        notificationHolder holder = new notificationHolder(ads);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        final Rcy_Cart_Model songs = rcyHomelList.get(position);

        tinyDB = new TinyDB(context);

        holder.itemView.getLayoutParams().width = getScreenWidth(context) / 2;

        notificationHolder itemHome = (notificationHolder) holder;

        itemHome.txttitle.setText(songs.getTxttitle());


        itemHome.txtprice.setText(songs.getTxtprice());


        Glide.with(context)
                .load(songs.getImghome())
                .placeholder(R.drawable.holder_png)
                .into(itemHome.imgphoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


//             EnglishSongHolder songHolder =(EnglishSongHolder)Holder;

    }


    @Override
    public int getItemCount() {
        return rcyHomelList.size();
    }


    public static class notificationHolder extends RecyclerView.ViewHolder {

        ImageView imgphoto;
        TextView txttitle, txtprice;
//        LinearLayout linearupcoming;
//        ShimmerFrameLayout container;


        private notificationHolder(@NonNull View itemView) {
            super(itemView);

            imgphoto = itemView.findViewById(R.id.imgPhoto);
            txtprice = itemView.findViewById(R.id.txtPrice);
            txttitle = itemView.findViewById(R.id.txtTitle);
//            linearupcoming = itemView.findViewById(R.id.linearUpComing);

//            container = itemView.findViewById(R.id.shimmer_view_container);
//            container.startShimmerAnimation();

        }
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels - 185;
    }
}