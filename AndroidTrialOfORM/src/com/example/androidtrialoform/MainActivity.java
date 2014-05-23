package com.example.androidtrialoform;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtrialoform.db.DatabaseHelper;
import com.example.androidtrialoform.db.QuestionTable;
import com.example.androidtrialoform.utilities.ShowDialog;
import com.j256.ormlite.android.apptools.OpenHelperManager;

public class MainActivity extends Activity {
	private static DatabaseHelper databaseHelper = null;
	private List<QuestionTable> listOfQuestions;
	private TextView txtQuestion;
	private String mId;
	private String mSearchString;
	private QuestionTable question;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		databaseHelper = MyApplication.getInstance().getHelper();
		txtQuestion = (TextView)findViewById(R.id.txtViewQuestion);
	}

	@Override
	protected void onDestroy() {
	    super.onDestroy();
	    if (databaseHelper != null) {
	        OpenHelperManager.releaseHelper();
	        databaseHelper = null;
	    }
	}
	
	public void buttonClicked(View v) {
		switch (v.getId()) {
		case R.id.btnCreateQuestion:
			/*QuestionTable questionTable = new QuestionTable();
			questionTable.setQuestionText("name");
			questionTable.setQuestionType(2);
			databaseHelper.createQuestion(questionTable);*/
			startActivity(new Intent(MainActivity.this, AddActivity.class));
			break;
			
		case R.id.btnViewQuestion:
			listOfQuestions = databaseHelper.getAllQuestions();
			showData();
			break;
			
		case R.id.btnGetSingleRecordById:
			ShowDialog showDialog = new ShowDialog(MainActivity.this);
			showDialog.showCustomDialog();
			break;
			
		case R.id.btnDelete:
			databaseHelper.deleteAllRecords();
			break;
			
		case R.id.btnGetSingleRecordByString:
			showDialog = new ShowDialog(MainActivity.this);
			showDialog.showStringSearchDialog();
			break;

		default:
			break;
		}
	}
	
	private void showData() {
		txtQuestion.setText("");
		for(QuestionTable question : listOfQuestions) {
			txtQuestion.append(question.getQuestionText() + " : " + question.getId() + " \n ");
		}
	}
	
	private void fillData() {
		if(question != null) {
			txtQuestion.setText(question.getQuestionText() + " : " + question.getId());
		}else {
			Toast.makeText(MainActivity.this,"No data found!",Toast.LENGTH_SHORT).show();
		}
	}
	
	public void receiveId(String id) {
		mId = id;
		question = databaseHelper.getSingleQuestion(Integer.parseInt(mId));
		fillData();
	}
	
	public void receiveString(String searchString) {
		mSearchString = searchString;
		listOfQuestions = databaseHelper.getQuestionsOnSearchString(mSearchString);
		showData();
	}
}
