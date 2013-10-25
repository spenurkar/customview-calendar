package com.demo.calendar.test;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.demo.calendar.R;

public class CalendarActivity extends Activity {

	/** Variables **/
	private int calendarTitleColor;
	private int sundayColor;
	//private int currentMonthDateBackColor;
	private int prevNextMonthDateBackColor;
	private int restdaysColor;
	//private int currentMonthDateColor;
	//private int prevNextMonthDateColor;
	private int parentGridBackgroundColor;
	private int thisMonth;
	private int thisYear;
	private int thisDate;
	private String formatedMonthTitleDate;

	/** Objects **/
	private GradientDrawable gradientDrawable;
	//private GradientDrawable dateClickedBackgroundColor;
	private CalendarAdapter adapter = null;
	//private Drawable smallCircle;
	private Calendar mCalendar = null;

	/** Views **/
	private Button btnNext;
	private Button btnPrevious;
	private GridView gridView = null;
	private TextView txtCurrentMonth = null;
	private TextView txtSunday = null;
	private TextView txtMonday = null;
	private TextView txtTuesday = null;
	private TextView txtWednesday = null;
	private TextView txtThursday = null;
	private TextView txtFriday = null;
	private TextView txtSaturday = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);
		initDrawablesAndColors();

		/** init calendar **/
		mCalendar = Calendar.getInstance();
		thisMonth = mCalendar.get(Calendar.MONTH);
		thisYear = mCalendar.get(Calendar.YEAR);
		thisDate = mCalendar.get(Calendar.DATE);
		mCalendar.set(thisYear, thisMonth, thisDate);
		/** init calendar end **/

		/** init screen components **/
		btnPrevious = (Button) findViewById(R.id.calendar_btn_previousmonth);
		txtCurrentMonth = (TextView) findViewById(R.id.calendar_tv_month);
		btnNext = (Button) findViewById(R.id.calendar_btn_nextmonth);
		gridView = (GridView) findViewById(R.id.month_gridView);
		txtMonday = (TextView) findViewById(R.id.txtMonday);
		txtTuesday = (TextView) findViewById(R.id.txtTuesday);
		txtWednesday = (TextView) findViewById(R.id.txtWednesday);
		txtThursday = (TextView) findViewById(R.id.txtThursday);
		txtFriday = (TextView) findViewById(R.id.txtFriday);
		txtSaturday = (TextView) findViewById(R.id.txtSaturday);
		txtSunday = (TextView) findViewById(R.id.txtSunday);

		txtSunday.setTextColor(sundayColor);
		txtMonday.setTextColor(restdaysColor);
		txtTuesday.setTextColor(restdaysColor);
		txtWednesday.setTextColor(restdaysColor);
		txtThursday.setTextColor(restdaysColor);
		txtFriday.setTextColor(restdaysColor);
		txtSaturday.setTextColor(restdaysColor);

		setWeekColumnWidth(480);

		/** init screen components end **/

		formatedMonthTitleDate = android.text.format.DateFormat.format(
				getString(R.string.date_format), mCalendar).toString();
		//Set formatted month to calendar title bar
		txtCurrentMonth.setText(formatedMonthTitleDate);
		txtCurrentMonth.setTextColor(calendarTitleColor);

		adapter = new CalendarAdapter(CalendarActivity.this);
		adapter.refreshDays();
		adapter.notifyDataSetChanged();
		gridView.setAdapter(adapter);
		
		btnPrevious.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onClickBtnPrevious();
			}
		});

		btnNext.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				onClickBtnNext();
			}
		});
	}
	
	/**
	 * Function called onClick of Previous Button Click displaying previous month
	 */
	private void onClickBtnPrevious(){
		if (mCalendar.get(Calendar.MONTH) == mCalendar
				.getActualMinimum(Calendar.MONTH)) {
			mCalendar.set((mCalendar.get(Calendar.YEAR) - 1),
					mCalendar.getActualMaximum(Calendar.MONTH), 1);
		} else {
			
			/** Add Condition for disabling previous months before current month **/
			
			if ((mCalendar.get(Calendar.MONTH) - 1) >= thisMonth
					|| (mCalendar.get(Calendar.YEAR) - 1) >= thisYear) {
				mCalendar.set(Calendar.MONTH,
						mCalendar.get(Calendar.MONTH) - 1);
			}
		}
		
		formatedMonthTitleDate = android.text.format.DateFormat.format(
				getString(R.string.date_format), mCalendar).toString();
		
		//Set formatted month to calendar title bar
		txtCurrentMonth.setText(formatedMonthTitleDate);
		txtCurrentMonth.setTextColor(calendarTitleColor);
		
		adapter.refreshDays();
		adapter.notifyDataSetChanged();
		gridView.setAdapter(adapter); //Setting modified adapter again to reflect changes. Otherwise grid doesn't reflect changes.
	}
	
	/**
	 * Function called onClick of Next Button Click displaying next month
	 */
	private void onClickBtnNext(){
		if (mCalendar.get(Calendar.MONTH) == mCalendar
				.getActualMaximum(Calendar.MONTH)) {
			mCalendar.set((mCalendar.get(Calendar.YEAR) + 1),
					mCalendar.getActualMinimum(Calendar.MONTH), 1);
		} else {
			mCalendar.set(Calendar.MONTH,
					mCalendar.get(Calendar.MONTH) + 1);
		}
		
		formatedMonthTitleDate = android.text.format.DateFormat.format(
				getString(R.string.date_format), mCalendar).toString();
		//Set formatted month to calendar title bar
		txtCurrentMonth.setText(formatedMonthTitleDate);
		txtCurrentMonth.setTextColor(calendarTitleColor);
		
		adapter.refreshDays();
		adapter.notifyDataSetChanged();
		gridView.setAdapter(adapter); //Setting modified adapter again to reflect changes. Otherwise grid doesn't reflect changes.
	}

	/**
	 * Colors and Drawable initialization
	 */
	private void initDrawablesAndColors() {
		/** Define Drawables **/
		//smallCircle = getResources().getDrawable(R.drawable.select_common);

		gradientDrawable = new GradientDrawable(
				GradientDrawable.Orientation.TOP_BOTTOM, new int[] {
						0xFFE96866, 0xFFBD0400 });
		//dateClickedBackgroundColor = new GradientDrawable(
		//		GradientDrawable.Orientation.TOP_BOTTOM, new int[] {
		//				0xFF9fcb53, 0xFF81bb26 });

		gradientDrawable.setCornerRadius(0f);
		gradientDrawable.setDither(true);

		/** Define Colors **/
		calendarTitleColor = Color.parseColor("#5b5b5b");
		sundayColor = Color.parseColor("#606060");
		restdaysColor = Color.parseColor("#606060");
		parentGridBackgroundColor = Color.parseColor("#fefefe");
		//currentMonthDateColor = Color.parseColor("#626262");
		//currentMonthDateBackColor = Color.TRANSPARENT;
		//prevNextMonthDateColor = Color.parseColor("#979797");
		prevNextMonthDateBackColor = Color.parseColor("#f3f4f4");
	}

	/**
	 * Sets week column's width to given width
	 * 
	 * @param width
	 */
	private void setWeekColumnWidth(int width) {
		txtSunday.setWidth((int) (width * 14.3) / 100);
		txtMonday.setWidth((int) (width * 14.3) / 100);
		txtTuesday.setWidth((int) (width * 14.3) / 100);
		txtWednesday.setWidth((int) (width * 14.3) / 100);
		txtThursday.setWidth((int) (width * 14.3) / 100);
		txtFriday.setWidth((int) (width * 14.3) / 100);
		txtSaturday.setWidth((int) (width * 14.3) / 100);
	}

	public class CalendarAdapter extends BaseAdapter {
		
		/** Variables **/
		private final short FIRST_DAY_OF_WEEK = 0;
		/**Week Day like Sun, Mon etc.**/
		private short currentWeekDay;
		private short maxday;
		private String[] day_of_month;
		private int[] day_of_week;
		private int dayWidth = 0;
		
		/** Objects **/
		private final Activity mContext;
		private ViewHolder holder;
		private Calendar selectedCalendar;
		
		/** Views **/
		private final LayoutInflater inflater;
	
		public CalendarAdapter(Activity context) {
			mContext = context;
			selectedCalendar = (Calendar) mCalendar.clone();
			inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			dayWidth = (int)(480 * 14.3) / 100;
		}

		public View getView(final int position, View convertView,
				final ViewGroup parent) {
			
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.calendarrow, null);
				holder.txtDay = (TextView) convertView.findViewById(R.id.calendarrow_date);
				holder.txtDay.setWidth(dayWidth);
				convertView.setBackgroundColor(parentGridBackgroundColor);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.txtDay.setTag(position);
			
			if ((position + 1 < currentWeekDay)) {
				/**
				 * To disable previous months date from current month.
				 */
				
				//Log.e("Snehal", position+1 + " < "+currentWeekDay + " / "+position+" / "+days[position]);
				holder.txtDay.setText(day_of_month[position]);
				holder.txtDay.setTextColor(Color.RED);
				holder.txtDay.setBackgroundColor(prevNextMonthDateBackColor);
				holder.txtDay.setClickable(false);
				holder.txtDay.setEnabled(false);
				holder.txtDay.setFocusable(false);
			} else
			if ((position + 1 >= maxday + currentWeekDay)) {
				
				/**
				 * To disable next months date from current month.
				 */
				
				//Log.v("Snehal", position+1 + " >= "+ (maxday + currentWeekDay) + " / "+position+" / "+days[position]);
				holder.txtDay.setText(day_of_month[position]);
				holder.txtDay.setTextColor(Color.GREEN);
				holder.txtDay.setBackgroundColor(prevNextMonthDateBackColor);
				holder.txtDay.setClickable(false);
				holder.txtDay.setEnabled(false);
				holder.txtDay.setFocusable(false);
			} else {
				
				/**
				 * Draw dates for current month.
				 */
				
				holder.txtDay.setTextColor(Color.MAGENTA);
				holder.txtDay.setText(day_of_month[position]+"="+day_of_week[position]);
				holder.txtDay.setClickable(true);
				holder.txtDay.setEnabled(true);
				holder.txtDay.setFocusable(true);

				/**
				 * Disabling past dates from current month wrt DAY_OF_WEEK
				 */
				
//				if(day_of_week[position] == Calendar.SATURDAY){
//					holder.txtDay.setTextColor(Color.GRAY);
//				}
				
				/**
				 * Disabling unavailable days
				 */
			}
			return convertView;
		}

		public void refreshDays() {
			day_of_month = null;
			day_of_week = null;
			
			currentWeekDay = (short) mCalendar.get(Calendar.DAY_OF_WEEK);
			// if (firstDay == 7) {
			day_of_month = new String[42];
			day_of_week = new int[42];
			// } else {
			// days = new String[35];
			// }
			short j = FIRST_DAY_OF_WEEK;

			if (currentWeekDay > 1) {
				Calendar calendar = Calendar.getInstance();
				
				if (mCalendar.get(Calendar.MONTH) == Calendar.JANUARY) {
					calendar.set(mCalendar.get(Calendar.YEAR) - 1,
							Calendar.DECEMBER, 1);
				} else {
					calendar.set(mCalendar.get(Calendar.YEAR),
							mCalendar.get(Calendar.MONTH) - 1, 1);
				}
				short length = 0;

				for (j = 0; j < currentWeekDay - FIRST_DAY_OF_WEEK; j++) {
					day_of_month[j] = "";
					length = j;
				}

				short maxDayPrevious = (short) calendar
						.getActualMaximum(Calendar.DAY_OF_MONTH);

				for (int i = length - 1; i >= 0; i--) {
					day_of_month[i] = String.valueOf(maxDayPrevious);
					maxDayPrevious--;
				}
				
			} else {
				for (j = 0; j < FIRST_DAY_OF_WEEK * 6; j++) {
					day_of_month[j] = "";
				}
				j = FIRST_DAY_OF_WEEK * 6 + 1;
			}

			short dayNumber = 1, startDay = 1;
			Calendar tempCal = (Calendar) mCalendar.clone();
			
			maxday = (short) mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			for (short i = (short) (j - 1); i < day_of_month.length; i++) {
				if (dayNumber <= maxday) {
					Log.d("Snehal", " # "+dayNumber);
					day_of_month[i] = "" + dayNumber;
					tempCal.set(Calendar.DAY_OF_MONTH, dayNumber);
					Log.d("Snehal", "DAY_OF_WEEK------"+tempCal.get(Calendar.DATE));
					day_of_week[i] = tempCal.get(Calendar.DAY_OF_WEEK);
					dayNumber++;
				} else {
					day_of_month[i] = "" + startDay;
					startDay++;
				}
			}
			tempCal = null;
		}

		public class ViewHolder {
			private TextView txtDay;
		}

		public int getCount() {
			return day_of_month.length;
		}

		public String getItem(int position) {
			return day_of_month[position];
		}

		public long getItemId(int position) {
			return 0;
		}

		@Override
		public boolean areAllItemsEnabled() {
			return super.areAllItemsEnabled();
		}

		@Override
		public boolean isEnabled(int position) {
			if ((position + 1 < currentWeekDay)
					|| (position + 1 >= maxday + currentWeekDay)) {
				return false;
			} else {
				return true;
			}
		}
	}
}
