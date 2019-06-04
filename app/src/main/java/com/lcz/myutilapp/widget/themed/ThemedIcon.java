package com.lcz.myutilapp.widget.themed;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.mikepenz.iconics.view.IconicsImageView;

/**
 * desc TODO
 * Created by lcz on 2019/5/17.
 */
public class ThemedIcon extends IconicsImageView {
    public ThemedIcon(Context context) {
        this(context, null);
    }

    public ThemedIcon(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThemedIcon(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
