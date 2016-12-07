package dk.unf.sdc.gruppeh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<WorkTime> {

	private ArrayList<WorkTime> workTime; 
	private Context c; 
	
	
	public MyArrayAdapter(Context context, int textViewResourceId,
			List<WorkTime> objects) {
		super(context, textViewResourceId, objects);
		
		c = context; 
		workTime = (ArrayList<WorkTime>) objects; 
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.list_view_item, null);

		TextView date = (TextView) v.findViewById(R.id.txtViewDate);
		TextView length = (TextView) v.findViewById(R.id.txtViewLength);
		
		long lengthHours = workTime.get(position).getLenghtInMin()/60;
		long lengthMin = workTime.get(position).getLenghtInMin()-(lengthHours*60);
		String lengthMinString;
		if (lengthMin < 10) {
			lengthMinString = "0" + lengthMin; 
		} else {
			lengthMinString = String.valueOf(lengthMin);
		}

		length.setText(lengthHours + ":" + lengthMinString + " timer");
		
		long longDate = workTime.get(position).getDay();
		//Log.d("date", String.valueOf( longDate ));
		Date day = new Date(longDate*1000);
		//String myDay = (day.getDate() + " " + day. + " " + day.getYear());
		date.setText("  " + day.toLocaleString().substring(0, day.toLocaleString().length()-9));
				
		return v;
	}
	
	
	
}
