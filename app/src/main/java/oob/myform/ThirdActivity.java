package oob.myform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {

    private String name;
    private int age;
    private int greetingType;
    private String message;

    private Button btnCheck;
    private Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        this.init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setVariables();
        this.setListeners();
    }

    private void setVariables() {
        this.setName(getIntent().getStringExtra(Utils.KEY_NAME));
        this.setAge(getIntent().getIntExtra(Utils.KEY_AGE, 18));
        this.setGreetingType(getIntent().getIntExtra(Utils.KEY_GREETING_TYPE, 1));

        this.setBtnCheck((Button) findViewById(R.id.btnCheck));
        this.setBtnShare((Button) findViewById(R.id.btnShare));
    }

    private void setListeners() {
        this.getBtnCheck().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                getGreetingMessage();
                Utils.showToast(ThirdActivity.this, getMessage());
                Utils.hideBtn(btnCheck);
                Utils.showBtn(btnShare);
            }
        });

        this.getBtnShare().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentShare = new Intent(Intent.ACTION_SEND);

                intentShare.setType("text/plain");
                intentShare.putExtra(Intent.EXTRA_TEXT, getMessage());

                startActivity(intentShare);
            }
        });
    }

    private void getGreetingMessage() {
        String message = "Hi " + this.getName() + "! how are you with your " + this.getAge() + " years old?";
        if (this.getGreetingType() == Utils.FAREWELL) {
            message = "Hope I'll see you soon, " + this.getName() + ". At least before you get " + (this.getAge() + 1) + " years old!";
        }

        this.setMessage(message);
    }

    // ------------------ Getter & Setters ------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGreetingType() {
        return greetingType;
    }

    public void setGreetingType(int greetingType) {
        this.greetingType = greetingType;
    }

    public Button getBtnCheck() {
        return btnCheck;
    }

    public void setBtnCheck(Button btnCheck) {
        this.btnCheck = btnCheck;
    }

    public Button getBtnShare() {
        return btnShare;
    }

    public void setBtnShare(Button btnShare) {
        this.btnShare = btnShare;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
