package com.example.anusara.bcv2.Guest.GQuestion;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anusara.bcv2.Member.MembermainActivity;
import com.example.anusara.bcv2.Member.Questionnaire.MQuestionActivity;
import com.example.anusara.bcv2.R;

public class GAnsQuesActivity extends AppCompatActivity {

    int sum1,sum2,sum3,sum4,sum5,sum6;
    TextView textShow, txtResult;
    Button btn1,btn2;
    ImageView Imageshow;


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gans_ques);
        sum1 = getIntent().getIntExtra("sum1", 0);
        sum2 = getIntent().getIntExtra("sum2", 0);
        sum3 = getIntent().getIntExtra("sum3", 0);
        sum4 = getIntent().getIntExtra("sum4", 0);
        sum5 = getIntent().getIntExtra("sum5", 0);
        sum6 = getIntent().getIntExtra("sum6", 0);


        textShow = (TextView) findViewById(R.id.textView15);
        Imageshow = (ImageView) findViewById(R.id.imageView);
        Datasum();

//        Buttonclick();


        btn1 = (Button) findViewById(R.id.button4);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.button4) {
                    Intent intent = new Intent(getApplicationContext(), MembermainActivity.class);
                    startActivity(intent);

                }

            }
        });
        btn2 = (Button) findViewById(R.id.button5);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.button5) {
                    finish();
                }

            }
        });

    }

    public void Datasum() {
        int a = (sum1 +sum2 + sum3 + sum4 + sum5 + sum6);

        String ANS = new String();

        if (sum2 != 0){
            ANS = "ควรไปพบแพทย์โดนด่วน";
            Imageshow.setImageResource(R.drawable.heat);

        }
        else if (sum5 != 0){
            ANS = "ควรไปพบแพทย์โดนด่วน";
            Imageshow.setImageResource(R.drawable.heat);
        }
        else if (sum6 != 0){
            ANS = "ควรไปพบแพทย์โดนด่วน";
            Imageshow.setImageResource(R.drawable.smile);
        }
        else if (a == 0) {
            ANS = "พบว่าคุณไม่มีความเสี่ยงมะเร็งเต้านม";
            Imageshow.setImageResource(R.drawable.medu);
        }
        else if (a <= 2  ) {
            ANS = "พบว่าคุณอาจมีความเสี่ยงมะเร็งเต้านม";
            Imageshow.setImageResource(R.drawable.medu);
        }
        else  {
            ANS = "พบว่าคุณมีความเสี่ยงมะเร็งเต้านมเพิ่มสูงขึ้น";
            Imageshow.setImageResource(R.drawable.medu);
        }




        Toast.makeText(getApplicationContext(),a + "res4fd",Toast.LENGTH_LONG).show();
        textShow.setText(ANS);
    }

//    public void Buttonclick(){
//        btn1 = (Button) findViewById(R.id.button4);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                if (v.getId() == R.id.nextbubutton4tton) {
//                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                    startActivity(intent);
//
//                }
//
//            }
//        });
//
//    }

    public int getSum1() {
        return sum1;
    }

    public void setSum1(int sum1) {
        this.sum1 = sum1;
    }

    public int getSum2() {
        return sum2;
    }

    public void setSum2(int sum2) {
        this.sum2 = sum2;
    }

    public int getSum3() {
        return sum3;
    }

    public void setSum3(int sum3) {
        this.sum3 = sum3;
    }

    public int getSum4() {
        return sum4;
    }

    public void setSum4(int sum4) {
        this.sum4 = sum4;
    }

    public int getSum5() {
        return sum5;
    }

    public void setSum5(int sum5) {
        this.sum5 = sum5;
    }

    public int getSum6() {
        return sum6;
    }

    public void setSum6(int sum6) {
        this.sum6 = sum6;
    }


}


