package org.test.pentaho.convertedcsharp;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;



public class Query {
	
	    public static class TYPOLOGIES
	    {
	        public static final int DIM_TYPOLOGY = 100;
	        public static final int MEZ_TYPOLOGY = 200;
	        public static final int BIZ_TYPOLOGY = 210;
	        //
	        public static final int COLUMN_TYPOLOGY = 500;
	        public static final int FIELD_TYPOLOGY = 510;
	        public static final int COMPLEXFLD_TYPOLOGY = 520;
	        //
	        public static final int FILTER_TYPOLOGY = 700;
	        //
	        public static final int BIZVIEW_TYPOLOGY = 300;
	        public static final int SOURCE_TYPOLOGY = 400;
	        public static final int COMPLEXSRC_TYPOLOGY = 410;
	        public static final int SRCREQUEST_TYPOLOGY = 450;
	        //
	        public static final int SELECT_DATA_TYPOLOGY = 2500;
	    }
	    
	    public static class TYPES
	    {
	        public static final int TXT_TYPE = 1;
	        public static final int NUM_TYPE = 2;
	        public static final int DATE_TYPE = 3;
	        public static final int CALCULATE_TXT_TYPE = 4;
	        public static final int CALCULATE_NUM_TYPE = 5;
	        public static final int CALCULATE_DATE = 6;
	        public static final int COMMENT_TYPE = 7;
	    }
	    
	    public static class RESPONSE
	    {
	        public static final String DATA = "data";
	        public static final String OOO = "ooo";
	    }
	     
	    public static class OPERATORS
	    {
	    	static Query q = new Query();
	        static List<Operator> _operators = new ArrayList<Operator>();
	        //
	        public static  Operator EQUAL_NUM = q.new Operator("eq", "=", TYPES.NUM_TYPE);
	        public static  Operator GREAT = q.new Operator("gt", ">", TYPES.NUM_TYPE);
	        public static  Operator GREATE = q.new Operator("ge", ">=", TYPES.NUM_TYPE);
	        public static  Operator LESS = q.new Operator("lt", "<", TYPES.NUM_TYPE);
	        public static  Operator LESSE = q.new Operator("le", "<=", TYPES.NUM_TYPE);
	        public static  Operator LIKE = q.new Operator("ilike", "like", TYPES.TXT_TYPE);
	        public static  Operator NOT_EQUAL_TXT = q.new Operator("ne", "!=", TYPES.TXT_TYPE);
	        public static  Operator NOT_EQUAL_NUM = q.new Operator("ne", "<>", TYPES.NUM_TYPE);
	        public static  Operator BETWEEN = q.new Operator("between", "]a;b[", TYPES.NUM_TYPE);
	        public static  Operator BETWEENE = q.new Operator("betweene", "[a;b]", TYPES.NUM_TYPE);
	        public static  Operator BETWEEN1 = q.new Operator("between1", "[a;b[", TYPES.NUM_TYPE);
	        public static  Operator BETWEEN2 = q.new Operator("between2", "]a;b]", TYPES.NUM_TYPE);
	        //
	        public static Operator AVERAGE =  q.new Operator("Average", "Average", TYPES.TXT_TYPE);
	        public static Operator COUNT = q.new Operator("Count", "Count", TYPES.TXT_TYPE);
	        public static Operator MAX = q.new Operator("Max", "Max", TYPES.TXT_TYPE);
	        public static Operator MIN = q.new Operator("Min", "Min", TYPES.TXT_TYPE);
	        public static Operator SUM = q.new Operator("Sum", "Sum", TYPES.TXT_TYPE);
	        public static Operator DISTINCT = q.new Operator("Distinct", "Distinct", TYPES.TXT_TYPE);
	        //
	        public static List<Operator> getItems()
	        {
	           
	                if (_operators.size() <= 0)
	                {
	                    _operators.add(OPERATORS.EQUAL_NUM);
	                    _operators.add(OPERATORS.GREAT);
	                    _operators.add(OPERATORS.GREATE);
	                    _operators.add(OPERATORS.LESS);
	                    _operators.add(OPERATORS.LESSE);
	                    _operators.add(OPERATORS.LIKE);
	                    _operators.add(OPERATORS.NOT_EQUAL_NUM);
	                    _operators.add(OPERATORS.NOT_EQUAL_TXT);
	                    _operators.add(OPERATORS.BETWEEN);
	                    _operators.add(OPERATORS.BETWEENE);
	                    _operators.add(OPERATORS.BETWEEN1);
	                    _operators.add(OPERATORS.BETWEEN2);
	                }
	                //
	                return _operators;
	            
	        }
	    }
	    
	    public class Operator
	    {
	        public String Id;
	        public String Label;
	        public int Type;
	        
	        public String getId() {
				return Id;
			}

			public void setId(String id) {
				Id = id;
			}

			public String getLabel() {
				return Label;
			}

			public void setLabel(String label) {
				Label = label;
			}

			public int getType() {
				return Type;
			}

			public void setType(int type) {
				Type = type;
			}

	        public Operator(String id, String label, int type)
	        {
	            Id = id;
	            Label = label;
	            Type = type;
	        }

	    }

	    public abstract class Mdl
	    {
	    	@SerializedName("mdl")
	        public String MdlName;
	    	@SerializedName("act")
	        public int Action;
	    	@SerializedName("restype")
	        public String Response;

	        public String ToJson()
	        {
	        	Gson gs = new Gson();
	            return gs.toJson(this);
	        }

			public String getMdlName() {
				return MdlName;
			}

			public void setMdlName(String mdlName) {
				MdlName = mdlName;
			}

			public int getAction() {
				return Action;
			}

			public void setAction(int action) {
				Action = action;
			}

			public String getResponse() {
				return Response;
			}

			public void setResponse(String response) {
				Response = response;
			}
	    }

	    public class QMetaInf extends Mdl
	    {
	        public QMetaInf(int typoAction)
	        {
	            MdlName = "qmetainf";
	            Action = typoAction; //SOURCE_TYPOLOGY,...
	            Response = RESPONSE.DATA;
	        }
	        @SerializedName("src")
	        public String source;
	        @SerializedName("fld")
	        public String fld;
	        @SerializedName("val")
	        public String val;
	        
			public String getSource() {
				return source;
			}
			public void setSource(String source) {
				this.source = source;
			}
			public String getFld() {
				return fld;
			}
			public void setFld(String fld) {
				this.fld = fld;
			}
			public String getVal() {
				return val;
			}
			public void setVal(String val) {
				this.val = val;
			}
	    }

	    /*
	    public class Qoo extends Mdl
	    {
	        public Qoo ()
	        {
	            MdlName = "qoo";
	            Action = TYPOLOGIES.SELECT_DATA_TYPOLOGY;
	            //
	            Selects = new ArrayList<ItemOoo>();
	            Criterias = new ArrayList<ItemOoo>();
	            Aggregators = new ArrayList<ItemOoo>();
	            Groupby = new ArrayList<ItemOoo>();
	            Orderby = new ArrayList<ItemOoo>();
	        }

	        @SerializedName("src")
	        public ItemOoo Source;

	        @SerializedName("selects")
	        public List<ItemOoo> Selects;

	        @SerializedName("criterias")
	        public List<ItemOoo> Criterias;

	        @SerializedName("aggregators ")
	        public List<ItemOoo> Aggregators;

	        @SerializedName("groupby")
	        public List<ItemOoo> Groupby;

	        @SerializedName("orderby ")
	        public List<ItemOoo> Orderby;

	        @SerializedName("restype")
	        public String Restype;

	    }
	    
	    [JsonObject(MemberSerialization.OptIn)]
	    public class ItemOoo extends Ooo
	    {
	    	public ItemOoo()
	        {
	            Values1 = new ArrayList<String>();
	            Values2 = new ArrayList<String>();
	            Name = "";
	            Group = "";
	            Connector = "";
	            Operator = "";
	        }
	    	
	    	@SerializedName("name")
	        public String Name;

	    	@SerializedName("group")
	        public String Group;

	    	@SerializedName("oper")
	        public String Operator;

	    	@SerializedName("type")
	        public int Type;

	    	@SerializedName("typo")
	        public int Typology;

	    	@SerializedName("pos")
	        public int Position;

	    	@SerializedName("val1")
	        public List<String> Values1;
	    	
	    	@SerializedName("val2")
	        public List<String> Values2;

	    	@SerializedName("con")
	        public String Connector;

	        public String getName() {
				return Name;
			}

			public void setName(String name) {
				Name = name;
			}

			public String getGroup() {
				return Group;
			}

			public void setGroup(String group) {
				Group = group;
			}

			public String getOperator() {
				return Operator;
			}

			public void setOperator(String operator) {
				Operator = operator;
			}

			public int getType() {
				return Type;
			}

			public void setType(int type) {
				Type = type;
			}

			public int getTypology() {
				return Typology;
			}

			public void setTypology(int typology) {
				Typology = typology;
			}

			public int getPosition() {
				return Position;
			}

			public void setPosition(int position) {
				Position = position;
			}

			public List<String> getValues1() {
				return Values1;
			}

			public void setValues1(List<String> values1) {
				Values1 = values1;
			}

			public List<String> getValues2() {
				return Values2;
			}

			public void setValues2(List<String> values2) {
				Values2 = values2;
			}

			public String getConnector() {
				return Connector;
			}

			public void setConnector(String connector) {
				Connector = connector;
			}

	    }
	*/
}

