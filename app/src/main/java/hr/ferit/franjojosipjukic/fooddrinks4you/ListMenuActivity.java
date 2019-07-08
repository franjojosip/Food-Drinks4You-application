package hr.ferit.franjojosipjukic.fooddrinks4you;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class ListMenuActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private PageAdapter pageAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<String> names;
    private int[] tabIcons= {
            R.drawable.fruit,
            R.drawable.vegetables,
            R.drawable.sweet,
            R.drawable.alcoholic_drink
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        addNamesOnList();
        initViews();
        setUpPager();
        setUpTabIcons();
    }



    private void setUpTabIcons() {
        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
        mTabLayout.getTabAt(2).setIcon(tabIcons[2]);
        mTabLayout.getTabAt(3).setIcon(tabIcons[3]);
        LinearLayout tabContainer =(LinearLayout) mTabLayout.getChildAt(0);
        LinearLayout child1 =(LinearLayout) tabContainer.getChildAt(0);
        LinearLayout child2 =(LinearLayout) tabContainer.getChildAt(1);
        LinearLayout child3 =(LinearLayout) tabContainer.getChildAt(2);
        LinearLayout child4 =(LinearLayout) tabContainer.getChildAt(3);

        child1.setBackgroundColor(ContextCompat.getColor(this,R.color.menuFirst));
        child2.setBackgroundColor(ContextCompat.getColor(this,R.color.menuSecond));
        child3.setBackgroundColor(ContextCompat.getColor(this,R.color.tabSnacksColor));
        child4.setBackgroundColor(ContextCompat.getColor(this,R.color.menuFourth));

    }

    private void addNamesOnList() {
        names = new ArrayList<>();
        names.add("Fruits");
        names.add("Vegetables");
        names.add("Snacks");
        names.add("Drinks");
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPager);
        mTabLayout = findViewById(R.id.tabMenu);

    }
    private void setUpPager() {
        pageAdapter = new PageAdapter(getSupportFragmentManager(),names);
        setFragments();
        mViewPager.setAdapter(pageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(getPosition());

    }


    private int getPosition() {
        Intent menuIntent = getIntent();
        return menuIntent.getIntExtra("Position",0);
    }

    private void setFragments() {
        for(int i=0;i<4;i++){
            pageAdapter.addFragment(new ListFragment(names.get(i)));
        }
    }


    public void toMenuPage(View view) {
        Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
        startActivity(intent);
    }

    public void toShoppingBasket(View view) {
        Intent intent = new Intent(getApplicationContext(),BasketActivity.class);
        startActivity(intent);
    }


    public void toLogOut(View view) {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this,LoginActivity.class));
    }

    public void toMainActivity(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

}
