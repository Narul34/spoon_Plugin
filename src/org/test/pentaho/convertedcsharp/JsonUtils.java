package org.test.pentaho.convertedcsharp;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//some methods to use for serializable files.

public class JsonUtils {

	  private static JsonUtils ctx;
	  private JsonParser jsonParser;
	  private Gson json;
	  
	  //singleton...
	  public static JsonUtils getCtx()
	  {
	    if (ctx == null) {
	      ctx = new JsonUtils();
	    }
	    return ctx;
	  }
	  
	  // create a new Gson object according to the map send
	  public Gson newGson(Map<Type, Object> mapAdapter)
	  {
	    GsonBuilder gb = new GsonBuilder();
	    if (mapAdapter != null) {
	      for (Type t : mapAdapter.keySet()) {
	        gb.registerTypeAdapter(t, mapAdapter.get(t));
	      }
	    }
	    gb.excludeFieldsWithoutExposeAnnotation();
	    return gb.create();
	  }
	  
	 
	  public JsonParser getJsonParser()
	  {
	    if (this.jsonParser == null) {
	      this.jsonParser = new JsonParser();
	    }
	    return this.jsonParser;
	  }
	  
	  public Gson getGson()
	  {
	    if (this.json == null)
	    {
	      GsonBuilder gb = new GsonBuilder();
	      gb.excludeFieldsWithoutExposeAnnotation();
	      this.json = gb.create();
	    }
	    return this.json;
	  }
	  
	  public boolean isValid(String json)
	  {
	    try
	    {
	      JsonObject jsonObject = getJsonParser().parse(json).getAsJsonObject();
	      if (jsonObject == null) {
	        return false;
	      }
	      return true;
	    }
	    catch (Exception e) {}
	    return false;
	  }
	  
	  public JsonObject parse(String json)
	  {
	    try
	    {
	      return getJsonParser().parse(json).getAsJsonObject();
	    }
	    catch (Exception e) {}
	    return null;
	  }
	  
	  public String toJson(Object object)
	  {
	    return getGson().toJson(object);
	  }
	  
	  public <T> T fromJson(String json, Class<T> classOfT)
	  {
	    return (T)getGson().fromJson(json, classOfT);
	  }
	}


