package com.demo.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private List<String> referenceDays = new ArrayList<String>();

	private GradientDrawable gradientDrawable, dateClickedBackgroundColor;
	private Drawable smallCircle;
	private int calendarTitleColor, sundayColor, restdaysColor,
			parentGridBackgroundColor;
	private int currentMonthDateColor, currentMonthDateBackColor,
			prevNextMonthDateColor, prevNextMonthDateBackColor;
	private CalendarAdapter adapter;

	private Calendar calMonth;
	int thisMonth;
	int thisDay;
	int thisYear;
	String formatedMonthTitleDate;
	String month_format = "MMMM yyyy";

	private Button btnNext, btnPrevious, btnSendRequest;
	private TextView txtCurrentMonth = null;
	private TextView txtMonday = null, txtTuesday = null, txtWednesday = null,
			txtThursday = null, txtFriday = null, txtSaturday = null,
			txtSunday = null;
	private GridView gridView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		referenceDays.add("Sun");
		referenceDays.add("Mon");
		referenceDays.add("Tue");
		referenceDays.add("Wed");
		referenceDays.add("Thu");
		referenceDays.add("Fri");
		referenceDays.add("Sat");

		setAccordingTheme();
		calMonth = Calendar.getInstance();
		thisMonth = calMonth.get(Calendar.MONTH);
		thisYear = calMonth.get(Calendar.YEAR);
		calMonth.set(calMonth.get(Calendar.YEAR), calMonth.get(Calendar.MONTH),
				calMonth.get(Calendar.DATE));

		initComponent();
		setWeekTitle(480);
		setViewBackground();

		formatedMonthTitleDate = android.text.format.DateFormat.format(
				month_format, calMonth).toString();
		setMonthTitle(formatedMonthTitleDate);

		adapter = new CalendarAdapter(this, calMonth,
				parentGridBackgroundColor, currentMonthDateColor,
				currentMonthDateBackColor, prevNextMonthDateColor,
				prevNextMonthDateBackColor, smallCircle,
				dateClickedBackgroundColor, calendarTitleColor, restdaysColor,
				sundayColor);

		//refreshCalendar();

	}

	private void refreshCalendar() {
//		adapter.refreshDays();
		adapter.notifyDataSetChanged();
		gridView.setAdapter(adapter);
	}

	public void setMonthTitle(final String dateFormat) {
		txtCurrentMonth.setText(dateFormat);
		txtCurrentMonth.setTextColor(calendarTitleColor);
	}

	private void initComponent() {
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
	}

	private void setAccordingTheme() {
		gradientDrawable = new GradientDrawable(
				GradientDrawable.Orientation.TOP_BOTTOM, new int[] {
						0xFFE96866, 0xFFBD0400 });
		gradientDrawable.setCornerRadius(0f);
		gradientDrawable.setDither(true);
		calendarTitleColor = Color.parseColor("#5b5b5b");
		// sundayColor = Color.parseColor("#E30000");
		sundayColor = Color.parseColor("#606060");
		restdaysColor = Color.parseColor("#606060");
		parentGridBackgroundColor = Color.parseColor("#fefefe");

		currentMonthDateColor = Color.parseColor("#626262");
		currentMonthDateBackColor = Color.TRANSPARENT;
		// currentMonthDateBackColor = Color.parseColor("#f68989");
		prevNextMonthDateColor = Color.parseColor("#979797");
		prevNextMonthDateBackColor = Color.parseColor("#f3f4f4");
		smallCircle = getResources().getDrawable(R.drawable.select_common);
		dateClickedBackgroundColor = new GradientDrawable(
				GradientDrawable.Orientation.TOP_BOTTOM, new int[] {
						0xFF9fcb53, 0xFF81bb26 });
	}

	private void setWeekTitle(int totalWidth) {
		txtSunday.setWidth((int) (totalWidth * 14.3) / 100);
		txtMonday.setWidth((int) (totalWidth * 14.3) / 100);
		txtTuesday.setWidth((int) (totalWidth * 14.3) / 100);
		txtWednesday.setWidth((int) (totalWidth * 14.3) / 100);
		txtThursday.setWidth((int) (totalWidth * 14.3) / 100);
		txtFriday.setWidth((int) (totalWidth * 14.3) / 100);
		txtSaturday.setWidth((int) (totalWidth * 14.3) / 100);
	}

	private void setViewBackground() {
		txtSunday.setTextColor(sundayColor);
		txtMonday.setTextColor(restdaysColor);
		txtTuesday.setTextColor(restdaysColor);
		txtWednesday.setTextColor(restdaysColor);
		txtThursday.setTextColor(restdaysColor);
		txtFriday.setTextColor(restdaysColor);
		txtSaturday.setTextColor(restdaysColor);
	}

}
