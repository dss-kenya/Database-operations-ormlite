package com.example.androidtrialoform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidtrialoform.db.DatabaseHelper;
import com.example.androidtrialoform.db.QuestionTable;

public class AddActivity extends Activity{
	private EditText mEditQuestionText;
	private EditText mEditQuestionType;
	private static DatabaseHelper mDatabaseHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_question);
		mEditQuestionText = (EditText)findViewById(R.id.editQuestion);
		mEditQuestionType = (EditText)findViewById(R.id.editQuestionType);
		mDatabaseHelper = MyApplication.getInstance().getHelper();
	}
	
	public void buttonClicked(View v) {
		switch (v.getId()) {
		case R.id.btnAdd:
			if(TextUtils.isEmpty(mEditQuestionText.getText()) || 
					TextUtils.isEmpty(mEditQuestionType.getText())) {
				Toast.makeText(AddActivity.this, 
						"Please enter values for the empty fields!",Toast.LENGTH_SHORT).show();
				return;
			}
			
			if(!TextUtils.isEmpty(mEditQuestionText.getText()) && 
					!TextUtils.isEmpty(mEditQuestionType.getText())) {
				QuestionTable questionTable = new QuestionTable();
				questionTable.setQuestionText(mEditQuestionText.getText().toString());
				questionTable.setQuestionType(Integer.parseInt(mEditQuestionType.getText().toString()));
				mDatabaseHelper.createQuestion(questionTable);
				//mDatabaseHelper.close();
				
				startActivity(new Intent(AddActivity.this, MainActivity.class));
				finish();
			}
			break;
			
		case R.id.btnCancel:
			startActivity(new Intent(AddActivity.this, MainActivity.class));
			finish();
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void onDestroy() {
	    super.onDestroy();
	}
}
