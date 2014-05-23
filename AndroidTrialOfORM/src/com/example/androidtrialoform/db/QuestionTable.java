package com.example.androidtrialoform.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class QuestionTable {
	public static final String COLUMN_NAME_ID = "id";
	public static final String COLUMN_NAME_QUESTION = "question_text";
	public static final String COLUMN_NAME_QUESTION_TYPE = "question_type";
	
	@DatabaseField(generatedId=true,unique=true, columnName=COLUMN_NAME_ID)
	public int id;
	@DatabaseField(canBeNull=true, columnName=COLUMN_NAME_QUESTION)
	public String questionText;
	@DatabaseField(columnName=COLUMN_NAME_QUESTION_TYPE)
	public int questionType;
	
	public QuestionTable() {
		// this is needed by ormlite
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}
}
