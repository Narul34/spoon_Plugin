package org.plugbi.pentaho.csharp;

/**
 * 
 * this class contains others local static class with string parameters used for creating url in http connection.
 * @author Adrien Blanes(conversion)
 * @version 0.5
 *
 */
public class Params {
	
	public static class PBI_MODEL {

		public static final String NAME = "mdl";
		public static final String BIZ_MODEL = "biz"; // for bizview model
		public static final String BLOCK_MODEL = "blk"; // for block model
		public static final String USER_MODEL = "usr"; // for user model
		public static final String PROFILE_MODEL = "prl"; // for profile model
		public static final String DASHBOARD_MODEL = "dash"; // for dashboard model
		public static final String JOINT_MODEL = "jnt"; // for jointure model
		public static final String CONNECT_MODEL = "con"; // for jointure model
		public static final String DASHADMIN_MODEL = "dashA"; // for jointure model
	}

	public static class PBI_ACT_BIZVIEW {
		// BIZVIEW
		public static final String ACT_BIZ_NAMES = "biznames";
		public static final String ACT_BIZ_VALUE = "bizval";

		public static final String ACT_BIZ_DIMS = "bizdims";
		public static final String ACT_BIZ_MEZS = "bizmezs";
		//
		public static final String ACT_DIMS = "dims";
		public static final String ACT_MEZS = "mezs";

		public static final String ACT_BIZ_MEZ_OP = "bizmezop";
		public static final String BIZ_NAME_PARAM = "biz";
		public static final String BIZ_KEY_PARAM = "key";
		public static final String BIZ_MEZ_PARAM = "mez";
	}

	public static class PBI_ACT_CONNECT {
		// Security
		public static final String PROFILEID_PARAM = "prlid"; // usrid=129 => connected user from extern request

		// CONNECTION
		public static final String ACT_CONNECTION = "conusr"; // act=conusr&usr=admin&pwd=password ==>"{list":["12345"]} (profilid)
		public static final String ACT_CONNECTION_USER = "conusrobj";// {list":[v0:xxx]} (user)
		public static final String CONUSR_PARAM = "usr"; // bizview name
		public static final String CONPWD_PARAM = "pwd"; // bizview key
	}

	public static class PBI_ACT_USER_PROFIL {
		// USER
		public static final String ACT_USERS_NAMES = "usrnames"; // mdl=usr&act=usrnames return json format of users names {"list": ["demo","a.sylvestre","a.bernard",]}
		public static final String ACT_USER_PROFILE = "usrprofile"; // mdl=usr&act=usrprofile&usr=a.sylvestre return in json format the profile name for a user {"list": ["GROUPE AUTO"]}
		public static final String USER_NAME_PARAM = "usr"; // user name
		// PROFILE
		public static final String ACT_PROFILS_NAMES = "prlnames"; // mdl=prl&act=prlnames return in json format the profiles names {"list": ["Administrateur siège","DEMO","GROUPE AUTO"]}
		public static final String ACT_PROFIL_HOMEPAGE = "prlhp"; // mdl=prl&act=prlhp&prl=GROUPE AUTO return in json format the home page for a profile {"list": [""]}
		public static final String PROFIL_NAME_PARAM = "prl"; // profile name
	}

	public static class PBI_ACT_BLOCK {
		// BLOCK
		public static final String ACT_BLOCKS_NAMES = "blknames"; // mdl=blk&act=blknames return in json format the blocks names {"list": [{V1...V6},{V1...V6}]}
		public static final String ACT_BLOCK_FIELDS = "blkflds"; // mdl=blk&act=blkflds&blk=ga_ref_flash return in json format the fields for a block {"list": [{xxxx}}
		public static final String ACT_BLOCK_FIELD_TYPE = "blkfldtype"; // mdl=blk&act=blkfldtype&blk=ga_ref_flash&fld=theme return in json format the type of field.
																// (a field have a intger type{0:unknow;1:text;2:numeric;3:date;4:calculate;5:condition;6:calculate_date;7:comment})

		public static final String ACT_BLOCK_LABEL = "blklabel"; // mdl=blk&act=blklabel&blk=ga_ref_flash return in json format the label of block {"list": ["GA_REF_FLASH"]}
		public static final String ACT_BLOCK_SELECT = "blkslt"; // mdl=blk&act=blkslt&blk=ndf_detail&fld=id_ndf&whr=rubrique_fraisµ'Internet' (symbole µ for =)
														// return in json format the result for a SQL request {"list": ["1377107569475","1377272981338",]}
		public static final String BLOCK_NAME_PARAM = "blk"; // block name
		public static final String BLOCK_FIELD_PARAM = "fld"; // block field name
		public static final String BLOCK_WHERE_PARAM = "whr"; // block where parameter
	}

	public static class PBI_ACT_JOINT {
		// JOINTURE
		public static final String ACT_JOINT_NAMES = "jntnames";// mdl=jnt&act=jntnames return in json format the jointures names {"list": [{V1....}]}
		public static final String ACT_JOINT_LABEL = "jntlabel";// mdl=jnt&act=jntlabel&jnt=JN_GAUTIL_NDFWRKF_CNTR return in json format the label for a jointure
														// {"list": ["JN_GAUTIL_NDFWRKF_CNTR"]}
		public static final String ACT_JOINT_FIELDS = "jntflds";// mdl=jnt&act=jntflds&jnt=JN_GAUTIL_NDFWRKF_CNTR return in json format the fields for a jointure 
														 //{"list": ["JN_GAUTIL_NDFWRKF_CNTR"]}
		public static final String ACT_JOINT_FIELD_TYPE = "jntfldtype";// mdl=jnt&act=jntfldtype&jnt=JN_GAUTIL_NDFWRKF_CNTR&jntfld= return in json format the type of field for a
																// jointure {"list": ["JN_GAUTIL_NDFWRKF_CNTR"]}
		public static final String JOINT_NAME_PARAM = "jnt"; // jointure name
		public static final String JOINT_FIELD_PARAM = "fld"; // jointure field
	}

	public static class PBI_ACT_DASHBOARD {
		// DASHBOARD
		public static final String ACT_DASHBOARDS_NAMES = "dashnames";// mdl=dash&act=dashnames return in json format the dashboards names {"list": [{V1.....}]}
		public static final String ACT_DASHBOARD_SHEET_NAMES = "dashsheets";// mdl=dash&act=dashsheets&dash=GA_NDF return in json format the dashboards sheet
																	// names for the dash GA_NDF {"list": ["NDF_EMETTEUR","NDF_NOUVELLE","NDF A TRAITER"]}
		public static final String ACT_DASHBOARD_SHEET_ID = "dashsheetid";// mdl=dash&act=dashsheetid&dash=GA_NDF&sheet=NDF_EMETTEUR return in json format
																	// the dashboards sheet number for the sheet NDF_EMETTEUR of the dash GA_NDF {"list": ["1"]}
		public static final String DASHBOARD_NAME_PARAM = "dash"; // dash name
		public static final String DASHBOARD_SHEET_NAME_PARAM = "sheet"; // sheet name
		// DASHADMIN
		public static final String ACT_DASHADMIN_DOWNLOAD = "dashdown"; // downlaod dashboard name
		public static final String ACT_DASHADMIN_ADD = "dashadd";// mdl=dashA&act ... add dashboard
		public static final String ACT_DASHADMIN_BIZADD = "bizadd";// mdl=dash&... add bizview
		public static final String ACT_DASHADMIN_UPDATE = "dashup";// mdl=dash&act=dashup... update dashboard
		public static final String ACT_DASHADMIN_BIZUPDATE = "bizup";// mdl=dash&act=dashbizup : update bizview
		public static final String DASHADMIN_NAME_PARAM = "dash"; // dash name
		public static final String DASHADMIN_GOUP_PARAM = "group"; // group name
	}

}
