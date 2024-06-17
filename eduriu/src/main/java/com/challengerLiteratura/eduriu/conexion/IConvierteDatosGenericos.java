package com.challengerLiteratura.eduriu.conexion;

public interface IConvierteDatosGenericos {
    <T> T consumoAPI(String json, Class<T> clase);
}
