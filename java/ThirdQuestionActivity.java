package wafoot.becoming.wafoot;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class ThirdQuestionActivity extends AppCompatActivity {

    private TextInputEditText question1;
    private TextInputEditText question2;
    private TextInputEditText question3;

    private FloatingActionButton fabQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_question);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        question1 = findViewById(R.id.soru1);
        question2 = findViewById(R.id.soru2);
        question3 = findViewById(R.id.soru3);

        fabQuestion = findViewById(R.id.fabSoru);

        fabQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (question1.getText().toString().equals("") || question2.getText().toString().equals("") ||
                        question3.getText().toString().equals("")) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(ThirdQuestionActivity.this);
                    alertDialogBuilder.setIcon(R.drawable.info);
                    alertDialogBuilder.setTitle(R.string.yetersiz_bilgiler31311331);
                    alertDialogBuilder.setMessage(R.string.hesaplama_yapabilmek_i_in_t_m_sorular_cevaplay_n_z311331);
                    alertDialogBuilder.setPositiveButton(R.string.tamam2111313, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                else {
                    Intent intent = new Intent(ThirdQuestionActivity.this, FifthQuestionActivity.class);
                    startActivity(intent);

                    SharedPreferences sharedPreferences = getSharedPreferences("Question3", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("gender3", question1.getText().toString());
                    editor.putString("family3", question2.getText().toString());
                    editor.putString("shower3", question3.getText().toString());
                    editor.apply();
                }
            }
        });

        question2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(ThirdQuestionActivity.this, question2);
                popupMenu.getMenuInflater().inflate(R.menu.yes_no2, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String itemTitle = item.getTitle().toString();
                        question2.setText(itemTitle);
                        return false;
                    }
                });

                popupMenu.show();
            }
        });
    }
}