******* STEPS OF USING ORMLITE FOR ANDROID *******

1. 	Download ormlite-core.jar (use the latest versions of the jar)
2. 	Download ormlite-android.jar (use the latest versions of the jar)
(Both jars should have the same versions)

3. 	Create a database helper that extends OrmLiteSqliteOpenHelper
4. 	override the methods and add a constructor
5. 	write in the database operations that have to be performed.

******* TO CREATE A TABLE *******
1. 	Create a model class that will define the fields of the table
2. 	Use @DatabaseField in order to define the property as a field

Example: @DatabaseField(generatedId=true)
         private int id;

3. 	In the onCreate method of the helper class use :
		TableUtils.create(connectionSource, YourClassFileName.class);

4. 	For dropping the table in onUpgrade method use :
		TableUtils.drop(connectionSource, YourClassFileName.class, allowErrorsOrNot);

5.  For every exception during onCreate and onUpgrade, throw the RuntimeException 
	and code for getting dao using the RuntimeExceptionDao also else you will get an 
	error saying that the RuntimeExceptionDao was never used or implemented
	
6.	To query for all the records like a select * use :
	Dao<YourClass, Integer> dao = getdDao(YourClass.class);
	List<YourClass> list = dao.queryForAll();
	
7.	To Create an object in the table (row) use :
	Dao<YourClass, Integer> dao = getdDao(YourClass.class);
	dao.create(the_object_instance_of_your_class);
	
8.	To Obtain a single object use :
	Dao<YourClass, Integer> dao = getdDao(YourClass.class);
	dao.queryForId(id);

9.	To query for a record using a search string then use :
	QueryBuilder<YourClass, Integer> queryBuilder = yourDaoObj.queryBuilder();
	queryBuilder.where().like(YourClass.COLUMN_NAME_HERE, searchString);
	List<YourClass> list = queryBuilder.query();
	
When an OpenHelperManager is released, and the database accessed, the following error is thrown :
	Caused by: java.lang.IllegalStateException: database not open.
	Scenario: Add an item into the db, finish the activity and access the items in the next activity
	Solution: prevent writing the following code when you wish to access the db immediately for other operations
	
	@Override
	protected void onDestroy() {
	    super.onDestroy();
	    if (databaseHelper != null) {
	        OpenHelperManager.releaseHelper();
	        databaseHelper = null;
	    }
	}

10. To use a like query in ormlite use :
	queryBuilder.where().like(YourClass.COLUMN_NAME_HERE,"%"+searchString+"%");
	RES: http://www.java2s.com/Open-Source/Java/Database-ORM/ORM-Lite-1.11/com/j256/ormlite/examples/simple/BasicMain.java.htm
	
11. 
	