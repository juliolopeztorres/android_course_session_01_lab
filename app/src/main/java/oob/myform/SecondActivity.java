package oob.myform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private String name;
    private int age;

    private SeekBar seekBarAge;
    private RadioGroup radioGroupSelectTypeGreeting;
    private Button btnNext;
    private TextView ageSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        this.init();
    }

    private void init() {
        this.setVariables();
        this.setListeners();
    }

    private void setVariables() {
        this.setName(getIntent().getStringExtra(Utils.KEY_NAME));
        this.setSeekBarAge((SeekBar) findViewById(R.id.seekBarAge));
        this.setRadioGroupSelectTypeGreeting((RadioGroup) findViewById(R.id.radioGroupSelectTypeGreeting));
        this.setBtnNext((Button) findViewById(R.id.btnNext));
        this.setAgeSelected((TextView) findViewById(R.id.ageSelected));
    }

    private void setListeners() {
        this.getBtnNext().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkGreetingChoose() && checkValidAge()) {
                    Intent intentPassToThirdActivity = new Intent(SecondActivity.this, ThirdActivity.class);

                    intentPassToThirdActivity.putExtra(Utils.KEY_NAME, getName());
                    intentPassToThirdActivity.putExtra(Utils.KEY_AGE, getAge());
                    intentPassToThirdActivity.putExtra(Utils.KEY_GREETING_TYPE, getSelectTypeGreeting());

                    startActivity(intentPassToThirdActivity);
                }
            }
        });

        this.getSeekBarAge().setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setAge(progress);
                updateAgeText(progress);
                showBtnNext();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private boolean checkGreetingChoose() {
        if (Utils.ID_NOT_FOUND == this.getRadioGroupSelectTypeGreeting().getCheckedRadioButtonId()) {
            Utils.showToast(this, getString(R.string.warningNoGreetingSelected));
            return false;
        }

        return true;
    }

    private boolean checkValidAge() {
        if (this.getAge() < 16) {
            Utils.showToast(this, getString(R.string.warningLowAge));
            this.hideBtnNext();
            return false;
        }

        if (this.getAge() > 60) {
            Utils.showToast(this, getString(R.string.warningHighAge));
            this.hideBtnNext();
            return false;
        }

        return true;
    }

    private void hideBtnNext() {
        this.getBtnNext().setVisibility(View.INVISIBLE);
    }

    private void showBtnNext() {
        this.getBtnNext().setVisibility(View.VISIBLE);
    }

    /**
     * @param age int
     */
    private void updateAgeText(int age) {
        this.getAgeSelected().setText(String.valueOf(age));
    }

    // ------------------ Getter & Setters ------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SeekBar getSeekBarAge() {
        return seekBarAge;
    }

    public void setSeekBarAge(SeekBar seekBarAge) {
        this.seekBarAge = seekBarAge;
    }

    public RadioGroup getRadioGroupSelectTypeGreeting() {
        return radioGroupSelectTypeGreeting;
    }

    public void setRadioGroupSelectTypeGreeting(RadioGroup radioGroupSelectTypeGreeting) {
        this.radioGroupSelectTypeGreeting = radioGroupSelectTypeGreeting;
    }

    public Button getBtnNext() {
        return btnNext;
    }

    public void setBtnNext(Button btnNext) {
        this.btnNext = btnNext;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public TextView getAgeSelected() {
        return ageSelected;
    }

    public void setAgeSelected(TextView ageSelected) {
        this.ageSelected = ageSelected;
    }

    public int getSelectTypeGreeting() {
        if (this.getRadioGroupSelectTypeGreeting().getCheckedRadioButtonId() == R.id.radioButtonGreeting) {
            return Utils.GREETING;
        }

        return Utils.FAREWELL;
    }
}
