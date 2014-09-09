package com.infopulse.task.app.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.infopulse.task.app.client.dataentities.User;

public class MySplitLayoutPanel extends Composite {

	private static MySplitLayoutPanelUiBinder uiBinder = GWT
			.create(MySplitLayoutPanelUiBinder.class);

	interface MySplitLayoutPanelUiBinder extends
			UiBinder<Widget, MySplitLayoutPanel> {
	}

	@UiField
	DataGrid<User> grid;

	public MySplitLayoutPanel(DataGrid<User> grid) {
		initWidget(uiBinder.createAndBindUi(this));
		this.grid = grid;
	    RootLayoutPanel.get().add(grid);
	}

}
