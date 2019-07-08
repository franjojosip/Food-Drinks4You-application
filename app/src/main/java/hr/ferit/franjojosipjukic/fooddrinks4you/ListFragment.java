package hr.ferit.franjojosipjukic.fooddrinks4you;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Math.round;
import static java.lang.String.format;


@SuppressLint("ValidFragment")
public class ListFragment extends Fragment implements onClickItemListener {

    private DatabaseReference reference;
    private FirebaseDatabase database;
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private List<Product> productList = new ArrayList<>();
    private Button btnAdd;
    private Dialog myDialog;
    private RelativeLayout close;
    private TextView tvName;
    private TextView tvPrice;
    private ImageView ivPicture;
    private int itemPosition;
    private String name;
    static boolean calledAlready = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        recyclerView = view.findViewById(R.id.listRecycler);
        recyclerAdapter = new RecyclerAdapter(productList, getContext(),this);
        setProducts();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerAdapter);
        if (!calledAlready)
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }

        database = FirebaseDatabase.getInstance();
        reference = database.getReference(name);
        getData();
        return view;
    }

    public ListFragment(String name) {
        this.name = name;
    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                addNewData(dataSnapshot);
                setProducts();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),getString(R.string.cant_access),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addNewData(DataSnapshot dataSnapshot) {
        productList.clear();
        recyclerAdapter.notifyDataSetChanged();
        for(DataSnapshot data : dataSnapshot.getChildren()){
            Product product = data.getValue(Product.class);
            productList.add(product);
        }
        setProducts();
    }



    public void createDialog(){
        myDialog = new Dialog(getActivity());
        myDialog.setContentView(R.layout.dialog_item);
        initViews();
        setContext();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             myDialog.cancel();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasketActivity.addItem(productList.get(itemPosition));
                Toast.makeText(getContext(),productList.get(itemPosition).getName() + " added to basket.",Toast.LENGTH_SHORT).show();
            }
        });
        myDialog.show();
    }

    private void setContext() {
        tvName.setText(productList.get(itemPosition).getName());
        tvPrice.setText("Price: $"+Double.toString(productList.get(itemPosition).getPrice())+" USD");
        Glide.with(this)
                .asBitmap()
                .load(productList.get(itemPosition).getImage())
                .into(ivPicture);
    }

    private void initViews() {
        close = myDialog.findViewById(R.id.dialogRelative);
        btnAdd = myDialog.findViewById(R.id.btnDialogAdd);
        tvName = myDialog.findViewById(R.id.tvDialogName);
        tvPrice = myDialog.findViewById(R.id.tvDialogPrice);
        ivPicture = myDialog.findViewById(R.id.ivDialogPicture);
    }

    private void setProducts() {
        recyclerAdapter.addData(productList);
    }


    @Override
    public void onClickPosition(int position) {
        this.itemPosition = position;
        createDialog();
    }
}
