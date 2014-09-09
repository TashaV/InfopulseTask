package com.infopulse.task.app.client;

import java.util.Arrays;
import java.util.List;

import com.infopulse.task.app.client.dataentities.Role;
import com.infopulse.task.app.client.dataentities.User;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class InfopulseTaskWithCellTable implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel.get().add(initUserDataGrid());
	}
	
	/**
	 * This method initializing the CellTable widget
	 */
	private static Widget initUserDataGrid() {
		final CellTable<User> cellTable = new CellTable<User>();

		// Add a selection model to handle user selection.
		final MultiSelectionModel<User> selectionModel = new MultiSelectionModel<User>();
		cellTable.setSelectionModel(selectionModel,
				DefaultSelectionEventManager.<User> createCheckboxManager());
	
		//Add a check-box to the header 
		Header<Boolean> header = new Header<Boolean>(new CheckboxCell(true, false)) {

			@Override
			public Boolean getValue() {
				return selectionModel.getSelectedSet().size() == cellTable.getRowCount();
			}
		};
		header.setUpdater(new ValueUpdater<Boolean>() {
			
			@Override
			public void update(Boolean value) {
				List<User> displayedItems = cellTable.getVisibleItems();
				for(User object : displayedItems) {
					selectionModel.setSelected(object, value);
				}
			}
		});

		//Add a column to show check-boxes
		Column<User, Boolean> checkColumn = new Column<User, Boolean>(new CheckboxCell(true, false)) {

			@Override
			public Boolean getValue(User object) {
				return selectionModel.isSelected(object);
			}
		};
		checkColumn.setFieldUpdater(new FieldUpdater<User, Boolean>() {
			
			@Override
			public void update(int index, User object, Boolean value) {
				selectionModel.setSelected(object, value);
			}
		});
		
		//Add a text column to show id
		TextColumn<User> idColumn = new TextColumn<User>() {
			@Override
			public String getValue(User object) {
				return object.getId();
			}
		};

		//Add a text column to show name
		TextColumn<User> nameColumn = new TextColumn<User>() {
			@Override
			public String getValue(User object) {
				return object.getName();
			}
		};

		//Add a column to show and choose roles 
		Column<User, String> roleColumn = new Column<User, String>(
				new SelectionCell(getAcceptableValues())) {

			@Override
			public String getValue(User object) {
				return object.getRole();
			}
		};

		cellTable.insertColumn(0, checkColumn, header);
		cellTable.setColumnWidth(checkColumn, 40, Unit.PX);
		cellTable.addColumn(idColumn, "Id");
		cellTable.addColumn(nameColumn, "Name");
		cellTable.addColumn(roleColumn, "Role");
		
		cellTable.setRowCount(USERS.size(), true);
		cellTable.setRowData(USERS);

		return cellTable;
	}
	
	/**
	 * 
	 * @return
	 */
	private static List<String> getAcceptableValues() {
        return Arrays.asList(Role.Admin.name(),
							 Role.User.name());
    }

	/**
	 * The list of data to display.
	 */
	private static final List<User> USERS = Arrays.asList(
			new User("0", "John", "Green", "jgreen@gmail.com", "User"), 
			new User("1", "Mary", "Smith", "marryly@gmail.com", "User"), 
			new User("2", "Robert", "Adams", "rob_adams@gmail.com", "Admin"), 
			new User("3", "Karen", "Gail", "kaliima@gmail.com", "User"), 
			new User("4", "Jack", "White", "whiteproject@gmail.com", "User"));
}
