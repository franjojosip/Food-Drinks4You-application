package hr.ferit.franjojosipjukic.fooddrinks4you;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth firebaseAuth;
    private RelativeLayout relativeFirst;
    private RelativeLayout relativeSecond;
    private RelativeLayout relativeThird;
    private RelativeLayout relativeFourth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initViews();
        setListeners();
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
    private void initViews() {
        relativeFirst = findViewById(R.id.relativeFruits);
        relativeSecond = findViewById(R.id.relativeVegetables);
        relativeThird = findViewById(R.id.relativeSnacks);
        relativeFourth = findViewById(R.id.relativeDrinks);
    }

    private void setListeners() {
        relativeFirst.setOnClickListener(this);
        relativeSecond.setOnClickListener(this);
        relativeThird.setOnClickListener(this);
        relativeFourth.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int position = 0;

        if(view.getId() == relativeSecond.getId()) position= 1;
        else if(view.getId() == relativeThird.getId()) position= 2;
        else if(view.getId() == relativeFourth.getId()) position= 3;

        Intent listMenu = new Intent(this,ListMenuActivity.class);
        listMenu.putExtra("Position", position);
        startActivity(listMenu);
    }

    public void goBack(View view) {
        finish();
    }
}
