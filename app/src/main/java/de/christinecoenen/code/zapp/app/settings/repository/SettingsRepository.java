package de.christinecoenen.code.zapp.app.settings.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatDelegate;

import de.christinecoenen.code.zapp.R;

public class SettingsRepository {

	private final Context context;
	private final SharedPreferences preferences;

	public SettingsRepository(Context context) {
		this.context = context.getApplicationContext();

		preferences = PreferenceManager.getDefaultSharedPreferences(this.context);
	}

	public boolean getLockVideosInLandcapeFormat() {
		return preferences.getBoolean(context.getString(R.string.pref_key_detail_landscape), true);
	}

	public boolean getWifiOnly() {
		return preferences.getBoolean(context.getString(R.string.pref_key_wifi_only), true);
	}

	public boolean getEnableSubtitles() {
		return preferences.getBoolean(context.getString(R.string.pref_key_enable_subtitles), false);
	}

	public void setEnableSubtitles(boolean enabled) {
		preferences.edit()
			.putBoolean(context.getString(R.string.pref_key_enable_subtitles), enabled)
			.apply();
	}

	public boolean getIsPlayerZoomed() {
		return preferences.getBoolean(context.getString(R.string.pref_key_player_zoomed), false);
	}

	public void setIsPlayerZoomed(boolean enabled) {
		preferences.edit()
			.putBoolean(context.getString(R.string.pref_key_player_zoomed), enabled)
			.apply();
	}

	public int getUiMode() {
		String uiMode = preferences.getString(context.getString(R.string.pref_key_ui_mode), "0");
		return prefValueToUiMode(uiMode);
	}

	public int prefValueToUiMode(String prefSetting) {
		// TODO: don't use hardcoded values
		switch (prefSetting) {
			case "1":
				// light
				return AppCompatDelegate.MODE_NIGHT_NO;
			case "2":
				// dark
				return AppCompatDelegate.MODE_NIGHT_YES;
			default:
				// default
				return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ?
					AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM :
					AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY;
		}
	}
}
