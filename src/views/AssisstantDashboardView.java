package views;

import models.Assistant;

public class AssisstantDashboardView extends DashboardView<Assistant> {
	
	public AssisstantDashboardView() {
		// TODO Auto-generated constructor stub
        setHeaderTitle("Assistant List");
        addIntegerColumn("Assistant Id","assistantId");
        addStringColumn("first name","firstName");
        addStringColumn("last name","lastName");
        addStringColumn("Contract Type", "contractType");

    }

}
