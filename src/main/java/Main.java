import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import oauth.Parser;
import oauth.VKHelper;

import java.io.IOException;
import java.util.List;

public class Main extends Application{
    public static final String REDIRECT_URL = "https://oauth.vk.com/blank.html";
    public static final String VK_AUTH_URL = "https://oauth.vk.com/authorize?client_id=7721096&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends&response_type=token&v=5.59";
    private static String tokenUrl;


    public static void main(String[] args) throws IOException {
        getTokenUrl();
        String token = Parser.parseToken(tokenUrl);
        StringBuilder JSONresponse = VKHelper.getUserData(token);
        List<String> userData;
        userData = Parser.parseJSON(JSONresponse);
        System.out.println("First name is " + userData.get(0) + ", Last name is " + userData.get(1));
    }

    public static void getTokenUrl() {
        launch(Main.class);
    }

    @Override
    public void start(final Stage primaryStage) {
        final WebView view = new WebView();
        final WebEngine engine = view.getEngine();
        engine.load(VK_AUTH_URL);

        primaryStage.setScene(new Scene(view));
        primaryStage.show();

        engine.locationProperty().addListener(new ChangeListener<String>(){
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.startsWith(REDIRECT_URL)){
                    tokenUrl = newValue;
                    primaryStage.close();
                }
            }
        }
        );
    }
}