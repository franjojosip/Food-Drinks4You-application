package hr.ferit.franjojosipjukic.fooddrinks4you;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends AppCompatActivity implements deleteClickListener,AnimatorUpdateListener {


    private Handler handler = new Handler();
    private Dialog myDialog;
    private ProgressDialog dialog;
    private RecyclerView recycler;
    private BasketRecyclerAdapter adapter;
    private static List<Product> productList = new ArrayList<>();
    private static double totalPrice=0;
    private TextView tvPrice;
    private TextView tvDialogPercent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        initViews();
        setDefaultPrice();
        setTotalPrice();
        setUpRecycler();
    }

    private void setDefaultPrice() {
        String number = String.format ("%.2f", totalPrice);
        tvPrice.setText("Total price: $" + number +"USD");
    }

    private void initViews() {
        this.tvPrice = findViewById(R.id.tvTotalPrice);
    }


    private void setUpRecycler() {
        recycler = findViewById(R.id.recyclerCart);
        adapter = new BasketRecyclerAdapter(productList,this,this);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    public void buyItems(View view) {
        if(productList.isEmpty()){
            Toast.makeText(this,"Sorry your basket is empty.",Toast.LENGTH_SHORT).show();
        }
        else {
            createDialog();

        }
    }

    private void createDialog() {
        myDialog= new Dialog(this);
        myDialog.setContentView(R.layout.dialog_order);
        tvDialogPercent = myDialog.findViewById(R.id.tvProgressBar);
        ProgressBar progressBar = myDialog.findViewById(R.id.pbOrder);
        myDialog.setCancelable(false);
        myDialog.show();
        animators(progressBar);
    }

    public void toMessageActivity() {
        productList.clear();
        Intent buy = new Intent(this, MessageActivity.class);
        startActivity(buy);
    }

    public void setTotalPrice() {
        totalPrice=0;
        for(int i=0;i<productList.size();i++){
         totalPrice+=productList.get(i).getPrice();
        }
        String number = String.format ("%.2f", totalPrice);
        tvPrice.setText(new StringBuilder().append("Total price: $").append(number).append("USD").toString());
    }

    public static void addItem(Product product) {
        productList.add(product);
        totalPrice+=product.getPrice();
    }

    @Override
    public void onXClick(int position) {
        dialog = ProgressDialog.show(BasketActivity.this, "","Deleting. Please wait ...", true);
        productList.remove(position);
        adapter.notifyItemRemoved(position);
        setTotalPrice();
        handler.postDelayed(new Runnable() {
            public void run() {
                dialog.dismiss();
            }
        }, 300);

    }

    public void goBack(View view) {
        finish();
    }


    public void toMenuPage(View view) {
        Intent menu = new Intent(this,MenuActivity.class);
        startActivity(menu);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int animProgress = (Integer) animation.getAnimatedValue();
        if ( animProgress == 101)
        {
            toMessageActivity();
        }
        else{
            tvDialogPercent.setText( String.valueOf(animation.getAnimatedValue())+"%");
        }
    }

    private void animators(ProgressBar progressBar) {
        ValueAnimator mValueAnimator = ValueAnimator.ofInt(0,101);
        mValueAnimator.setDuration(5000);
        mValueAnimator.addUpdateListener(this);

        ObjectAnimator mObjectAnimator = ObjectAnimator.ofInt(progressBar, getString(R.string.progress), 0, 101);
        mObjectAnimator.setDuration(5000);
        mObjectAnimator.setInterpolator(new DecelerateInterpolator());

        mValueAnimator.start();
        mObjectAnimator.start();
    }


}
