package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAllAddress.Pattrens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAllAddress.Model.ModelAllAddressResponse;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioConfirmOrder.Controller.Confirm_Order_Fragment;
import com.application.tedallal_app.Utils.Realm_adapter;
import com.application.tedallal_app.Utils.TinyDB;

import java.util.List;

import io.realm.Realm;


public class Rcy_All_Address_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ModelAllAddressResponse> rcyHomelList;
    private Context context;
    private TinyDB tinyDB;
    private int selected_position = -1;
    public static int edit_car_brand = 0;
    Realm realm;
    Realm_adapter adapter;



    public Rcy_All_Address_Adapter(List<ModelAllAddressResponse> rcypreviouslList, Context context) {

        this.context = context;
        this.rcyHomelList = rcypreviouslList;

    }


    @NonNull
    @Override
    public notificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_address_item, parent, false);
        notificationHolder holder = new notificationHolder(ads);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        final ModelAllAddressResponse songs = rcyHomelList.get(position);

        tinyDB = new TinyDB(context);

        notificationHolder itemHome = (notificationHolder) holder;

        if (songs.getName() != null){

            itemHome.txtname.setText(songs.getName());

        }else {

            itemHome.txtname.setText("");

        }


        itemHome.txtAddress.setText(songs.getStreet()+" , "+songs.getArea()+" , "+songs.getCountry()+" , "+songs.getCity()+" , "+songs.getPhone());

        itemHome.btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realm = Realm.getDefaultInstance();
                adapter = new Realm_adapter(realm);
                adapter.updateAddress(songs.getId(),songs.getAddress());
                tinyDB.putString("addressChoose",songs.getName()+" "+songs.getArea()+" "+songs.getStreet()+" "+songs.getCity()+" "+songs.getCountry()+" "+songs.getPhone());
                tinyDB.putString("addressNaigbourStreet",songs.getArea()+" "+songs.getStreet());
                tinyDB.putString("addressCountryCity",songs.getCity()+" "+songs.getCountry());
                tinyDB.putString("addressPhone",songs.getPhone());

                if (songs.getName() != null){

                    tinyDB.putString("addressName",songs.getName());

                }else {

                    tinyDB.putString("addressName","");

                }

                FragmentTransaction fr = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Confirm_Order_Fragment());
                fr.addToBackStack(null);
                fr.commit();


            }
        });



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
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (selected_position == position) {
//                    selected_position = -1;
//                    notifyDataSetChanged();
//                    return;
//                }
//                selected_position = position;
//                notifyDataSetChanged();
//
////                tinyDB.putString("Brand_Id", String.valueOf(rcyHomelList.get(position).getId()));
//
//
////                context.startActivity(new Intent(context, Order_Details.class));
//
//
//            }
//        });


//             EnglishSongHolder songHolder =(EnglishSongHolder)Holder;

    }


    @Override
    public int getItemCount() {
        return rcyHomelList.size();
    }


    public static class notificationHolder extends RecyclerView.ViewHolder {

        Button btnChoose;
        TextView txtAddress,txtname;
//        LinearLayout linearupcoming;
//        ShimmerFrameLayout container;


        private notificationHolder(@NonNull View itemView) {
            super(itemView);

            btnChoose = itemView.findViewById(R.id.btnChoose);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtname = itemView.findViewById(R.id.txtName);



//            linearupcoming = itemView.findViewById(R.id.linearUpComing);

//            container = itemView.findViewById(R.id.shimmer_view_container);
//            container.startShimmerAnimation();

        }
    }
}