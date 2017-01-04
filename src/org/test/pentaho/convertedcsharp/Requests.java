package org.test.pentaho.convertedcsharp;

import org.test.pentaho.convertedcsharp.Params.PBI_ACT_BIZVIEW;
import org.test.pentaho.convertedcsharp.Params.PBI_ACT_BLOCK;
import org.test.pentaho.convertedcsharp.Params.PBI_ACT_CONNECT;
import org.test.pentaho.convertedcsharp.Params.PBI_ACT_DASHBOARD;
import org.test.pentaho.convertedcsharp.Params.PBI_ACT_JOINT;
import org.test.pentaho.convertedcsharp.Params.PBI_MODEL;

public class Requests extends Request {

	private static Requests _instance;

	public static Requests getInstance() {

		if (_instance == null)
			_instance = new Requests();

		return _instance;

	}

	public String BizViewList() {
		return MakeModel(PBI_MODEL.BIZ_MODEL)+ AND
				+ MakeAct(PBI_ACT_BIZVIEW.ACT_BIZ_NAMES);
	}

	public String BlockList() {
		return MakeModel(PBI_MODEL.BLOCK_MODEL) + AND
				+ MakeAct(PBI_ACT_BLOCK.ACT_BLOCKS_NAMES);
	}

	public String FieldList(String blckname) {
		return MakeModel(PBI_MODEL.BLOCK_MODEL) + AND
				+ MakeAct(PBI_ACT_BLOCK.ACT_BLOCK_FIELDS) + AND
				+ MakeParam(PBI_ACT_BLOCK.BLOCK_NAME_PARAM, blckname); // "mdl=blk&act=blkflds&blk="
																		// +
																		// blkname
	}

	public String JointList() {
		return MakeModel(PBI_MODEL.JOINT_MODEL) + AND
				+ MakeAct(PBI_ACT_JOINT.ACT_JOINT_NAMES);
	}

	public String JointFieldList(String jtkname) {
		return MakeModel(PBI_MODEL.JOINT_MODEL) + AND
				+ MakeAct(PBI_ACT_JOINT.ACT_JOINT_FIELDS) + AND
				+ MakeParam(PBI_ACT_JOINT.JOINT_NAME_PARAM, jtkname);
	}

	public String DashList() {
		return MakeModel(PBI_MODEL.DASHBOARD_MODEL) + AND
				+ MakeAct(PBI_ACT_DASHBOARD.ACT_DASHBOARDS_NAMES);
	}

	public String AddDash(String dashname, String groupname) {
		return MakeModel(PBI_MODEL.DASHADMIN_MODEL) + AND
				+ MakeAct(PBI_ACT_DASHBOARD.ACT_DASHADMIN_ADD) + AND
				+ MakeParam(PBI_ACT_DASHBOARD.DASHADMIN_NAME_PARAM, dashname)
				+ AND
				+ MakeParam(PBI_ACT_DASHBOARD.DASHADMIN_GOUP_PARAM, groupname); // mdl=dashA&act=dashadd&dash=Classeur12345&group=@sans
																				// groupement&prlid=2
	}

	public String DownloadDash(String dashname, String groupname) {
		return MakeModel(PBI_MODEL.DASHADMIN_MODEL) + AND
				+ MakeAct(PBI_ACT_DASHBOARD.ACT_DASHADMIN_DOWNLOAD) + AND
				+ MakeParam(PBI_ACT_DASHBOARD.DASHADMIN_NAME_PARAM, dashname)
				+ AND
				+ MakeParam(PBI_ACT_DASHBOARD.DASHADMIN_GOUP_PARAM, groupname); // mdl=dashA&act=dashadd&dash=Classeur12345&group=@sans
																				// groupement&prlid=2
	}

	public String UpdateDash(String dashname, String groupname) {
		return MakeModel(PBI_MODEL.DASHADMIN_MODEL) + AND
				+ MakeAct(PBI_ACT_DASHBOARD.ACT_DASHADMIN_UPDATE) + AND
				+ MakeParam(PBI_ACT_DASHBOARD.DASHADMIN_NAME_PARAM, dashname)
				+ AND
				+ MakeParam(PBI_ACT_DASHBOARD.DASHADMIN_GOUP_PARAM, groupname); // mdl=dashA&act=dashadd&dash=Classeur12345&group=@sans
																				// groupement&prlid=2
	}

	// Bizviews
	public String BizviewList() {
		return MakeModel(PBI_MODEL.BIZ_MODEL) + AND
				+ MakeAct(PBI_ACT_BIZVIEW.ACT_BIZ_NAMES);
	}

	public String BizviewNames() {
		return MakeModel(PBI_MODEL.BIZ_MODEL) + AND
				+ MakeAct(PBI_ACT_BIZVIEW.ACT_BIZ_NAMES);
	}

	public String MezsList(String namebiz) {
		return MakeModel(PBI_MODEL.BIZ_MODEL) + AND
				+ MakeAct(PBI_ACT_BIZVIEW.ACT_MEZS) + AND
				+ MakeParam(PBI_ACT_BIZVIEW.BIZ_NAME_PARAM, namebiz);
		
	}

	public String MezsNames(String namebiz) {
		return MakeModel(PBI_MODEL.BIZ_MODEL) + AND
				+ MakeAct(PBI_ACT_BIZVIEW.ACT_BIZ_MEZS) + AND
				+ MakeParam(PBI_ACT_BIZVIEW.BIZ_NAME_PARAM, namebiz);
		
	}

	public String DimsList(String namebiz) {
		return MakeModel(PBI_MODEL.BIZ_MODEL) + AND
				+ MakeAct(PBI_ACT_BIZVIEW.ACT_DIMS) + AND
				+ MakeParam(PBI_ACT_BIZVIEW.BIZ_NAME_PARAM, namebiz);
	}

	public String DimsNames(String namebiz) {
		return MakeModel(PBI_MODEL.BIZ_MODEL) + AND
				+ MakeAct(PBI_ACT_BIZVIEW.ACT_BIZ_DIMS) + AND
				+ MakeParam(PBI_ACT_BIZVIEW.BIZ_NAME_PARAM, namebiz);
	}

	// TODO AR publish bizview
	public String AddBizFolder(String foldername, String groupname) {
		return MakeModel(PBI_MODEL.DASHADMIN_MODEL) + AND
				+ MakeAct(PBI_ACT_DASHBOARD.ACT_DASHADMIN_BIZADD) + AND
				+ MakeParam(PBI_ACT_DASHBOARD.DASHADMIN_NAME_PARAM, foldername)
				+ AND
				+ MakeParam(PBI_ACT_DASHBOARD.DASHADMIN_GOUP_PARAM, groupname); // mdl=dashA&act=bizadd&dash=Classeur12345&group=@sans
																				// groupement&prlid=2
	}

	public String DownloadBizFolder(String flodername, String groupname) {
		// TODO to use later prob sync with xml bizview
		 return "";
	}

	public String UpdateBizFolder(String foldername, String groupname) {
		return MakeModel(PBI_MODEL.DASHADMIN_MODEL) + AND
				+ MakeAct(PBI_ACT_DASHBOARD.ACT_DASHADMIN_BIZUPDATE) + AND
				+ MakeParam(PBI_ACT_DASHBOARD.DASHADMIN_NAME_PARAM, foldername)
				+ AND
				+ MakeParam(PBI_ACT_DASHBOARD.DASHADMIN_GOUP_PARAM, groupname); // mdl=dashA&act=bizup&dash=Classeur12345&group=@sans
																				// groupement&prlid=2
	}

	// Connection
	// ("mdl=con&act=conusr&usr=" + user + "&pwd=" + pword))
	public String Connection(String user, String password) {
		return MakeModel(PBI_MODEL.CONNECT_MODEL) + AND
				+ MakeAct(PBI_ACT_CONNECT.ACT_CONNECTION) + AND
				+ MakeParam(PBI_ACT_CONNECT.CONUSR_PARAM, user) + AND
				+ MakeParam(PBI_ACT_CONNECT.CONPWD_PARAM, password);
	}

	// Connection
		// ("mdl=con&act=conusrobj&usr=" + user + "&pwd=" + pword))
	public String UserConnection(String user, String password) {
		return MakeModel(PBI_MODEL.CONNECT_MODEL) + AND
				+ MakeAct(PBI_ACT_CONNECT.ACT_CONNECTION_USER) + AND
				+ MakeParam(PBI_ACT_CONNECT.CONUSR_PARAM, user) + AND
				+ MakeParam(PBI_ACT_CONNECT.CONPWD_PARAM, password);
	}

	public String AndProfileId(Integer id) {
		return AND
				+ MakeParam(PBI_ACT_CONNECT.PROFILEID_PARAM,
						id != null ? id.toString() : "-1");
	}
}
