package sample.MainPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.MainPage.MainPage;
import sample.Categories.Processors.Processors;
import sample.DataBase.ProcessorsService;

public class PopUp {
    @FXML
    private ComboBox comboBox1;
    private static int kp=0,kr=0,kg=0,ks=0;
    private static String nume;

    @FXML
    private TextField numeprodus,descriere,tip,pret,garantie;
    private Stage stage=new Stage();

    @FXML
    private void initialize(){
        if(MainPage.GetNr()==1) {
            comboBox1.getItems().add("Processors");
            comboBox1.getItems().add("RAM");
            comboBox1.getItems().add("Graphic Cards");
            comboBox1.getItems().add("Power Supply Unit");
        }
    }

    public static String returnNume()
    {
        return nume;
    }

    public void addProduct(ActionEvent event)
    {
        if(comboBox1.getSelectionModel().getSelectedItem().toString().equals("Processors"))
        {
            if(kp>10)
                return;
            ProcessorsService.addProcessors(numeprodus.getText(),pret.getText(),descriere.getText(),tip.getText(),garantie.getText(),0);
            kp++;
        }
        stage.close();


    }

    public static int GetKP()
    {
        return kp;
    }
    public static int GetKS()
    {
        return ks;
    }
    public static int GetKG()
    {
        return kg;
    }
    public static int GetKR()
    {
        return kr;
    }
}
