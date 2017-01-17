package com.cameronvoell.listo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.model.FrequencyWord;
import com.cameronvoell.listo.model.SavedWord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by cameronvoell on 1/15/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

	Context mContext;

	//Used for upgrading the database
	private static final int DATABASE_VERSION = 11;

	//name of our database file
	private static final String DATABASE_NAME = "listo_database";

	//Shared Database Column names
	public static final String KEY_ID = "_id";

	//Frequency Database Helper Strings
	public static final String KEY_FREQ_RANK = "freq_rank";
	public static final String KEY_FREQ_SPANISH_WORD = "freq_span_word";
	public static final String KEY_FREQ_ENG_DEF = "freq_eng_def";
	public static final String KEY_FREQ_TYPE = "freq_type";

	public static final String TABLE_FREQUENCY = "frequency";

	private static final String CREATE_FREQUENCY_TABLE = "CREATE TABLE " + TABLE_FREQUENCY
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ KEY_FREQ_SPANISH_WORD + " TEXT, "
			+ KEY_FREQ_ENG_DEF + " TEXT, "
			+ KEY_FREQ_RANK + " INTEGER, "
			+ KEY_FREQ_TYPE + " TEXT" + ")";

	//Saved words Database Helper Strings
	public static final String KEY_SPANISH_WORD = "spanish_word";
	public static final String KEY_ENG_DEF = "eng_def";
	public static final String KEY_FREQ = "freq";
	public static final String KEY_ADDED_DATE = "added_date";
	public static final String KEY_TYPE = "type";
	public static final String KEY_EXAMPLE = "example";
	public static final String KEY_HINT = "hint";
	public static final String KEY_MEMORIZED = "memory";
	public static final String KEY_MEMORY_STRENGTH = "memory_strength";
	public static final String KEY_LAST_REVIEWED_DATE = "last_reviewed_date";
	public static final String KEY_NUM_TIMES_REVIEWED = "num_times_reviewed";
	public static final String KEY_NUM_TIMES_INCORRECT = "num_times_incorrect";


	public static final String TABLE_SAVED_WORDS = "saved_words";
	private static final String CREATE_SAVED_WORDS_TABLE = "CREATE TABLE " + TABLE_SAVED_WORDS
			+ "("
				+ KEY_ID + " INTEGER PRIMARY KEY, "
				+ KEY_SPANISH_WORD + " TEXT, "
				+ KEY_ENG_DEF + " TEXT, "
				+ KEY_FREQ + " INTEGER, "
				+ KEY_ADDED_DATE + " DATETIME, "
				+ KEY_TYPE + " TEXT, "
				+ KEY_EXAMPLE + " TEXT, "
				+ KEY_HINT + " TEXT, "
				+ KEY_MEMORIZED + " INTEGER, "
				+ KEY_MEMORY_STRENGTH + " INTEGER, "
				+ KEY_LAST_REVIEWED_DATE + " DATETIME, "
				+ KEY_NUM_TIMES_REVIEWED + " INTEGER, "
				+ KEY_NUM_TIMES_INCORRECT + " INTEGER"
			+ ")";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		mContext = context;
	}

	/**
	 * Called when the database is created for the first time. This is where the
	 * creation of tables and the initial population of the tables should happen.
	 *
	 * @param db The database.
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_FREQUENCY_TABLE);
		db.execSQL(CREATE_SAVED_WORDS_TABLE);
		initiateFrequencyTableFromText(mContext, db);
	}

	/**
	 * Called when the database needs to be upgraded. The implementation
	 * should use this method to drop tables, add tables, or do anything else it
	 * needs to upgrade to the new schema version.
	 * <p/>
	 * <p>
	 * The SQLite ALTER TABLE documentation can be found
	 * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
	 * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
	 * you can use ALTER TABLE to rename the old table, then create the new table and then
	 * populate the new table with the contents of the old table.
	 * </p><p>
	 * This method executes within a transaction.  If an exception is thrown, all changes
	 * will automatically be rolled back.
	 * </p>
	 *
	 * @param db         The database.
	 * @param oldVersion The old database version.
	 * @param newVersion The new database version.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FREQUENCY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAVED_WORDS);

		db.execSQL(CREATE_FREQUENCY_TABLE);
		db.execSQL(CREATE_SAVED_WORDS_TABLE);

		initiateFrequencyTableFromText(mContext, db);
	}

	public void initiateFrequencyTableFromText(Context context, SQLiteDatabase db) {
		Scanner s = new Scanner(context.getResources().openRawResource(R.raw.all_words_unordered));
		ArrayList<FrequencyWord> frequencyWords = new ArrayList<>();

		try {
			while (s.hasNext()) {
				String word = s.nextLine();
				String[] words = word.split(" ");
				int freq = Integer.parseInt(words[0]);
				String spanishWord = words[1];
				String type = words[2];
				String def = "";
				for (int i = 3; i < words.length; i++) {
					def = def + " " + words[i];
				}
				frequencyWords.add(new FrequencyWord(freq, spanishWord, def, type));
			}
		} finally {
			s.close();
		}

		for (FrequencyWord word: frequencyWords) {

			ContentValues values = new ContentValues();

			values.put(KEY_FREQ_SPANISH_WORD, word.getmWord());
			values.put(KEY_FREQ_ENG_DEF, word.getmEng());
			values.put(KEY_FREQ_RANK, word.getmFrequency());
			values.put(KEY_FREQ_TYPE, word.getmType());
			db.insert(TABLE_FREQUENCY, null, values);
		}
	}

	public FrequencyWord searchForWord(String spanishWord) {
		if (TextUtils.isEmpty(spanishWord)) return null;

		FrequencyWord word = null;
		String WHERE = " WHERE " + KEY_FREQ_SPANISH_WORD + "=\"" + spanishWord + "\"";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_FREQUENCY + WHERE, null);
		while (c.moveToNext()) {
			word = cursorToFrequencyWord(c);
		}
		return word;
	}

	public FrequencyWord cursorToFrequencyWord (Cursor c) {
		String spanishWord = c.getString(c.getColumnIndex(KEY_FREQ_SPANISH_WORD));
		String englishDef = c.getString(c.getColumnIndex(KEY_FREQ_ENG_DEF));
		int frequency = c.getInt(c.getColumnIndex(KEY_FREQ_RANK));
		String type = c.getString(c.getColumnIndex(KEY_FREQ_TYPE));
		return new FrequencyWord(frequency, spanishWord, englishDef, type);
	}

	public SavedWord cursorToSavedWord (Cursor c) {
		String spanishWord = c.getString(c.getColumnIndex(KEY_SPANISH_WORD));
		String englishDef = c.getString(c.getColumnIndex(KEY_ENG_DEF));
		int frequency = c.getInt(c.getColumnIndex(KEY_FREQ));
		String date = c.getString(c.getColumnIndex(KEY_ADDED_DATE));
		String type = c.getString(c.getColumnIndex(KEY_TYPE));
		String example = c.getString(c.getColumnIndex(KEY_EXAMPLE));
		String hint = c.getString(c.getColumnIndex(KEY_HINT));
		boolean isMemorized = c.getInt(c.getColumnIndex(KEY_MEMORIZED)) == 1;
		int memoryStrength = c.getInt(c.getColumnIndex(KEY_MEMORY_STRENGTH));
		String lastReviewedDate = c.getString(c.getColumnIndex(KEY_LAST_REVIEWED_DATE));
		int numTimesReviewed = c.getInt(c.getColumnIndex(KEY_NUM_TIMES_REVIEWED));
		int numTimesIncorrect = c.getInt(c.getColumnIndex(KEY_NUM_TIMES_INCORRECT));


		SavedWord word = new SavedWord(spanishWord, englishDef, frequency, date, type, example, hint,
										isMemorized, memoryStrength, lastReviewedDate, numTimesReviewed,
										numTimesIncorrect);
		return word;
	}

	public void saveVocabWord(String spanishWord, String englishDef, String wordType, int freq) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date date = new Date();
		String addedDate = dateFormat.format(date);

		ContentValues values = new ContentValues();
		values.put(KEY_SPANISH_WORD, spanishWord);
		values.put(KEY_ENG_DEF, englishDef);
		values.put(KEY_FREQ, freq);
		values.put(KEY_ADDED_DATE, addedDate);
		values.put(KEY_TYPE, wordType);

		SQLiteDatabase db = this.getWritableDatabase();
		db.insert(TABLE_SAVED_WORDS, null, values);
	}

	public void deleteSavedWord(String spanishWord) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_SAVED_WORDS, KEY_SPANISH_WORD + "=\"" + spanishWord + "\"", null);
	}

//	public int getNumWordsSaved() {
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_SAVED_WORDS, null);
//		int counter = 0;
//		while (c.moveToNext()) {
//			String s = c.getString(c.getColumnIndex(KEY_ADDED_DATE));
//			SimpleDateFormat dateFormat = new SimpleDateFormat(
//					"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//			Date date = new Date();
//			try {
//				Date added = dateFormat.parse(s);
//				if(date.getTime() - added.getTime() < 24 * 60 * 60 * 1000) counter++;
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//			//counter++;
//		}
//		return counter;
//	}

	public Cursor getSavedWordsCursor() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_SAVED_WORDS + " ORDER BY " + KEY_ADDED_DATE + " DESC", null);
		return c;
	}

	public Cursor getSavedWordsCursorSortedByFrequency() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_SAVED_WORDS + " ORDER BY " + KEY_FREQ + " ASC", null);
		return c;
	}

	public boolean contains(String word) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = " WHERE " + KEY_SPANISH_WORD + "==\"" + word + "\"";
		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_SAVED_WORDS + where, null);
		int counter = 0;
		while (c.moveToNext()) {
			counter++;
		}
		return counter > 0;
	}

//	public SavedWord getSavedWord(int position) {
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_SAVED_WORDS + " ORDER BY " + KEY_ADDED_DATE + " DESC", null);
//		int counter = 0;
//		while (c.moveToNext()) {
//			if (counter++ == position) {
//				return cursorToSavedWord(c);
//			}
//		}
//		return null;
//	}

	public FrequencyWord searchForWordWithEnglishDef(String englishWord) {
		if (TextUtils.isEmpty(englishWord)) return null;

		FrequencyWord word = null;
		String WHERE = " WHERE " + KEY_FREQ_ENG_DEF + " LIKE \'%" + englishWord + "%\'";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_FREQUENCY + WHERE, null);
		while (c.moveToNext()) {
			FrequencyWord tempWord = cursorToFrequencyWord(c);
			if (word == null || tempWord.getmFrequency() < word.getmFrequency()){
				word = tempWord;
			}
		}
		return word;
	}

	public Cursor getSuggestedWordsCursor() {
		SQLiteDatabase db = this.getWritableDatabase();
		//String where = " WHERE " + KEY_SPANISH_WORD + "==\"" + word + "\"";

		String query = "SELECT * FROM " + TABLE_FREQUENCY
				       + " LEFT JOIN "
				       + TABLE_SAVED_WORDS + " ON "
				       + TABLE_FREQUENCY + "." + KEY_FREQ_SPANISH_WORD + " = "
				       + TABLE_SAVED_WORDS + "." + KEY_SPANISH_WORD
					   + " WHERE " + TABLE_SAVED_WORDS + "." + KEY_SPANISH_WORD + " IS NULL"
					   + " ORDER BY " + TABLE_FREQUENCY + "." + KEY_FREQ_RANK + " ASC"
				       + " LIMIT 200";

		Cursor c = db.rawQuery(query, null);


		return c;
	}

	public void saveVocabWordsWithFreq(HashSet<Integer> checkedIndices) {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "SELECT * FROM " + TABLE_FREQUENCY;
		Cursor c = db.rawQuery(query, null);

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date date = new Date();
		String addedDate = dateFormat.format(date);

		while (c.moveToNext()) {
			FrequencyWord fw = cursorToFrequencyWord(c);
			if (checkedIndices.contains(fw.getmFrequency())) {
				ContentValues values = new ContentValues();
				values.put(KEY_SPANISH_WORD, fw.getmWord());
				values.put(KEY_ENG_DEF, fw.getmEng());
				values.put(KEY_TYPE, fw.getmType());
				values.put(KEY_FREQ, fw.getmFrequency());
				values.put(KEY_ADDED_DATE, addedDate);

				db.insert(TABLE_SAVED_WORDS, null, values);
			}
		}







	}
}
