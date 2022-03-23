package com.example.desktop.controllers;

import com.example.desktop.models.Model;
import com.example.desktop.models.Person;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.cell.MFXListCell;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class SubjectController implements Initializable {

    @FXML
    private MFXListView<Person> custList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> strings = Model.strings;
        ObservableList<Person> people = Model.people;
        StringConverter<Person> converter = FunctionalStringConverter.to(person -> (person == null) ? "" : person.getName() + " " + person.getSurname());


        custList.setItems(people);
        custList.setConverter(converter);
        custList.setCellFactory(person -> new PersonCellFactory(custList, person));
        custList.features().enableBounceEffect();
        custList.features().enableSmoothScrolling(0.5);

    }

   /* @FXML
    void changeColors(ActionEvent event) {
        custList.setTrackColor(ColorUtils.getRandomColor());
        custList.setThumbColor(ColorUtils.getRandomColor());
        custList.setThumbHoverColor(ColorUtils.getRandomColor());
    }

    @FXML
    void changeDepth(ActionEvent event) {
        DepthLevel newLevel = (custList.getDepthLevel() == DepthLevel.LEVEL0) ? DepthLevel.LEVEL2 : DepthLevel.LEVEL0;
        custList.setDepthLevel(newLevel);
    }*/


    public class PersonCellFactory extends MFXListCell<Person> {
        private final MFXFontIcon userIcon;

        public PersonCellFactory(MFXListView<Person> listView, Person data) {
            super(listView, data);

            userIcon = new MFXFontIcon("mfx-user", 18);
            userIcon.getStyleClass().add("user-icon");
            render(data);
        }

        @Override
        protected void render(Person data) {
            super.render(data);
            if (userIcon != null) getChildren().add(0, userIcon);
        }
    }
}
