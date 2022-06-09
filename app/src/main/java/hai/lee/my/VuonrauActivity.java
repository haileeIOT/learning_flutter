package hai.lee.my;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VuonrauActivity extends AppCompatActivity {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch sw1, sw2, SWmai_che;
    TextView getTemp, getHum, Chi_Phi, text_LB_PHCUHM, text_LB_PHCVS, text_LB_URE, text_LB_lan, text_LB_kali, text_LB_NPK, text_BL_PHCUHM, text_BL_PHCVS, text_BL_URE, text_BL_lan, text_BL_kali, text_BL_NPK, text_BT_PHCUHM, text_BT_PHCVS, text_BT_URE, text_BT_lan, text_BT_kali, text_BT_NPK;
    ImageView imageView1, imageView2, imageView3;
    EditText dien_tich;
    Button gt;
    private Spinner spnCategory;
    private CategoryAdapter categoryAdapter;
    private static final int NOTIFICATION_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhxa();
        DatabaseReference mdata;
        mdata = FirebaseDatabase.getInstance().getReference();
        readhum();
        readtemp();

        checkMotor();
        categoryAdapter = new CategoryAdapter(this, R.layout.item_slao, getListCategory());
        spnCategory.setAdapter(categoryAdapter);
        set_table();


        sw1.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                mdata.child("VuonRau/MOTOR").setValue(1);
                imageView1.setImageResource(R.drawable.won);
                Toast.makeText(VuonrauActivity.this, "đã bật motor", Toast.LENGTH_SHORT).show();
            } else {
                mdata.child("VuonRau/MOTOR").setValue(0);
                imageView1.setImageResource(R.drawable.motor);
                Toast.makeText(VuonrauActivity.this, "đã tắt motor", Toast.LENGTH_SHORT).show();

            }
        });
        SWmai_che.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                mdata.child("VuonRau/DEN SUOI").setValue(1);
                imageView3.setImageResource(R.drawable.ledon);
                Toast.makeText(VuonrauActivity.this, "đã bật đèn sưởi", Toast.LENGTH_SHORT).show();
            } else {
                mdata.child("VuonRau/DEN SUOI").setValue(0);
                imageView3.setImageResource(R.drawable.ledoff);
                Toast.makeText(VuonrauActivity.this, "đã tắt đèn sưởi", Toast.LENGTH_SHORT).show();
            }
        });
        sw2.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                mdata.child("VuonRau/QUAT").setValue(1);
                imageView2.setImageResource(R.drawable.fanon);
                Toast.makeText(VuonrauActivity.this, "đã bật quạt", Toast.LENGTH_SHORT).show();
            } else {
                mdata.child("VuonRau/QUAT").setValue(0);
                imageView2.setImageResource(R.drawable.offfan);
                Toast.makeText(VuonrauActivity.this, "đã tắt quạt", Toast.LENGTH_SHORT).show();
            }
        });
        gt.setOnClickListener(view -> {
            Intent intent = new Intent(VuonrauActivity.this, introActivity.class);
            startActivity(intent);
        });
    }

    //giá trị bảng
    private void set_table() {
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            float LB_uhm, LB_VS, LB_ure, LB_Kali, LB_npk, LB_Lan, BL_uhm, BL_kali, BL_npk, BL_lan, BT_VS, BT_ure, BT_kali, BT_npk, BT_lan;
            String S;

            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(VuonrauActivity.this, categoryAdapter.getItem(i).getName(), Toast.LENGTH_SHORT).show();
                if (categoryAdapter.getItem(i).getName().equals("rau dền")) {
                    S = dien_tich.getText().toString();
                    float A = Float.parseFloat(S);
                    LB_uhm = A * 200;
                    LB_VS = A * 18;
                    LB_ure = A * 3;
                    LB_Kali = A * 3;
                    LB_npk = A * 23;
                    LB_Lan = A * 9;

                    BL_uhm = A * 100;
                    BL_lan = A * 70;
                    BL_kali = A * 30;
                    BL_npk = A * 30;

                    BT_VS = A * 50;
                    BT_ure = A * 50;
                    BT_lan = A * 30;
                    BT_kali = A * 30;
                    BT_npk = A * 30;

                    text_LB_PHCUHM.setText(LB_uhm + "");
                    text_LB_PHCVS.setText(LB_VS + " ");
                    text_LB_URE.setText(LB_ure + "");
                    text_LB_lan.setText(LB_Lan + "");
                    text_LB_kali.setText(LB_Kali + "");
                    text_LB_NPK.setText(LB_npk + "");

                    text_BL_PHCUHM.setText(BL_uhm + "");
                    text_BL_lan.setText(BL_lan + "");
                    text_BL_kali.setText(BL_kali + "");
                    text_BL_NPK.setText(BL_npk + "");

                    text_BT_PHCVS.setText(BT_VS + "/lần");
                    text_BT_URE.setText(BT_ure + "/lần");
                    text_BT_lan.setText(BT_lan + "/lần 1");
                    text_BT_kali.setText(BT_kali + "/lần");
                    text_BT_NPK.setText(BT_npk + "/lần 2");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void readtemp() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("VuonRau/Temperature");//Temperature
        ref.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Float value = dataSnapshot.getValue(Float.class);
                getTemp.setText(value + " C");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void readhum() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Humidity");//Temperature
        ref.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Float H = dataSnapshot.getValue(Float.class);
                getHum.setText(H + " %");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
//    private void readhum() {
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference ref = database.getReference("VuonRau/Humidity");//Humidity
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                getHum.setText(value + "%");
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//            }
//        });
//    }

    private List<Category> getListCategory() {
        List<Category> list = new ArrayList<>();
        list.add(new Category("xà lách"));
        list.add(new Category("cải thìa"));
        list.add(new Category("rau dền"));
        list.add(new Category("súp lơ"));
        return list;
    }

    protected void anhxa() {
        //ánh xạ table
        text_LB_PHCUHM = findViewById(R.id.LB_PHCHM);
        text_LB_PHCVS = findViewById(R.id.LB_PHCVS);
        text_LB_lan = findViewById(R.id.LB_lan);
        text_LB_kali = findViewById(R.id.LB_kali);
        text_LB_URE = findViewById(R.id.LB_URE);
        text_LB_NPK = findViewById(R.id.LB_npk);

        text_BL_PHCUHM = findViewById(R.id.BL_PHCHM);
        text_BL_PHCVS = findViewById(R.id.BL_PHCVS);
        text_BL_lan = findViewById(R.id.BL_lan);
        text_BL_kali = findViewById(R.id.BL_kali);
        text_BL_URE = findViewById(R.id.BL_URE);
        text_BL_NPK = findViewById(R.id.BL_npk);

        text_BT_PHCUHM = findViewById(R.id.BT_PHCHM);
        text_BT_PHCVS = findViewById(R.id.BT_PHCVS);
        text_BT_lan = findViewById(R.id.BT_lan);
        text_BT_kali = findViewById(R.id.BT_kali);
        text_BT_URE = findViewById(R.id.BT_URE);
        text_BT_NPK = findViewById(R.id.BT_npk);

        SWmai_che = findViewById(R.id.swM);
        dien_tich = findViewById(R.id.dien_tich);
        getTemp = findViewById(R.id.temp);
        getHum = findViewById(R.id.hum);
        imageView1 = findViewById(R.id.img1);
        imageView2 = findViewById(R.id.img2);
        imageView3 = findViewById(R.id.mai_che);
        sw1 = findViewById(R.id.swD);
        sw2 = findViewById(R.id.swQ);
        gt = findViewById(R.id.gt);
        spnCategory = findViewById(R.id.spn_category);
        Chi_Phi = findViewById(R.id.chi_phi);
    }

    private void checkMotor() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("VuonRau/MOTOR");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Integer value = dataSnapshot.getValue(Integer.class);
                assert value != null;
                if(value.equals(0)){
                    NotificationOff();
                }
                if(value.equals(1)){
                    NotificationOn();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void NotificationOn() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setSmallIcon(R.drawable.ledon)
                .setContentTitle("MOTOR")
                .setContentText("đã bật")
                .setLargeIcon(bitmap)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(NOTIFICATION_ID, notification);
        }
    }

    private void NotificationOff() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setSmallIcon(R.drawable.ledon)
                .setContentTitle("MOTOR")
                .setContentText("đã tắt")
                .setLargeIcon(bitmap)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(NOTIFICATION_ID, notification);
        }
    }

}
