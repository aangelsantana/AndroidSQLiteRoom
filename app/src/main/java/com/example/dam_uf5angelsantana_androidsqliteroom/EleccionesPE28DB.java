package com.example.dam_uf5angelsantana_androidsqliteroom;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Candidato.class}, version = 1) // Se define la entidad y la versión de la base de datos

public abstract class EleccionesPE28DB extends RoomDatabase {

    /**
     * Método abstracto que proporciona acceso al DAO (Data Access Object) de User.
     * Room se encargará de implementar este método en tiempo de ejecución.
     */
    public abstract EleccionesPE28Dao userDao();
}
