package com.lisbongames.shared.termux.theme;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lisbongames.shared.theme.NightMode;
import com.lisbongames.shared.termux.settings.properties.TermuxPropertyConstants;
import com.lisbongames.shared.termux.settings.properties.TermuxSharedProperties;

public class TermuxThemeUtils {

    /** Get the {@link TermuxPropertyConstants#KEY_NIGHT_MODE} value from the properties file on disk
     * and set it to app wide night mode value. */
    public static void setAppNightMode(@NonNull Context context) {
        NightMode.setAppNightMode(TermuxSharedProperties.getNightMode(context));
    }

    /** Set name as app wide night mode value. */
    public static void setAppNightMode(@Nullable String name) {
        NightMode.setAppNightMode(name);
    }

}
