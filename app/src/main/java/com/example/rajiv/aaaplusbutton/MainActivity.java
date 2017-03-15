package com.example.rajiv.aaaplusbutton;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "MainActivity";
    EditText height, width;
    Button but_add;
    ImageButton but_remove;
    TextView txtSum, zerotextView;
    LinearLayout linearBelowBoard;
    int e1, e2, e3, e4, e5, e6, e7, e8, e9, e10;
    String eE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearBelowBoard = (LinearLayout) findViewById(R.id.linearBelowBoard);
        txtSum = (TextView) findViewById(R.id.totalText);
        but_add = (Button) findViewById(R.id.button2);

        but_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater =
                        (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.row, null);


                width = (EditText) addView.findViewById(R.id.width);
                height = (EditText) addView.findViewById(R.id.height);
                zerotextView = (TextView) addView.findViewById(R.id.zerotextView);

                width.addTextChangedListener(new MyTextWatcher(height, width, zerotextView));
                height.addTextChangedListener(new MyTextWatcher(height, width, zerotextView));


                but_remove = (ImageButton) addView.findViewById(R.id.remove);
                final View.OnClickListener thisListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((LinearLayout) addView.getParent()).removeView(addView);
                    }
                };

                but_remove.setOnClickListener(thisListener);
                linearBelowBoard.addView(addView);
            }

        });

    }

    private class MyTextWatcher implements TextWatcher {
        EditText mEditText1 = null;
        EditText mEditText2 = null;
        TextView textView = null;

        public MyTextWatcher(EditText mEditText1, EditText mEditText2, TextView textView) {
            this.textView = textView;
            this.mEditText1 = mEditText1;
            this.mEditText2 = mEditText2;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            calCheck(mEditText1, mEditText2, textView);
            calCheck2();
        }
    }

    public void calCheck(EditText height, EditText width, TextView zerotextView) {
        try {
            e1 = Integer.parseInt(width.getText().toString());
            e2 = Integer.parseInt(height.getText().toString());
            e3 = e1 * e2;
            zerotextView.setText(Integer.toString(e3));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void calCheck2() {
        try {

            if (linearBelowBoard != null) {
            e1 = 0;
            eE=null;
            for (int i = 0; i < linearBelowBoard.getChildCount(); i++) {
                TextView textView1 = getEditTextFromPosition(i);
                String str = textView1.getText().toString();
                TextView textView2 = getEditTextFromPositionWidth(i);
                String str2 = textView2.getText().toString();
                TextView textView3 = getEditTextFromPositionHeight(i);
                String str3 = textView3.getText().toString();
                Log.d(LOG_TAG, "calCheck2: " + str +" "+str2+" "+str3);
                if (!str.isEmpty()) {
//                    e1 = e1 + Integer.parseInt(textView1.getText().toString());

                    eE= "width " +str2+"    height "+str3+"    Total "+str;

                }
            }
        }
        txtSum.setText(String.valueOf(eE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TextView getEditTextFromPosition(int position) {
        return (TextView) linearBelowBoard.getChildAt(position).findViewById(R.id.zerotextView);
    }

    private TextView getEditTextFromPositionWidth(int position) {
        return (TextView) linearBelowBoard.getChildAt(position).findViewById(R.id.width);
    }

    private TextView getEditTextFromPositionHeight(int position) {
        return (TextView) linearBelowBoard.getChildAt(position).findViewById(R.id.height);
    }


}