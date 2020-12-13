import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * A GUI tab containing components for displaying a list of assigned employees.
 * @author Timothy Engkar
 * @version 1.0
 */
public class AssignTasksGUI4 {

    private SepGUI sepGUI;

    private VBox mainPane;
    private HBox topPane;
    private HBox bottomButtons;

    private Label titleLabel;
    private Label tableLabel;

    private TableView<AssignedEmployee> allAssignedTasksTable;
    private TableColumn firstNameColumn;
    private TableColumn lastNameColumn;
    private TableColumn birthdayColumn;

    private Button buttonContinue;
    private Button goBackButton;

    /**
     * Constructor initializing the GUI components
     */
    public AssignTasksGUI4(SepGUI sepGUI){

        this.sepGUI = sepGUI;
        titleLabel = new Label("Assign Employee");
        titleLabel.getStyleClass().add("heading");

        topPane = new HBox(500);
        topPane.getChildren().addAll(titleLabel);

        tableLabel = new Label("Choose an employee from the list");

        firstNameColumn = new TableColumn<Employee, String>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<AssignedEmployee, String>("firstName"));
        firstNameColumn.setPrefWidth(165);

        lastNameColumn = new TableColumn<Employee, String>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<AssignedEmployee, String>("lastName"));
        lastNameColumn.setPrefWidth(165);

        birthdayColumn = new TableColumn<Employee, MyDate>("Birthday");
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<AssignedEmployee, MyDate>("dateOfBirth"));
        birthdayColumn.setPrefWidth(165);

        allAssignedTasksTable = new TableView<AssignedEmployee>();
        allAssignedTasksTable.getColumns().addAll(firstNameColumn,lastNameColumn,birthdayColumn);
        allAssignedTasksTable.setPrefHeight(290);

        buttonContinue = new Button("Continue");
        goBackButton = new Button("Go back");

        bottomButtons = new HBox(8);
        bottomButtons.getChildren().addAll(buttonContinue, goBackButton);

        mainPane = new VBox(8);
        mainPane.getChildren().addAll(topPane, tableLabel, allAssignedTasksTable, bottomButtons);
    }

    public VBox getMainPane()
    {
        initializeTable();
        return mainPane;
    }

    public TableView<AssignedEmployee> getAllAssignedTasksTable()
    {
        return allAssignedTasksTable;
    }

    public Button getButtonContinue()
    {
        return buttonContinue;
    }

    public Button getGoBackButton()
    {
        return goBackButton;
    }
    private void initializeTable(){
        allAssignedTasksTable.getItems().clear();
        Project project = sepGUI.getAssignTasksGUI1().getAssignTasksTable().getSelectionModel().getSelectedItem();
        for (int i = 0; i < project.getAssignedEmployeeList().size(); i++) {
            allAssignedTasksTable.getItems().add(project.getAssignedEmployeeList().get(i));
        }
    }
    public boolean callContinueButton(){
        boolean gogo = true;
        if(sepGUI.getAssignTasksGUI3().getTasksTable().getSelectionModel().getSelectedItem()==null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Warning");
            alert.setContentText("No task was chosen!");
            alert.showAndWait();
            gogo = false;
        }
        else
        {
            String projectName = sepGUI.getAssignTasksGUI1().getAssignTasksTable().getSelectionModel().getSelectedItem().getName();
            String requirementName = sepGUI.getAssignTasksGUI2().getRequirementTable().getSelectionModel().getSelectedItem().toString();
            String taskName = sepGUI.getAssignTasksGUI3().getTasksTable().getSelectionModel().getSelectedItem().getName();
            titleLabel.setText(projectName + "\\" + requirementName + "\\" + taskName);
            gogo =true;
        }

        return gogo;
    }

}