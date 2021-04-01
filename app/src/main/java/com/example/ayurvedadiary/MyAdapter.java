package com.example.ayurvedadiary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

//It connects adapter class with Recycler view of upload activity
public class MyAdapter extends RecyclerView.Adapter<FoodViewHolder>{

    private Context mContext;
    private List<ProductData> myProductList;
    private int lastPosition = -1;

    public MyAdapter(Context mContext, List<ProductData> myProductList) {
        this.mContext = mContext;
        this.myProductList = myProductList;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row_item, viewGroup, false);
        return new FoodViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder foodViewHolder, int i) {

        //This is used for retrieval of the image when clicked on upload image
        Glide.with(mContext)
                .load(myProductList.get(i).getItemImage())
                .into(foodViewHolder.imageView);

        //This is used for retrieval of the description, estimated price and title of the product
        //foodViewHolder.imageView.setImageResource(myProductList.get(i).getItemImage());
        foodViewHolder.mTitle.setText(myProductList.get(i).getItemName());
        foodViewHolder.mDescription.setText(myProductList.get(i).getItemDescription());
        foodViewHolder.mPrice.setText(myProductList.get(i).getItemPrice());

        //Once clicked on the product image stored in the upload activity, it will send you to the description page
        foodViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext,DetailActivity.class);
                intent.putExtra("Image",myProductList.get(foodViewHolder.getAdapterPosition()).getItemImage());
                intent.putExtra("Description",myProductList.get(foodViewHolder.getAdapterPosition()).getItemDescription());
                intent.putExtra("Title",myProductList.get(foodViewHolder.getAdapterPosition()).getItemName());
                intent.putExtra("keyValue",myProductList.get(foodViewHolder.getAdapterPosition()).getKey());
                mContext.startActivity(intent);

            }
        });

        //Here I have added the popup animation i.e. here I have just called the function of set Animation
        setAnimation(foodViewHolder.itemView,i);
    }

    //Pop up animation function
    public void setAnimation(View viewToAnimate, int position) {
        if(position >lastPosition){
            ScaleAnimation animation = new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(1500);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return myProductList.size();
    }

    public void filteredList(ArrayList<ProductData> filterList) {

        myProductList = filterList;
        notifyDataSetChanged();
    }
}
//Connects foodviewholder to the recycler view
class FoodViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mTitle, mDescription, mPrice;
    CardView mCardView;

    public FoodViewHolder( View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mDescription = itemView.findViewById(R.id.tvDescription);
        mPrice = itemView.findViewById(R.id.tvPrice);
        mCardView = itemView.findViewById(R.id.myCardView);

    }
}