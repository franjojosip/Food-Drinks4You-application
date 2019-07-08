package hr.ferit.franjojosipjukic.fooddrinks4you;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BasketRecyclerAdapter extends RecyclerView.Adapter<BasketViewHolder> {

    private List<Product> productList;
    private Context mContext;
    private deleteClickListener listener;

    public BasketRecyclerAdapter(List<Product> products, Context mContext, deleteClickListener listener) {
        this.productList = products;
        this.mContext = mContext;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BasketViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.basket_item, viewGroup, false);
        return new BasketViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull BasketViewHolder basketViewHolder, int i) {
        basketViewHolder.setmContext(mContext);
        basketViewHolder.setName(productList.get(i).getName());
        basketViewHolder.setPrice(productList.get(i).getPrice());
        Glide.with(mContext)
                .asBitmap()
                .load(productList.get(i).getImage())
                .into(basketViewHolder.getImage());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}