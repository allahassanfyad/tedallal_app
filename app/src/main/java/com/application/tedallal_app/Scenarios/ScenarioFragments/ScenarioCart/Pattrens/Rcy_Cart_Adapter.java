package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Pattrens;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Controller.Cart_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Model.Rcy_Cart_Model;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Utils.Realm_adapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class Rcy_Cart_Adapter extends RecyclerView.Adapter<Rcy_Cart_Adapter.ItemeCartHolder> {


    List<Rcy_Cart_Model> rcyHomelList;

    Realm realm;
    Realm_adapter adapter;
    Context context;
    int num = 1;

    ArrayList<Rcy_Cart_Model> cartModels = new ArrayList<>();

    public Rcy_Cart_Adapter(List<Rcy_Cart_Model> rcyHomelList, Context context) {

        this.context = context;
        this.rcyHomelList = rcyHomelList;

    }


    @NonNull
    @Override
    public ItemeCartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        ItemeCartHolder holder = new ItemeCartHolder(ads);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ItemeCartHolder holder, final int position) {
        int viewType = getItemViewType(position);
        final Rcy_Cart_Model songs = rcyHomelList.get(position);


        ItemeCartHolder itemeCartHolder = (ItemeCartHolder) holder;

        double price = 0;
        double number = 0;

        if (songs.getTxtprice().equals("")||songs.getTxtprice() == null){

             price = 10;

        }else {

             price = Double.parseDouble(songs.getTxtprice());

        }
        number = Double.parseDouble(songs.getTxtnumberchoose());


        double totalpricebefortax =  (price * number);


        itemeCartHolder.txttitle.setText(songs.getTxttitle());
        itemeCartHolder.txtprice.setText(""+totalpricebefortax);
        itemeCartHolder.txtnumber.setText(songs.getTxtnumberchoose());
        itemeCartHolder.txtsize.setText(songs.getTxtsize());

        Glide.with(context)
                .load(songs.getImghome())
                .placeholder(R.drawable.holder_png)
                .into(itemeCartHolder.imgitemhome);

        holder.txtincrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                int price = Integer.parseInt(songs.getTxtprice());
//                int selectrd = num * price;
//
//                itemeCartHolder.txtprice.setText(""+selectrd);
//                totalPrice1 = Integer.parseInt(price_details[position].getPrice());

                num = Integer.parseInt(itemeCartHolder.txtnumber.getText().toString());
                num++;
                if (num < 30) {
                    itemeCartHolder.txtnumber.setText("" + num);
//                    itemeCartHolder.txtprice.setText("" + num * totalPrice1);
                    realm = Realm.getDefaultInstance();
                    adapter = new Realm_adapter(realm);
                    adapter.updaatenumberofboxes(songs.getId(), holder.txtnumber.getText().toString());

//                    totalPrices();
                    double priceincreace = 0;
                    if (songs.getTxtprice().equals("")||songs.getTxtprice() == null){

                        priceincreace = 10;

                    }else {

                        priceincreace = Double.parseDouble(songs.getTxtprice());

                    }
                    double number = Double.parseDouble(songs.getTxtnumberchoose());

                    double totalpricebefortax = priceincreace * number;

                    itemeCartHolder.txtprice.setText(""+totalpricebefortax);

                    MainActivity mainActivity = new MainActivity();
                    mainActivity.setcartcount();

                } else if (num > 30) {
                    num = 30;
                    itemeCartHolder.txtnumber.setText("" + num);
//                    itemeCartHolder.txtprice.setText("" + num * totalPrice1);
                    realm = Realm.getDefaultInstance();
                    adapter = new Realm_adapter(realm);
                    adapter.updaatenumberofboxes(songs.getId(), holder.txtnumber.getText().toString());
//                    totalPrices();

                    double priceincreace = 0;
                    if (songs.getTxtprice().equals("")||songs.getTxtprice() == null){

                        priceincreace = 10;

                    }else {

                        priceincreace = Double.parseDouble(songs.getTxtprice());

                    }
                    double number = Double.parseDouble(songs.getTxtnumberchoose());

                    double totalpricebefortax = priceincreace * number;

                    itemeCartHolder.txtprice.setText(""+totalpricebefortax);

                    MainActivity mainActivity = new MainActivity();
                    mainActivity.setcartcount();

                }

            }
        });

        holder.txtdecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                num = Integer.parseInt(itemeCartHolder.txtnumber.getText().toString());
                num--;
                if (num >= 1) {
                    itemeCartHolder.txtnumber.setText("" + num);
//                    txtprice.setText("" + num * totalPrice1);
                    realm = Realm.getDefaultInstance();
                    adapter = new Realm_adapter(realm);
                    adapter.updaatenumberofboxes(songs.getId(), holder.txtnumber.getText().toString());
//                    totalPrices();

                    double pricedecrease = 0;
                    if (songs.getTxtprice().equals("")||songs.getTxtprice() == null){

                        pricedecrease = 10;

                    }else {

                        pricedecrease = Double.parseDouble(songs.getTxtprice());

                    }
                    double number = Double.parseDouble(songs.getTxtnumberchoose());

                    double totalpricebefortax = pricedecrease * number;

                    itemeCartHolder.txtprice.setText(""+totalpricebefortax);
                    MainActivity mainActivity = new MainActivity();
                    mainActivity.setcartcount();

                } else if (num <= 0) {
                    num = 1;
                    itemeCartHolder.txtnumber.setText("" + num);
//                    txtprice.setText("" + num * totalPrice1);
                    realm = Realm.getDefaultInstance();
                    adapter = new Realm_adapter(realm);
                    adapter.updaatenumberofboxes(songs.getId(), holder.txtnumber.getText().toString());
//                    totalPrices();

                    double pricedecrease = 0;
                    if (songs.getTxtprice().equals("")||songs.getTxtprice() == null){

                        pricedecrease = 10;

                    }else {

                        pricedecrease = Double.parseDouble(songs.getTxtprice());

                    }
                    double number = Double.parseDouble(songs.getTxtnumberchoose());

                    double totalpricebefortax = pricedecrease * number;

                    itemeCartHolder.txtprice.setText(""+totalpricebefortax);
                    MainActivity mainActivity = new MainActivity();
                    mainActivity.setcartcount();
                }

            }
        });


        itemeCartHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBulder = new AlertDialog.Builder(context);

                alertDialogBulder.setTitle(R.string.delete_product);

                alertDialogBulder
                        .setMessage(R.string.delete_product_cart)
                        .setCancelable(false)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

//                                    String price = "";
//
//                                    Intent in = new Intent("delete_action");
//                                    in.putExtra("category", price);
//                                    context.sendBroadcast(in);


                                realm = Realm.getDefaultInstance();
                                adapter = new Realm_adapter(realm);
                                adapter.delete(position);
                                adapter.retrieve();
                                rcyHomelList.remove(position);
                                notifyItemRemoved(position);

                                FragmentTransaction fr = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                                fr.replace(R.id.fragment_container, new Cart_Fragment());
                                fr.addToBackStack(null);
                                fr.commit();

                                MainActivity mainActivity1 = new MainActivity();
                                mainActivity1.setcartcount();

                            }
                        }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

                //create it
                AlertDialog alertDialog = alertDialogBulder.create();
                // show it
                alertDialog.show();


            }
        });


//        Holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                dialog_home_item dialogHomeItem = new dialog_home_item();
//                dialogHomeItem.dialog(context, R.layout.item_home_dialog, 1.05);
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


    public static class ItemeCartHolder extends RecyclerView.ViewHolder {
        private final TextView txttitle, txtprice, txtincrease, txtdecrease, txtnumber,txtsize;
        private final ImageView imgitemhome, imgDelete;


        public ItemeCartHolder(@NonNull View itemView) {
            super(itemView);
            txttitle = itemView.findViewById(R.id.txtTitle);
            txtprice = itemView.findViewById(R.id.txtPrice);
            imgitemhome = itemView.findViewById(R.id.imgCart);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            txtincrease = itemView.findViewById(R.id.txtIncrease);
            txtdecrease = itemView.findViewById(R.id.txtDecrease);
            txtnumber = itemView.findViewById(R.id.txtQuntity);
            txtsize = itemView.findViewById(R.id.txtSize);


        }
    }
//    public void totalPrices(){
//
//        realm = Realm.getDefaultInstance();
//        adapter = new Realm_adapter(realm);
//        cartModels = adapter.retrieve();
//        int toatalprice = 0;
//        for (int x=0; x<adapter.retrieve().size();x++){
//
//            int price = Integer.parseInt(cartModels.get(x).getTxtprice());
//            int number = Integer.parseInt(cartModels.get(x).getTxtnumberchoose());
//
//            int totalpricebefortax = price * number;
//
//            toatalprice += totalpricebefortax ;
//
//        }
//        int taxPercentage = Integer.parseInt(saved_data.get_Tax_Percentage(context));
//        double taxpercent = (double) taxPercentage/100;
//        double tax = toatalprice * taxpercent;
//
//
//        int discountPercentage = Integer.parseInt(saved_data.get_Discount_Percentage(context));
//        double discountpercent = (double) discountPercentage/100;
//        double discount = toatalprice * discountpercent;
//
//
//        int delivary = Integer.parseInt(saved_data.get_Delevary(context));
//
//        double totalpriceaftertaxes = toatalprice + tax - discount + delivary;
//
//    }
}