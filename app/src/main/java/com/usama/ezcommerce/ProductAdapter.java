package com.usama.ezcommerce;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ArrayList<ProductData> productdata;
    private Context context;

    public ProductAdapter(Context context, ArrayList<ProductData> productdata) {
        this.context = context;
        this.productdata = productdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.activity_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final ProductData myproductData = productdata.get(position);
        holder.getImageView().setImageResource(myproductData.getImgId());
        holder.getProductName().setText(myproductData.getProductname());
        holder.getPrice().setText(myproductData.getPrice());
        holder.getRatingbar().setRating(myproductData.getStars());

        holder.getCradView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int index = holder.getAdapterPosition();
                ProductData Data = productdata.get(index);
                String item = Data.getProductname();;
                int imgID = Data.getImgId();
                float rate = Data.getStars();
                String price = Data.getPrice();
              //  Log.d("this", item + " " + String.valueOf(rate) + " " + price);

                Intent intent = new Intent(context, Page2.class);
                intent.putExtra("UniqueImage", imgID);
                intent.putExtra("UniqueItem", item);
                intent.putExtra("UniqueStars", rate);
                intent.putExtra("UniquePrice", price);

                ////// Starting next activity ///////
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productdata.size();
    }

    public void filteredList(ArrayList<ProductData> filteredList){
        productdata = filteredList;
        notifyDataSetChanged();;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView productNameView, priceView;
        public RatingBar ratingbarView;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView3);
            this.productNameView = (TextView) itemView.findViewById(R.id.productName);
            this.priceView = (TextView) itemView.findViewById(R.id.price);
            this.ratingbarView = (RatingBar) itemView.findViewById(R.id.ratingBar);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getProductName() {
            return productNameView;
        }

        public TextView getPrice() {
            return priceView;
        }

        public RatingBar getRatingbar() {
            return ratingbarView;
        }

        public CardView getCradView() {
            return cardView;
        }

    }
}
