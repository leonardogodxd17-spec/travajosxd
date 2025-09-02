package pe.edu.upeu.asistencia.control;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;   

import java.io.IOException;
import java.util.Map;

@Controller
public class MainguiController {

    @FXML
    private BorderPane bp;
    @FXML
    MenuBar menuBar;
    @FXML
    TabPane tabPane;
    @FXML
    MenuItem menuItem1, menuItemC;
    @Autowired
    ApplicationContext context;

     @FXML
    public void initialize(){
         MenuItemListener mIL = new MenuItemListener();
         menuItem1.setOnAction(mIL::handle);
         menuItemC.setOnAction(mIL::handle);

     }

     class MenuItemListener {
         Map<String, String[]> menuconfig=Map.of(
                 "menuItem1" , new String[]{"/fmxl/main_participante.fxml", "Reg.participante", "T"},
                 "menuItemc", new  String[]{"/fmxl/login.fmxl", "Salir", "C"}
         );
         public void handle(ActionEvent e){
             String id=((MenuItem)e.getSource()).getId();
             if(menuconfig.containsKey(id)){
                 String[] items=menuconfig.get(id);
                 if(items[2].equals("C")){
                     Platform.exit();
                     System.exit(0);
                 }else{
                     abrirTAbPaneFXLM(items[0],items[1]);
                 }
             }

         }
         private void abrirTAbPaneFXLM(String fxmlPAth, String tittle){
             try{
                 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPAth));
                 fxmlLoader.setControllerFactory(context::getBean);
                 Parent root = fxmlLoader.load();
                 ScrollPane scrollPane = new ScrollPane(root);
                 scrollPane.setFitToWidth(true);
                 scrollPane.setFitToHeight(true);
                 Tab newTab = new Tab(tittle, scrollPane);
                 tabPane.getTabs().clear();
                 tabPane.getTabs().add(newTab);


             }catch(IOException ex){
                 throw  new RuntimeException(ex);

             }
         }


     }
     class MenuCListener {
        public void handle(Event e){

        }
     }




}
