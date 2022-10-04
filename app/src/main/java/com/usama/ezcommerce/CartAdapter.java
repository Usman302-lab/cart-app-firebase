package com.usama.ezcommerce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    public ArrayList<CartData> cartdata;
    private Context context;

    public CartAdapter(Context context, ArrayList<CartData> cartdata) {
        this.context = context;
        this.cartdata = cartdata;
    }

    public int convertString(String str){
        char[] ch = str.toCharArray();
        int len = ch.length;

        char[] arr = new char[len-1];
        for(int i=0; i<(len-1); i++){
           arr[i] = ch[i+1];
        }

        //Log.d("this", String.valueOf(arr));
        int price = Integer.parseInt(String.valueOf(arr));
        return price;
    }

    public int getTotal(){
        int total = 0, price;
        String str;
        for(int i=0 ; i<MainActivity.cart.size() ; i++){
            str = MainActivity.cart.get(i).getPrice();
            price = convertString(str);
            total = total + (price * MainActivity.cart.get(i).getQuantity());
        }
        return total;
    }


    //// generating layout for every single item and initializing its views ///////
   @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.cart_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    //// putting values into that views (binding data with its view) //////
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CartData mycartData = cartdata.get(position);
        holder.getImageView().setImageResource(mycartData.getImgId());
        holder.getProductNameView().setText(mycartData.getProductname());
        holder.getPriceView().setText(mycartData.getPrice());
        holder.getQuantityView().setText(String.valueOf((mycartData.getQuantity())));
    }

    @Override
    public int getItemCount() {
        return cartdata.size();
    }


    ////// class which hold all views of single cart item ////////
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView productNameView, priceView, quantityView;
        Button addButtonView, subButtonView, deleteView;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView3);
            this.productNameView = (TextView) itemView.findViewById(R.id.textView5);
            this.priceView = (TextView) itemView.findViewById(R.id.textView6);
            this.quantityView = (TextView) itemView.findViewById(R.id.textView7);
            this.addButtonView = (Button) itemView.findViewById(R.id.button3);
            this.subButtonView = (Button) itemView.findViewById(R.id.button4);
            this.deleteView = (Button) itemView.findViewById(R.id.button2);
            cardView = (CardView) itemView.findViewById(R.id.cardView);


            //// defining action-listeners on buttons ////
            addButtonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String srequantity = quantityView.getText().toString();
                    int quantity = Integer.parseInt(srequantity);
                    quantity++;
                    srequantity = String.valueOf(quantity);
                    quantityView.setText(srequantity);

                    int index = getAdapterPosition();           //// item index for which '+' button is clicked ////
                    MainActivity.cart.get(index).setQuantity(quantity);   /// add to updated value in arrayList also  ////


                    //// update total price ////
                    int total_amount = getTotal();
                    Page3.textView.setText(String.valueOf(total_amount));
                }
            });


            ///// clicking on '-' button to decrease quantity /////
            subButtonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String srequantity = quantityView.getText().toString();
                    int quantity = Integer.parseInt(srequantity);
                    if(quantity > 1) {
                        quantity--;
                        srequantity = String.valueOf(quantity);
                        quantityView.setText(srequantity);

                        int index = getAdapterPosition();           //// item index for which '-' button is clicked ////
                        MainActivity.cart.get(index).setQuantity(quantity);   /// add to updated value in arrayList also  ////


                        //// update total price ////
                        int total_amount = getTotal();
                        Page3.textView.setText(String.valueOf(total_amount));
                    }
                }
            });


            //// clicking on 'Delete' button to delete item ////
            deleteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();           //// item index for which button is clicked ////
                    MainActivity.cart.remove(index);                   //// only removes item from arraylist  ///
                    notifyItemRemoved(index);                          //// also update recyclerView ////
                    notifyItemRangeChanged(index, MainActivity.cart.size());


                    //// update total price ////
                    int total_amount = getTotal();
                    Page3.textView.setText(String.valueOf(total_amount));
                }
            });
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getProductNameView() {
            return productNameView;
        }

        public TextView getPriceView() {
            return priceView;
        }

        public TextView getQuantityView() {
            return quantityView;
        }
    }
}
