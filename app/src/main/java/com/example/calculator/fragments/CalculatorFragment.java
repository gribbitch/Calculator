package com.example.calculator.fragments;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.example.calculator.R;

public class CalculatorFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {

    private Boolean initialized = false;
    private Boolean finalIsOperator = false;
//    private MediaPlayer mp;

    public CalculatorFragment() {
        super(R.layout.fragment_calculator);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

//        return inflater.inflate(R.layout.fragment_calculator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button one = view.findViewById(R.id.btn_one);
        Button two = view.findViewById(R.id.btn_two);
        Button three = view.findViewById(R.id.btn_three);
        Button four = view.findViewById(R.id.btn_four);
        Button five = view.findViewById(R.id.btn_five);
        Button six = view.findViewById(R.id.btn_six);
        Button seven = view.findViewById(R.id.btn_seven);
        Button eight = view.findViewById(R.id.btn_eight);
        Button nine = view.findViewById(R.id.btn_nine);
        Button zero = view.findViewById(R.id.btn_zero);

        Button multiply = view.findViewById(R.id.btn_multiply);
        Button divide = view.findViewById(R.id.btn_divide);
        Button minus = view.findViewById(R.id.btn_minus);
        Button plus = view.findViewById(R.id.btn_plus);
        Button equals = view.findViewById(R.id.btn_equals);

        Button decimal = view.findViewById(R.id.btn_decimal);
        Button clear = view.findViewById(R.id.btn_clear);

//        one.setOnLongClickListener(this);
        one.setOnClickListener(this);
        one.setOnTouchListener(this);
        two.setOnClickListener(this);
        two.setOnTouchListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);

        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        minus.setOnClickListener(this);
        plus.setOnClickListener(this);
        equals.setOnClickListener(this);

        decimal.setOnClickListener(this);
        clear.setOnClickListener(this);


//        System.out.println(2+3*4/3-8);
    }


//    @Override
//    public boolean onLongClick(View v) {
//        int viewId = v.getId();
//
//        if (viewId == R.id.btn_one) {
//            concat("1");
//            MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.as_sound);
//            mp.start();
//        }
//        return false;
//    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int id = v.getId();

        MediaPlayer mp = new MediaPlayer();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                System.out.println("DOWN");

                if (id == R.id.btn_one) {
                    mp = MediaPlayer.create(getContext(), R.raw.as_sound);
                } else if (id == R.id.btn_two) {
                    mp = MediaPlayer.create(getContext(), R.raw.as_sound);
                }

                mp.start();
                System.out.println("Playing: " + mp.getTrackInfo());
                break;

            case MotionEvent.ACTION_UP:

//                mp.setLooping(false);
//                System.out.println("Looping: " + mp.isLooping());

//                System.out.println("Playing: " + mp.isPlaying());
//                if (mp != null) {
//                    System.out.println("MP is n ot null");
//                    mp.stop();
//                    mp.release();
//                }

                mp.pause();
                mp = new MediaPlayer();
                mp = null;

                System.out.println("UP");
                System.out.println("mp is null: " + mp == null);
//                mp.setLooping(false);
//                mp.pause();
//                mp.stop();
//                mp.release();
//                try {
//                    System.out.println(mp = MediaPlayer.create(getContext(), id));
//                } catch (NullPointerException e) {
//                    System.out.println("Pause audio");
//                }
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        if (viewId == R.id.btn_one) {
            concat("1");
        } else if (viewId == R.id.btn_two) {
            concat("2");
        } else if (viewId == R.id.btn_three) {
            concat("3");
        } else if (viewId == R.id.btn_four) {
            concat("4");
        } else if (viewId == R.id.btn_five) {
            concat("5");
        } else if (viewId == R.id.btn_six) {
            concat("6");
        } else if (viewId == R.id.btn_seven) {
            concat("7");
        } else if (viewId == R.id.btn_eight) {
            concat("8");
        } else if (viewId == R.id.btn_nine) {
            concat("9");
        } else if (viewId == R.id.btn_zero) {
            concat("0");
        } else if (viewId == R.id.btn_multiply) {
            if (!finalIsOperator && initialized) {
                concat("*");
                finalIsOperator = true;
            }
        } else if (viewId == R.id.btn_divide) {
            if (!finalIsOperator && initialized) {
                concat("/");
                finalIsOperator = true;
            }
        } else if (viewId == R.id.btn_minus) {
            if (!finalIsOperator) {
                concat("-");
                finalIsOperator = true;
            }
        } else if (viewId == R.id.btn_plus) {
            if (!finalIsOperator) {
                concat("+");
                finalIsOperator = true;
            }
        } else if (viewId == R.id.btn_equals) {
            calculate();
        } else if (viewId == R.id.btn_decimal) {
            if (!finalIsOperator && initialized) {
                concat(".");
                finalIsOperator = true;
            }
        } else if (viewId == R.id.btn_clear) {
            AppCompatTextView display = getView().findViewById(R.id.display);
            display.setText("");
            finalIsOperator = false;
            initialized = false;
        }

//        switch (v.getId()) {
//            case R.id.btn_one:
//                System.out.println("One");
//                break;
//            case R.id.btn_two:
//                System.out.println("Two");
//                break;
//        }

    }

    private void concat(String number) {
        AppCompatTextView display = getView().findViewById(R.id.display);
        if (initialized) {
            display.append(number);
        } else {
            display.setText(number);
            initialized = true;
        }
        finalIsOperator = false;
    }

    private void calculate() {
        System.out.println("Calculating expression");

        AppCompatTextView display = getView().findViewById(R.id.display);
        String expression = display.getText().toString();
        double result = 0;

        List<String> list = new ArrayList<String>();
        Matcher m = Pattern.compile("[/*]|[-+]?[0-9.]*").matcher(expression);

        List<Integer> mdIndexes = new ArrayList<Integer>();
        int mdCount = 0;

        while (m.find()) {
            if (m.group().equals("*") || m.group().equals("/")) {
                mdIndexes.add(list.size());
            }
            list.add(m.group());
        }

        for (int i = 0; i < mdIndexes.size(); i++) {
            System.out.println("Beep");
            int index = mdIndexes.get(i) - (mdCount * 2);
            double a = Double.valueOf(list.get(index-1));
            double b = Double.valueOf(list.get(index+1));
            if (list.get(index).equals("*")) {
                list.set(index-1, String.valueOf(a*b));
            } else {
                list.set(index-1, String.valueOf(a/b));
            }
            list.remove(index);
            list.remove(index);
            mdCount++;
        }

        System.out.println("Calculating for Final Answer");
        for (int i = 0; i < list.size()-1; i++) {
            result += Double.valueOf(list.get(i));
        }

        System.out.println(Double.compare(result % 1, 0.0));

        if (Double.compare(result % 1, 0.0) == 0) {
            DecimalFormat format = new DecimalFormat("0.#");
            display.setText(format.format(result));
        } else {
            display.setText(String.valueOf(result));
        }
        System.out.println("Result: " + result);
        System.out.println("Modulus: " + result%1);
    }
}
