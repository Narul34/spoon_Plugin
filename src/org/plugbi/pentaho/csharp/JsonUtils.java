package org.plugbi.pentaho.csharp;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//some methods to use for serializable files.
/**
 * 
 * This class contains methods and features for serializing json files towards or from the server.
 * @author Adrien Blanes(conversion)
 * @version 0.5
 *
 */
public class JsonUtils {

	  private static JsonUtils ctx;
	  private JsonParser jsonParser;
	  private Gson json;
	  
	  //singleton...
	  /**
	   * This return the instance of a JsonUtils object in singleton pattern.
	   * @return JsonUtils
	   */
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
	  
	 /**
	  * This return the instance of a JsonParser object in singleton pattern.
	  * @return JsonParser
	  */
	  public JsonParser getJsonParser()
	  {
	    if (this.jsonParser == null) {
	      this.jsonParser = new JsonParser();
	    }
	    return this.jsonParser;
	  }
	  
	  /**
	   * This return the instance of a Gson object in singleton pattern.
	   * @return Gson
	   */
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
	  
	  /**
	   * This method check if a string is a well formated json.
	   * @param json
	   * 		The string to verify.
	   * @return boolean
	   */
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
	  
	  /**
	   * Parse the string and return it as a JsonObject.
	   * @param json
	   * 		The string to parse.
	   * @return
	   */
	  public JsonObject parse(String json)
	  {
	    try
	    {
	      return getJsonParser().parse(json).getAsJsonObject();
	    }
	    catch (Exception e) {}
	    return null;
	  }
	  
	  /**
	   * 
	   * This method serialize an object into his Json representation.
	   * @param object
	   * @return a string containing the Json representation
	   */
	  public String toJson(Object object)
	  {
	    return getGson().toJson(object);
	  }
	  
	  /**
	   * This method deserializes the specified Json into an object of the specified type.
	   * @param json
	   * 		The string representation of a json
	   * @param classOfT
	   * 		The class of T
	   * @return an object as the json file (could be a string eventually).
	   */
	  public <T> T fromJson(String json, Class<T> classOfT)
	  {
	    return (T)getGson().fromJson(json, classOfT);
	  }
	}


