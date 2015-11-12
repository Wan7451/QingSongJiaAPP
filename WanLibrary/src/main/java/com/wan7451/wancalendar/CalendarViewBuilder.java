package com.wan7451.wancalendar;

import android.content.Context;


public class CalendarViewBuilder {
    private DateView[] calendarViews;

    public DateView[] createMassCalendarViews(Context context, int count, int style, DateView.CallBack callBack) {
        calendarViews = new DateView[count];
        for (int i = 0; i < count; i++) {
            calendarViews[i] = new DateView(context, style, callBack);
        }
        return calendarViews;
    }

    public DateView[] createMassCalendarViews(Context context, int count, DateView.CallBack callBack) {

        return createMassCalendarViews(context, count, DateView.MONTH_STYLE, callBack);
    }

    public void swtichCalendarViewsStyle(int style) {
        if (calendarViews != null)
            for (int i = 0; i < calendarViews.length; i++) {
                calendarViews[i].switchStyle(style);
            }
    }

    public void backTodayCalendarViews() {
        if (calendarViews != null)
            for (int i = 0; i < calendarViews.length; i++) {
                calendarViews[i].backToday();
            }
    }
}
