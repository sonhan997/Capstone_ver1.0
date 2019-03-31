package com.example.nguye.capston1_dtu.Controller;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguye.capston1_dtu.Model.Question;
import com.example.nguye.capston1_dtu.R;

import java.util.ArrayList;

public class TestDoneActivity extends AppCompatActivity {

    ArrayList<Question> list = new ArrayList<>();
    public static final String NAME = "tam";
    String title;
    int numNoAns;
    int numAnsTrue;
    int numAnsFalse;
    int totalNum = 0;

    Button btAgain,btEx;
    TextView tvTrue,tvFalse,tvNO,tvSum,tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_done);

        final Intent intent = getIntent();
        list = (ArrayList<Question>) intent.getExtras().getSerializable("listQuestion"); // đường dẫn đổ data vào như bên web
        //title = (String) intent.getExtras().getSerializable(NAME);
      //  tvResult.setText(title);
        AnhXa();
        checkKQ();
        totalNum = numAnsTrue*10;
        tvTrue.setText(""+numAnsTrue);
        tvFalse.setText(""+numAnsFalse);
        tvNO.setText(""+numNoAns);
        tvSum.setText(""+totalNum);
        btAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /* if (title.equals(tvResult)){
                   Intent intent1 = new Intent(TestDoneActivity.this,TesT_List.class);
                   startActivity(intent1);
               }*/
                Toast.makeText(getApplicationContext(),"fixing", Toast.LENGTH_LONG).show();
            }
        });
        btEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentA = new Intent(TestDoneActivity.this,MainActivity_Chinh.class);
                startActivity(intentA);
            }
        });
    }

    /**
     * Goi ID tu Layout*/
    private void AnhXa() {
        tvTrue = findViewById(R.id.tvTrue);
        tvFalse = findViewById(R.id.tvFalse);
        tvNO = findViewById(R.id.tvChuaTL);
        tvSum = findViewById(R.id.tvSUM);
        tvResult = findViewById(R.id.tvResult);
        btAgain = findViewById(R.id.btAgain);
        btEx = findViewById(R.id.btExit);
    }

    /**
     * Hàm check kết quả*/
    public void checkKQ(){
        for (int i =0;i<list.size();i++){
            if (list.get(i).getTamAns().equals("") == true){
                numNoAns++;
            }else if (list.get(i).getDapAn().equals(list.get(i).getTamAns())){
                numAnsTrue++;
            }else numAnsFalse++;
        }
    }

}
