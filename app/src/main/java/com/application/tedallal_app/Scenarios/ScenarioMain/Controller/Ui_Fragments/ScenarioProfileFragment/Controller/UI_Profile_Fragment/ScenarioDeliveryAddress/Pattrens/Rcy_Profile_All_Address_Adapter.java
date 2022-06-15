package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioProfileFragment.Controller.UI_Profile_Fragment.ScenarioDeliveryAddress.Pattrens;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddAddress.Controller.Add_Address_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAllAddress.Model.ModelAllAddressResponse;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioProfileFragment.Controller.UI_Profile_Fragment.ScenarioDeliveryAddress.Controller.Profile_Delivery_Fragment;
import com.application.tedallal_app.Utils.Realm_adapter;
import com.application.tedallal_app.Utils.TinyDB;

import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;


public class Rcy_Profile_All_Address_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements NetworkInterface {

    private List<ModelAllAddressResponse> rcyHomelList;
    private Context context;
    private TinyDB tinyDB;
    private int selected_position = -1;
    public static int edit_car_brand = 0;
    Realm realm;
    Realm_adapter adapter;


    public Rcy_Profile_All_Address_Adapter(List<ModelAllAddressResponse> rcypreviouslList, Context context) {

        this.context = context;
        this.rcyHomelList = rcypreviouslList;

    }


    @NonNull
    @Override
    public notificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_all_address_item, parent, false);
        notificationHolder holder = new notificationHolder(ads);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        final ModelAllAddressResponse songs = rcyHomelList.get(position);

        tinyDB = new TinyDB(context);

        notificationHolder itemHome = (notificationHolder) holder;


        if (songs.getName() != null) {

            itemHome.txtName.setText(songs.getName());

        } else {

            itemHome.txtName.setText("");

        }
//        itemHome.txtAddress.setText(songs.getStreet()+" , "+songs.getArea()+" , "+songs.getCountry()+" , "+songs.getCity()+" , "+songs.getPhone());

        itemHome.txtnaigbourstreet.setText(context.getString(R.string.naighbour_)+" "+songs.getArea()+ " , " +context.getString(R.string.streat)+" "+songs.getStreet());
        itemHome.txtcitycountry.setText(songs.getCity()+" , "+songs.getCountry());
        itemHome.txtpostalcode.setText(context.getString(R.string.post_code)+" "+songs.getPosta());
        itemHome.txtphone.setText(songs.getPhone());

        itemHome.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Profile_Delivery_Fragment.address = 2;
                tinyDB.putString("AddressId", String.valueOf(songs.getId()));
                tinyDB.putString("AddressCountry", String.valueOf(songs.getCountry()));
                tinyDB.putString("AddressCity", String.valueOf(songs.getCity()));
                tinyDB.putString("AddressArea", String.valueOf(songs.getArea()));
                tinyDB.putString("AddressStreet", String.valueOf(songs.getStreet()));
                tinyDB.putString("AddressPosta", String.valueOf(songs.getPosta()));
                tinyDB.putString("AddressPhone", String.valueOf(songs.getPhone()));
                tinyDB.putString("AddressName", String.valueOf(songs.getName()));

//                FragmentTransaction fr = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
//                fr.replace(R.id.fragment_container, new Confirm_Order_Fragment());
//                fr.addToBackStack(null);
//                fr.commit();
                FragmentTransaction fr = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container2, new Add_Address_Fragment());
                fr.addToBackStack(null);
                fr.commit();

            }
        });

        itemHome.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.loading.setVisibility(View.VISIBLE);
                new Apicalls(context, Rcy_Profile_All_Address_Adapter.this).delete_Address(String.valueOf(songs.getId()));

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

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("response", model.getResponse());
        if (model.getResponse().equals("True")) {

            FragmentTransaction fr = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container2, new Profile_Delivery_Fragment());
            fr.addToBackStack(null);
            fr.commit();
            Toasty.success(Objects.requireNonNull(context), R.string.address_deleted_successfully, Toast.LENGTH_LONG).show();


        }


    }

    @Override
    public void OnError(VolleyError error) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("error", error.toString());

    }


    public static class notificationHolder extends RecyclerView.ViewHolder {

        Button btnEdit, btnDelete;
        TextView txtnaigbourstreet, txtName, txtcitycountry, txtphone,txtpostalcode;
//        LinearLayout linearupcoming;
//        ShimmerFrameLayout container;


        private notificationHolder(@NonNull View itemView) {
            super(itemView);

            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            txtnaigbourstreet = itemView.findViewById(R.id.txtNaigbourStreet);
            txtcitycountry = itemView.findViewById(R.id.txtCityCountry);
            txtphone = itemView.findViewById(R.id.txtCodePhone);
            txtName = itemView.findViewById(R.id.txtName);
            txtpostalcode = itemView.findViewById(R.id.txtPostalCode);


//            linearupcoming = itemView.findViewById(R.id.linearUpComing);

//            container = itemView.findViewById(R.id.shimmer_view_container);
//            container.startShimmerAnimation();

        }
    }
}