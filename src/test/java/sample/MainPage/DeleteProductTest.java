package sample.MainPage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import sample.DataBase.*;
import sample.exceptions.ProductAlreadyExists;
import sample.exceptions.UsernameAlreadyExistException;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class DeleteProductTest {
    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        ProcessorsService.initDataBaseforProcessors();
        GraphicCardsService.initDataBaseforGraphicCards();
        RAMService.initDataBaseforRAM();
        SourcesService.initDataBaseforSources();
        UserService.initDataBase();
        TempOrderService.initDataBase();
        OrderService.initDataBase();
        FinalStatusService.initDataBase();
    }

    @AfterEach
    void close(){
        UserService.closeDataBase();
        ProcessorsService.closeDataBase();
        GraphicCardsService.closeDataBase();
        RAMService.closeDataBase();
        SourcesService.closeDataBase();
        TempOrderService.closeDataBase();
        OrderService.closeDataBase();
        FinalStatusService.closeDataBase();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));
        primaryStage.setTitle("Home");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Test
    void DeleteProductsTest(FxRobot robot) throws UsernameAlreadyExistException, ProductAlreadyExists {
        robot.clickOn("#login");

        UserService.addUser("seller", "test", "mail", "Seller", true, 2);
        robot.clickOn("#username");
        robot.write("seller");
        robot.clickOn("#password");
        robot.write("test");
        robot.clickOn("#login");

        ProcessorsService.addProcessors("Processor", "300$", "New", "Proccesor", "1 years", 2, 10);
        ProcessorsService.addProcessors("Processor1", "300$", "New", "Proccesor", "1 years", 2, 10);
        RAMService.addRAM("Ram", "210$", "New", "Rams", "2 years", 2, 10);
        SourcesService.addSource("Power Unit Supply", "400$", "New", "PUS", "2 years", 2, 10);
        GraphicCardsService.addGraphic("Graphic Card", "210$", "New", "Graphics", "2 years", 2, 10);

        robot.clickOn("#Delete");

        robot.clickOn("#combo");
        robot.clickOn("Processors");

        robot.clickOn("#b1");
        assertThat(ProcessorsService.getAllProccesors()).size().isEqualTo(1);
        robot.clickOn("#b2");
        assertThat(ProcessorsService.getAllProccesors()).size().isEqualTo(0);

        robot.clickOn("#combo");
        robot.clickOn("RAM");
        robot.clickOn("#b1");
        assertThat(RAMService.getAllProduct()).size().isEqualTo(0);

        robot.clickOn("#close");





    }

}