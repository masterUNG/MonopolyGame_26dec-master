package rtc.nuttamon.kantana.monopolygame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class PlayGame extends AppCompatActivity {

    // Explicit
    private TextView questionTextView;
    private RadioButton[] choiceRadioButtons;
    private ImageView imageView;
    private int timesAnInt = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        bindWidget();

        showView(timesAnInt);

        buttonController();


    } // Main Method

    private void buttonController() {

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timesAnInt += 1;
                Log.d("26decV1", "times ==> " + timesAnInt);
                showView(timesAnInt);

            }   // onClick
        });


    }   // buttonController


    private void showView(int index) {

        try {

            synQuestion objSynQuestion = new synQuestion(PlayGame.this);
            objSynQuestion.execute();
            String s = objSynQuestion.get();

            JSONArray jsonArray = new JSONArray(s);
            String[] questionStrings = new String[jsonArray.length()];
            String[] choice1Strings = new String[jsonArray.length()];
            String[] choice2Strings = new String[jsonArray.length()];
            String[] choice3Strings = new String[jsonArray.length()];
            String[] choice4Strings = new String[jsonArray.length()];
            String[] answerStrings = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                questionStrings[i] = jsonObject.getString("Question");
                choice1Strings[i] = jsonObject.getString("Choice1");
                choice2Strings[i] = jsonObject.getString("Choice2");
                choice3Strings[i] = jsonObject.getString("Choice3");
                choice4Strings[i] = jsonObject.getString("Choice4");
                answerStrings[i] = jsonObject.getString("Answer");
            }

            questionTextView.setText(questionStrings[index]);
            choiceRadioButtons[0].setText(choice1Strings[index]);
            choiceRadioButtons[1].setText(choice2Strings[index]);
            choiceRadioButtons[2].setText(choice3Strings[index]);
            choiceRadioButtons[3].setText(choice4Strings[index]);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }   // showView

    private void bindWidget() {

        questionTextView = (TextView) findViewById(R.id.textView4);
        choiceRadioButtons = new RadioButton[4];
        choiceRadioButtons[0] = (RadioButton) findViewById(R.id.radioButton4);
        choiceRadioButtons[1] = (RadioButton) findViewById(R.id.radioButton3);
        choiceRadioButtons[2] = (RadioButton) findViewById(R.id.radioButton2);
        choiceRadioButtons[3] = (RadioButton) findViewById(R.id.radioButton);
        imageView = (ImageView) findViewById(R.id.imageView8);


    }
} // Main Class
