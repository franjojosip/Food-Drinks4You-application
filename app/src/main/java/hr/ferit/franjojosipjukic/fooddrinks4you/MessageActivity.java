package hr.ferit.franjojosipjukic.fooddrinks4you;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }
    public void onBackPressed() {}

    public void goBackToMenu(View view) {
        Intent backMenu = new Intent(this, MenuActivity.class);
        startActivity(backMenu);

    }
}
