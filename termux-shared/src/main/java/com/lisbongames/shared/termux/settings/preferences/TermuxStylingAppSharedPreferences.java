package com.lisbongames.shared.termux.settings.preferences;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lisbongames.shared.android.PackageUtils;
import com.lisbongames.shared.logger.Logger;
import com.lisbongames.shared.settings.preferences.AppSharedPreferences;
import com.lisbongames.shared.settings.preferences.SharedPreferenceUtils;
import com.lisbongames.shared.termux.TermuxConstants;
import com.lisbongames.shared.termux.TermuxUtils;

public class TermuxStylingAppSharedPreferences extends AppSharedPreferences {

    private static final String LOG_TAG = "TermuxStylingAppSharedPreferences";

    private TermuxStylingAppSharedPreferences(@NonNull Context context) {
        super(context,
            SharedPreferenceUtils.getPrivateSharedPreferences(context,
                TermuxConstants.TERMUX_STYLING_DEFAULT_PREFERENCES_FILE_BASENAME_WITHOUT_EXTENSION),
            SharedPreferenceUtils.getPrivateAndMultiProcessSharedPreferences(context,
                TermuxConstants.TERMUX_STYLING_DEFAULT_PREFERENCES_FILE_BASENAME_WITHOUT_EXTENSION));
    }

    /**
     * Get {@link TermuxStylingAppSharedPreferences}.
     *
     * @param context The {@link Context} to use to get the {@link Context} of the
     *                {@link TermuxConstants#TERMUX_STYLING_PACKAGE_NAME}.
     * @return Returns the {@link TermuxStylingAppSharedPreferences}. This will {@code null} if an exception is raised.
     */
    @Nullable
    public static TermuxStylingAppSharedPreferences build(@NonNull final Context context) {
        Context termuxStylingPackageContext = PackageUtils.getContextForPackage(context, TermuxConstants.TERMUX_STYLING_PACKAGE_NAME);
        if (termuxStylingPackageContext == null)
            return null;
        else
            return new TermuxStylingAppSharedPreferences(termuxStylingPackageContext);
    }

    /**
     * Get {@link TermuxStylingAppSharedPreferences}.
     *
     * @param context The {@link Context} to use to get the {@link Context} of the
     *                {@link TermuxConstants#TERMUX_STYLING_PACKAGE_NAME}.
     * @param exitAppOnError If {@code true} and failed to get package context, then a dialog will
     *                       be shown which when dismissed will exit the app.
     * @return Returns the {@link TermuxStylingAppSharedPreferences}. This will {@code null} if an exception is raised.
     */
    public static TermuxStylingAppSharedPreferences build(@NonNull final Context context, final boolean exitAppOnError) {
        Context termuxStylingPackageContext = TermuxUtils.getContextForPackageOrExitApp(context, TermuxConstants.TERMUX_STYLING_PACKAGE_NAME, exitAppOnError);
        if (termuxStylingPackageContext == null)
            return null;
        else
            return new TermuxStylingAppSharedPreferences(termuxStylingPackageContext);
    }



    public int getLogLevel(boolean readFromFile) {
        if (readFromFile)
            return SharedPreferenceUtils.getInt(mMultiProcessSharedPreferences, TermuxPreferenceConstants.TERMUX_STYLING_APP.KEY_LOG_LEVEL, Logger.DEFAULT_LOG_LEVEL);
        else
            return SharedPreferenceUtils.getInt(mSharedPreferences, TermuxPreferenceConstants.TERMUX_STYLING_APP.KEY_LOG_LEVEL, Logger.DEFAULT_LOG_LEVEL);
    }

    public void setLogLevel(Context context, int logLevel, boolean commitToFile) {
        logLevel = Logger.setLogLevel(context, logLevel);
        SharedPreferenceUtils.setInt(mSharedPreferences, TermuxPreferenceConstants.TERMUX_STYLING_APP.KEY_LOG_LEVEL, logLevel, commitToFile);
    }

}
