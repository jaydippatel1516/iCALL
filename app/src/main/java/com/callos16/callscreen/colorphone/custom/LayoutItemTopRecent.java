package com.callos16.callscreen.colorphone.custom;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import com.callos16.callscreen.colorphone.R;
import com.callos16.callscreen.colorphone.utils.MyShare;
import com.callos16.callscreen.colorphone.utils.OtherUtils;



public class LayoutItemTopRecent extends LinearLayout {
    public LayoutItemTopRecent(Context context) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);
        int widthScreen = OtherUtils.getWidthScreen(context) / 25;
        int i = (int) (widthScreen * 3.2f);
        TextW textW = new TextW(context);
        textW.setText(R.string.recents);
        textW.setupText(600, 8.0f);
        textW.setPadding(i, widthScreen / 4, 0, widthScreen / 8);
        addView(textW, -1, -2);
        View view = new View(context);
        view.setBackgroundColor(Color.parseColor("#8A8A8E"));
        LayoutParams layoutParams = new LayoutParams(-1, 1);
        layoutParams.setMargins(i, 0, 0, 0);
        addView(view, layoutParams);
        if (MyShare.getTheme(context)) {
            textW.setTextColor(-16777216);
        } else {
            textW.setTextColor(-1);
        }
    }
}
