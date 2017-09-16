package nuesoft.helpdroid;

import android.content.Context;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.util.DisplayMetrics;

import org.junit.Assert;
import org.junit.Test;

import nuesoft.helpdroid.UI.Unit;

/**
 * Created by mysterious on 9/16/17.
 */

public class UnitTest {

    @Test
    public void dpToPx() {

        Context context = InstrumentationRegistry.getTargetContext();
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        if (metrics.densityDpi == 560) {
            float px = Unit.dpToPx(35, context);
            Assert.assertEquals(px, 122.5, 5);
        }
    }

    @Test
    public void pxToDp() {
        Context context = InstrumentationRegistry.getTargetContext();
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        if (metrics.densityDpi == 560) {
            float px = Unit.pxToDp(100, context);
            Assert.assertEquals(px, 28.571428, 5);
        }
    }
}
