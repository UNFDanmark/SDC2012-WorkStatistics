package dk.unf.sdc.gruppeh;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	private Button btnWriteWorktime, btnStatistics, btnEndProgram, btnSaveWorkTime;
	private ToggleButton tBtnStartWorkTimer;
	private EditText editTxtWriteWorkTimeMin, editTxtWriteWorkTimeHours;
	private boolean isWriteWorkVisible = false;
	private boolean tBtnTimerIsOnline;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnWriteWorktime = (Button) findViewById(R.id.btnWriteWorkTime);
        btnStatistics = (Button) findViewById(R.id.btnStatistics);
        btnEndProgram = (Button) findViewById(R.id.btnEndProgram);
        tBtnStartWorkTimer = (ToggleButton) findViewById(R.id.tBtnStartWorkTimer);
        editTxtWriteWorkTimeMin = (EditText) findViewById(R.id.editTxtWriteWorkTimeMin);
        editTxtWriteWorkTimeHours = (EditText) findViewById(R.id.editTxtWriteWorkTimeHours);
        btnSaveWorkTime = (Button) findViewById(R.id.btnSaveWorkTime);
        
        btnWriteWorktime.setOnClickListener(btnWriteWorkListener);
        btnStatistics.setOnClickListener(btnStatisticsListener);
        btnEndProgram.setOnClickListener(btnEndListener);
        tBtnStartWorkTimer.setOnClickListener(tBtnWorkTimerListener);
        btnSaveWorkTime.setOnClickListener(btnSaveWorkTimeListener);
        
        
        Databehandling data = new Databehandling(getApplicationContext());
        if (data.returnIdOpenWorkTime() == 0) {
        	tBtnTimerIsOnline = false;
        	tBtnStartWorkTimer.setTextOff("Mød på arbejde");
         } else {
        	tBtnTimerIsOnline = true;
        	tBtnStartWorkTimer.setTextOn("Slut arbejdsdagen\nMidlertidig varighed: " + calcVarighedOnOpenItem());
        }    	
        tBtnStartWorkTimer.setChecked(tBtnTimerIsOnline);
        //update tBtnIsOn, tjek om der er en i databasen som mangler en længde, hvis ja, beregn forskellen
        //hvis den er online, så skal vi fjerne den enhed i listviewet som har længden 0
    }

    OnClickListener btnWriteWorkListener = new OnClickListener() {
		
		public void onClick(View v) {
			changeVisibility();
		}
	};
     
	public String calcVarighedOnOpenItem() {
		Databehandling data = new Databehandling(getApplicationContext());
		long lengthInMinAll = data.calcOpenWorkTimeLenght();
		long lengthHours = lengthInMinAll/60;
		long lengthMin = lengthInMinAll - (lengthHours*60);
        String lengthMinString;
        if (lengthMin < 10) {
			lengthMinString = "0" + lengthMin; 
		} else {
			lengthMinString = String.valueOf(lengthMin);
		}
        String varighed = (lengthHours + ":" + lengthMinString + " timer");
        return varighed;
	}
	
	OnClickListener btnSaveWorkTimeListener = new OnClickListener() {
		
		public void onClick(View v) {
			String minString; //Denne if under, skal ændres på, den virker ikke
			
			if (editTxtWriteWorkTimeHours.getText().length() != 0) { //hvis der ikke står noget i timer = ingenting
				if (editTxtWriteWorkTimeMin.getText().length() != 0) { //hvis der ikke står noget i minutter, ændre minut = 0
					minString = editTxtWriteWorkTimeMin.getText().toString();
				} else {
					minString = "0";
				}
				int min = Integer.parseInt(minString);
				//editable, ellers kan man ikke lave den 
				//om til int og tjekke om den er større end 59
				if (min > 59) {
					Toast.makeText(getApplicationContext(), "Tælleren kan ikke komme over 59 min", Toast.LENGTH_LONG).show();
				} else {
					int hours = Integer.parseInt(editTxtWriteWorkTimeHours.getText().toString()); //læser hours og laver om til int
					
					int minInAll = (hours*60)+min;
					Databehandling data = new Databehandling(getApplicationContext());
					data.saveWorkTime(minInAll);
					changeVisibility();
					
					Toast.makeText(getApplicationContext(), "Gemt", Toast.LENGTH_SHORT).show();
					//gem til hdd den tid som man har arbejde
				}
			}
		}
	};
 
	
	OnClickListener tBtnWorkTimerListener = new OnClickListener() {
		
		public void onClick(View v) {
			
			Databehandling data = new Databehandling(getApplicationContext());
			
			if (tBtnTimerIsOnline) {
				tBtnStartWorkTimer.setTextOn("Slut arbejdsdagen"); //+ tid gået siden den blev startet)
				tBtnTimerIsOnline = false;
				long lengthInMin = data.calcOpenWorkTimeLenght();
				if (lengthInMin >= 1) {
					data.updateWorkTime(lengthInMin);
					
				}
				data.removeInvalidWorkTime();
				//"start" timeren"
			} else {
				tBtnStartWorkTimer.setTextOff("Mød på arbejde");
				tBtnTimerIsOnline = true;
				data.saveStartLog();
				
				//Toast.makeText(getApplicationContext(), String.valueOf(data.returnIdOpenWorkTime()), Toast.LENGTH_SHORT).show();
				//data.saveLogTime(c.getTime(Calendar.DATE), (c.getTime(Calendar.HOUR) + " " + c.getTime(Calendar.MINUTE)));
				//log tidspunktet når der bliver trykket på knappen 
			}
		}
	};
	   
	OnClickListener btnStatisticsListener = new OnClickListener() {
		
		public void onClick(View v) {
			Intent intent = new Intent(getApplicationContext(), StatisticsActitvity.class);
			startActivity(intent);
			// Åbner statistik siden
		}
	};
    
	OnClickListener btnEndListener = new OnClickListener() {
		
		public void onClick(View v) {
			finish();
		}
	};
	
	public void changeVisibility() {
		if(!isWriteWorkVisible) {
			editTxtWriteWorkTimeMin.setVisibility(View.VISIBLE);
			editTxtWriteWorkTimeHours.setVisibility(View.VISIBLE);
			editTxtWriteWorkTimeHours.requestFocus();
			btnSaveWorkTime.setVisibility(View.VISIBLE);
			isWriteWorkVisible = true;
		} else {
			editTxtWriteWorkTimeMin.setVisibility(View.GONE);
			editTxtWriteWorkTimeHours.setVisibility(View.GONE);
			btnSaveWorkTime.setVisibility(View.GONE);
			isWriteWorkVisible = false;
		}
	}
	
	public void showPopUp() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
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
        getMenuInflater().inflate(R.menu.activity_main, menu);  
        return true;
    }
}
