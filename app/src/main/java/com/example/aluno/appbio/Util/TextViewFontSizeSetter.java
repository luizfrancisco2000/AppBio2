package com.example.aluno.appbio.Util;

import android.support.annotation.NonNull;
import android.widget.TextView;

import butterknife.ButterKnife;

public class TextViewFontSizeSetter {

    public static final ButterKnife.Setter<TextView, Float> SET_FONT_SIZE = new ButterKnife.Setter<TextView, Float>() {

        @Override
        public void set(@NonNull TextView view, Float font_size, int index) {
            view.setTextSize(font_size);
        }
    };


}
