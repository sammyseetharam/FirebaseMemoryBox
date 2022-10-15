package com.example.mymemorybox;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddMemoryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    Spinner spinner;
    EditText memoryName, memoryDesc;
    String spinnerSelectedText = "none";

    // How to implement a Spinner
    // https://www.tutorialspoint.com/how-to-get-spinner-value-in-android

    // How to style the spinner
    // https://www.youtube.com/watch?v=7tnlh1nVkuE

    public AddMemoryActivity(){
        memoryName = findViewById(R.id.memoryName);
        memoryDesc = findViewById(R.id.descriptionEditText);
    }

    public void addMemoryButtonClicked(View view) {
        String memName = memoryName.getText().toString();
        String memDesc = memoryDesc.getText().toString();
        int memoryRatingNum = 0;
        // This will take the option they clicked on and ensure it is a number.
// My options went from 5 to 1, so that is why I have it adjusted with 6-i
// I also had an instruction statement as my first line in my string array
// ADJUST THIS LOOP TO MATCH YOUR CODE!

// Note the syntax here for how to access an index of a string array within
// the java
        for (int i = 1; i < 6; i++) {
            if (spinnerSelectedText.equals(getResources().
                    //is it based of spinner array?
                    getStringArray(R.array.spinnerList)[i])) {
                memoryRatingNum = 6-i;
                break;
            }
        }

        Memory m = new Memory(memoryRatingNum, memName, memDesc);
        SignInActivity.firebaseHelper.addData(m);

        memoryName.setText("");
        memoryDesc.setText("");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_memory_activity);

        // this attaches my spinner design (spinner_list.xml) and my array of spinner choices(R.array.memoryRating)
        spinner = findViewById(R.id.spinnerListObject);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_list,
                getResources().getStringArray(R.array.spinnerList));

        // this attaches my custom row design (how I want each row to look)
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_row);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }
    // This method is required, even if empty, for the OnItemSelectedListener to work
    @Override
    public void onNothingSelected(AdapterView<?> parent) { }


}
