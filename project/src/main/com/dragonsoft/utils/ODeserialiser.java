package main.com.dragonsoft.utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ODeserialiser implements JsonSerializer<Object> {
	/*
	 * Add .registerTypeHierarchyAdapter(any_object.class, new ODeserialiser()) to the gsonbuilder before .create()
	 * to add its class name property to the ouput file
	 */

    @Override
    public JsonElement serialize(Object src, Type typeOfSrc, JsonSerializationContext context) {
        Gson gson = new Gson();
        JsonElement serialize = gson.toJsonTree(src);
        JsonObject o = (JsonObject) serialize;
        o.addProperty("_class", src.getClass().getName());
        return serialize;
    }

}