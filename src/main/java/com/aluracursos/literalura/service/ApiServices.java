package com.aluracursos.literalura.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class ApiServices {
    static ConsumoAPI consumoAPI = new ConsumoAPI();
    public static Map<String, Object> getJsonData(String url, String titulo){
        var json = consumoAPI.getMethod(url+titulo.replace(" ", "%20"));
        Gson gson = new Gson();
        // Definir el tipo de los datos
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        // Deserializar el JSON y retornar el Map
        return gson.fromJson(json, type);
    }
}
