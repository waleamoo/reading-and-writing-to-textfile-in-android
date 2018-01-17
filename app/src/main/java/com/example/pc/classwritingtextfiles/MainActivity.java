package com.example.pc.classwritingtextfiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    EditText etName, etSurname;
    Button btAdd, btSave;
    TextView tvResult;
    ArrayList<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText)findViewById(R.id.etName);
        etSurname = (EditText)findViewById(R.id.etSurname);
        btAdd = (Button)findViewById(R.id.btAdd);
        btSave = (Button)findViewById(R.id.btSave);
        tvResult = (TextView)findViewById(R.id.tvResult);

        // declare the arraylist
        persons = new ArrayList<Person>();

        load_data();
    }

    private void load_data() {
        // clear the arraylist
        persons.clear();

        File file = getApplicationContext().getFileStreamPath("Data.txt");
        String lineFromFile;
        if(file.exists()){
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("Data.txt")));
                while((lineFromFile = reader.readLine()) != null){
                    StringTokenizer tokens = new StringTokenizer(lineFromFile, ", ");
                    Person person = new Person(tokens.nextToken(), tokens.nextToken());
                    persons.add(person);
                }
            }catch (IOException ex){
                Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void AddPerson(View v){
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();

        // create an instance of person
        Person person = new Person(name, surname);
        // add the new person to the arrayList
        persons.add(person);
        // method sets th text to the text view
        setTextToTextView();
    }

    private void setTextToTextView() {
        String text = "";
        for(int i = 0; i < persons.size(); i++){ // like we do with advanced for loop
            text = text + persons.get(i).getName() + " " + persons.get(i).getSurname() + "\n";
        }
        // set the text
        tvResult.setText(text);
    }

    public void SavePerson(View v){
        try{
            FileOutputStream file = openFileOutput("Data.txt", MODE_PRIVATE);
            OutputStreamWriter outputFile = new OutputStreamWriter(file);
            for(int i = 0; i < persons.size(); i++){
                outputFile.write(persons.get(i).getName() + ", " + persons.get(i).getSurname() + "\n");
            }
            outputFile.flush(); // make sure all the files are written to the file
            outputFile.close(); // closes the file

            Toast.makeText(MainActivity.this, "Successfully written", Toast.LENGTH_SHORT).show();

        }catch (IOException ex){
            Toast.makeText(MainActivity.this, "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}
