package sample.MainPage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.DataBase.OrderService;

import java.util.ArrayList;
import java.util.List;

public class PopUpAccept {

    private static List<Text> numeProdus = new ArrayList<>();
    private static List<Text> cantitate = new ArrayList<>();
    private static List<Button> AcceptButton = new ArrayList<>();
    private static List<Button> DeclineButton = new ArrayList<>();
    private static VBox vbox1 = new VBox();
    private static Pane[] pane1 = new Pane[10];
    @FXML
    private AnchorPane CentralAnchor;
    @FXML
    private Button Close;
    private Stage stage;


    private void initVBOX(){
        vbox1.setPadding(new Insets(10,10,10,10));
        vbox1.setSpacing(50);
    }

    public static void Test(String numeProduse,String cantitate1){
        for(int i=0; i<10; i++){
            numeProdus.add(i,new Text(numeProduse));
            numeProdus.get(i).setLayoutX(0);
            numeProdus.get(i).setLayoutY(3);
            cantitate.add(i,new Text(cantitate1));
            cantitate.get(i).setLayoutY(3);
            cantitate.get(i).setLayoutX(100);
            AcceptButton.add(i,new Button("Accept"));
            AcceptButton.get(i).setLayoutX(520);
            DeclineButton.add(i,new Button("Decline"));
            DeclineButton.get(i).setLayoutX(460);
            pane1[i]= new Pane();
            pane1[i].setLayoutX(300);
            pane1[i].setLayoutY(50);
            pane1[i].getChildren().addAll(numeProdus.get(i),cantitate.get(i),AcceptButton.get(i),DeclineButton.get(i));
        }

        for(int i=0; i<10; i++){
            final int nr = i;
            AcceptButton.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    OrderService.SetStatusOrder(numeProdus.get(nr).getText(),"Acceptat");
                    AcceptButton.get(nr).setVisible(false);
                    DeclineButton.get(nr).setVisible(false);
                    return;
                }
            });
        }

        for(int i=0; i<10; i++){
            final int nr = i;
            DeclineButton.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    OrderService.SetStatusOrder(numeProdus.get(nr).getText(),"Respins");
                    AcceptButton.get(nr).setVisible(false);
                    DeclineButton.get(nr).setVisible(false);
                    return;
                }
            });
        }

        vbox1.getChildren().add(pane1[PopUp.GetKP()]);
    }

    public void init(){

        vbox1=new VBox();
        initVBOX();
        CentralAnchor.getChildren().add(vbox1);
    }

    @FXML
    public void initialize(){
        initVBOX();
        init();
    }

    public void CloseWindow(ActionEvent event)throws Exception{
        if(event.getSource() == Close){
            stage = (Stage) Close.getScene().getWindow();
            stage.close();
        }
    }

}
