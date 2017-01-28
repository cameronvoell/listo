package com.cameronvoell.listo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.cameronvoell.listo.R;
import com.cameronvoell.listo.model.FrequencyWord;
import com.cameronvoell.listo.model.ReviewWordsSession;
import com.cameronvoell.listo.model.SavedWord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
	private static final int DATABASE_VERSION = 15;

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

	//Verb Conjugation Database Helper Strings
	public static final String KEY_SPANISH_INFINITIVO = "KEY_SPANISH_INFINITIVO";
	public static final String KEY_INFINITIVO_INGLES = "KEY_INFINITIVO_INGLES";
	public static final String KEY_GERUND = "KEY_GERUND";
	public static final String KEY_GERUND_INGLES = "KEY_GERUND_INGLES";
	public static final String KEY_PAST_PARTICIPLE = "KEY_PAST_PARTICIPLE";
	public static final String KEY_PAST_PARTICPLE_INGLES = "KEY_PAST_PARTICPLE_INGLES";

	//tense 1
	public static final String KEY_INDICATIVE_PRESENT_INGLES = "KEY_INDICATIVE_PRESENT_INGLES";
	public static final String KEY_INDICATIVE_PRESENT_YO = "KEY_INDICATIVE_PRESENT_YO";
	public static final String KEY_INDICATIVE_PRESENT_TU = "KEY_INDICATIVE_PRESENT_TU";
	public static final String KEY_INDICATIVE_PRESENT_EL = "KEY_INDICATIVE_PRESENT_EL";
	public static final String KEY_INDICATIVE_PRESENT_NOS = "KEY_INDICATIVE_PRESENT_NOS";
	public static final String KEY_INDICATIVE_PRESENT_VOS = "KEY_INDICATIVE_PRESENT_VOS";
	public static final String KEY_INDICATIVE_PRESENT_ELLOS = "KEY_INDICATIVE_PRESENT_ELLOS";

	//tense 2
	public static final String KEY_INDICATIVE_FUTURO_INGLES = "KEY_INDICATIVE_FUTURO_INGLES";
	public static final String KEY_INDICATIVE_FUTURO_YO = "KEY_INDICATIVE_FUTURO_YO";
	public static final String KEY_INDICATIVE_FUTURO_TU = "KEY_INDICATIVE_FUTURO_TU";
	public static final String KEY_INDICATIVE_FUTURO_EL = "KEY_INDICATIVE_FUTURO_EL";
	public static final String KEY_INDICATIVE_FUTURO_NOS = "KEY_INDICATIVE_FUTURO_NOS";
	public static final String KEY_INDICATIVE_FUTURO_VOS = "KEY_INDICATIVE_FUTURO_VOS";
	public static final String KEY_INDICATIVE_FUTURO_ELLOS = "KEY_INDICATIVE_FUTURO_ELLOS";

	//tense 3
	public static final String KEY_INDICATIVE_IMPERFECTO_INGLES = "INDICATIVE_IMPERFECTO_INGLES";
	public static final String KEY_INDICATIVE_IMPERFECTO_YO = "KEY_INDICATIVE_IMPERFECTO_YO";
	public static final String KEY_INDICATIVE_IMPERFECTO_TU = "KEY_INDICATIVE_IMPERFECTO_TU";
	public static final String KEY_INDICATIVE_IMPERFECTO_EL = "KEY_INDICATIVE_IMPERFECTO_EL";
	public static final String KEY_INDICATIVE_IMPERFECTO_NOS = "KEY_INDICATIVE_IMPERFECTO_NOS";
	public static final String KEY_INDICATIVE_IMPERFECTO_VOS = "KEY_INDICATIVE_IMPERFECTO_VOS";
	public static final String KEY_INDICATIVE_IMPERFECTO_ELLOS = "KEY_INDICATIVE_IMPERFECTO_ELLOS";

	//tense 4
	public static final String KEY_INDICATIVE_PRETERITO_INGLES = "INDICATIVE_PRETERITO_INGLES";
	public static final String KEY_INDICATIVE_PRETERITO_YO = "KEY_INDICATIVE_PRETERITO_YO";
	public static final String KEY_INDICATIVE_PRETERITO_TU = "KEY_INDICATIVE_PRETERITO_TU";
	public static final String KEY_INDICATIVE_PRETERITO_EL = "KEY_INDICATIVE_PRETERITO_EL";
	public static final String KEY_INDICATIVE_PRETERITO_NOS = "KEY_INDICATIVE_PRETERITO_NOS";
	public static final String KEY_INDICATIVE_PRETERITO_VOS = "KEY_INDICATIVE_PRETERITO_VOS";
	public static final String KEY_INDICATIVE_PRETERITO_ELLOS = "KEY_INDICATIVE_PRETERITO_ELLOS";

	//tense 5
	public static final String KEY_INDICATIVE_CONDITIONAL_INGLES = "INDICATIVE_CONDITIONAL_INGLES";
	public static final String KEY_INDICATIVE_CONDITIONAL_YO = "KEY_INDICATIVE_CONDITIONAL_YO";
	public static final String KEY_INDICATIVE_CONDITIONAL_TU = "KEY_INDICATIVE_CONDITIONAL_TU";
	public static final String KEY_INDICATIVE_CONDITIONAL_EL = "KEY_INDICATIVE_CONDITIONAL_EL";
	public static final String KEY_INDICATIVE_CONDITIONAL_NOS = "KEY_INDICATIVE_CONDITIONAL_NOS";
	public static final String KEY_INDICATIVE_CONDITIONAL_VOS = "KEY_INDICATIVE_CONDITIONAL_VOS";
	public static final String KEY_INDICATIVE_CONDITIONAL_ELLOS = "KEY_INDICATIVE_CONDITIONAL_ELLOS";

	//tense 6
	public static final String KEY_SUBJUNCTIVO_PRESENT_INGLES = "KEY_SUBJUNCTIVO_PRESENT_INGLES";
	public static final String KEY_SUBJUNCTIVO_PRESENT_YO = "KEY_SUBJUNCTIVO_PRESENT_YO";
	public static final String KEY_SUBJUNCTIVO_PRESENT_TU = "KEY_SUBJUNCTIVO_PRESENT_TU";
	public static final String KEY_SUBJUNCTIVO_PRESENT_EL = "KEY_SUBJUNCTIVO_PRESENT_EL";
	public static final String KEY_SUBJUNCTIVO_PRESENT_NOS = "KEY_SUBJUNCTIVO_PRESENT_NOS";
	public static final String KEY_SUBJUNCTIVO_PRESENT_VOS = "KEY_SUBJUNCTIVO_PRESENT_VOS";
	public static final String KEY_SUBJUNCTIVO_PRESENT_ELLOS = "KEY_SUBJUNCTIVO_PRESENT_ELLOS";

	//tense 7
	public static final String KEY_SUBJUNCTIVO_IMPERFECTO_INGLES = "KEY_SUBJUNCTIVO_IMPERFECTO_INGLES";
	public static final String KEY_SUBJUNCTIVO_IMPERFECTO_YO = "KEY_SUBJUNCTIVO_IMPERFECTO_YO";
	public static final String KEY_SUBJUNCTIVO_IMPERFECTO_TU = "KEY_SUBJUNCTIVO_IMPERFECTO_TU";
	public static final String KEY_SUBJUNCTIVO_IMPERFECTO_EL = "KEY_SUBJUNCTIVO_IMPERFECTO_EL";
	public static final String KEY_SUBJUNCTIVO_IMPERFECTO_NOS = "KEY_SUBJUNCTIVO_IMPERFECTO_NOS";
	public static final String KEY_SUBJUNCTIVO_IMPERFECTO_VOS = "KEY_SUBJUNCTIVO_IMPERFECTO_VOS";
	public static final String KEY_SUBJUNCTIVO_IMPERFECTO_ELLOS = "KEY_SUBJUNCTIVO_IMPERFECTO_ELLOS";

	//tense 8
	public static final String KEY_SUBJUNCTIVO_FUTURO_INGLES = "KEY_SUBJUNCTIVO_FUTURO_INGLES";
	public static final String KEY_SUBJUNCTIVO_FUTURO_YO = "KEY_SUBJUNCTIVO_FUTURO_YO";
	public static final String KEY_SUBJUNCTIVO_FUTURO_TU = "KEY_SUBJUNCTIVO_FUTURO_TU";
	public static final String KEY_SUBJUNCTIVO_FUTURO_EL = "KEY_SUBJUNCTIVO_FUTURO_EL";
	public static final String KEY_SUBJUNCTIVO_FUTURO_NOS = "KEY_SUBJUNCTIVO_FUTURO_NOS";
	public static final String KEY_SUBJUNCTIVO_FUTURO_VOS = "KEY_SUBJUNCTIVO_FUTURO_VOS";
	public static final String KEY_SUBJUNCTIVO_FUTURO_ELLOS = "KEY_SUBJUNCTIVO_FUTURO_ELLOS";

	//tense 9
	public static final String KEY_IMPERATIVO_INGLES = "KEY_IMPERATIVO_INGLES";
	public static final String KEY_IMPERATIVO_1 = "KEY_IMPERATIVO_1";
	public static final String KEY_IMPERATIVO_2 = "KEY_IMPERATIVO_2";
	public static final String KEY_IMPERATIVO_3 = "KEY_IMPERATIVO_3";
	public static final String KEY_IMPERATIVO_4 = "KEY_IMPERATIVO_4";
	public static final String KEY_IMPERATIVO_5 = "KEY_IMPERATIVO_5";
	public static final String KEY_IMPERATIVO_6 = "KEY_IMPERATIVO_6";
	public static final String KEY_IMPERATIVO_7 = "KEY_IMPERATIVO_7";
	public static final String KEY_IMPERATIVO_8 = "KEY_IMPERATIVO_8";

	public static final String TABLE_VERB_CONJUGATIONS = "verb_conjugations";
	private static final String CREATE_VERB_CONJUGATIONS_TABLE = "CREATE TABLE " + TABLE_VERB_CONJUGATIONS
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY, "

			+ KEY_SPANISH_INFINITIVO + " TEXT, "
			+ KEY_INFINITIVO_INGLES + " TEXT, "
			+ KEY_GERUND + " TEXT, "
			+ KEY_GERUND_INGLES + " TEXT, "
			+ KEY_PAST_PARTICIPLE + " TEXT, "
			+ KEY_PAST_PARTICPLE_INGLES + " TEXT, "

			//tense 1
			+ KEY_INDICATIVE_PRESENT_INGLES + " TEXT, "
			+ KEY_INDICATIVE_PRESENT_YO + " TEXT, "
			+ KEY_INDICATIVE_PRESENT_TU + " TEXT, "
			+ KEY_INDICATIVE_PRESENT_EL + " TEXT, "
			+ KEY_INDICATIVE_PRESENT_NOS + " TEXT, "
			+ KEY_INDICATIVE_PRESENT_VOS + " TEXT, "
			+ KEY_INDICATIVE_PRESENT_ELLOS + " TEXT, "

			//tense 2
			+ KEY_INDICATIVE_FUTURO_INGLES + " TEXT, "
			+ KEY_INDICATIVE_FUTURO_YO + " TEXT, "
			+ KEY_INDICATIVE_FUTURO_TU + " TEXT, "
			+ KEY_INDICATIVE_FUTURO_EL + " TEXT, "
			+ KEY_INDICATIVE_FUTURO_NOS + " TEXT, "
			+ KEY_INDICATIVE_FUTURO_VOS + " TEXT, "
			+ KEY_INDICATIVE_FUTURO_ELLOS + " TEXT, "

			//tense 3
			+ KEY_INDICATIVE_IMPERFECTO_INGLES + " TEXT, "
			+ KEY_INDICATIVE_IMPERFECTO_YO + " TEXT, "
			+ KEY_INDICATIVE_IMPERFECTO_TU + " TEXT, "
			+ KEY_INDICATIVE_IMPERFECTO_EL + " TEXT, "
			+ KEY_INDICATIVE_IMPERFECTO_NOS + " TEXT, "
			+ KEY_INDICATIVE_IMPERFECTO_VOS + " TEXT, "
			+ KEY_INDICATIVE_IMPERFECTO_ELLOS + " TEXT, "

			//tense 4
			+ KEY_INDICATIVE_PRETERITO_INGLES + " TEXT, "
			+ KEY_INDICATIVE_PRETERITO_YO + " TEXT, "
			+ KEY_INDICATIVE_PRETERITO_TU + " TEXT, "
			+ KEY_INDICATIVE_PRETERITO_EL + " TEXT, "
			+ KEY_INDICATIVE_PRETERITO_NOS + " TEXT, "
			+ KEY_INDICATIVE_PRETERITO_VOS + " TEXT, "
			+ KEY_INDICATIVE_PRETERITO_ELLOS + " TEXT, "

			//tense 5
			+ KEY_INDICATIVE_CONDITIONAL_INGLES + " TEXT, "
			+ KEY_INDICATIVE_CONDITIONAL_YO + " TEXT, "
			+ KEY_INDICATIVE_CONDITIONAL_TU + " TEXT, "
			+ KEY_INDICATIVE_CONDITIONAL_EL + " TEXT, "
			+ KEY_INDICATIVE_CONDITIONAL_NOS + " TEXT, "
			+ KEY_INDICATIVE_CONDITIONAL_VOS + " TEXT, "
			+ KEY_INDICATIVE_CONDITIONAL_ELLOS + " TEXT, "

			//tense 6
			+ KEY_SUBJUNCTIVO_PRESENT_INGLES + " TEXT, "
			+ KEY_SUBJUNCTIVO_PRESENT_YO + " TEXT, "
			+ KEY_SUBJUNCTIVO_PRESENT_TU + " TEXT, "
			+ KEY_SUBJUNCTIVO_PRESENT_EL + " TEXT, "
			+ KEY_SUBJUNCTIVO_PRESENT_NOS + " TEXT, "
			+ KEY_SUBJUNCTIVO_PRESENT_VOS + " TEXT, "
			+ KEY_SUBJUNCTIVO_PRESENT_ELLOS + " TEXT, "

			//tense 7
			+ KEY_SUBJUNCTIVO_IMPERFECTO_INGLES + " TEXT, "
			+ KEY_SUBJUNCTIVO_IMPERFECTO_YO + " TEXT, "
			+ KEY_SUBJUNCTIVO_IMPERFECTO_TU + " TEXT, "
			+ KEY_SUBJUNCTIVO_IMPERFECTO_EL + " TEXT, "
			+ KEY_SUBJUNCTIVO_IMPERFECTO_NOS + " TEXT, "
			+ KEY_SUBJUNCTIVO_IMPERFECTO_VOS + " TEXT, "
			+ KEY_SUBJUNCTIVO_IMPERFECTO_ELLOS + " TEXT, "

			//tense 8
			+ KEY_SUBJUNCTIVO_FUTURO_INGLES + " TEXT, "
			+ KEY_SUBJUNCTIVO_FUTURO_YO + " TEXT, "
			+ KEY_SUBJUNCTIVO_FUTURO_TU + " TEXT, "
			+ KEY_SUBJUNCTIVO_FUTURO_EL + " TEXT, "
			+ KEY_SUBJUNCTIVO_FUTURO_NOS + " TEXT, "
			+ KEY_SUBJUNCTIVO_FUTURO_VOS + " TEXT, "
			+ KEY_SUBJUNCTIVO_FUTURO_ELLOS + " TEXT, "

			//tense 9
			+ KEY_IMPERATIVO_INGLES + " TEXT, "
			+ KEY_IMPERATIVO_1 + " TEXT, "
			+ KEY_IMPERATIVO_2 + " TEXT, "
			+ KEY_IMPERATIVO_3 + " TEXT, "
			+ KEY_IMPERATIVO_4 + " TEXT, "
			+ KEY_IMPERATIVO_5 + " TEXT, "
			+ KEY_IMPERATIVO_6 + " TEXT, "
			+ KEY_IMPERATIVO_7 + " TEXT, "
			+ KEY_IMPERATIVO_8 + " TEXT " + ")";

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
		db.execSQL(CREATE_VERB_CONJUGATIONS_TABLE);

		initiateFrequencyTableFromText(mContext, db);
		initiateVerbConjugationsTableFromText(mContext, db);
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
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_VERB_CONJUGATIONS);

		db.execSQL(CREATE_FREQUENCY_TABLE);
		db.execSQL(CREATE_SAVED_WORDS_TABLE);
		db.execSQL(CREATE_VERB_CONJUGATIONS_TABLE);

		initiateFrequencyTableFromText(mContext, db);
		initiateVerbConjugationsTableFromText(mContext, db);
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

	public int getNumWordsMemorized() {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "SELECT * FROM " + TABLE_SAVED_WORDS
				+ " WHERE " + KEY_MEMORIZED + " = 1";

		Cursor c = db.rawQuery(query, null);
		int numMemorized = 0;
		while (c.moveToNext()) {
			numMemorized++;
		}
		return numMemorized;
	}

	public int getNumWordsNotYetMemorized() {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "SELECT * FROM " + TABLE_SAVED_WORDS
				+ " WHERE " + KEY_MEMORIZED + " <> 1";

		Cursor c = db.rawQuery(query, null);
		int num = 0;
		while (c.moveToNext()) {
			num++;
		}
		return num;
	}

	public int getNumWordsFromTop5000() {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "SELECT * FROM " + TABLE_SAVED_WORDS
				+ " WHERE " + KEY_FREQ + " <> 9999";

		Cursor c = db.rawQuery(query, null);
		int num = 0;
		while (c.moveToNext()) {
			num++;
		}
		return num;
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
		values.put(KEY_EXAMPLE, "EMPTY");
		values.put(KEY_HINT, "EMPTY");
		values.put(KEY_MEMORIZED, 0);
		values.put(KEY_MEMORY_STRENGTH, 0);
		values.put(KEY_NUM_TIMES_REVIEWED, 0);
		values.put(KEY_NUM_TIMES_INCORRECT, 0);

		SQLiteDatabase db = this.getWritableDatabase();
		db.insert(TABLE_SAVED_WORDS, null, values);
	}

	public void deleteSavedWord(String spanishWord) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_SAVED_WORDS, KEY_SPANISH_WORD + "=\"" + spanishWord + "\"", null);
	}

	public int getNumWordsSavedToday() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_SAVED_WORDS, null);
		int counter = 0;
		while (c.moveToNext()) {
			String s = c.getString(c.getColumnIndex(KEY_ADDED_DATE));
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
			Date date = new Date();
			try {
				Date added = dateFormat.parse(s);
				int dateDay = date.getDay();
				int addedDay = added.getDay();
				int dateMonth = date.getMonth();
				int addedMonth = added.getMonth();
				int dateYear = date.getYear();
				int addedYear = added.getYear();
				if(dateDay == addedDay && dateMonth == addedMonth && dateYear == addedYear) {
					counter++;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return counter;
	}

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
				values.put(KEY_EXAMPLE, "EMPTY");
				values.put(KEY_HINT, "EMPTY");
				values.put(KEY_MEMORIZED, 0);
				values.put(KEY_MEMORY_STRENGTH, 0);
				values.put(KEY_NUM_TIMES_REVIEWED, 0);
				values.put(KEY_NUM_TIMES_INCORRECT, 0);

				db.insert(TABLE_SAVED_WORDS, null, values);
			}
		}
	}

	public ArrayList<SavedWord> getListOfWordsToReview(int numWords) {

		SQLiteDatabase db = this.getWritableDatabase();

		String query = "SELECT * FROM " + TABLE_SAVED_WORDS
				+ " WHERE " + KEY_MEMORIZED + " <> 1"
				+ " ORDER BY " + KEY_FREQ + " ASC";

		Cursor c = db.rawQuery(query, null);
		ArrayList<SavedWord> allWords = new ArrayList<>();
		while (c.moveToNext()) {
			allWords.add(cursorToSavedWord(c));
		}
		Collections.shuffle(allWords);

		return allWords.size() < numWords ? allWords : new ArrayList<>(allWords.subList(0, 20));
	}

	public void updateWordReviewData(SavedWord savedWord, Integer attemptsNeeded) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date reviewedDate = new Date();
		String reviewedDateFormatted = dateFormat.format(reviewedDate);

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_SPANISH_WORD, savedWord.getmWord());
		values.put(KEY_ENG_DEF, savedWord.getmEng());
		values.put(KEY_TYPE, savedWord.getmType());
		values.put(KEY_FREQ, savedWord.getmFrequency());
		values.put(KEY_ADDED_DATE, savedWord.getmAddedDate());
		values.put(KEY_EXAMPLE, savedWord.getmExample());
		values.put(KEY_HINT, savedWord.getmHint());
		values.put(KEY_MEMORIZED, savedWord.ismIsMemorized() ? 1 : 0);
		values.put(KEY_MEMORY_STRENGTH, savedWord.getmMemoryStrength());
		values.put(KEY_NUM_TIMES_REVIEWED, savedWord.getmNumTimesReviewed() + 1);
		values.put(KEY_NUM_TIMES_INCORRECT, savedWord.getmNumTimesIncorrect() + attemptsNeeded - 1);
		values.put(KEY_LAST_REVIEWED_DATE, reviewedDateFormatted);
		String WHERE = KEY_SPANISH_WORD + "=\"" + savedWord.getmWord() + "\"";
		db.update(TABLE_SAVED_WORDS, values, WHERE, null);
	}

	public void updateWordReviewDataMemorized(SavedWord savedWord, Integer attemptsNeeded) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date reviewedDate = new Date();
		String reviewedDateFormatted = dateFormat.format(reviewedDate);

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_SPANISH_WORD, savedWord.getmWord());
		values.put(KEY_ENG_DEF, savedWord.getmEng());
		values.put(KEY_TYPE, savedWord.getmType());
		values.put(KEY_FREQ, savedWord.getmFrequency());
		values.put(KEY_ADDED_DATE, savedWord.getmAddedDate());
		values.put(KEY_EXAMPLE, savedWord.getmExample());
		values.put(KEY_HINT, savedWord.getmHint());
		values.put(KEY_MEMORIZED, 1);
		values.put(KEY_MEMORY_STRENGTH, savedWord.getmMemoryStrength());
		values.put(KEY_NUM_TIMES_REVIEWED, savedWord.getmNumTimesReviewed() + 1);
		values.put(KEY_NUM_TIMES_INCORRECT, savedWord.getmNumTimesIncorrect() + attemptsNeeded - 1);
		values.put(KEY_LAST_REVIEWED_DATE, reviewedDateFormatted);
		String WHERE = KEY_SPANISH_WORD + "=\"" + savedWord.getmWord() + "\"";
		db.update(TABLE_SAVED_WORDS, values, WHERE, null);
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

	private void initiateVerbConjugationsTableFromText(Context mContext, SQLiteDatabase db) {
		Scanner s = new Scanner(mContext.getResources().openRawResource(R.raw.verb_conjugations_sorted));
		ArrayList<VerbConjugationRow> verbConjugationRows = new ArrayList<>();

		String workingInfinitive = "";
		VerbConjugationRow row = new VerbConjugationRow("");
		int verbTenseLineCounter = 1;

		try {
			while (s.hasNext()) {
				String verbTense = s.nextLine();
				if (TextUtils.isEmpty(verbTense) && s.hasNext()) {
					verbTense = s.nextLine();
				}
				String[] fields = verbTense.split("\",\"");
				String infinitive = "";
				try {
					infinitive = fields[0].substring(1, fields[0].length());

				} catch (Exception e) {
					String hello = new String();
				}
				if (!infinitive.equals(workingInfinitive)) {
					row = new VerbConjugationRow(infinitive);
					workingInfinitive = infinitive;
					verbTenseLineCounter = 1;
				}
				switch (verbTenseLineCounter) {
					case 1:
						row.setInfinitivoIngles(fields[1]);
						row.setGerund(fields[13]);
						row.setGerundIngles(fields[14]);
						row.setPastParticiple(fields[15]);
						row.setPastParticipleIngles(fields[16].substring(0, fields[16].length() - 1));

						//Imperative Affirmative
						row.setImperativeIngles(fields[6]);
						row.setImperative1(fields[8]);
						row.setImperative2(fields[9]);
						row.setImperative3(fields[11]);
						row.setImperative4(fields[12]);
						break;
					case 2:
						//Imperative Negative
						row.setImperative5(fields[8]);
						row.setImperative6(fields[9]);
						row.setImperative7(fields[11]);
						row.setImperative8(fields[12]);
						break;
					case 4:
						//Conditional
						row.setIndicativeConditionalIngles(fields[6]);
						row.setIndicativeConditionalYo(fields[7]);
						row.setIndicativeConditionalTu(fields[8]);
						row.setIndicativeConditionalEl(fields[9]);
						row.setIndicativeConditionalNos(fields[10]);
						row.setIndicativeConditionalVos(fields[11]);
						row.setIndicativeConditionalEllos(fields[12]);
						break;
					case 6:
						//Futuro
						row.setIndicativeFuturoIngles(fields[6]);
						row.setIndicativeFuturoYo(fields[7]);
						row.setIndicativeFuturoTu(fields[8]);
						row.setIndicativeFuturoEl(fields[9]);
						row.setIndicativeFuturoNos(fields[10]);
						row.setIndicativeFuturoVos(fields[11]);
						row.setIndicativeFuturoEllos(fields[12]);
						break;
					case 7:
						//Imperfect
						row.setIndicativeImperfectoIngles(fields[6]);
						row.setIndicativeImperfectoYo(fields[7]);
						row.setIndicativeImperfectoTu(fields[8]);
						row.setIndicativeImperfectoEl(fields[9]);
						row.setIndicativeImperfectoNos(fields[10]);
						row.setIndicativeImperfectoVos(fields[11]);
						row.setIndicativeImperfectoEllos(fields[12]);
						break;
					case 10:
						//Presente
						row.setIndicativePresentIngles(fields[6]);
						row.setIndicativePresentYo(fields[7]);
						row.setIndicativePresentTu(fields[8]);
						row.setIndicativePresentEl(fields[9]);
						row.setIndicativePresentNos(fields[10]);
						row.setIndicativePresentVos(fields[11]);
						row.setIndicativePresentEllos(fields[12]);
						break;
					case 12:
						//Preterito
						row.setIndicativePreteritoIngles(fields[6]);
						row.setIndicativePreteritoYo(fields[7]);
						row.setIndicativePreteritoTu(fields[8]);
						row.setIndicativePreteritoEl(fields[9]);
						row.setIndicativePreteritoNos(fields[10]);
						row.setIndicativePreteritoVos(fields[11]);
						row.setIndicativePreteritoEllos(fields[12]);
						break;
					case 14:
						//Subjunctive Future
						row.setSubjunctivoFuturoIngles(fields[6]);
						row.setSubjunctivoFuturoYo(fields[7]);
						row.setSubjunctivoFuturoTu(fields[8]);
						row.setSubjunctivoFuturoEl(fields[9]);
						row.setSubjunctivoFuturoNos(fields[10]);
						row.setSubjunctivoFuturoVos(fields[11]);
						row.setSubjunctivoFuturoEllos(fields[12]);
						break;
					case 15:
						//Subjunctive Imperfecto
						row.setSubjunctivoImperfectoIngles(fields[6]);
						row.setSubjunctivoImperfectoYo(fields[7]);
						row.setSubjunctivoImperfectoTu(fields[8]);
						row.setSubjunctivoImperfectoEl(fields[9]);
						row.setSubjunctivoImperfectoNos(fields[10]);
						row.setSubjunctivoImperfectoVos(fields[11]);
						row.setSubjunctivoImperfectoEllos(fields[12]);
						break;
					case 18:
						//Subjunctive Present
						row.setSubjunctivoPresentIngles(fields[6]);
						row.setSubjunctivoPresentYo(fields[7]);
						row.setSubjunctivoPresentTu(fields[8]);
						row.setSubjunctivoPresentEl(fields[9]);
						row.setSubjunctivoPresentNos(fields[10]);
						row.setSubjunctivoPresentVos(fields[11]);
						row.setSubjunctivoPresentEllos(fields[12]);
						verbConjugationRows.add(row);
						break;
					default:
						//nada
						break;
				}

				verbTenseLineCounter++;

			}
		} finally {
			s.close();
		}

		for (VerbConjugationRow verbRow: verbConjugationRows) {

			ContentValues values = new ContentValues();

			values.put(KEY_SPANISH_INFINITIVO, verbRow.getInfinitivo());
			values.put(KEY_INFINITIVO_INGLES, verbRow.getInfinitivoIngles());
			values.put(KEY_GERUND, verbRow.getGerund());
			values.put(KEY_GERUND_INGLES, verbRow.getGerundIngles());
			values.put(KEY_PAST_PARTICIPLE, verbRow.getPastParticiple());
			values.put(KEY_PAST_PARTICPLE_INGLES, verbRow.getPastParticipleIngles());

			//tense 1
			values.put(KEY_INDICATIVE_PRESENT_INGLES, verbRow.getIndicativePresentIngles());
			values.put(KEY_INDICATIVE_PRESENT_YO, verbRow.getIndicativePresentYo());
			values.put(KEY_INDICATIVE_PRESENT_TU, verbRow.getIndicativePresentTu());
			values.put(KEY_INDICATIVE_PRESENT_EL, verbRow.getIndicativePresentEl());
			values.put(KEY_INDICATIVE_PRESENT_NOS, verbRow.getIndicativePresentNos());
			values.put(KEY_INDICATIVE_PRESENT_VOS, verbRow.getIndicativePresentVos());
			values.put(KEY_INDICATIVE_PRESENT_ELLOS, verbRow.getIndicativePresentEllos());

			//tense 2
			values.put(KEY_INDICATIVE_FUTURO_INGLES, verbRow.getIndicativeFuturoIngles());
			values.put(KEY_INDICATIVE_FUTURO_YO, verbRow.getIndicativeFuturoYo());
			values.put(KEY_INDICATIVE_FUTURO_TU, verbRow.getIndicativeFuturoTu());
			values.put(KEY_INDICATIVE_FUTURO_EL, verbRow.getIndicativeFuturoEl());
			values.put(KEY_INDICATIVE_FUTURO_NOS, verbRow.getIndicativeFuturoNos());
			values.put(KEY_INDICATIVE_FUTURO_VOS, verbRow.getIndicativeFuturoVos());
			values.put(KEY_INDICATIVE_FUTURO_ELLOS, verbRow.getIndicativeFuturoEllos());

			//tense 3
			values.put(KEY_INDICATIVE_IMPERFECTO_INGLES, verbRow.getIndicativeImperfectoIngles());
			values.put(KEY_INDICATIVE_IMPERFECTO_YO, verbRow.getIndicativeImperfectoYo());
			values.put(KEY_INDICATIVE_IMPERFECTO_TU, verbRow.getIndicativeImperfectoTu());
			values.put(KEY_INDICATIVE_IMPERFECTO_EL, verbRow.getIndicativeImperfectoEl());
			values.put(KEY_INDICATIVE_IMPERFECTO_NOS, verbRow.getIndicativeImperfectoNos());
			values.put(KEY_INDICATIVE_IMPERFECTO_VOS, verbRow.getIndicativeImperfectoVos());
			values.put(KEY_INDICATIVE_IMPERFECTO_ELLOS, verbRow.getIndicativeImperfectoEllos());

			//tense 4
			values.put(KEY_INDICATIVE_PRETERITO_INGLES, verbRow.getIndicativePreteritoIngles());
			values.put(KEY_INDICATIVE_PRETERITO_YO, verbRow.getIndicativePreteritoYo());
			values.put(KEY_INDICATIVE_PRETERITO_TU, verbRow.getIndicativePreteritoTu());
			values.put(KEY_INDICATIVE_PRETERITO_EL, verbRow.getIndicativePreteritoEl());
			values.put(KEY_INDICATIVE_PRETERITO_NOS, verbRow.getIndicativePreteritoNos());
			values.put(KEY_INDICATIVE_PRETERITO_VOS, verbRow.getIndicativePreteritoVos());
			values.put(KEY_INDICATIVE_PRETERITO_ELLOS, verbRow.getIndicativePreteritoEllos());

			//tense 5
			values.put(KEY_INDICATIVE_CONDITIONAL_INGLES, verbRow.getIndicativeConditionalIngles());
			values.put(KEY_INDICATIVE_CONDITIONAL_YO, verbRow.getIndicativeConditionalYo());
			values.put(KEY_INDICATIVE_CONDITIONAL_TU, verbRow.getIndicativeConditionalTu());
			values.put(KEY_INDICATIVE_CONDITIONAL_EL, verbRow.getIndicativeConditionalEl());
			values.put(KEY_INDICATIVE_CONDITIONAL_NOS, verbRow.getIndicativeConditionalNos());
			values.put(KEY_INDICATIVE_CONDITIONAL_VOS, verbRow.getIndicativeConditionalVos());
			values.put(KEY_INDICATIVE_CONDITIONAL_ELLOS, verbRow.getIndicativeConditionalEllos());

			//tense 6
			values.put(KEY_SUBJUNCTIVO_PRESENT_INGLES, verbRow.getSubjunctivoPresentIngles());
			values.put(KEY_SUBJUNCTIVO_PRESENT_YO, verbRow.getSubjunctivoPresentYo());
			values.put(KEY_SUBJUNCTIVO_PRESENT_TU, verbRow.getSubjunctivoPresentTu());
			values.put(KEY_SUBJUNCTIVO_PRESENT_EL, verbRow.getSubjunctivoPresentEl());
			values.put(KEY_SUBJUNCTIVO_PRESENT_NOS, verbRow.getSubjunctivoPresentNos());
			values.put(KEY_SUBJUNCTIVO_PRESENT_VOS, verbRow.getSubjunctivoPresentVos());
			values.put(KEY_SUBJUNCTIVO_PRESENT_ELLOS, verbRow.getSubjunctivoPresentEllos());

			//tense 7
			values.put(KEY_SUBJUNCTIVO_IMPERFECTO_INGLES, verbRow.getSubjunctivoPresentIngles());
			values.put(KEY_SUBJUNCTIVO_IMPERFECTO_YO, verbRow.getSubjunctivoPresentYo());
			values.put(KEY_SUBJUNCTIVO_IMPERFECTO_TU, verbRow.getSubjunctivoPresentTu());
			values.put(KEY_SUBJUNCTIVO_IMPERFECTO_EL, verbRow.getSubjunctivoPresentEl());
			values.put(KEY_SUBJUNCTIVO_IMPERFECTO_NOS, verbRow.getSubjunctivoPresentNos());
			values.put(KEY_SUBJUNCTIVO_IMPERFECTO_VOS, verbRow.getSubjunctivoPresentVos());
			values.put(KEY_SUBJUNCTIVO_IMPERFECTO_ELLOS, verbRow.getSubjunctivoPresentEllos());

			//tense 8
			values.put(KEY_SUBJUNCTIVO_FUTURO_INGLES, verbRow.getSubjunctivoFuturoIngles());
			values.put(KEY_SUBJUNCTIVO_FUTURO_YO, verbRow.getSubjunctivoFuturoYo());
			values.put(KEY_SUBJUNCTIVO_FUTURO_TU, verbRow.getSubjunctivoFuturoTu());
			values.put(KEY_SUBJUNCTIVO_FUTURO_EL, verbRow.getSubjunctivoFuturoEl());
			values.put(KEY_SUBJUNCTIVO_FUTURO_NOS, verbRow.getSubjunctivoFuturoNos());
			values.put(KEY_SUBJUNCTIVO_FUTURO_VOS, verbRow.getSubjunctivoFuturoVos());
			values.put(KEY_SUBJUNCTIVO_FUTURO_ELLOS, verbRow.getSubjunctivoFuturoEllos());

			//tense 9
			values.put(KEY_IMPERATIVO_INGLES, verbRow.getImperativeIngles());
			values.put(KEY_IMPERATIVO_1, verbRow.getImperative1());
			values.put(KEY_IMPERATIVO_2, verbRow.getImperative2());
			values.put(KEY_IMPERATIVO_3, verbRow.getImperative3());
			values.put(KEY_IMPERATIVO_4, verbRow.getImperative4());
			values.put(KEY_IMPERATIVO_5, verbRow.getImperative5());
			values.put(KEY_IMPERATIVO_6, verbRow.getImperative6());
			values.put(KEY_IMPERATIVO_7, verbRow.getImperative7());
			values.put(KEY_IMPERATIVO_8, verbRow.getImperative8());

			db.insert(TABLE_VERB_CONJUGATIONS, null, values);
		}
	}

	public VerbConjugationRow getVerbConjugationsData(String infinitive) {
		SQLiteDatabase db = this.getWritableDatabase();

		String where = " WHERE " + KEY_SPANISH_INFINITIVO + " == \"" + infinitive + "\"";

		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_VERB_CONJUGATIONS + where, null);
		VerbConjugationRow verbInfo = new VerbConjugationRow(infinitive);
		while (c.moveToNext()) {
			verbInfo = convertCursorToVerbRowInfo(c);
		}
		return verbInfo;
	}

	private VerbConjugationRow convertCursorToVerbRowInfo(Cursor c) {
		String spanishInfinitive = c.getString(c.getColumnIndex(KEY_SPANISH_INFINITIVO));

		VerbConjugationRow verbInfo = new VerbConjugationRow(spanishInfinitive);

		verbInfo.setGerund(c.getString(c.getColumnIndex(KEY_GERUND)));
		verbInfo.setPastParticiple(c.getString(c.getColumnIndex(KEY_PAST_PARTICIPLE)));

		//tense 1 - ind present
		verbInfo.setIndicativePresentYo(c.getString(c.getColumnIndex(KEY_INDICATIVE_PRESENT_YO)));
		verbInfo.setIndicativePresentTu(c.getString(c.getColumnIndex(KEY_INDICATIVE_PRESENT_TU)));
		verbInfo.setIndicativePresentEl(c.getString(c.getColumnIndex(KEY_INDICATIVE_PRESENT_EL)));
		verbInfo.setIndicativePresentNos(c.getString(c.getColumnIndex(KEY_INDICATIVE_PRESENT_NOS)));
		verbInfo.setIndicativePresentVos(c.getString(c.getColumnIndex(KEY_INDICATIVE_PRESENT_VOS)));
		verbInfo.setIndicativePresentEllos(c.getString(c.getColumnIndex(KEY_INDICATIVE_PRESENT_ELLOS)));

		//tense 2 - ind preterito
		verbInfo.setIndicativePreteritoYo(c.getString(c.getColumnIndex(KEY_INDICATIVE_PRETERITO_YO)));
		verbInfo.setIndicativePreteritoTu(c.getString(c.getColumnIndex(KEY_INDICATIVE_PRETERITO_TU)));
		verbInfo.setIndicativePreteritoEl(c.getString(c.getColumnIndex(KEY_INDICATIVE_PRETERITO_EL)));
		verbInfo.setIndicativePreteritoNos(c.getString(c.getColumnIndex(KEY_INDICATIVE_PRETERITO_NOS)));
		verbInfo.setIndicativePreteritoVos(c.getString(c.getColumnIndex(KEY_INDICATIVE_PRETERITO_VOS)));
		verbInfo.setIndicativePreteritoEllos(c.getString(c.getColumnIndex(KEY_INDICATIVE_PRETERITO_ELLOS)));

		//tense 3 - ind imperfecto
		verbInfo.setIndicativeImperfectoYo(c.getString(c.getColumnIndex(KEY_INDICATIVE_IMPERFECTO_YO)));
		verbInfo.setIndicativeImperfectoTu(c.getString(c.getColumnIndex(KEY_INDICATIVE_IMPERFECTO_TU)));
		verbInfo.setIndicativeImperfectoEl(c.getString(c.getColumnIndex(KEY_INDICATIVE_IMPERFECTO_EL)));
		verbInfo.setIndicativeImperfectoNos(c.getString(c.getColumnIndex(KEY_INDICATIVE_IMPERFECTO_NOS)));
		verbInfo.setIndicativeImperfectoVos(c.getString(c.getColumnIndex(KEY_INDICATIVE_IMPERFECTO_VOS)));
		verbInfo.setIndicativeImperfectoEllos(c.getString(c.getColumnIndex(KEY_INDICATIVE_IMPERFECTO_ELLOS)));

		//tense 4 - ind futuro
		verbInfo.setIndicativeFuturoYo(c.getString(c.getColumnIndex(KEY_INDICATIVE_FUTURO_YO)));
		verbInfo.setIndicativeFuturoTu(c.getString(c.getColumnIndex(KEY_INDICATIVE_FUTURO_TU)));
		verbInfo.setIndicativeFuturoEl(c.getString(c.getColumnIndex(KEY_INDICATIVE_FUTURO_EL)));
		verbInfo.setIndicativeFuturoNos(c.getString(c.getColumnIndex(KEY_INDICATIVE_FUTURO_NOS)));
		verbInfo.setIndicativeFuturoVos(c.getString(c.getColumnIndex(KEY_INDICATIVE_FUTURO_VOS)));
		verbInfo.setIndicativeFuturoEllos(c.getString(c.getColumnIndex(KEY_INDICATIVE_FUTURO_ELLOS)));

		//tense 5 - ind conditional
		verbInfo.setIndicativeConditionalYo(c.getString(c.getColumnIndex(KEY_INDICATIVE_CONDITIONAL_YO)));
		verbInfo.setIndicativeConditionalTu(c.getString(c.getColumnIndex(KEY_INDICATIVE_CONDITIONAL_TU)));
		verbInfo.setIndicativeConditionalEl(c.getString(c.getColumnIndex(KEY_INDICATIVE_CONDITIONAL_EL)));
		verbInfo.setIndicativeConditionalNos(c.getString(c.getColumnIndex(KEY_INDICATIVE_CONDITIONAL_NOS)));
		verbInfo.setIndicativeConditionalVos(c.getString(c.getColumnIndex(KEY_INDICATIVE_CONDITIONAL_VOS)));
		verbInfo.setIndicativeConditionalEllos(c.getString(c.getColumnIndex(KEY_INDICATIVE_CONDITIONAL_ELLOS)));

		//tense 6 - subj present
		verbInfo.setSubjunctivoPresentYo(c.getString(c.getColumnIndex(KEY_SUBJUNCTIVO_PRESENT_YO)));
		verbInfo.setSubjunctivoPresentTu(c.getString(c.getColumnIndex(KEY_SUBJUNCTIVO_PRESENT_TU)));
		verbInfo.setSubjunctivoPresentEl(c.getString(c.getColumnIndex(KEY_SUBJUNCTIVO_PRESENT_EL)));
		verbInfo.setSubjunctivoPresentNos(c.getString(c.getColumnIndex(KEY_SUBJUNCTIVO_PRESENT_NOS)));
		verbInfo.setSubjunctivoPresentVos(c.getString(c.getColumnIndex(KEY_SUBJUNCTIVO_PRESENT_VOS)));
		verbInfo.setSubjunctivoPresentEllos(c.getString(c.getColumnIndex(KEY_SUBJUNCTIVO_PRESENT_ELLOS)));

		//tense 6 - subj imperfecto
		verbInfo.setSubjunctivoImperfectoYo(c.getString(c.getColumnIndex(KEY_SUBJUNCTIVO_IMPERFECTO_YO)));
		verbInfo.setSubjunctivoImperfectoTu(c.getString(c.getColumnIndex(KEY_SUBJUNCTIVO_IMPERFECTO_TU)));
		verbInfo.setSubjunctivoImperfectoEl(c.getString(c.getColumnIndex(KEY_SUBJUNCTIVO_IMPERFECTO_EL)));
		verbInfo.setSubjunctivoImperfectoNos(c.getString(c.getColumnIndex(KEY_SUBJUNCTIVO_IMPERFECTO_NOS)));
		verbInfo.setSubjunctivoImperfectoVos(c.getString(c.getColumnIndex(KEY_SUBJUNCTIVO_IMPERFECTO_VOS)));
		verbInfo.setSubjunctivoImperfectoEllos(c.getString(c.getColumnIndex(KEY_SUBJUNCTIVO_IMPERFECTO_ELLOS)));

		//tense 8 - subj futuro
		verbInfo.setSubjunctivoFuturoYo(c.getString(c.getColumnIndex(KEY_SUBJUNCTIVO_FUTURO_YO)));
		verbInfo.setSubjunctivoFuturoTu(c.getString(c.getColumnIndex(KEY_SUBJUNCTIVO_FUTURO_TU)));
		verbInfo.setSubjunctivoFuturoEl(c.getString(c.getColumnIndex(KEY_SUBJUNCTIVO_FUTURO_EL)));
		verbInfo.setSubjunctivoFuturoNos(c.getString(c.getColumnIndex(KEY_SUBJUNCTIVO_FUTURO_NOS)));
		verbInfo.setSubjunctivoFuturoVos(c.getString(c.getColumnIndex(KEY_SUBJUNCTIVO_FUTURO_VOS)));
		verbInfo.setSubjunctivoFuturoEllos(c.getString(c.getColumnIndex(KEY_SUBJUNCTIVO_FUTURO_ELLOS)));

		//tense 9 - Imperativo
		verbInfo.setImperative1(c.getString(c.getColumnIndex(KEY_IMPERATIVO_1)));
		verbInfo.setImperative2(c.getString(c.getColumnIndex(KEY_IMPERATIVO_2)));
		verbInfo.setImperative3(c.getString(c.getColumnIndex(KEY_IMPERATIVO_3)));
		verbInfo.setImperative4(c.getString(c.getColumnIndex(KEY_IMPERATIVO_4)));
		verbInfo.setImperative5(c.getString(c.getColumnIndex(KEY_IMPERATIVO_5)));
		verbInfo.setImperative6(c.getString(c.getColumnIndex(KEY_IMPERATIVO_6)));
		verbInfo.setImperative7(c.getString(c.getColumnIndex(KEY_IMPERATIVO_7)));
		verbInfo.setImperative8(c.getString(c.getColumnIndex(KEY_IMPERATIVO_8)));

		return verbInfo;
	}
}
