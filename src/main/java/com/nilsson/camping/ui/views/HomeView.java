package com.nilsson.camping.ui.views;

import com.nilsson.camping.app.LanguageManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class HomeView extends VBox {

    public HomeView() {
        this.setPadding(new Insets(20));
        this.setSpacing(15);
        this.setAlignment(Pos.TOP_LEFT);

        // Logo Image Loading with Error Handling
        Label logo = new Label();
        Image logoImage = null;
        try {
            logoImage = new Image(getClass().getResource("/logo.png").toExternalForm());
        } catch (Exception e) {
            System.err.println("Error loading status image: " + "/logo.png" + ". Using text placeholder.");
        }

        if (logoImage != null) {
            ImageView statusImageView = new ImageView(logoImage);
            statusImageView.setFitWidth(800);
            statusImageView.setPreserveRatio(true);
            logo.setGraphic(statusImageView);
            logo.setAlignment(Pos.TOP_CENTER);
        } else {
            // Fallback text if image fails to load
            logo.setText("Wigell Camping");
        }

        String welcomeText = LanguageManager.getInstance().getString("txt.welcome");

        Label welcomeLabel = new Label(welcomeText);
        welcomeLabel.setWrapText(true);
        welcomeLabel.setMaxWidth(900);

        welcomeLabel.setStyle("-fx-font-size: 1.3em; " + "-fx-line-spacing: 8px; " + "-fx-text-alignment: LEFT;");

        this.getChildren().addAll(logo, welcomeLabel);
    }
}