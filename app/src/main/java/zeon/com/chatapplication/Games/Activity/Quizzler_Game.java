package zeon.com.chatapplication.Games.Activity;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import zeon.com.chatapplication.R;

public class Quizzler_Game extends AppCompatActivity {

    Button mTrueButton;
    Button mFalseButton;
    TextView mScoreTextView;
    TextView mQuestionTextView;
    ProgressBar mProgressBar;
    int mIndex;
    int mScore;
    int mQuestion;
    Toast mToastMessage; // For keeping track if a Toast message is being shown.

    // Create question bank using the TrueFalse class for each item in the array
    @NonNull
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / mQuestionBank.length);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzler__game);

        // Declaring constants here. Rather than a fixed number, choosing to make it a function
        // of the length of the question bank (the number of questions)





            mTrueButton = findViewById(R.id.true_button);
            mFalseButton = findViewById(R.id.false_button);
            mQuestionTextView = findViewById(R.id.question_text_view);
            mScoreTextView = findViewById(R.id.score);
            mProgressBar = findViewById(R.id.progress_bar);

            // Restores the 'state' of the app upon screen rotation:
            if (savedInstanceState != null) {
                mScore = savedInstanceState.getInt("ScoreKey");
                mIndex = savedInstanceState.getInt("IndexKey");
                mScoreTextView.setText("Score " + mScore + "/" + mQuestionBank.length);
            } else {
                mScore = 0;
                mIndex = 0;
            }

            mQuestion = mQuestionBank[mIndex].getQuestionID();
            mQuestionTextView.setText(mQuestion);

            mTrueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                    updateQuestion();
                }
            });

            mFalseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                    updateQuestion();
                }
            });

        }

        private void updateQuestion() {
            // This takes the modulus. Not a division.
            mIndex = (mIndex + 1) % mQuestionBank.length;

            // Present an alert dialog if we reach the end.
            if(mIndex == 0) {
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Game Over");
                alert.setCancelable(false);
                alert.setMessage("You scored " + mScore + " points!");
                alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alert.show();
            }

            mQuestion = mQuestionBank[mIndex].getQuestionID();
            mQuestionTextView.setText(mQuestion);
            mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
            mScoreTextView.setText("Score " + mScore + "/" + mQuestionBank.length);
        }

        private void checkAnswer(boolean userSelection) {

            boolean correctAnswer = mQuestionBank[mIndex].isAnswer();

            // Can cancel the Toast message if there is one on screen and a new answer
            // has been submitted.
            if (mToastMessage != null) {
                mToastMessage.cancel();
            }

            if(userSelection == correctAnswer) {
                mToastMessage = Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT);
                mScore = mScore + 1;

            } else {
                mToastMessage = Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_LONG);
            }

            mToastMessage.show();

        }

        // This callback is received when the screen is rotated so we can save the 'state'
        // of the app in a 'bundle'.
        @Override
        public void onSaveInstanceState(Bundle outState){
            super.onSaveInstanceState(outState);

            outState.putInt("ScoreKey", mScore);
            outState.putInt("IndexKey", mIndex);
        }

    }
