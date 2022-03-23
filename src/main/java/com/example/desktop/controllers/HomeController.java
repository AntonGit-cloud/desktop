package com.example.desktop.controllers;

import com.example.desktop.models.Device;
import com.example.desktop.models.Model;
import com.example.desktop.models.Person;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.EnumFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.others.observables.When;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private MFXTableView<Person> table;

    @FXML
    private MFXPaginatedTableView<Device> paginated;

    @FXML
    private MFXTableView<Person> table1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        setupPaginated();
        setupTable1();

        table.autosizeColumnsOnInitialization();
        paginated.autosizeColumnsOnInitialization();
        table1.autosizeColumnsOnInitialization();

        When.onChanged(paginated.currentPageProperty())
                .then((oldValue, newValue) -> paginated.autosizeColumns())
                .listen();
    }

    private void setupTable() {
        MFXTableColumn<Person> nameColumn = new MFXTableColumn<>("Предмет", true, Comparator.comparing(Person::getName));
        MFXTableColumn<Person> surnameColumn = new MFXTableColumn<>("Пррепод", true, Comparator.comparing(Person::getSurname));
        MFXTableColumn<Person> ageColumn = new MFXTableColumn<>("Age", true, Comparator.comparing(Person::getAge));

        nameColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getName));
        surnameColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getSurname));
        ageColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getAge) {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        ageColumn.setAlignment(Pos.CENTER_RIGHT);

        table.getTableColumns().addAll(nameColumn, surnameColumn, ageColumn);
        table.getFilters().addAll(
                new StringFilter<>("Предмет", Person::getName),
                new StringFilter<>("Пррепод", Person::getSurname)
        );
        table.setItems(Model.people);

    }

    private void setupTable1() {
        MFXTableColumn<Person> nameColumn = new MFXTableColumn<>("Предмет", true, Comparator.comparing(Person::getName));
        MFXTableColumn<Person> surnameColumn = new MFXTableColumn<>("Пррепод", true, Comparator.comparing(Person::getSurname));
        MFXTableColumn<Person> ageColumn = new MFXTableColumn<>("Age", true, Comparator.comparing(Person::getAge));

        nameColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getName));
        surnameColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getSurname));
        ageColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getAge) {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        ageColumn.setAlignment(Pos.CENTER_RIGHT);

        table1.getTableColumns().addAll(nameColumn, surnameColumn, ageColumn);
        table1.getFilters().addAll(
                new StringFilter<>("Предмет", Person::getName),
                new StringFilter<>("Пррепод", Person::getSurname)
        );
        table1.setItems(Model.people);
    }

    private void setupPaginated() {
        MFXTableColumn<Device> idColumn = new MFXTableColumn<>("ID", false, Comparator.comparing(Device::getID));
        MFXTableColumn<Device> nameColumn = new MFXTableColumn<>("Name", false, Comparator.comparing(Device::getName));
        MFXTableColumn<Device> ipColumn = new MFXTableColumn<>("IP", false, Comparator.comparing(Device::getIP));
        MFXTableColumn<Device> ownerColumn = new MFXTableColumn<>("Owner", false, Comparator.comparing(Device::getOwner));
        MFXTableColumn<Device> stateColumn = new MFXTableColumn<>("State", false, Comparator.comparing(Device::getState));

        idColumn.setRowCellFactory(device -> new MFXTableRowCell<>(Device::getID));
        nameColumn.setRowCellFactory(device -> new MFXTableRowCell<>(Device::getName));
        ipColumn.setRowCellFactory(device -> new MFXTableRowCell<>(Device::getIP) {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        ownerColumn.setRowCellFactory(device -> new MFXTableRowCell<>(Device::getOwner));
        stateColumn.setRowCellFactory(device -> new MFXTableRowCell<>(Device::getState));
        ipColumn.setAlignment(Pos.CENTER_RIGHT);

        paginated.getTableColumns().addAll(idColumn, nameColumn, ipColumn, ownerColumn, stateColumn);
        paginated.getFilters().addAll(
                new IntegerFilter<>("ID", Device::getID),
                new StringFilter<>("Name", Device::getName),
                new StringFilter<>("IP", Device::getIP),
                new StringFilter<>("Owner", Device::getOwner),
                new EnumFilter<>("State", Device::getState, Device.State.class)
        );
        paginated.setItems(Model.devices);
    }
}
