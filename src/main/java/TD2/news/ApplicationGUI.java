package TD2.news;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.concurrent.atomic.AtomicInteger;

public class ApplicationGUI extends Application {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private Stage window;
    private Scene main;

    public static void main(String[] args) {
        launch(args);
    }

    private Scene renderCreation() {
        VBox vBox = new VBox();
        Label label = new Label("Data base was created");
        vBox.getChildren().addAll(label, renderBackBtn());
        vBox.setAlignment(Pos.CENTER);
        return new Scene(vBox, WIDTH, HEIGHT);
    }

    private Scene renderRestoreDB() {
        Text fileName = new Text("Name of DB");
        TextField textFieldDBName = new TextField();
        Button restore = new Button("Restore");
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(WIDTH, HEIGHT);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(fileName, 0, 0);
        gridPane.add(textFieldDBName, 1, 0);
        gridPane.add(restore, 0, 1);
        gridPane.add(renderBackBtn(), 1, 1);
        Label success = new Label("Data was restored.");
        Label error = new Label("Wrong input/output or object does not exist. Try again.");

        restore.setOnAction(e -> {
            try {
                BaseDeNews.restoreDataBase(textFieldDBName.getText().trim());
                gridPane.getChildren().remove(error);
                gridPane.add(success, 1, 2);
            } catch (IOException | ClassNotFoundException e1) {
                gridPane.getChildren().remove(success);
                gridPane.add(error, 0, 2);
            }
        });
        return new Scene(gridPane);
    }

    private Scene renderSaveDB() {
        Label text = new Label("Please, enter the name of file, where you want to save a db");
        Text fileName = new Text("Name of DB");
        TextField textFieldDBName = new TextField();
        Button restore = new Button("Save");
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(WIDTH, HEIGHT);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text, 0, 0, 3, 1);
        gridPane.add(fileName, 0, 1);
        gridPane.add(textFieldDBName, 1, 1);
        gridPane.add(restore, 0, 2);
        gridPane.add(renderBackBtn(), 1, 2);
        Label success = new Label("Data was saved.");
        Label error = new Label("Wrong input/output or object does not exist. Try again.");

        restore.setOnAction(e -> {
            try {
                BaseDeNews.saveStateOfDataBase(textFieldDBName.getText().trim());
                gridPane.getChildren().remove(error);
                gridPane.add(success, 1, 3);
            } catch (IOException e1) {
                gridPane.getChildren().remove(success);
                gridPane.add(error, 0, 3);
            }
        });
        return new Scene(gridPane);
    }

    private Scene createNews() {
        Text title = new Text("Title");
        TextField textFieldTitle = new TextField();
        Text date = new Text("Date (dd-mm-yyyy)");
        TextField textFieldDate = new TextField();
        Text author = new Text("Author");
        TextField textFieldAuthor = new TextField();
        Text source = new Text("Source");
        TextField textFieldSource = new TextField();
        Text longVersionOfText = new Text("Long version of the article)");
        TextField textFieldLongVersionOfText = new TextField();

        Text paperVersion = new Text("Is there a paper version?");
        TilePane tilePane = new TilePane();
        ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Yes");
        rb1.setToggleGroup(group);
        RadioButton rb2 = new RadioButton("No");
        rb2.setToggleGroup(group);
        rb2.setSelected(true);
        tilePane.getChildren().addAll(rb1, rb2);
        Text content = new Text("Content");
        TextArea textAreaContent = new TextArea();
        textAreaContent.setMaxWidth(200);
        textAreaContent.setMaxHeight(100);
        Button createBtn = new Button("Create");
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(WIDTH, HEIGHT);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.TOP_CENTER);

        gridPane.add(title, 0, 0);
        gridPane.add(textFieldTitle, 1, 0);
        gridPane.add(date, 0, 1);
        gridPane.add(textFieldDate, 1, 1);
        gridPane.add(author, 0, 2);
        gridPane.add(textFieldAuthor, 1, 2);
        gridPane.add(source, 0, 3);
        gridPane.add(textFieldSource, 1, 3);
        gridPane.add(longVersionOfText, 0, 4);
        gridPane.add(textFieldLongVersionOfText, 1, 4);
        gridPane.add(paperVersion, 0, 5);
        gridPane.add(tilePane, 1, 5);
        gridPane.add(content, 0, 6);
        gridPane.add(textAreaContent, 1, 6);
        gridPane.add(renderBackBtn(), 0, 7);
        gridPane.add(createBtn, 1, 7);

        Label wrongDate = new Label("Wrong format of date.");
        Label wrongURL = new Label("Wrong URL.");
        Label wrongURLong = new Label("Wrong URL.");
        Label success = new Label("News was created");
        Label error = new Label("Please, fill fields with correct information");

        createBtn.setOnAction((e) -> {
            String tittleNews = textFieldTitle.getText().trim();
            String dateNews = textFieldDate.getText().trim();
            String auuthorNews = textFieldAuthor.getText().trim();
            String sourceNews = textFieldSource.getText().trim();
            String longVersionNews = textFieldLongVersionOfText.getText().trim();
            boolean isPaper = rb1.isSelected();
            String contentNews = textAreaContent.getText().trim();
            LocalDate localDate = null;
            try {
                localDate = LocalDate.parse(dateNews, FORMAT);
                gridPane.getChildren().remove(wrongDate);
            } catch (DateTimeParseException e1) {
                gridPane.add(wrongDate, 2, 1);
            }
            URL sourceURL = null;
            try {
                sourceURL = new URL(sourceNews);
                gridPane.getChildren().remove(wrongURL);
            } catch (MalformedURLException e1) {
                gridPane.add(wrongURL, 2, 3);
            }

            URL longVersionNewsURL = null;
            try {
                longVersionNewsURL = new URL(longVersionNews);
                gridPane.getChildren().remove(wrongURLong);
            } catch (MalformedURLException e1) {
                gridPane.add(wrongURLong, 2, 4);
            }
            if (longVersionNewsURL != null && sourceURL != null && localDate != null
                    && !tittleNews.isEmpty() && !auuthorNews.isEmpty()
                    && !contentNews.isEmpty()) {
                News news = new Article(tittleNews, localDate, auuthorNews, sourceURL, contentNews, longVersionNewsURL, isPaper );
                BaseDeNews.ajoute(news);
                gridPane.getChildren().remove(error);
                gridPane.add(success, 0, 8, 4, 1);
            } else {
                gridPane.getChildren().remove(success);
                gridPane.add(error, 0, 8, 4, 1);
            }
        });

        return new Scene(gridPane);
    }

    private Scene showNews() {
        AtomicInteger counter = new AtomicInteger(1);
        Label text = new Label("News: ");
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(700, 600);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.add(text, 0, 0);
        gridPane.add(renderBackBtn(), 5, 0);
        Scene scene = new Scene(gridPane);
        BaseDeNews.getNewsSet().forEach((news) -> {
            Text newsText = new Text(news.toString());
            newsText.wrappingWidthProperty().bind(scene.widthProperty().subtract(15));
            gridPane.add(newsText, 0, counter.get(), 5, 1);
            counter.getAndIncrement();
        });
        return scene;
    }

    private Scene renderMenu() {
        Button createDateBase = new Button("Create a data base");
        createDateBase.setOnAction((e) -> {
            BaseDeNews.initialise();
            window.setScene(renderCreation());
        });

        Button restoreDateBase = new Button("Restore a data base");
        restoreDateBase.setOnAction((e) -> {
            window.setScene(renderRestoreDB());
        });

        Button saveDateBase = new Button("Save current data base");
        saveDateBase.setOnAction((e) -> {
            window.setScene(renderSaveDB());
        });

        Button printData = new Button("Show data");
        printData.setOnAction(e -> {
            window.setScene(showNews());
        });

        Button createNews = new Button("Create news");
        createNews.setOnAction(e -> {
            window.setScene(createNews());
        });
        Button deleteNews = new Button("Delete news");

        Button search = new Button("Search");
        Button exit = new Button("Exit");

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(WIDTH, HEIGHT);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(createDateBase, 0, 0);
        gridPane.add(restoreDateBase, 0, 1);
        gridPane.add(saveDateBase, 0, 2);
        gridPane.add(printData, 0, 3);
        gridPane.add(createNews, 0, 4);
        gridPane.add(deleteNews, 0, 5);
        gridPane.add(search, 0, 6);
        gridPane.add(exit, 0, 7);

        return new Scene(gridPane, WIDTH, HEIGHT);
    }

    private Button renderBackBtn() {
        Button back = new Button("Back");
        back.setOnAction(e -> window.setScene(main));
        return back;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        main = renderMenu();
        main.getStylesheets().add("css/style.css");
        primaryStage.setTitle("News");
        primaryStage.setScene(main);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
