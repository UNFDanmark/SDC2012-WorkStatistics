package dk.unf.sdc.gruppeh;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class StatisticsActitvity extends Activity {
	private Button btnBack, btnDeleteRecent, btnDeleteAll;
	private ListView listRecent;
	private Button btnWeekStats;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);

		// Buttons findViewById
		btnBack = (Button) findViewById(R.id.btnBack);
		btnDeleteRecent = (Button) findViewById(R.id.btnRemoveLast);
		btnDeleteAll = (Button) findViewById(R.id.btnDeleteAll);
		listRecent = (ListView) findViewById(R.id.listRecent);
		btnWeekStats = (Button) findViewById(R.id.btnWeekStats);
		
		// Listeners 
		btnBack.setOnClickListener(btnBackListener);
		btnDeleteRecent.setOnClickListener(btnDeleteListener);
		btnDeleteAll.setOnClickListener(btnDeleteAllListener);
		btnWeekStats.setOnClickListener(btnWeekStatsListener);
	
		Databehandling data = new Databehandling(getApplicationContext());
		MyArrayAdapter ma = new MyArrayAdapter(getApplicationContext(), R.layout.list_view_item, data.loadRecentToList());
		
		listRecent.setAdapter(ma);
		ma.notifyDataSetChanged();
	}
	
	View.OnClickListener btnWeekStatsListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(StatisticsActitvity.this);
			Databehandling data = new Databehandling(getApplicationContext());
			int size = data.loadRecentToList().size();
			if(size == 0) {
				dialog.setMessage("Ingen arbejdstider!");
			}
			else{
				int minInAllLastWeek = data.readLastWeekHours();
				int hours = minInAllLastWeek/60;
				int mins = minInAllLastWeek - (hours*60);
				dialog.setMessage("Du har arbejdet i " + hours +":"+ mins + " timer, sidste uge");
			}
			
			dialog.setTitle("Sidste uges arbejdstid");
			
			
			dialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	        	   	//add functions??
	        	}
	    	});
		           
			dialog.show();
				
		}
	};

	View.OnClickListener btnBackListener = new View.OnClickListener() {
		public void onClick(View v) {
			onBackPressed();
		}
	};

	View.OnClickListener btnDeleteAllListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Databehandling data = new Databehandling(getApplicationContext());
			data.removeAllWorkTimes();
			Intent intent = new Intent(getApplicationContext(), StatisticsActitvity.class);
			startActivity(intent);
			finish();
		}
	};
	
	View.OnClickListener btnDeleteListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			
			Databehandling data = new Databehandling(getApplicationContext());
			if (data.returnIdOpenWorkTime() == 0) {
				data.removeLastWorkTime();
			}
			Intent intent = new Intent(getApplicationContext(), StatisticsActitvity.class);
			startActivity(intent);
			finish();
		}
	};

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
	
	public void showPopUp() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(StatisticsActitvity.this);
		dialog.setTitle("Om Work Statistics");
		String message = "Work Statistics hjælper med, at holde styr på dine arbejdstider m.m.\n" + 	
						"Work Statistics indeholder auto-generede statestikker over de forskellige arbejdstider, " +
						"og du får det nemmere i en ellers hverdag. Det eneste du skal, er blot at trykke på en knap.\n\n" +
						"Udviklet af Lasse, Emil og Tim i samarbejde med UNF SDC 2012.";
		dialog.setMessage(message);
		dialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 

        	}
    	});
		dialog.show();
	}
		
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menu_settings:
	            showPopUp();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_statistics, menu);
		return true;
	}

}
