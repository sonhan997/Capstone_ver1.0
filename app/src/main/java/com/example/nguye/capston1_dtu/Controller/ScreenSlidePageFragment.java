package com.example.nguye.capston1_dtu.Controller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.nguye.capston1_dtu.Model.Question;
import com.example.nguye.capston1_dtu.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ScreenSlidePageFragment extends Fragment {

    /**
     * khai bao cac thuoc tinh*/

    ArrayList<Question> list;
    public static final String PAGE_CH = "pages";
    public static final String CHECK_ANSWER = "check";
    private int mPageNumber; // so trang slide
    public int checkAns;
    /**
     * lay id tu chac nang ra*/
    TextView title;
    ImageView imageQ;
    RadioGroup radioGroup;
    RadioButton ansA,ansB,ansC,ansD;

    public ScreenSlidePageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);
        title = rootView.findViewById(R.id.tvNum);
        imageQ = rootView.findViewById(R.id.imgQuestion);
        ansA = rootView.findViewById(R.id.radA);
        ansB = rootView.findViewById(R.id.radB);
        ansC = rootView.findViewById(R.id.radC);
        ansD = rootView.findViewById(R.id.radD);
        radioGroup = rootView.findViewById(R.id.radGroup);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        ScreenSlidePagerActivity slidePagerActivity = (ScreenSlidePagerActivity) getActivity();
        list = slidePagerActivity.getData();
        mPageNumber = getArguments().getInt(PAGE_CH);
        checkAns = getArguments().getInt(CHECK_ANSWER);
    }

    public static ScreenSlidePageFragment create (int numberPager, int checkAnswer){
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PAGE_CH,numberPager);
        bundle.putInt(CHECK_ANSWER,checkAnswer);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        title.setText(list.get(mPageNumber).getTitle());
        Picasso.get().load(list.get(mPageNumber).getViewQuestion()).into(imageQ);
        ansA.setText(list.get(mPageNumber).getQuestionA());
        ansB.setText(list.get(mPageNumber).getQuestionB());
        ansC.setText(list.get(mPageNumber).getQuestionC());
        ansD.setText(list.get(mPageNumber).getQuestionD());

        /**
         * khoa nut radio button*/

        if (checkAns!=0){
            ansA.setClickable(false);
            ansB.setClickable(false);
            ansC.setClickable(false);
            ansD.setClickable(false);
            getCheckAnswer(list.get(mPageNumber).getDapAn());
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                list.get(mPageNumber).checkID = checkedId;
                list.get(mPageNumber).setTamAns(getAnswer(checkedId));
            }
        });
    }

    /**
     * Get ket qua vao dialog*/
    private String getAnswer(int ID){
        if (ID == R.id.radA){
            return "A";
        }else if (ID == R.id.radB){
            return "B";
        }else if (ID == R.id.radC){
            return "C";
        }else if (ID == R.id.radD){
            return "D";
        }return "";
    }

    /**
     * ham kiem tra cau dung*/
    private void getCheckAnswer(String ans){
        if (ans.equals("A")== true){
            ansA.setBackgroundColor(Color.RED);
        }else if (ans.equals("B") == true){
            ansB.setBackgroundColor(Color.RED);
        }else if (ans.equals("C") == true){
            ansC.setBackgroundColor(Color.RED);
        }else if (ans.equals("D") == true){
            ansD.setBackgroundColor(Color.RED);
        }return;
    }
}
