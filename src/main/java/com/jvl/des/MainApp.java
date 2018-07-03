    package com.jvl.des;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import logic.DbInitializer;
    


public class MainApp extends Application {

    
    public MainApp() {
        DbInitializer dbInit = new DbInitializer();
        dbInit.initialize();
    }
    
        /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
   
    @Override
    public void start(Stage stage) throws Exception {
        UIManager manager = new UIManager(stage);
        manager.initialize();
        manager.createLogin();        
    }
}
