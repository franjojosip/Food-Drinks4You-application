package hr.ferit.franjojosipjukic.fooddrinks4you;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView name;
    private ImageView image;
    private TextView price;
    private onClickItemListener listener;
    private LinearLayout list_layout;

    public ViewHolder(@NonNull View itemView, onClickItemListener listener) {
        super(itemView);
        this.list_layout = itemView.findViewById(R.id.list_layout);
        this.name = itemView.findViewById(R.id.tvName);
        this.image = itemView.findViewById(R.id.ivPicture);
        this.price = itemView.findViewById(R.id.tvPrice);
        this.listener = listener;
        this.list_layout.setOnClickListener(this);
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
        listener.onClickPosition(getAdapterPosition());
    }


    public LinearLayout getLayout() {
        return list_layout;
    }

    public ImageView getImage() {
        return image;
    }
}