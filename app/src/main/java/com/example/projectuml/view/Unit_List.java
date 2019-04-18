package com.example.projectuml.view;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.projectuml.MainActivity;
import com.example.projectuml.R;
import com.example.projectuml.db.DatabaseHelper;

public class Unit_List extends AppCompatActivity {

    public static String UNIT_NAME_TAG = "UNIT_NAME_TAG";
    private int TYPE;
    ListView userList;
    TextView header;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit__list);

        Intent intent = getIntent();
        TYPE = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 0);

        header = findViewById(R.id.header);
        userList = findViewById(R.id.list);

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Unit.class);
                userCursor.moveToPosition(position);
                intent.putExtra(UNIT_NAME_TAG, userCursor.getString(1));
                userCursor.moveToFirst();
                startActivity(intent);
            }
        });

        databaseHelper = new DatabaseHelper(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // открываем подключение
        db = databaseHelper.getReadableDatabase();

        //получаем все грамматические юниты
        userCursor = databaseHelper.getAllUnitsByType(db, TYPE);

        // определяем, какие столбцы из курсора будут выводиться в ListView
        String[] headers = new String[]{DatabaseHelper.getColumnUnitName()};

        // создаем адаптер, передаем в него курсор
        userAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                userCursor, headers, new int[]{android.R.id.text1}, 0);

        String result = (TYPE == 0 ? "Грамматические юниты" : "Лексические юниты") +
                "\nНайдено элементов: " +
                String.valueOf(userCursor.getCount());
        header.setText(result);
        userList.setAdapter(userAdapter);
    }
}
