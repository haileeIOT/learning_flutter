package hai.lee.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity {
    Button BT_nhakinh,BT_Ao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);
        anhxa();
        BT_nhakinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this, VuonrauActivity.class);
                startActivity(intent);
            }
        });
        BT_Ao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this,AocaActivity.class );
                startActivity(intent);
            }
        });
    }

   protected void anhxa(){
        BT_nhakinh = findViewById(R.id.bt_nhakinh);
        BT_Ao = findViewById(R.id.bt_ao);
    }
}
