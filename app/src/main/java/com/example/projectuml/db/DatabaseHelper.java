package com.example.projectuml.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.projectuml.R;
import com.example.projectuml.model.Target;
import com.example.projectuml.model.TargetState;
import com.example.projectuml.model.TargetType;
import com.example.projectuml.model.Task;
import com.example.projectuml.view.GR_Task_1_fragment;
import com.example.projectuml.view.GR_Task_2_fragment;
import com.example.projectuml.view.GR_Task_3_fragment;
import com.example.projectuml.view.Rule_Fragment;
import com.example.projectuml.view.V_Task_1_fragment;
import com.example.projectuml.view.V_Task_2_fragment;
import com.example.projectuml.view.V_Task_3_fragment;
import com.example.projectuml.view.V_Task_4_fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "umlstore.db";
    private static final int SCHEMA = 3; // версия базы, если решишь что-то изменять, меняй версию на n+1, где n - предыдущая версия базы

    private static final String TABLE_TARGETS = "targets"; // название таблицы с целями в бд
    private static final String TABLE_UNITS = "units"; // название таблицы с юнитами в бд
    private static final String TABLE_TASKS = "tasks"; // название таблицы с тасками в бд
    private static final String TABLE_TRASH = "trash"; // название таблицы с мусором в бд

    //названия столбцов в таблице с таргетами
    private static final String COLUMN_TARGET_ID = "_id";
    private static final String COLUMN_TARGET_NAME = "name";
    private static final String COLUMN_TARGET_TYPE = "type";
    private static final String COLUMN_TARGET_QUANTITY = "quantity";
    private static final String COLUMN_TARGET_HOURS = "hours";
    private static final String COLUMN__TARGET_DAYS = "days";
    private static final String COLUMN_TARGET_START = "start";
    private static final String COLUMN_TARGET_PROGRESS = "progress";
    private static final String COLUMN_TARGET_STATE = "state";

    // названия столбцов в таблице юнитов
    private static final String COLUMN_UNIT_ID = "_id";
    private static final String COLUMN_UNIT_NAME = "name";
    private static final String COLUMN_UNIT_TYPE = "type";

    // названия столбцов в таблице юнитов
    private static final String COLUMN_TASK_ID = "_id";
    private static final String COLUMN_TASK_ID_UNIT = "unitid";
    private static final String COLUMN_TASK_NAME = "name";
    private static final String COLUMN_TASK_TYPE = "type";
    private static final String COLUMN_TASK_QUESTION = "question";
    private static final String COLUMN_TASK_ANSWER = "answer";
    private static final String COLUMN_TASK_STATE = "state";

    // названия столбцов в таблице мусора
    private static final String COLUMN_TRASH_ID = "_id";
    private static final String COLUMN_TRASH_NAME = "name";

    public static String getColumnUnitId() {
        return COLUMN_UNIT_ID;
    }

    public static String getColumnUnitName() {
        return COLUMN_UNIT_NAME;
    }

    public static String getColumnUnitType() {
        return COLUMN_UNIT_TYPE;
    }

    public static String getColumnTaskId() {
        return COLUMN_TASK_ID;
    }

    public static String getColumnTaskIdUnit() {
        return COLUMN_TASK_ID_UNIT;
    }

    public static String getColumnTaskName() {
        return COLUMN_TASK_NAME;
    }

    public static String getColumnTaskType() {
        return COLUMN_TASK_TYPE;
    }

    public static String getColumnTaskQuestion() {
        return COLUMN_TASK_QUESTION;
    }

    public static String getColumnTaskAnswer() {
        return COLUMN_TASK_ANSWER;
    }

    public static String getColumnTaskState() {
        return COLUMN_TASK_STATE;
    }

    public static String getColumnTrashId() {
        return COLUMN_TRASH_ID;
    }

    public static String getColumnTrashName() {
        return COLUMN_TRASH_NAME;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA); // здесь произойдет магия, потом объясню
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //создаем строку-sql запрос создающий бд с целями
        StringBuilder sbTargets = new StringBuilder();
        sbTargets.append("CREATE TABLE ");
        sbTargets.append(TABLE_TARGETS);
        sbTargets.append(" (");
        sbTargets.append(COLUMN_TARGET_ID);
        sbTargets.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
        sbTargets.append(COLUMN_TARGET_NAME);
        sbTargets.append(" TEXT, ");
        sbTargets.append(COLUMN_TARGET_TYPE);
        sbTargets.append(" TEXT, ");
        sbTargets.append(COLUMN_TARGET_QUANTITY);
        sbTargets.append(" INTEGER, ");
        sbTargets.append(COLUMN_TARGET_HOURS);
        sbTargets.append(" INTEGER, ");
        sbTargets.append(COLUMN__TARGET_DAYS);
        sbTargets.append(" INTEGER, ");
        sbTargets.append(COLUMN_TARGET_START);
        sbTargets.append(" TEXT, ");
        sbTargets.append(COLUMN_TARGET_PROGRESS);
        sbTargets.append(" INTEGER, ");
        sbTargets.append(COLUMN_TARGET_STATE);
        sbTargets.append(" TEXT);");

        /*db.execSQL("CREATE TABLE " + TABLE_TARGETS + " (" + COLUMN_TARGET_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TARGET_NAME
                + " TEXT, " + COLUMN_TARGET_TYPE + " TEXT, " + COLUMN_TARGET_QUANTITY + " INTEGER, "
                + COLUMN_TARGET_HOURS + " INTEGER, " + COLUMN__TARGET_DAYS + " INTEGER, "
                + COLUMN_TARGET_START + " TEXT, " + COLUMN_TARGET_PROGRESS + " INTEGER, "
                + COLUMN_TARGET_STATE + " TEXT);");*/

        //создаем строку-sql запрос создающий бд с юнитами
        StringBuilder sbUnits = new StringBuilder();
        sbUnits.append("CREATE TABLE ");
        sbUnits.append(TABLE_UNITS);
        sbUnits.append(" (");
        sbUnits.append(COLUMN_UNIT_ID);
        sbUnits.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
        sbUnits.append(COLUMN_UNIT_NAME);
        sbUnits.append(" TEXT,");
        sbUnits.append(COLUMN_UNIT_TYPE);
        sbUnits.append(" INTEGER");
        sbUnits.append(");");

        //создаем строку-sql запрос создающий бд с тасками
        StringBuilder sbTasks = new StringBuilder();
        sbTasks.append("CREATE TABLE ");
        sbTasks.append(TABLE_TASKS);
        sbTasks.append(" (");
        sbTasks.append(COLUMN_TASK_ID);
        sbTasks.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
        sbTasks.append(COLUMN_TASK_ID_UNIT);
        sbTasks.append(" INTEGER,");
        sbTasks.append(COLUMN_TASK_NAME);
        sbTasks.append(" TEXT,");
        sbTasks.append(COLUMN_TASK_TYPE);
        sbTasks.append(" INTEGER,");
        sbTasks.append(COLUMN_TASK_QUESTION);
        sbTasks.append(" TEXT,");
        sbTasks.append(COLUMN_TASK_ANSWER);
        sbTasks.append(" TEXT,");
        sbTasks.append(COLUMN_TASK_STATE);
        sbTasks.append(" INTEGER");
        sbTasks.append(");");

        //создаем строку-sql запрос создающий бд с юнитами
        StringBuilder sbTrash = new StringBuilder();
        sbTrash.append("CREATE TABLE ");
        sbTrash.append(TABLE_TRASH);
        sbTrash.append(" (");
        sbTrash.append(COLUMN_TRASH_ID);
        sbTrash.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
        sbTrash.append(COLUMN_TRASH_NAME);
        sbTrash.append(" TEXT");
        sbTrash.append(");");

        //выполняем описанные выше запросы
        log(sbTargets);
        db.execSQL(sbTargets.toString());
        log(sbUnits);
        db.execSQL(sbUnits.toString());
        log(sbTasks);
        db.execSQL(sbTasks.toString());
        log(sbTrash);
        db.execSQL(sbTrash.toString());

        insertTestValues(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        log("DROP TABLE IF EXISTS "+ TABLE_TARGETS);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TARGETS);
        log("DROP TABLE IF EXISTS " + TABLE_UNITS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNITS);
        log("DROP TABLE IF EXISTS " + TABLE_TASKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        log("DROP TABLE IF EXISTS " + TABLE_TRASH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRASH);
        onCreate(db);
    }

    private void insertTestValues(SQLiteDatabase db) {
        log(createInsertUnit("Первый юнит", 0));
        db.execSQL(createInsertUnit("Первый юнит", 0));

        log(createInsertUnit("Второй юнит", 1));
        db.execSQL(createInsertUnit("Второй юнит", 1));

        log(createInsertUnit("Третий юнит", 0));
        db.execSQL(createInsertUnit("Третий юнит", 0));

        log(createInsertUnit("Четвертый юнит", 1));
        db.execSQL(createInsertUnit("Четвертый юнит", 1));

        log(createInsertTask(2, "Первое правило для первого юнита", 8, "Toast - тост\nUniversity - университет\nBus - автобус\nPhone - автобус", ""));
        db.execSQL(createInsertTask(2, "Первое правило для первого юнита", 8, "Toast - тост\nUniversity - университет\nBus - автобус\nPhone - автобус", ""));

        log(createInsertTask(2, "Первое задание из первого юнита", 4, "Выберите правильный ответ(toast)?", "toast"));
        db.execSQL(createInsertTask(2, "Первое задание из первого юнита", 4, "Выберите правильный ответ(toast)?", "toast"));

        log(createInsertTask(2, "Второе задание из первого юнита", 5, "Выберите правильный ответ(twitter)?", String.valueOf(R.drawable.i1)));
        db.execSQL(createInsertTask(2, "Второе задание из первого юнита", 5, "Выберите правильный ответ(twitter)?", String.valueOf(R.drawable.i1)));

        log(createInsertTask(2, "Третье задание из первого юнита", 6, String.valueOf(R.drawable.i1), "twitter"));
        db.execSQL(createInsertTask(2, "Третье задание из первого юнита", 6, String.valueOf(R.drawable.i1), "twitter"));

        log(createInsertTask(2, "Четвертое задание из первого юнита", 7, String.valueOf(R.raw.record), "twitter"));
        db.execSQL(createInsertTask(2, "Четвертое задание из первого юнита", 7, String.valueOf(R.raw.record), "twitter"));

        log(createInsertTask(1, "Первое правило для второго юнита", 8, "Первое правило бойцовского клуба - никому не рассказывать о бойцовском клубе", ""));
        db.execSQL(createInsertTask(1, "Первое правило для второго юнита", 8, "Первое правило бойцовского клуба - никому не рассказывать о бойцовском клубе", ""));

        log(createInsertTask(1, "Первое задание из второго юнита", 0, "Ben is little boy. He ... six (is)", "is"));
        db.execSQL(createInsertTask(1, "Первое задание из второго юнита", 0, "Ben is little boy. He ... six (is)", "is"));

        log(createInsertTask(1, "Второе задание из второго юнита", 1, "Ben is little boy. He ... six (is)", "is"));
        db.execSQL(createInsertTask(1, "Второе задание из второго юнита", 1, "Ben is little boy. He ... six (is)", "is"));

        log(createInsertTask(1, "Третье задание из второго юнита", 2, "Ben is little boy. He are six!  (are->is)", "Ben is little boy. He are six"));
        db.execSQL(createInsertTask(1, "Третье задание из второго юнита", 2, "Ben is little boy. He are six!  (are->is)", "Ben is little boy. He are six"));


//        log(createInsertTask(2, "Второе задание из первого юнита", 4, "Выберите правильный ответ(university)?", "university"));
//        db.execSQL(createInsertTask(2, "Второе задание из первого юнита", 4, "Выберите правильный ответ(university)?", "university"));
//
//        log(createInsertTask(2, "Третье задание из первого юнита", 4, "Выберите правильный ответ(bus)?", "bus"));
//        db.execSQL(createInsertTask(2, "Третье задание из первого юнита", 4, "Выберите правильный ответ(bus)?", "bus"));
//
//        log(createInsertTask(2, "Четвертое задание из первого юнита", 4, "Выберите правильный ответ(phone)?", "phone"));
//        db.execSQL(createInsertTask(2, "Четвертое задание из первого юнита", 4, "Выберите правильный ответ(phone)?", "phone"));

        log(createInsertTrash("car"));
        db.execSQL(createInsertTrash("car"));

        log(createInsertTrash("cup"));
        db.execSQL(createInsertTrash("cup"));

        log(createInsertTrash("mother"));
        db.execSQL(createInsertTrash("mother"));

        log(createInsertTrash("conversation"));
        db.execSQL(createInsertTrash("conversation"));

        log(createInsertTrash("code"));
        db.execSQL(createInsertTrash("code"));

        log(createInsertTrash("trash"));
        db.execSQL(createInsertTrash("trash"));

        log(createInsertTrash("ball"));
        db.execSQL(createInsertTrash("ball"));

        log(createInsertTrash("water"));
        db.execSQL(createInsertTrash("water"));

        log(createInsertTrash("alcohol"));
        db.execSQL(createInsertTrash("alcohol"));

        log(createInsertTrash("delicious"));
        db.execSQL(createInsertTrash("delicious"));

        log(createInsertTrash(String.valueOf(R.drawable.i8)));
        db.execSQL(createInsertTrash(String.valueOf(R.drawable.i8)));

        log(createInsertTrash(String.valueOf(R.drawable.i1)));
        db.execSQL(createInsertTrash(String.valueOf(R.drawable.i1)));

        log(createInsertTrash(String.valueOf(R.drawable.i2)));
        db.execSQL(createInsertTrash(String.valueOf(R.drawable.i2)));

        log(createInsertTrash(String.valueOf(R.drawable.i9)));
        db.execSQL(createInsertTrash(String.valueOf(R.drawable.i9)));

        log(createInsertTrash(String.valueOf(R.drawable.i3)));
        db.execSQL(createInsertTrash(String.valueOf(R.drawable.i3)));

        log(createInsertTrash(String.valueOf(R.drawable.i4)));
        db.execSQL(createInsertTrash(String.valueOf(R.drawable.i4)));

        log(createInsertTrash(String.valueOf(R.drawable.i5)));
        db.execSQL(createInsertTrash(String.valueOf(R.drawable.i5)));

        log(createInsertTrash(String.valueOf(R.drawable.i6)));
        db.execSQL(createInsertTrash(String.valueOf(R.drawable.i6)));

        log(createInsertTrash(String.valueOf(R.drawable.i7)));
        db.execSQL(createInsertTrash(String.valueOf(R.drawable.i7)));

        log(createInsertTrash(String.valueOf(R.drawable.i10)));
        db.execSQL(createInsertTrash(String.valueOf(R.drawable.i10)));

        log(createInsertTrash("are"));
        db.execSQL(createInsertTrash("are"));

        log(createInsertTrash("am"));
        db.execSQL(createInsertTrash("am"));

        log(createInsertTrash("was"));
        db.execSQL(createInsertTrash("was"));

        log(createInsertTrash("were"));
        db.execSQL(createInsertTrash("were"));

        log(createInsertTrash("will"));
        db.execSQL(createInsertTrash("will"));

        log(createInsertTrash("been"));
        db.execSQL(createInsertTrash("been"));

        log(createInsertTrash("have benn"));
        db.execSQL(createInsertTrash("have benn"));

        log(createInsertTrash("had been"));
        db.execSQL(createInsertTrash("had been"));

        log(createInsertTrash("have had been"));
        db.execSQL(createInsertTrash("have had been"));

        log(createInsertTrash("had have been"));
        db.execSQL(createInsertTrash("had have been"));
    }

    private String createInsertUnit(String name, int type) {
        return "INSERT INTO " +
                TABLE_UNITS +
                " (" +
                COLUMN_UNIT_NAME +
                "," +
                COLUMN_UNIT_TYPE +
                ") VALUES ('" + name + "'," + String.valueOf(type) + ");";
    }

    private String createInsertTask(int unitid, String name, int type, String question, String answer) {
        return "INSERT INTO " +
                TABLE_TASKS +
                " (" +
                COLUMN_TASK_ID_UNIT + ", " +
                COLUMN_TASK_NAME + ", " +
                COLUMN_TASK_TYPE + ", " +
                COLUMN_TASK_QUESTION + ", " +
                COLUMN_TASK_ANSWER + ", " +
                COLUMN_TASK_STATE +
                ") VALUES (" +
                unitid + ", " +
                "'" + name + "', " +
                type + ", " +
                "'" + question + "', " +
                "'" + answer +
                "', 0" +
                ");";
    }

    private String createInsertTrash(String name) {
        return "INSERT INTO " +
                TABLE_TRASH +
                " (" +
                COLUMN_UNIT_NAME +
                ") VALUES ('" + name + "');";
    }

    public void insertTarget(String name, String type, int quantity, int hours, int days, String start, int progress, String state){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO "+ TABLE_TARGETS +" (" + COLUMN_TARGET_NAME
                + ", " + COLUMN_TARGET_TYPE + ", " + COLUMN_TARGET_QUANTITY + ", " + COLUMN_TARGET_HOURS +
                ", " + COLUMN__TARGET_DAYS + ", " + COLUMN_TARGET_START + ", " + COLUMN_TARGET_PROGRESS +
                ", " + COLUMN_TARGET_STATE + ") VALUES ("+ "'" + name + "', "
                + "'" + type + "', '" + String.valueOf(quantity) +
                "', '" + String.valueOf(hours) + "', '" + String.valueOf(days) +
                "', '" + start + "', '" + String.valueOf(progress) + "', '" + String.valueOf(state) + "');");
        db.close();
    }


    public ArrayList<Target> getTargets() {
        ArrayList<Target> targets = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_TARGETS, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            TargetType type = TargetType.valueOf(cursor.getString(2));
            int quantity = cursor.getInt(3);
            int hours = cursor.getInt(4);;
            int days = cursor.getInt(5);;
            String start = cursor.getString(6);
            int progress = cursor.getInt(7);
            TargetState state = TargetState.valueOf(cursor.getString(8));
            Target target = new Target(id, name, type, quantity, hours, days, start, progress, state);
            targets.add(target);
        }
        cursor.close();
        db.close();
        return  targets;
    }

    public void updateTargetState(int id, String newState) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_TARGETS + " SET " + COLUMN_TARGET_STATE + " = '" + newState + "' WHERE " + COLUMN_TARGET_ID + " = " + id);
        db.close();
    }

    public void updateTargetProgress(String unitName) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_UNIT_ID + ", " + COLUMN_UNIT_TYPE + " FROM " + TABLE_UNITS + " WHERE " + COLUMN_UNIT_NAME + " = '" + unitName + "'", null);
        cursor.moveToFirst();
        int idUnit = cursor.getInt(0);
        int unitType = cursor.getInt(1);
        String SQLRequest = "SELECT " + COLUMN_TARGET_ID + " , " + COLUMN_TARGET_PROGRESS + " , " + COLUMN_TARGET_QUANTITY + " , " + COLUMN_TARGET_START + " , " + COLUMN_TARGET_HOURS + " , " + COLUMN__TARGET_DAYS + " , " + COLUMN_TARGET_TYPE + " FROM " + TABLE_TARGETS + " WHERE " + COLUMN_TARGET_STATE + " = '" + TargetState.NOT_FINISHED.toString() + "'";
        if (unitType == 0) {
            SQLRequest += " AND (" + COLUMN_TARGET_TYPE + " = '" + TargetType.GRAMMAIR_UNIT + "' OR " + COLUMN_TARGET_TYPE + " = '" + TargetType.UNIT  + "')";
        }
        else {
            SQLRequest += " AND (" + COLUMN_TARGET_TYPE + " = '" + TargetType.LEXIQUE_UNIT + "' OR " + COLUMN_TARGET_TYPE + " = '" + TargetType.UNIT + "' OR " + COLUMN_TARGET_TYPE + " = '" + TargetType.WORDS  + "')";
        }
        log(SQLRequest);
        cursor = db.rawQuery(SQLRequest, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int current =  cursor.getInt(1);
            int quantity = cursor.getInt(2);
            if (cursor.getString(6).equals(TargetType.WORDS.toString())) {
                Cursor cursorTask = db.rawQuery("SELECT * FROM " + TABLE_TASKS + " WHERE " + COLUMN_TASK_ID_UNIT + " = " + idUnit, null);
                cursor.moveToFirst();
                current += cursorTask.getCount();
                cursorTask.close();
            } else {
                current++;
            }
            String SQLRequestUpdate = "UPDATE " + TABLE_TARGETS + " SET " + COLUMN_TARGET_PROGRESS + " = '" + current + "' WHERE " + COLUMN_TARGET_ID + " = " + id;
            db.execSQL(SQLRequestUpdate);
            if (current == quantity) {
                GregorianCalendar start = Target.parseStringToDate(cursor.getString(3));
                start.add(Calendar.DAY_OF_MONTH, cursor.getInt(5));
                start.add(Calendar.HOUR_OF_DAY, cursor.getInt(4));
                Date curDate = new Date();
                if (curDate.after(start.getTime())) {
                    updateTargetState(id, TargetState.NOT_SUCCES.toString());
                } else {
                    updateTargetState(id, TargetState.SUCCES.toString());
                }
            }
        }
        cursor.close();
        db.close();
    }

    public int getReadyUnit(int type) {
        SQLiteDatabase db = getReadableDatabase();
        int readyUnits = 0;
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_UNIT_ID + " FROM " + TABLE_UNITS + " WHERE " + COLUMN_UNIT_TYPE + " = " + type, null);
        while(cursor.moveToNext()) {
            int readyTask = 0;
            int idUnit = cursor.getInt(0);
            Cursor cursorTask = db.rawQuery("SELECT " + COLUMN_TASK_STATE + " FROM " + TABLE_TASKS + " WHERE " + COLUMN_TASK_ID_UNIT + " = " + idUnit, null);
            while (cursorTask.moveToNext()) {
                int stateTask = cursorTask.getInt(0);
                if (stateTask == 1) {
                    readyTask++;
                }
            }
            if (readyTask == cursorTask.getCount() && readyTask != 0) {
                readyUnits++;
            }
        }
        cursor.close();
        db.close();
        return readyUnits;
    }

    public int getReadyWords() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_UNITS + " JOIN " + TABLE_TASKS + " ON " +
                TABLE_UNITS + "." + COLUMN_UNIT_ID + " = " + COLUMN_TASK_ID_UNIT + " WHERE " + TABLE_UNITS + "." + COLUMN_UNIT_TYPE +
                " = 1 AND " + COLUMN_TASK_STATE + " = 1", null);
        int words = cursor.getCount();
        cursor.close();
        db.close();
        return words;
    }

    public Cursor getAllUnitsByType(SQLiteDatabase db, int type) {
        log("SELECT * FROM " + DatabaseHelper.TABLE_UNITS + " WHERE " + COLUMN_UNIT_TYPE + "= " + String.valueOf(type));
        return db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_UNITS + " WHERE " + COLUMN_UNIT_TYPE + "= " + String.valueOf(type), null);
    }

    public List<Task> getAllTasksByUnit(SQLiteDatabase db, String unitName) {
        String SQLRequest = "SELECT * FROM " + TABLE_UNITS + " WHERE " + COLUMN_UNIT_NAME + " = " + "'" + unitName + "'";
        log(SQLRequest);
        Cursor csrBuf = db.rawQuery(SQLRequest, null);
        csrBuf.moveToFirst();
        int unitId = csrBuf.getInt(0);
        csrBuf.close();
        SQLRequest = "SELECT * FROM " + TABLE_TASKS + " WHERE " + COLUMN_TASK_ID_UNIT + " = " + String.valueOf(unitId);
        log(SQLRequest);
        csrBuf = db.rawQuery(SQLRequest, null);
        csrBuf.moveToFirst();
        List<Task> result = new ArrayList<Task>();
        while (true) {
            switch (csrBuf.getInt(3)) {
                case 0:
                    result.add(new Task(csrBuf.getString(4), csrBuf.getString(5), GR_Task_1_fragment.class));
                    break;
                case 1:
                    result.add(new Task(csrBuf.getString(4), csrBuf.getString(5), GR_Task_2_fragment.class));
                    break;
                case 2:
                    result.add(new Task(csrBuf.getString(4), csrBuf.getString(5), GR_Task_3_fragment.class));
                    break;
                /*case 3:
                    result.add(new Task(csrBuf.getString(4), csrBuf.getString(5), GR_Task_4_fragment.class));
                    break;*/
                case 4:
                    result.add(new Task(csrBuf.getString(4), csrBuf.getString(5), V_Task_1_fragment.class));
                    break;
                case 5:
                    result.add(new Task(csrBuf.getString(4), csrBuf.getString(5), V_Task_2_fragment.class));
                    break;
                case 6:
                    result.add(new Task(csrBuf.getString(4), csrBuf.getString(5), V_Task_3_fragment.class));
                    break;
                case 7:
                    result.add(new Task(csrBuf.getString(4), csrBuf.getString(5), V_Task_4_fragment.class));
                    break;
                case 8:
                    result.add(new Task(csrBuf.getString(4), csrBuf.getString(5), Rule_Fragment.class));
                    break;
            }
            if (csrBuf.isLast()) {
                break;
            }
            csrBuf.moveToNext();
        }
        csrBuf.close();
        return result;
    }

    public String getTrash(SQLiteDatabase db, int min, int max) {
        log("SELECT * FROM " + TABLE_TRASH);
        Cursor csrBuf = db.rawQuery("SELECT * FROM " + TABLE_TRASH, null);
        csrBuf.move(getIntRandomFromRange(min, max));
        return csrBuf.getString(1);
    }

    //TODO
    public void updateTaskState(SQLiteDatabase db, String question, String answer) {
        log("UPDATE " + TABLE_TASKS + " SET " + COLUMN_TASK_STATE + " = 1  WHERE " + COLUMN_TASK_QUESTION + " = '" + question + "' AND " + COLUMN_TASK_ANSWER + " = ''" + answer);
        db.execSQL("UPDATE " + TABLE_TASKS + " SET " + COLUMN_TASK_STATE + " = 1  WHERE " + COLUMN_TASK_QUESTION + " = '" + question + "' AND " + COLUMN_TASK_ANSWER + " = '" + answer + "'");
        db.close();
    }

    private void log(StringBuilder request) {
        System.out.println("SQL request >>> " + request.toString());
    }

    private void log(String request) {
        System.out.println("SQL request >>> " + request);
    }

    public static int getIntRandomFromRange(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

}
