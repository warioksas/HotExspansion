 package lt.ktu.ktuhot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

 public class AboutAppAcitvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_aboutapp);
        TextView description = findViewById(R.id.appDescription);
        String text = "<b>Mobilioji aplikacija</b> buvo sukurta susipažinimui su kūno ilgio pasikeitimu" +
                ", kai kūnas yra veikiamas temperatūros.<br><b>Žaidime</b> galima pasirinkti populiariausias" +
                " medžiagas ir matyti pokyčius jas šildant arba šaldant. Taip pat galima palyginti," +
                " kaip skirtingos medžiagos reaguoja į temperatūros pokyčius.<br><b>Skaičiuotuvo pagalba</b>" +
                " galima sužinoti temperatūrinį ilgėjimo koeficientą, jei yra žinoma, koks buvo" +
                " pradinis kūno ilgis ir temperatūra ir koks buvo galutinis kūno ilgis bei" +
                " temperatūra.<br>Mobilioji aplikacija turi <b>žinyną</b>, kuriame pateikiama informacija" +
                " apie daugelį medžiagų: temperatūriniai ilgėjimo koeficientai, paveikslėliai," +
                " medžiagų aprašymai.";
        description.setText(Html.fromHtml(text));
    }
}