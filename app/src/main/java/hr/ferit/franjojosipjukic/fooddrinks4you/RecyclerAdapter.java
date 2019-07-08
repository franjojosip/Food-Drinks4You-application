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
import java.util.Random;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Product> productList;
    private Context mContext;
    private onClickItemListener listener;

    public RecyclerAdapter(List<Product> products, Context mContext, onClickItemListener listener) {
        this.productList = products;
        this.mContext = mContext;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder nameViewHolder, int position) {
        nameViewHolder.setName(productList.get(position).getName());
        nameViewHolder.setPrice(productList.get(position).getPrice());
        Glide.with(mContext)
                .asBitmap()
                .load(productList.get(position).getImage())
                .into(nameViewHolder.getImage());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void addData(List<Product> body) {
        this.productList = body;
        notifyDataSetChanged();
    }
}