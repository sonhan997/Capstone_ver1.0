package com.example.nguye.capston1_dtu.Controller;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguye.capston1_dtu.Adapter_controller.CheckAnswer;
import com.example.nguye.capston1_dtu.Model.Question;
import com.example.nguye.capston1_dtu.R;
import com.example.nguye.capston1_dtu.common.IsFireBaseLoadDone;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ScreenSlidePagerActivity extends FragmentActivity implements IsFireBaseLoadDone {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGE = 4;
    // private int NUM ,count=0;
    public int check = 0;
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    ArrayList<Question> listQuestion;
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;
    /**
     * data base from firebase
     */
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference myRef;
    IsFireBaseLoadDone isFireBaseLoadDone;
    TextView tvKiemTra, tvTimer, tvXemDiem,textView1;
    /**
     * common
     */
    String name;
    CounterClass aClass;
    public static final String MY = "title";

    /**
     * MAIN */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
        /**
         * get data tu activity Test list sang*/
        Intent intent = getIntent();
        name = intent.getStringExtra(MY);

        // fire base
        FirebaseApp.initializeApp(ScreenSlidePagerActivity.this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.pager);

        isFireBaseLoadDone = this;
        loadFirebase();
        mPager.setPageTransformer(true, new DepthPageTransformer());

        // anh xa
        aClass = new CounterClass(60*1000,1000);

        tvKiemTra = findViewById(R.id.tvKiemTra);
        tvKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListAnswer();
            }
        });
        tvTimer = findViewById(R.id.tvTimer);
        tvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        aClass.start();
        tvXemDiem = findViewById(R.id.tvScore);
        tvXemDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScreenSlidePagerActivity.this,TestDoneActivity.class);
                intent.putExtra("listQuestion",listQuestion);
                intent.putExtra("tam",name);
                startActivity(intent);
            }
        });

    }
    /**
     * show list answer
     */
    private void showListAnswer() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.check_answer_dialog);

        dialog.setTitle("Danh sách câu trả lời");


        // show dialog
        CheckAnswer checkAnswer = new CheckAnswer(this, listQuestion);
        GridView gridView = dialog.findViewById(R.id.gvCheckAnswer);
        gridView.setAdapter(checkAnswer);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // chuyển trang tới câu hỏi khi click vào
                mPager.setCurrentItem(position);
                dialog.dismiss();
            }
        });

        Button btClose, btChek;

        btClose = dialog.findViewById(R.id.btClose);
        btChek = dialog.findViewById(R.id.check);

        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btChek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aClass.cancel();
                result();
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    /**
     * show ket qua*/
    private void result() {
        check=1;
        if (mPager.getCurrentItem() >=2) mPager.setCurrentItem(mPager.getCurrentItem()-2);
        else if (mPager.getCurrentItem() <= 2) mPager.setCurrentItem(mPager.getCurrentItem() +2);
        tvXemDiem.setVisibility(View.VISIBLE);
        tvKiemTra.setVisibility(View.GONE);
    }

    /**
     * check online*/
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * load data from firebase
     * /*
     */
    public ArrayList<Question> loadFirebase() {
        if (name.equals("Toan de thi 1")){
            if (isOnline()){
                myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Toan").child("test").child("Đề số 1");
                final Question[] question = {null};
                Log.d("dexem", "vao dc roi");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        listQuestion = new ArrayList<>();
                        // check = 0;
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            // NUM = 1;
                            String title = (String) snapshot.child("title").getValue();
                            String viewQuestion = (String) snapshot.child("question").getValue();
                            String ansA = (String) snapshot.child("ansA").getValue();
                            String ansB = (String) snapshot.child("ansB").getValue();
                            String ansC = (String) snapshot.child("ansC").getValue();
                            String ansD = (String) snapshot.child("ansD").getValue();
                            String dapan = (String) snapshot.child("result").getValue();
                            question[0] = new Question(title, viewQuestion, ansA, ansB, ansC, ansD, dapan, "");
                            listQuestion.add(question[0]);
                            Log.d("de_xem", "test:" + ansA + "," + ansB + "," + ansC + "," + ansD + "!!!");
                        }
                        //  count = count+ NUM;
                        //  NUM = 0;
                        isFireBaseLoadDone.onFirebaseLoadSuccess(listQuestion);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d("Fail_test", "ko connect dc vs Firebase");
                        isFireBaseLoadDone.onFirebaseLoadFailed(databaseError.getMessage());
                    }

                });
            }else {
                Toast.makeText(getApplicationContext(),"Ko co internet",Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Updating",Toast.LENGTH_LONG).show();
        }
        return listQuestion;
    }
    /**
     * lay data tu fire base va bo vao slide*/
    public ArrayList<Question> getData() {
        // loadFirebase();
        return listQuestion;
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * kiểm tra có kết nối dc fire base ko?*/
    @Override
    public void onFirebaseLoadSuccess(ArrayList<Question> arrayList) {
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onFirebaseLoadFailed(String mess) {
        Toast.makeText(this, "" + mess, Toast.LENGTH_LONG).show();
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
            return fragment.create(position,check);
        }

        @Override
        public int getCount() {
            return NUM_PAGE;
        }
    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }

    /**
     * count time
     */
    public class CounterClass extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished));
            tvTimer.setText(countTime);
        }

        @Override
        public void onFinish() {
            tvTimer.setText("00:00");
        }
    }
}
