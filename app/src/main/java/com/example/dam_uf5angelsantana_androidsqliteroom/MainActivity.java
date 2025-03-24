package com.example.dam_uf5angelsantana_androidsqliteroom;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.dam_uf5angelsantana_androidsqliteroom.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    // Variable para el ViewBinding, que facilita la referencia a los elementos del layout
    private ActivityMainBinding binding;

    // Base de datos de la aplicación
    private EleccionesPE28DB db;

    // Objeto DAO para interactuar con la base de datos
    private EleccionesPE28Dao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Inicializa ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Inicializa la base de datos usando Room
        db = Room.databaseBuilder(
                        getApplicationContext(), // Contexto de la aplicación
                        EleccionesPE28DB.class,       // Clase de la base de datos
                        "LISTAS_CANDIDATOS")              // Nombre de la base de datos
                .allowMainThreadQueries() // Permite consultas en el hilo principal (no recomendado para producción)
                .build();

        // Obtiene el DAO para manipular la base de datos
        userDao = db.userDao();

        binding.btnConsultar.setOnClickListener(v -> showCandidatos());
        binding.btnInsertar.setOnClickListener(v -> insertCandidato());
        binding.btnNumCandidatos.setOnClickListener(v -> countCandidatos());
        binding.btnActualizarAnio.setOnClickListener(v -> updateYearCandidato());
        binding.btnBorrartodo.setOnClickListener(v -> deleteAllCandidatos());
        binding.btnEliminar.setOnClickListener(v -> deleteCandidato());
        binding.btnActualizar.setOnClickListener(v -> updateCandidato());


       // isertDataCandidatos();



    }

    /*private void isertDataCandidatos() {

        if(userDao.getAllCandidatos().isEmpty()){
            userDao.insertData();
        }
    }
*/
    private void updateCandidato() {
        String dni = binding.etDni.getText().toString();

        // Verifica si el usuario existe
        if (!userDao.candidatoExists(dni)) {
            Toast.makeText(this, "Candidato " + dni + " no existe", Toast.LENGTH_SHORT).show();
            return;
        }

        // Si existe, lo actualiza
        userDao.updateByDni(
                Integer.parseInt(binding.etAnio.getText().toString()),
                binding.etPartido.getText().toString(),
                binding.etNombre.getText().toString(),
                binding.etPuesto.getText().toString(),
                dni
        );

        Toast.makeText(this, "Candidato " + dni + " actualizado", Toast.LENGTH_SHORT).show();
        limpiarDatos();
    }

    private void deleteCandidato() {

        String dni = binding.etDni.getText().toString();

        // Verifica si el candidato existe
        if (!userDao.candidatoExists(dni)) {
            Toast.makeText(this, "Candidato " + dni + " no existe", Toast.LENGTH_SHORT).show();
        } else {
            userDao.deleteByDni(dni); // Elimina el candidato
            Toast.makeText(this, "Candidato " + dni + " eliminado", Toast.LENGTH_SHORT).show();
            userDao.minusSqnc(); // Disminuye la secuencia de IDs
        }
        limpiarDatos();
    }

    private void deleteAllCandidatos() {
        userDao.deleteAllCandidatos(); // Elimina todos los registros
        Toast.makeText(this, "Todos los candidatos han sido eliminados", Toast.LENGTH_SHORT).show();
        userDao.restartSqnc(); // Reinicia la secuencia de IDs
    }

    private void updateYearCandidato() {


        // Si existe, lo actualiza
        userDao.updateByAnio(
                Integer.parseInt(binding.etAnio.getText().toString())
        );

        Toast.makeText(this, "Año de los candidatos actualizados", Toast.LENGTH_SHORT).show();
        limpiarDatos();
    }

    private void countCandidatos() {
        int count = userDao.countCandidatos();
        // Muestra los datos en el TextView
        binding.tvNumCandidatos.setText("Hay un total de " + count + " de candidatos en la base de datos");    }


    private void showCandidatos() {
        List<Candidato> candidatos = userDao.getAllCandidatos(); // Obtiene todos los usuarios

        StringBuilder candidatoData = new StringBuilder();
        for (Candidato candidato : candidatos) {
            candidatoData.append(candidato.getId()).append(") ")
                    .append(candidato.getAnio()).append(" | ")
                    .append(candidato.getPartido()).append(" | ")
                    .append(candidato.getNombre()).append(" | ")
                    .append(candidato.getDni()).append(" | ")
                    .append(candidato.getPuesto()).append("\n\n");
        }

        // Muestra los datos en el TextView
        binding.tvListadoCandidatos.setText(candidatoData.toString());
    }

    private void insertCandidato() {
        userDao.insertRecord(new Candidato(
                binding.etDni.getText().toString(), // DNI
                Integer.parseInt(binding.etAnio.getText().toString()),
                binding.etPartido.getText().toString(),
                binding.etNombre.getText().toString(),
                binding.etPuesto.getText().toString()

        ));

        // Muestra un mensaje indicando que el candidato fue introducido
        Toast.makeText(this, "Candidato " + binding.etNombre.getText().toString() + " introducido", Toast.LENGTH_SHORT).show();

        limpiarDatos();
    }

    private void limpiarDatos() {

        binding.etDni.setText("");
        binding.etAnio.setText("");
        binding.etPartido.setText("");
        binding.etNombre.setText("");
        binding.etPuesto.setText("");


    }


}