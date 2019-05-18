package zeon.com.chatapplication.Games.Activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import zeon.com.chatapplication.R;

public class Main2_Destiny extends AppCompatActivity {


    //TODO:the member class
    private Button btn1;
    private Button btn2;
    private TextView question;
    private TextView ans1, ans2;
    private int numleft = 0;
    private int numright = 0;

    private TreeStory[] StoryArray = new TreeStory[]{
            new TreeStory(R.string.T1_Story, R.string.T1_Ans1, R.string.T1_Ans2),
            new TreeStory(R.string.T2_Story, R.string.T2_Ans1, R.string.T2_Ans2),
            new TreeStory(R.string.T3_Story, R.string.T3_Ans1, R.string.T3_Ans2),
            new TreeStory(R.string.T4_End, 0, 0),
            new TreeStory(R.string.T5_End, 0, 0),
            new TreeStory(R.string.T6_End, 0, 0),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2__destiny);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        question = (TextView) findViewById(R.id.first);

        question.setText(StoryArray[0].getQue());


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                update_left();
                Log.d("Distny", "Left Click" + "LeftValue is ="+ numleft);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_right();
                Log.d("Distny","Right Click"+ " RightValue ="+ numright);
            }
        });
    }

    public void update_left() {
        int id;
        int ans1;
        int ans2;
        boolean finish = false;
        if (numleft == 0) {
            id = StoryArray[2].getQue();
            question.setText(id);
            ans1 = StoryArray[2].getAns1();
            btn1.setText(ans1);
            ans2 = StoryArray[2].getAns2();
            btn1.setText(ans2);
            numleft = numleft + 1;
        } else if (numleft == 1) {
            id = StoryArray[5].getQue();
            question.setText(id);
            btn1.setText("Finish");
            btn2.setText("Finish");
            finish = true;
        }
        if (finish == true) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("The End");
            alert.setCancelable(false);
            alert.setMessage("Your Story Is Finish");
            alert.setPositiveButton("Leave", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }

    }

    public void update_right() {
        int id;
        int ans1;
        int ans2;
        boolean finish = false;
        if (numright == 0) {
            id = StoryArray[1].getQue();
            question.setText(id);
            ans1 = StoryArray[1].getAns1();
            btn1.setText(ans1);
            ans2 = StoryArray[1].getAns2();
            btn1.setText(ans2);
            numright = numright + 1;
        } else if (numright == 1) {
            id = StoryArray[3].getQue();
            question.setText(id);
            btn1.setText("Finish");
            btn2.setText("Finish");
            finish = true;
        }
        else if(numright == 2)
        {
            id = StoryArray[4].getQue();
            question.setText(id);
            btn1.setText("Finish");
            btn2.setText("Finish");
            finish = true;
        }
        if (finish == true) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("The End");
            alert.setMessage("The Story Is Finish");
            alert.setCancelable(false);
            alert.setPositiveButton("Leave", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }

    }
}