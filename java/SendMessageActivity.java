// UDP server will be used soon in this code.

package wafoot.becoming.wafoot;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SendMessageActivity extends AppCompatActivity {

    private EditText editMessage;

    private Button buttonMessage;
    private Button buttonMessage2;
    private Button buttonMessage3;

    private StudentsInterface studentsInterface;
    private StaffInterface staffInterface;
    private TeachersInterface teachersInterface;

    private FloatingActionButton fabMessage;
    private FloatingActionButton fabMessage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        editMessage = findViewById(R.id.editMessage);
        buttonMessage = findViewById(R.id.buttonMessage);
        fabMessage2 = findViewById(R.id.fabMessage2);
        buttonMessage2 = findViewById(R.id.buttonMessage2);
        buttonMessage3 = findViewById(R.id.buttonMessage3);
        fabMessage = findViewById(R.id.fabMessage);

        studentsInterface = ApiUtils.getStudentsInterface();
        staffInterface = ApiUtilsStaff.getStaffInterface();
        teachersInterface = ApiUtilsTeachers.getTeachersInterface();

        fabMessage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {}});
    }
}
