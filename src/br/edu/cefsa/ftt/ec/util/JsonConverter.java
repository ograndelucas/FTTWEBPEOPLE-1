package br.edu.cefsa.ftt.ec.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.edu.cefsa.ftt.ec.model.People;
import br.edu.cefsa.ftt.ec.model.Trabalho;

import java.util.List;

public class JsonConverter {
    
    private final Gson gson;
    
    public JsonConverter() {
        
        gson = new GsonBuilder().create();
    }

    public <T> String convertToJsonArray(List<T> list, String arrayName) {
        
        JsonArray jarray = gson.toJsonTree(list).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(arrayName, jarray);

        return jsonObject.toString();
    }
    
    public String ConvertPeopleToJson(People p) {
    	return gson.toJson(p);
    }
    
    public String ConveretTrabalhoToJson(Trabalho trab) {
    	return gson.toJson(trab);
    }
    
    
}
