package by.bsuir.ui.lists.controller;

import by.bsuir.ui.lists.entity.LevelRegion;
import by.bsuir.ui.lists.service.LevelRegionService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@FxmlView("/fxml/modal.fxml")
@Component
public class ModalController implements Initializable {

    @FXML
    private Button addNewLevelRegion;

    @FXML
    private TextField nameLevelRegionTextField;

    private LevelRegion levelRegion;

    private LevelRegionService levelRegionService;

    @Autowired
    public ModalController(LevelRegionService levelRegionService) {
        this.levelRegionService = levelRegionService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addNewLevelRegion.setOnMouseClicked(event -> {
            LevelRegion levelRegion = new LevelRegion();
            levelRegion.setName(nameLevelRegionTextField.getText());
            levelRegion.setLevelRegionId(this.levelRegion.getId());
            levelRegionService.save(levelRegion);
            ((Stage) addNewLevelRegion.getScene().getWindow()).close();
        });

    }

    public void setLevelRegion(LevelRegion levelRegion) {
        this.levelRegion = levelRegion;
    }


}
