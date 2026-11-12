package com.junlei_li.sprint1
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ajuste de márgenes por la barra de estado (viene en la plantilla de Android Studio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // WebView: carga la web de Android Developers dentro de la app
        val webView = findViewById<WebView>(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://developer.android.com")

        // Spinner: hace falta un adapter para que muestre opciones
        val spinner = findViewById<Spinner>(R.id.spCurso)
        val cursos = arrayOf("1.º DAM", "2.º DAM", "1.º DAW", "2.º DAW")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cursos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val sbNivel = findViewById<SeekBar>(R.id.sbNivel)
        val tvNivel = findViewById<TextView>(R.id.tvNivel)

        sbNivel.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvNivel.text = "Nivel seleccionado: $progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        val swNotificaciones = findViewById<Switch>(R.id.swNotificaciones)

        swNotificaciones.setOnCheckedChangeListener { _, isChecked ->
            val mensaje = if (isChecked) "Notificaciones activadas" else "Notificaciones desactivadas"
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val cbAndroid = findViewById<CheckBox>(R.id.cbAndroid)
        val cbReact = findViewById<CheckBox>(R.id.cbReact)
        val cbFlutter = findViewById<CheckBox>(R.id.cbFlutter)
        val rgModalidad = findViewById<RadioGroup>(R.id.rgModalidad)
        val btnGuardar1 = findViewById<Button>(R.id.btnGuardar1)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        btnGuardar1.setOnClickListener {
            val nombre = etNombre.text.toString().ifBlank { "Sin nombre" }

            val tecnologias = mutableListOf<String>()
            if (cbAndroid.isChecked) tecnologias.add("Android")
            if (cbReact.isChecked) tecnologias.add("React")
            if (cbFlutter.isChecked) tecnologias.add("Flutter")

            val modalidad = findViewById<RadioButton>(rgModalidad.checkedRadioButtonId).text

            tvResultado.text = "Nombre: $nombre\n" +
                    "Tecnologías: ${tecnologias.joinToString().ifEmpty { "Ninguna" }}\n" +
                    "Modalidad: $modalidad"
        }
    }
}