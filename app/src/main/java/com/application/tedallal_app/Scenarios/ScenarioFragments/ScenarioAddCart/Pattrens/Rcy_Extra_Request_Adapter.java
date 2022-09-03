package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Pattrens;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Controller.Add_Cart_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Model.ModelExtraRequestResponse;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.Home_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Controller.Home_Details_Fragment2;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Model.ModelAllCategoryResponse;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class Rcy_Extra_Request_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ModelExtraRequestResponse> rcyHomelList;
    private Context context;
    private TinyDB tinyDB;
    private int selected_position = -1;
    public static int edit_car_brand = 0;
    ArrayList<String> images = new ArrayList<String>();
    public static double extrarequest = 0;
    TextView txtPrice;
    TextView txtNumber;
    String priceee;


    public Rcy_Extra_Request_Adapter(List<ModelExtraRequestResponse> rcypreviouslList, Context context, TextView txtPrice, String priceee, TextView txtNumber) {

        this.context = context;
        this.rcyHomelList = rcypreviouslList;
        this.txtPrice = txtPrice;
        this.priceee = priceee;

        this.txtNumber = txtNumber;

    }


    @NonNull
    @Override
    public notificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.extra_request_item, parent, false);
        notificationHolder holder = new notificationHolder(ads);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        final ModelExtraRequestResponse songs = rcyHomelList.get(position);

        tinyDB = new TinyDB(context);

        notificationHolder itemHome = (notificationHolder) holder;

        if (saved_data.get_lang_num(context).equals("ar")) {

            itemHome.txtextra.setText(songs.getTitle_ar());

        } else if (saved_data.get_lang_num(context).equals("en")) {


            itemHome.txtextra.setText(songs.getTitle_en());


        }


//
//
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (itemHome.checkBox.isChecked()) {

                    itemHome.checkBox.setChecked(false);

                    double extraPrice = Double.parseDouble(songs.getProduct_price());
                    extrarequest = extrarequest - extraPrice;


                    Add_Cart_Fragment.extraRequests.remove(songs.getTitle_ar());

                    Add_Cart_Fragment add_cart_fragment = new Add_Cart_Fragment();
                    add_cart_fragment.calc_Price(priceee, txtNumber, txtPrice, Add_Cart_Fragment.sizePrice);

//                    services_selected.remove(String.valueOf(songs.getId()));
                    Log.e("Services_Deleted", String.valueOf(extrarequest));
//                    Log.e("extraArray", Add_Cart_Fragment.extraRequests.toString());

                } else {

                    itemHome.checkBox.setChecked(true);

                    Add_Cart_Fragment.extraRequests.add(songs.getTitle_ar());

                    double extraPrice = Double.parseDouble(songs.getProduct_price());
                    extrarequest = extrarequest + extraPrice;

                    Add_Cart_Fragment add_cart_fragment = new Add_Cart_Fragment();
                    add_cart_fragment.calc_Price(priceee, txtNumber, txtPrice, Add_Cart_Fragment.sizePrice);




                    Log.e("Services_Added", String.valueOf(extrarequest));
//                    Log.e("extraArray", Add_Cart_Fragment.extraRequests.toString());

                }

            }
        });


//             EnglishSongHolder songHolder =(EnglishSongHolder)Holder;

    }


    @Override
    public int getItemCount() {
        return rcyHomelList.size();
    }


    public static class notificationHolder extends RecyclerView.ViewHolder {

        CheckBox checkBox;
        TextView txtextra;
        LinearLayout linearcheck;
//        LinearLayout linearupcoming;
//        ShimmerFrameLayout container;


        private notificationHolder(@NonNull View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.CheckBox);
            linearcheck = itemView.findViewById(R.id.linearCheck);
            txtextra = itemView.findViewById(R.id.txtCheck);


//            linearupcoming = itemView.findViewById(R.id.linearUpComing);

//            container = itemView.findViewById(R.id.shimmer_view_container);
//            container.startShimmerAnimation();

        }
    }
}