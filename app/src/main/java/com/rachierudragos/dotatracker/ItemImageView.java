package com.rachierudragos.dotatracker;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Dragos on 08-Nov-17.
 */

public class ItemImageView extends android.support.v7.widget.AppCompatImageView implements View.OnClickListener {

    public ItemImageView(Context context) {
        super(context);
        setOnClickListener(this);
    }

    public ItemImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    public ItemImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
