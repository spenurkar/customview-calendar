package com.demo.calendar;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CalendarAdapter extends BaseAdapter {

	private ViewHolder holder;
	private Context mContext;
	private final Calendar month, selectedDate;
	private final LayoutInflater inflater;
	private final int currentMonthDateColor, currentMonthDateBackColor,
			prevNextMonthDateColor, prevNextMonthDateBackColor,
			parentGridBackgroundColor, sundayColor;
	private final GradientDrawable dateClickedBackgroundColor;
	private int days_lenght = 0;
	private short lastClicked = -1;
	private final short FIRST_DAY_OF_WEEK = 0;
	private short firstDay, maxday, currentDatePosition;
	private String[] days;

	public class ViewHolder {
		private TextView txtDay;
	}

	public CalendarAdapter(Activity context, Calendar month,
			int parentGridBackgroundColor, int currentMonthDateColor,
			int currentMonthDateBackColor, int prevNextMonthDateColor,
			int prevNextMonthDateBackColor, Drawable smallCircle,
			GradientDrawable dateClickedBackgroundColor, int eventTitleColor,
			int eventDescTimeColor, int sundayColor) {
		mContext = context;
		this.month = month;
		selectedDate = (Calendar) month.clone();
		this.month.set(Calendar.DAY_OF_MONTH, 1);
		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.parentGridBackgroundColor = parentGridBackgroundColor;
		this.currentMonthDateColor = currentMonthDateColor;
		this.currentMonthDateBackColor = currentMonthDateBackColor;
		this.prevNextMonthDateColor = prevNextMonthDateColor;
		this.prevNextMonthDateBackColor = prevNextMonthDateBackColor;
		this.dateClickedBackgroundColor = dateClickedBackgroundColor;
		this.sundayColor = sundayColor;
		days_lenght = 2/* unavailableDays.size() */;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return days.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return days[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Calendar calendar = Calendar.getInstance();

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.row_calendar, null);
			holder.txtDay = (TextView) convertView
					.findViewById(R.id.calendarrow_date);
			holder.txtDay.setWidth((int) (480 * 14.3) / 100);
			convertView.setBackgroundColor(parentGridBackgroundColor);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txtDay.setTag(position);

		if ((position + 1 < firstDay)) {
			holder.txtDay.setText(days[position]);
			holder.txtDay.setTextColor(prevNextMonthDateColor);
			holder.txtDay.setBackgroundColor(prevNextMonthDateBackColor);
			holder.txtDay.setClickable(false);
			holder.txtDay.setEnabled(false);
			holder.txtDay.setFocusable(false);
		} else if ((position + 1 >= maxday + firstDay)) {
			holder.txtDay.setText(days[position]);
			holder.txtDay.setTextColor(prevNextMonthDateColor);
			holder.txtDay.setBackgroundColor(prevNextMonthDateBackColor);
			holder.txtDay.setClickable(false);
			holder.txtDay.setEnabled(false);
			holder.txtDay.setFocusable(false);
		} else {
			holder.txtDay.setText(days[position]);
			holder.txtDay.setClickable(true);
			holder.txtDay.setEnabled(true);
			holder.txtDay.setFocusable(true);
		}

		return null;
	}

	public void refreshDays() {
		lastClicked = -1;
		days = null;
		firstDay = (short) month.get(Calendar.DAY_OF_WEEK);
		// if (firstDay == 7) {
		days = new String[42];
		// } else {
		// days = new String[35];
		// }
		short j = FIRST_DAY_OF_WEEK;

		if (firstDay > 1) {
			Calendar calendar = Calendar.getInstance();
			if (month.get(Calendar.MONTH) == Calendar.JANUARY) {
				calendar.set(month.get(Calendar.YEAR) - 1, Calendar.DECEMBER, 1);
			} else {
				calendar.set(month.get(Calendar.YEAR),
						month.get(Calendar.MONTH) - 1, 1);
			}
			short length = 0;

			for (j = 0; j < firstDay - FIRST_DAY_OF_WEEK; j++) {
				days[j] = "";
				length = j;
			}

			short maxDayPrevious = (short) calendar
					.getActualMaximum(Calendar.DAY_OF_MONTH);

			for (int i = length - 1; i >= 0; i--) {
				days[i] = String.valueOf(maxDayPrevious);
				maxDayPrevious--;
			}
		} else {
			for (j = 0; j < FIRST_DAY_OF_WEEK * 6; j++) {
				days[j] = "";
			}
			j = FIRST_DAY_OF_WEEK * 6 + 1;
		}

		short dayNumber = 1, startDay = 1;
		maxday = (short) month.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (short i = (short) (j - 1); i < days.length; i++) {
			if (dayNumber <= maxday) {
				days[i] = "" + dayNumber;
				dayNumber++;
			} else {
				days[i] = "" + startDay;
				startDay++;
			}
		}
	}

}
