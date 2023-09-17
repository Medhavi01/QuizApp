package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button optionA,optionB,optionC,optionD;
    Button submitBtn;

    int score=0;
    int totalQuestions = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.total_questions);
        questionTextView = findViewById(R.id.question);
        optionA = findViewById(R.id.Option_A);
        optionB = findViewById(R.id.Option_B);
        optionC = findViewById(R.id.Option_C);
        optionD = findViewById(R.id.Option_D);
        submitBtn = findViewById(R.id.submit_btn);

        optionA.setOnClickListener(this);
        optionB.setOnClickListener(this);
        optionC.setOnClickListener(this);
        optionD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total Questions : "+totalQuestions);

        loadNewQuestion();

    }

    @Override
    public void onClick(View view) {

        optionA.setBackgroundColor(Color.WHITE);
        optionB.setBackgroundColor(Color.WHITE);
        optionC.setBackgroundColor(Color.WHITE);
        optionD.setBackgroundColor(Color.WHITE);

        Button clickedButton =(Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if(selectedAnswer.equals(QuestionAnswer.correctAnswer[currentQuestionIndex])){
                score++;
            }

            currentQuestionIndex++;
            loadNewQuestion();

        }else{
            //choices button clicked
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.GREEN);
        }

    }

    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestions ){
            finishQuiz();
            return;
        }

        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        optionA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        optionB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        optionC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        optionD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

    }
    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestions*0.60){
        passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is"+score+"out of "+totalQuestions)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();
    }
    void restartQuiz(){
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }
}