package kr.co.witches.simplememo.ui.main;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class FavoriteView {

    @IntDef({SELECTED, UNSELECTED, GONE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Visibility {}

    public static final int SELECTED = 0x00000000;

    public static final int UNSELECTED = 0x00000004;

    public static final int GONE = 0x00000008;

}
