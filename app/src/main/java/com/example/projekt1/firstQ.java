package com.example.projekt1;


import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class firstQ extends Fragment implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score= 0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_q, container, false);

        totalQuestionsTextView = view.findViewById(R.id.total_question);
        questionTextView = view.findViewById(R.id.question);
        ansA =view.findViewById(R.id.ans_A);
        ansB =view.findViewById(R.id.ans_B);
        ansC =view.findViewById(R.id.ans_C);
        ansD =view.findViewById(R.id.ans_D);
        submitBtn =view.findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions : "+totalQuestion);

        loadNewQuestion(view);

        return view;
    }

    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion(view);
        }else{
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.YELLOW);
        }

    }

    void loadNewQuestion(View view){

        if (currentQuestionIndex == totalQuestion){
            finishQuiz(view);
            return;
        }

        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }

    void finishQuiz(View view){
        String passStatus = "";
        if(score > totalQuestion*0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(getContext())
                .setTitle(passStatus)
                .setMessage("Score is " + score+" out of "+ totalQuestion)
                .setPositiveButton("End",(dialogInterface, i) -> restartQuiz(view) )
                .setCancelable(false)
                .show();
    }

    void restartQuiz (View view){
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion(view);
        Navigation.findNavController(view).navigate(R.id.action_firstQ_to_endScreen);
    }
}

