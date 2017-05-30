package oob.myform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnNext;
    private EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.init();
    }

    private void init() {
        this.setVariables();
        this.addClickListeners();
    }

    private void setVariables() {
        this.setEditTextName((EditText) this.findViewById(R.id.editTextName));
        this.setBtnNext((Button) this.findViewById(R.id.btnNext));
    }

    private void addClickListeners() {
        this.getBtnNext().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String name = getStringFromEditText(getEditTextName());
            if (Utils.checkValidString(name)) {
                Intent intentPassToSecondActivity = new Intent(MainActivity.this, SecondActivity.class);
                intentPassToSecondActivity.putExtra(Utils.KEY_NAME, name);

                startActivity(intentPassToSecondActivity);
            } else {
                Utils.showToast(MainActivity.this, getString(R.string.warningNoNameEntered));
            }
            }
        });
    }

    /**
     * @param editText EditText
     * @return String
     */
    private String getStringFromEditText(EditText editText) {
        return editText.getText().toString();
    }

    // ------------------ Getter & Setters ------------------

    public Button getBtnNext() {
        return btnNext;
    }

    /**
     * @param btnNext Button
     */
    public void setBtnNext(Button btnNext) {
        this.btnNext = btnNext;
    }

    public EditText getEditTextName() {
        return editTextName;
    }

    /**
     * @param editTextName EditText
     */
    public void setEditTextName(EditText editTextName) {
        this.editTextName = editTextName;
    }
}
