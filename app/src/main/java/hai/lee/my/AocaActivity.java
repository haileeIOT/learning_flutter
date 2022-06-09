package hai.lee.my;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AocaActivity extends AppCompatActivity {

    private Switch swMotor;
    private Switch swDen;
    private ImageView imageView1;
    private ImageView imageView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aoca);
        anhxa();
        DatabaseReference mdata;
        mdata = FirebaseDatabase.getInstance().getReference();
        swDen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {

                    mdata.child("AoCa/Den").setValue(1);
                    imageView1.setImageResource(R.drawable.won);
                    Toast.makeText(AocaActivity.this, "đã bật đèn", Toast.LENGTH_SHORT).show();
                } else {
                    mdata.child("AoCa/Den").setValue(0);
                    imageView1.setImageResource(R.drawable.motor);
                    Toast.makeText(AocaActivity.this, "đã tắt đèn", Toast.LENGTH_SHORT).show();
                }
            }
        });

        swMotor.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                mdata.child("AoCa/motor").setValue(1);
                imageView1.setImageResource(R.drawable.won);
                Toast.makeText(AocaActivity.this, "đã bật motor", Toast.LENGTH_SHORT).show();
            } else {
                mdata.child("AoCa/motor").setValue(0);
                imageView1.setImageResource(R.drawable.motor);
                Toast.makeText(AocaActivity.this, "đã tắt motor", Toast.LENGTH_SHORT).show();
            }
        });

        swMotor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mdata.child("AoCa/Motor").setValue(1);
                    imageView2.setImageResource(R.drawable.fanon);
                    Toast.makeText(AocaActivity.this, "đã bật motor oxi", Toast.LENGTH_SHORT).show();
                } else {
                    mdata.child("AoCa/Motor").setValue(0);
                    imageView2.setImageResource(R.drawable.offfan);
                    Toast.makeText(AocaActivity.this, "đã tắt motor oxi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void anhxa() {
        swDen = findViewById(R.id.swDenAo);
        swMotor = findViewById(R.id.swmotorAo);
        imageView1 = findViewById(R.id.den);
    }
}
