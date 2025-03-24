package com.example.dam_uf5angelsantana_androidsqliteroom;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Candidato {

    @ColumnInfo(name = "id") // Define el nombre de la columna en la base de datos
    @PrimaryKey(autoGenerate = true)
    public int id;

    /**
     * Columnas de la base de datos que almacenan la información del usuario.
     * @ColumnInfo permite personalizar los nombres de las columnas en la base de datos.
     */
    @ColumnInfo(name = "dni")
    public String dni;

    @ColumnInfo(name = "anio")
    public int anio;

    @ColumnInfo(name = "partido")
    public String partido;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "puesto")
    public String puesto;


    public Candidato(String dni, int anio, String partido, String nombre, String puesto) {
        this.dni = dni;
        this.anio = anio;
        this.partido = partido;
        this.nombre = nombre;
        this.puesto = puesto;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}
