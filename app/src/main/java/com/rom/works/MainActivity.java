package com.rom.works;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rom.works.dto.BaseMeaning;
import com.rom.works.dto.DefinitionResponse;
import com.rom.works.dto.Meaning;
import com.rom.works.entity.Definition;
import com.rom.works.helper.DefinitionResponseMeaningResolver;
import com.rom.works.listener.DefinitionEventListener;
import com.rom.works.listener.DictionaryApiClientEventListener;
import com.rom.works.service.DictionaryServiceImpl;
import com.rom.works.service.UserDefinitionDatabaseHelper;
import com.rom.works.ui.notifications.NotificationIssuer;

import lombok.NonNull;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private AlertDialog definitionAlertDialog;
    private DictionaryServiceImpl dictionaryService;
    private UserDefinitionDatabaseHelper databaseHelper;
    private NotificationIssuer notificationIssuer;
    private EditText addDefinitionWord;
    private EditText addDefinitionMeaning;
    private EditText addDefinitionExample;
    public static DefinitionEventListener definitionEventListener;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dictionaryService = new DictionaryServiceImpl();
        databaseHelper = new UserDefinitionDatabaseHelper(this);
        notificationIssuer = new NotificationIssuer(this);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        definitionAlertDialog = setupDefinitionAlertDialog().create();
        setupAddDefinitionButton();
        // notificationIssuer.generateNotification();
    }

    private AlertDialog.Builder setupDefinitionAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View inflate = inflater.inflate(R.layout.definition_add_layout, null);
        builder.setView(inflate)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Definition definition = new Definition();
                        definition.setWord(addDefinitionWord.getText().toString());
                        definition.setMeaning(addDefinitionMeaning.getText().toString());
                        definition.setExample(addDefinitionExample.getText().toString());
                        databaseHelper.addDefinition(definition);
                        definitionEventListener.onAdd(definition);
                        clearDialogFields();
                    }
                })
                .setNeutralButton("Pull Definition", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dictionaryService.getDefinitionList(addDefinitionWord.getText().toString(), getListener());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        definitionAlertDialog.cancel();
                        clearDialogFields();
                    }
                });
        addDefinitionWord = inflate.findViewById(R.id.add_definition_name);
        addDefinitionMeaning = inflate.findViewById(R.id.add_definition_meaning);
        addDefinitionExample = inflate.findViewById(R.id.add_definition_example);
        return builder;
    }

    private void clearDialogFields() {
        addDefinitionWord.setText(null);
        addDefinitionExample.setText(null);
        addDefinitionMeaning.setText(null);
    }

    private DictionaryApiClientEventListener getListener() {
        return new DictionaryApiClientEventListener() {
            @Override
            public void onSuccess(Call call, DefinitionResponse[] responses) {
                DefinitionResponse definitionResponse = responses[0];
                Meaning meaning = definitionResponse.getMeaning();
                @NonNull BaseMeaning firstAvailableMeaning = DefinitionResponseMeaningResolver.getFirstAvailableMeaning(meaning);
                addDefinitionMeaning.setText(firstAvailableMeaning.getDefinition());
                addDefinitionExample.setText(firstAvailableMeaning.getExample());
                definitionAlertDialog.show();
            }

            @Override
            public void onError(Call call, Throwable t) {
                Toast.makeText(MainActivity.this, "Sorry pal, we couldn't find definitions for the word you were looking for.", Toast.LENGTH_LONG).show();
                definitionAlertDialog.show();
            }
        };
    }

    private void setupAddDefinitionButton() {
        final FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                definitionAlertDialog.show();
            }
        });
    }
}
