package com.example.dam_uf5angelsantana_androidsqliteroom;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EleccionesPE28Dao {


    @Query("UPDATE sqlite_sequence SET seq=0 WHERE name = 'User'")
    void restartSqnc();

    @Insert
    void insertRecord(Candidato candidato);

    @Query("SELECT * FROM Candidato")
    List<Candidato> getAllCandidatos();

    @Query("SELECT COUNT(*) FROM Candidato")
    int countCandidatos();

    @Query("UPDATE Candidato SET anio = :anio")
    void updateByAnio( int anio);


    @Query("DELETE FROM Candidato")
    void deleteAllCandidatos();

    @Query("SELECT COUNT(*) FROM Candidato WHERE dni = :dni")
    boolean candidatoExists(String dni);

    @Query("SELECT COUNT(*) FROM Candidato WHERE partido = :partido")
    boolean candidatoPartidoExists(String partido);

    @Query("DELETE FROM Candidato WHERE dni = :dni")
    void deleteByDni(String dni);

    @Query("UPDATE sqlite_sequence SET SEQ=(SELECT COUNT(*) FROM Candidato) -1 WHERE NAME = 'Candidato'")
    void minusSqnc();

    @Query("UPDATE Candidato SET anio = :anio, partido = :partido, nombre = :nombre, puesto = :puesto WHERE dni = :dni")
    void updateByDni(int anio, String partido, String nombre, String puesto, String dni);

    /*@Query("INSERT INTO Candidato VALUES ('', '98603192V', 2028, 'MAS MADRID', 'Rita Maestre Fernandez', 'CONCEJAL'), " +
            "('','98603192V', 2028, 'MAS MADRID', 'Rita Maestre Fernandez', 'CONCEJAL')," +
            "('','67627486K', 2028, 'CIUDADANOS', 'Begona Villacis Sanchez', 'CONCEJAL')," +
            "('','21993491F', 2028, 'PARTIDO SOCIALISTA OBRERO ESPANOL', 'Maria Reyes Maroto Illera', 'CONCEJAL')," +
            "('','30125487M', 2028, 'VOX', 'Francisco Javier Ortega Smith-Molina', 'CONCEJAL')")
            void insertData();*/







}
