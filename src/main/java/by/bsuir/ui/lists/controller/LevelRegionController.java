package by.bsuir.ui.lists.controller;

import by.bsuir.ui.lists.entity.LevelRegion;
import by.bsuir.ui.lists.service.LevelRegionService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@FxmlView("/fxml/main.fxml")
@Component
public class LevelRegionController {

    private final static String PARENT_REGION = "*";

    private final static String DEFAULT_ADD_PARAM = "Добавить";

    private final static String DEFAULT_REMOVE_PARAM = "Удалить";

    private ContextMenu contextMenu;

    private LevelRegionService levelRegionService;

    private FxWeaver fxWeaver;

    private String selectedId;

    @FXML
    private TreeView<LevelRegion> treeViewPanelId;

    @FXML
    private Button buttonUpdate;

    @Autowired
    public LevelRegionController(LevelRegionService levelRegionService, FxWeaver fxWeaver) {
        this.levelRegionService = levelRegionService;
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() throws Exception {

        createContextMenu();

        fillTreeTable();

        treeViewPanelId.setOnMouseClicked(event -> {

            if (event.getClickCount() == 2 && event.getButton().equals(MouseButton.PRIMARY)) {
                showModalView();
            }

            if (contextMenu.isShowing()) {
                contextMenu.hide();
                this.selectedId = null;
            }

            if (event.getButton().equals(MouseButton.SECONDARY)) {
                contextMenu.show(treeViewPanelId, event.getScreenX(), event.getScreenY());
                TreeItem<LevelRegion> selectedItem = treeViewPanelId.getSelectionModel().getSelectedItem();
                this.selectedId = selectedItem.getValue().getId();
            }

        });

        buttonUpdate.setOnMouseClicked(event -> fillTreeTable());

    }

    private void createContextMenu() {
        MenuItem addItem = new MenuItem(DEFAULT_ADD_PARAM);
        MenuItem removeItem = new MenuItem(DEFAULT_REMOVE_PARAM);

        addItem.setOnAction(changeTabPlacement());
        removeItem.setOnAction(changeTabPlacement());

        this.contextMenu = new ContextMenu(addItem, removeItem);
        contextMenu.hide();
    }

    private void fillTreeTable() {

        LevelRegion parentLevelRegion = levelRegionService.findById(PARENT_REGION);
        TreeItem<LevelRegion> levelRegionTreeItem = new TreeItem<>(parentLevelRegion);
        treeViewPanelId.setRoot(treeRecursive(parentLevelRegion, levelRegionTreeItem));

    }

    private TreeItem<LevelRegion> treeRecursive(LevelRegion levelRegion, TreeItem<LevelRegion> levelRegionTreeItem) {

        if (levelRegion != null) {
            for (LevelRegion lr : levelRegionService.findByLevelRegionId(levelRegion.getId())) {
                levelRegionTreeItem.getChildren().add(treeRecursive(lr, new TreeItem<>(lr)));
            }
        }
        return levelRegionTreeItem;
    }

    private EventHandler<ActionEvent> changeTabPlacement() {
        return event -> {
            MenuItem mItem = (MenuItem) event.getSource();
            String selected = mItem.getText();
            if (DEFAULT_ADD_PARAM.equalsIgnoreCase(selected)) {
                showModalView();
            }
            if (DEFAULT_REMOVE_PARAM.equalsIgnoreCase(selected)) {
                levelRegionService.remove(this.selectedId);
            }

        };
    }

    private void showModalView() {
        TreeItem<LevelRegion> selectedItem = treeViewPanelId.getSelectionModel().getSelectedItem();

        Stage stage = new Stage();
        Parent root = fxWeaver.loadView(ModalController.class);

        ModalController modalController = fxWeaver.getBean(ModalController.class);
        modalController.setLevelRegion(selectedItem.getValue());

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
//        stage.initOwner(
//                ((Node) event.getSource()).getScene().getWindow());
        stage.showAndWait();
    }
}
