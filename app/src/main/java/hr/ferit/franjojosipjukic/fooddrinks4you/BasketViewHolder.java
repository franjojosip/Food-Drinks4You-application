package hr.ferit.franjojosipjukic.fooddrinks4you;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.target.Target;


public class BasketViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private Context mContext;
    private TextView name;
    private ImageView image;
    private TextView price;
    private deleteClickListener listener;
    private ImageView ivX;

    public BasketViewHolder(@NonNull View itemView, deleteClickListener listener) {
        super(itemView);
        this.name = itemView.findViewById(R.id.tvName);
        this.image = itemView.findViewById(R.id.ivPicture);
        this.price = itemView.findViewById(R.id.tvPrice);
        this.ivX = itemView.findViewById(R.id.ivCheck);
        this.listener = listener;
        this.ivX.setOnClickListener(this);
    }


    public void setName(String name) {
        this.name.setText(name);
    }

    public void setImage(int image) {
        this.image.setImageResource(image);
    }

    public void setPrice(double price) {
        this.price.setText("Price: $"+Double.toString(price)+" USD");
    }


    @Override
    public void onClick(View v) {
        listener.onXClick(getAdapterPosition());
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public ImageView getImage() {
        return image;
    }
}