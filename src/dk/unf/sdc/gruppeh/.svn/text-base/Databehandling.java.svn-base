package dk.unf.sdc.gruppeh;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Databehandling extends DatabaseManagerAbstraction {
	private static final String DATABASE_NAME = "ARBEJDSTIDER_DB";
	private static final String TABLE_ARBEJDSTIDER = "arbejdstider";

	private static final String KEY_ARBEJDSTIDER_ID = "id";
	private static final String KEY_ARBEJDSTIDER_DATE = "date";
	private static final String KEY_ARBEJDSTIDER_LENGTH = "length";
	
	private static final String CREATE_TABLE_ARBEJDSTIDER = "" +
			"CREATE TABLE " + TABLE_ARBEJDSTIDER + " (" +
			 KEY_ARBEJDSTIDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			 KEY_ARBEJDSTIDER_DATE + " TEXT, " +
			 KEY_ARBEJDSTIDER_LENGTH + " INTEGER " +
					")";
	
	public Databehandling(Context context) {
		super(context, DATABASE_NAME);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL(CREATE_TABLE_ARBEJDSTIDER);
	}

	public void saveWorkTime(int minInAll) {
		ContentValues table_arbejdstider_values = new ContentValues();
		table_arbejdstider_values.put(KEY_ARBEJDSTIDER_DATE, returnDay());
		table_arbejdstider_values.put(KEY_ARBEJDSTIDER_LENGTH, minInAll);
		
		long insertID = insertRow(TABLE_ARBEJDSTIDER, table_arbejdstider_values);
	
		if (insertID == -1){
			return ;
		}
	}
	
	public void updateWorkTime(long minInAll) {
		ContentValues table_arbejdstider_values = new ContentValues();
		table_arbejdstider_values.put(KEY_ARBEJDSTIDER_DATE, returnDay());
		table_arbejdstider_values.put(KEY_ARBEJDSTIDER_LENGTH, minInAll);
		long insertID = updateById(TABLE_ARBEJDSTIDER, KEY_ARBEJDSTIDER_ID, returnIdOpenWorkTime(), table_arbejdstider_values);
	
		if (insertID == -1){
			return ;
		}
	}
	
	public void removeInvalidWorkTime(){
		// Vi mangler at finde id på det sidst lagrede data

		 Map<String, String> where = new HashMap<String, String>();
		 where.put(KEY_ARBEJDSTIDER_LENGTH, "0");
		 List<Map<String, Object>> missingLength = getAllWhere(TABLE_ARBEJDSTIDER, where);
		 //for (Map<String, Object> item: getAllWhere(TABLE_ARBEJDSTIDER, where)); {
		 if (missingLength.size() == 0) {
			 return;
		 } else {
			 removeID((Integer) missingLength.get(0).get(KEY_ARBEJDSTIDER_ID));
		 }
		 //}	 
	}
	
	public void removeID(int id) {
		removeById(TABLE_ARBEJDSTIDER, KEY_ARBEJDSTIDER_ID, id);
	}
	
	public void removeLastWorkTime() {
		 List<Map<String, Object>> lastObject = getAll(TABLE_ARBEJDSTIDER);
		 int highScoreId = 0;
		 if (lastObject.size() == 0) {
			 return;
		 }
		 int dontDeleteMeId = returnIdOpenWorkTime();
		 for (Map<String, Object> item: lastObject) { 
			 int itemId = ((Integer) item.get(KEY_ARBEJDSTIDER_ID));
			 if (highScoreId < itemId && dontDeleteMeId != itemId) {
				 highScoreId = itemId;
			 }
		 }
		 removeID(highScoreId);
	}
	
	public void removeAllWorkTimes() {
		List<Map<String, Object>> lastObject = getAll(TABLE_ARBEJDSTIDER);
		int numbersOfViews = lastObject.size();
		for (int i = 0; i < numbersOfViews; i++) {
			removeLastWorkTime();
		}
	}
	
	public List<Map<String, Object>> readAllWorkTimes() {
		//for loop der kører readworktime for at læse en af gangen
		List<Map<String, Object>> allWorkTimes = getAll(TABLE_ARBEJDSTIDER);
//		ArrayList<WorkTime> result = new ArrayList<WorkTime>(allWorkTimes.size());
		List<Map<String, Object>> statistics = new ArrayList<Map<String, Object>>();
		
		for (Map<String, Object> item: allWorkTimes) {
			if (!(item.get(KEY_ARBEJDSTIDER_LENGTH) == "0")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put( KEY_ARBEJDSTIDER_LENGTH, (Integer) (item.get(KEY_ARBEJDSTIDER_LENGTH)));
				map.put( KEY_ARBEJDSTIDER_DATE, Long.parseLong((String)item.get(KEY_ARBEJDSTIDER_DATE)));
				statistics.add(map);
			}
		}
		return statistics;
	}
	
	public ArrayList<WorkTime> loadRecentToList() {
		ArrayList<WorkTime> values = new ArrayList<WorkTime>();
		List<Map<String, Object>> loadedData = new ArrayList<Map<String, Object>>();
		loadedData = readAllWorkTimes();
		if (loadedData.size() == 0) {
			return values;
		}
		for (Map<String, Object> item : loadedData) {
			WorkTime workTime = new WorkTime((Integer) item.get(KEY_ARBEJDSTIDER_LENGTH));
			workTime.setDay( (Long)item.get(KEY_ARBEJDSTIDER_DATE));
			values.add(workTime);
		}
		/*Map<String, Integer> getValue = loadedData.get(0);

		int minInAll = (Integer) getValue.get(KEY_ARBEJDSTIDER_LENGTH);
		WorkTime workTime = new WorkTime(minInAll);
		Date date = new Date(getValue.get(KEY_ARBEJDSTIDER_DATE));
		workTime.setDay(date);
		values.add(workTime);*/
		return values;
	}

	public String readWorkTime() {
		//læser en arbejdstid.
		return ""; //returner nyeste arbejdstid
	}
	
	public long calcOpenWorkTimeLenght(){
		int id = returnIdOpenWorkTime();
		Log.d("id", String.valueOf(id));
		Map<String, Object> readedWorkTime = getById(TABLE_ARBEJDSTIDER, KEY_ARBEJDSTIDER_ID, id);
		//thedate = readedworktime ændret til tid i milliseconds efter 1970....
		
		long theDate = Long.parseLong(readedWorkTime.get(KEY_ARBEJDSTIDER_DATE).toString());

		WorkTime workTime = new WorkTime();
		long lengthInMin = workTime.returnLenghtWorkTime(theDate);
		return lengthInMin;				
	}
	
	public int returnIdOpenWorkTime() {
		Map<String, String> unFinished = new HashMap<String, String>();
		unFinished.put(KEY_ARBEJDSTIDER_LENGTH, "0");
		List<Map<String, Object>> unFinishedWorkTimes = getAllWhere(TABLE_ARBEJDSTIDER, unFinished);
		Log.d("idOnNotFinished", String.valueOf(unFinishedWorkTimes.size()));
		if (unFinishedWorkTimes.size() == 0) {
			return 0;
		} 
		int id = (Integer) unFinishedWorkTimes.get(0).get(KEY_ARBEJDSTIDER_ID);
		return id;
		
		/*int id;
		for (Map<String, Object> item: unFinishedWorkTimes) {
			id = Integer.parseInt(item.toString());
			
			Log.d("id", item.toString());
			return id;
		}
		return -1;
		
		List<Map<String, Object>> getList = getAllWhere(table, where);
		if (getList.size() != 1) return null; */
	}
	
	public void saveStartLog() {
		ContentValues table_arbejdstider_values = new ContentValues();
		table_arbejdstider_values.put(KEY_ARBEJDSTIDER_DATE, returnDay());
		table_arbejdstider_values.put(KEY_ARBEJDSTIDER_LENGTH, 0);
		
		long insertID = insertRow(TABLE_ARBEJDSTIDER, table_arbejdstider_values);
	
		if (insertID == -1){
			return ;
		}
	}
	
	public int readLastWeekHours() {
		List<Map<String, Object>> lastWeekItems = new ArrayList<Map<String,Object>>();
		long week = System.currentTimeMillis()/1000 - 604800; //1 uge i ms
		List<Map<String, Object>> allWorkTimes = readAllWorkTimes();
		for (Map<String, Object> item: allWorkTimes) {
			long date = (Long)item.get(KEY_ARBEJDSTIDER_DATE);
			if (date > week) {
				lastWeekItems.add(item);
			}
		}
		
		int workedLastWeekMins = 0;
		for (Map<String, Object> item: lastWeekItems) {
			workedLastWeekMins += (Integer) item.get(KEY_ARBEJDSTIDER_LENGTH);
		}
		return workedLastWeekMins;
	}
	
	public String returnDay() {
		WorkTime workTime = new WorkTime();
		//getDay returnere tiden i ms siden 1970..
		return String.valueOf( workTime.getDay());
	}
}