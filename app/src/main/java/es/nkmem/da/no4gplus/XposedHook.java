package es.nkmem.da.no4gplus;

import android.content.res.XResources;
import android.graphics.drawable.Drawable;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class XposedHook implements IXposedHookInitPackageResources {
    public static final String PACKAGE_SYSTEMUI = "com.android.systemui";

    @Override
    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {
        if (!resparam.packageName.equals(PACKAGE_SYSTEMUI)) {
            return;
        }
        final int lteId = resparam.res.getIdentifier("ic_qs_signal_lte", "drawable", PACKAGE_SYSTEMUI);
        resparam.res.setReplacement(PACKAGE_SYSTEMUI, "drawable", "ic_qs_signal_4g_plus", new XResources.DrawableLoader() {
            @Override
            public Drawable newDrawable(XResources res, int id) throws Throwable {
                // noinspection deprecation
                return res.getDrawable(lteId);
            }
        });
    }
}
