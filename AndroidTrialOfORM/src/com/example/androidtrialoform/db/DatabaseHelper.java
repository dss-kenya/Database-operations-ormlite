package com.example.androidtrialoform.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidtrialoform.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	private static final String DATABASE_NAME = "ormlite_db.sqlite";
	private static final int DATABASE_VERSION = 1;
	private Dao<QuestionTable, Integer> questionTableDAO = null;
	private ConnectionSource connectionSource = null;
	private RuntimeExceptionDao<QuestionTable, Integer> runtimeQuestionTableDAO = null;

	/**
	 * Constructor
	 * @param context
	 */
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource source) {
		try {
			TableUtils.createTable(source, QuestionTable.class);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource source, int oldVersion,
			int newVersion) {
		try {
			TableUtils.dropTable(source, QuestionTable.class,true);
			onCreate(db, source);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	/**
	 * Gets the dao into the dao object
	 * @return
	 */
	public Dao<QuestionTable, Integer> getQuestion() {
		try {
			if(questionTableDAO == null) {
				questionTableDAO = getDao(QuestionTable.class);
			}
		}catch(SQLException s) {
			s.printStackTrace();
		}
		return questionTableDAO;
	}

	/**
	 * inserts an object into the database
	 * @param questionTable
	 */
	public void createQuestion(QuestionTable questionTable) {
		try {
			if(questionTableDAO == null) {
				questionTableDAO = getDao(QuestionTable.class);
			}
			questionTableDAO.create(questionTable);
		}catch(SQLException s) {
			s.printStackTrace();
		}
	}

	/**
	 * Gets all the questions from the database
	 * @return
	 */
	public List<QuestionTable> getAllQuestions() {
		List<QuestionTable> listOfQuestions = new ArrayList<QuestionTable>();
		try {
			if(questionTableDAO == null) {
				questionTableDAO = getDao(QuestionTable.class);
			}
			listOfQuestions = questionTableDAO.queryForAll();
		}catch(SQLException s) {
			s.printStackTrace();
		}
		return listOfQuestions;
	}

	/**
	 * Closes the daos
	 */
	@Override
	public void close() {
		super.close();
		questionTableDAO = null;
		runtimeQuestionTableDAO = null;
	}

	/**
	 * Used when a RuntimeException occurs
	 * @return
	 */
	public RuntimeExceptionDao<QuestionTable, Integer> getSimpleQuestion() {
		if (runtimeQuestionTableDAO == null) {
			runtimeQuestionTableDAO = getRuntimeExceptionDao(QuestionTable.class);
		}
		return runtimeQuestionTableDAO;
	}

	/**
	 * Gets a single question based on the id entered
	 * @param id
	 * @return
	 */
	public QuestionTable getSingleQuestion(int id) {
		QuestionTable question = null;
		try {
			if(questionTableDAO == null) {
				questionTableDAO = getDao(QuestionTable.class);
			}
			question = questionTableDAO.queryForId(id);
		}catch(SQLException s) {
			s.printStackTrace();
		}
		return question;
	}
	
	public List<QuestionTable> getQuestionsOnSearchString(String search) {
		List<QuestionTable> list = new ArrayList<QuestionTable>();
		try {
			if(questionTableDAO == null) {
				questionTableDAO = getDao(QuestionTable.class);
			}
			QueryBuilder<QuestionTable, Integer> queryBuilder = questionTableDAO.queryBuilder();
			
			queryBuilder.where().like(QuestionTable.COLUMN_NAME_QUESTION, "%"+ search + "%");
			list = queryBuilder.query();
		}catch(SQLException s) {
			s.printStackTrace();
		}
		return list;
	}
	
	@Override
	public ConnectionSource getConnectionSource() {
	    if (connectionSource == null) {
	        connectionSource = super.getConnectionSource();
	    }
	    return connectionSource;
	}
	
	public void deleteAllRecords() {
		try {
			if(questionTableDAO == null) {
				questionTableDAO = getDao(QuestionTable.class);
			}
			questionTableDAO.deleteBuilder().delete();
		}catch(SQLException s) {
			s.printStackTrace();
		}
	}
}
