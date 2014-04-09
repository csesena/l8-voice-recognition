package com.l8smartlight.l8voicerecognition;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private static final int REQUEST_CODE = 1234;
	private ArrayList<String> yellow_words;
	private ArrayList<String> blue_words;
	private ArrayList<String> green_words;
	private ArrayList<String> gray_words;
	private ArrayList<String> pink_words;
	private ArrayList<String> orange_words;
	private ArrayList<String> white_words;
	private ArrayList<String> red_words;
	private ArrayList<String> purple_words;
	private ArrayList<String> shutdown_words;
	private ArrayList<String> clear_words;
	private ArrayList<String> closebt_words;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Define dictionary
		manageDictionary();

		Button speakButton = (Button) findViewById(R.id.buttonSpeak);

		// Disable button if no recognition service is present
		PackageManager pm = getPackageManager();
		List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(
				RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
		if (activities.size() == 0) {
			speakButton.setEnabled(false);
			speakButton.setText(getString(R.string.recog_not_present));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Manage the words to compare with the voice-recognized words (by language)
	 */
	public void manageDictionary() {
		yellow_words = new ArrayList<String>();
		blue_words = new ArrayList<String>();
		green_words = new ArrayList<String>();
		gray_words = new ArrayList<String>();
		pink_words = new ArrayList<String>();
		orange_words = new ArrayList<String>();
		white_words = new ArrayList<String>();
		red_words = new ArrayList<String>();
		purple_words = new ArrayList<String>();
		shutdown_words = new ArrayList<String>();
		clear_words = new ArrayList<String>();
		closebt_words = new ArrayList<String>();

		// If language is Spanish
		if (Locale.getDefault().getDisplayLanguage().compareTo("Spanish") == 0) {
			// Yellow
			yellow_words.add("ama");

			// Blue
			blue_words.add("azu");

			// Green
			green_words.add("ver");

			// Gray
			gray_words.add("gri");

			// Pink
			pink_words.add("ros");

			// Orange
			orange_words.add("nar");

			// White
			white_words.add("bla");

			// Red
			red_words.add("roj");

			// Purple
			purple_words.add("mor");

			// Shut down
			shutdown_words.add("apa");

			// Clear
			clear_words.add("limp");

			// Close
			closebt_words.add("cer");

			// If language is not Spanish
		} else {
			// Yellow
			yellow_words.add("yel");

			// Blue
			blue_words.add("bl");

			// Green
			green_words.add("gree");

			// Gray
			gray_words.add("grey");
			gray_words.add("gray");

			// Pink
			pink_words.add("pin");

			// Orange
			orange_words.add("oran");

			// White
			white_words.add("whi");

			// Red
			red_words.add("red");

			// Purple
			purple_words.add("pur");

			// Shut down
			shutdown_words.add("shut");
			shutdown_words.add("sat");

			// Clear
			clear_words.add("clea");

			// Close
			closebt_words.add("clos");
		}

	}

	/**
	 * Handle the action of the button being clicked
	 */
	public void speakButtonClicked(View v) {
		startVoiceRecognitionActivity();
	}

	/**
	 * Fire an intent to start the voice recognition activity.
	 */
	private void startVoiceRecognitionActivity() {
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
				getString(R.string.l8_voice_recognition));
		startActivityForResult(intent, REQUEST_CODE);
	}

	/**
	 * Get results from voice recognition and light L8
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
			// Populate the wordsList with the String values the recognition
			// engine thought it heard
			ArrayList<String> matches = data
					.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			for (int i = 0; i < matches.size(); i++) {
				System.out.println(matches.get(i));
			}

			Intent intent;
			if (in_array(yellow_words, matches)) {
				intent = new Intent(
						"com.l8smartlight.action.connect",
						Uri.parse("l8://l8smartlight.com/setMatrixColor?color=FFFF00"));
				this.startActivity(intent);
			} else if (in_array(blue_words, matches)) {
				intent = new Intent(
						"com.l8smartlight.action.connect",
						Uri.parse("l8://l8smartlight.com/setMatrixColor?color=1900FF"));
				this.startActivity(intent);
			} else if (in_array(green_words, matches)) {
				intent = new Intent(
						"com.l8smartlight.action.connect",
						Uri.parse("l8://l8smartlight.com/setMatrixColor?color=00FF2A"));
				this.startActivity(intent);
			} else if (in_array(gray_words, matches)) {
				intent = new Intent(
						"com.l8smartlight.action.connect",
						Uri.parse("l8://l8smartlight.com/setMatrixColor?color=454545"));
				this.startActivity(intent);
			} else if (in_array(pink_words, matches)) {
				intent = new Intent(
						"com.l8smartlight.action.connect",
						Uri.parse("l8://l8smartlight.com/setMatrixColor?color=FF00FF"));
				this.startActivity(intent);
			} else if (in_array(orange_words, matches)) {
				intent = new Intent(
						"com.l8smartlight.action.connect",
						Uri.parse("l8://l8smartlight.com/setMatrixColor?color=FF8800"));
				this.startActivity(intent);
			} else if (in_array(white_words, matches)) {
				intent = new Intent(
						"com.l8smartlight.action.connect",
						Uri.parse("l8://l8smartlight.com/setMatrixColor?color=FFFFFF"));
				this.startActivity(intent);
			} else if (in_array(red_words, matches)) {
				intent = new Intent(
						"com.l8smartlight.action.connect",
						Uri.parse("l8://l8smartlight.com/setMatrixColor?color=FF0000"));
				this.startActivity(intent);
			} else if (in_array(purple_words, matches)) {
				intent = new Intent(
						"com.l8smartlight.action.connect",
						Uri.parse("l8://l8smartlight.com/setMatrixColor?color=8400DB"));
				this.startActivity(intent);
			} else if (in_array(shutdown_words, matches)) {
				intent = new Intent("com.l8smartlight.action.connect",
						Uri.parse("l8://l8smartlight.com/shutDown"));
				this.startActivity(intent);
			} else if (in_array(clear_words, matches)) {
				intent = new Intent("com.l8smartlight.action.connect",
						Uri.parse("l8://l8smartlight.com/clear"));
				this.startActivity(intent);
			} else if (in_array(closebt_words, matches)) {
				intent = new Intent("com.l8smartlight.action.connect",
						Uri.parse("l8://l8smartlight.com/closeBtConnection"));
				this.startActivity(intent);
			} else {
				intent = new Intent(
						"com.l8smartlight.action.connect",
						Uri.parse("l8://l8smartlight.com/setMatrixColor?color=FFFFFF"));
				this.startActivity(intent);
			}
		}

	}

	/**
	 * Check if one of the strings inside an ArrayList belongs to another
	 * ArrayList
	 */
	public static boolean in_array(ArrayList<String> haystack,
			ArrayList<String> needles) {
		for (int i = 0; i < haystack.size(); i++) {
			for (int j = 0; j < haystack.size(); j++) {
				if (haystack.get(i).toString().startsWith(needles.get(0))) {
					return true;
				}
			}
		}
		return false;
	}
}
