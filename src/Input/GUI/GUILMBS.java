package Input.GUI;

import Input.CommandInterpretter;
import Library.Day;
import Library.Library;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.Date;
import java.util.GregorianCalendar;

public class GUILMBS extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Group root = new Group();

        GridPane gridPane = new GridPane();

        String serializeFileName = "library.ser";
        GregorianCalendar start = new GregorianCalendar();
        start.setTime(new Date());
        Day startDayObv = new Day(start);


        Library library = null;
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        try {
            fileInputStream = new FileInputStream(serializeFileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            library = (Library) objectInputStream.readObject();
            objectInputStream.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found: " + fnfe.getMessage());
            //Close all I/O streams
            //Handle the exception here
            library = new Library(startDayObv);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            //Close all I/O streams
            //Handle the exception here
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            //Close all I/O streams
            //Handle the exception here
        }


        CommandInterpretter commandInterpretter = new CommandInterpretter(startDayObv);


        mainPage(gridPane,library,commandInterpretter);

        root.getChildren().add(gridPane);
        Scene scene = new Scene(root, 600, 300);

        primaryStage.setTitle("Library Book Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static GridPane mainPage(GridPane gridPane,Library library,CommandInterpretter interpretter) {
        gridPane.getChildren().clear();
        Label title = new Label("Library Book Management System");

        Label selectCommand = new Label("Please select a command: ");

        Button advanceTime = new Button("Advance Time");
        advanceTime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AdvanceTimeGUI advanceTimeGUI = new AdvanceTimeGUI(gridPane,library,interpretter);
                advanceTimeGUI.advanceTimeGridPane();
            }
        });
        Button beginVisit = new Button("Begin Visit");
        beginVisit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                BeginVisitGUI beginVisitGUI = new BeginVisitGUI(gridPane,library,interpretter);
                beginVisitGUI.beginVisitGridPane();

            }
        });
        Button libraryBookSearch = new Button("Library Book Search");
        libraryBookSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LibraryBookSearchGUI libraryBookSearchGUI = new LibraryBookSearchGUI(gridPane,library,interpretter);
                libraryBookSearchGUI.libraryBookSearchGridPane();
            }
        });
        Button borrowBook = new Button("Borrow Book");
        borrowBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                BorrowBookGUI borrowBookGUI = new BorrowBookGUI(gridPane,library,interpretter);
                borrowBookGUI.borrowBookGridPane();
            }
        });
        Button borrowedBooksSearch = new Button("Borrowed Books Search");
        borrowedBooksSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                BorrowedBooksSearchGUI borrowedBooksSearchGUI = new BorrowedBooksSearchGUI(gridPane,library,interpretter);
                borrowedBooksSearchGUI.borrowedBooksSearchGridPane();
            }
        });
        Button currentDateTime = new Button("Current Date Time");
        currentDateTime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CurrentDateTimeGUI currentDateTimeGUI = new CurrentDateTimeGUI(gridPane,library,interpretter);
                currentDateTimeGUI.dateTimeGridPane();
            }
        });
        Button endVisit = new Button("End Visit");
        endVisit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                EndVisitGUI endVisitGUI = new EndVisitGUI(gridPane,library,interpretter);
                endVisitGUI.endVisitGridPane();
            }
        });
        Button newVisitor = new Button("Register Visitor");
        newVisitor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                RegisterVisitorGUI registerVisitorGUI = new RegisterVisitorGUI(gridPane,library,interpretter);
                registerVisitorGUI.newVisitorGrid();
            }
        });
        Button payFine = new Button("Pay Fine");
        payFine.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PayFineGUI payFineGUI = new PayFineGUI(gridPane,library,interpretter);
                payFineGUI.payFineGridPane();
            }
        });
        Button purchaseBook = new Button("Purchase Book");
        purchaseBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PurchaseBookGUI purchaseBookGUI = new PurchaseBookGUI(gridPane,library,interpretter);
                purchaseBookGUI.purchaseBookGrid();
            }
        });
        Button libraryReport = new Button("Library Report");
        libraryReport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LibraryReportGUI libraryReportGUI = new LibraryReportGUI(gridPane,library,interpretter);
                libraryReportGUI.libraryReportGridPane();
            }
        });
        Button returnBook = new Button("Return Book");
        returnBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ReturnBookGUI returnBookGUI = new ReturnBookGUI(gridPane,library,interpretter);
                returnBookGUI.returnBookGridPane();
            }
        });
        Button bookStoreSearch = new Button("Store Search");
        bookStoreSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                BookStoreSearchGUI bookStoreSearchGUI = new BookStoreSearchGUI(gridPane,library,interpretter);
                bookStoreSearchGUI.bookStoreSearchGridPane();
            }
        });
        Button quit = new Button("Quit");
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                QuitGUI quitGUI = new QuitGUI(gridPane,library,interpretter);
                quitGUI.quitGridPane();
            }
        });


        gridPane.add(title, 1, 0);
        gridPane.add(selectCommand, 1, 1);
        gridPane.add(beginVisit, 1, 2);
        gridPane.add(advanceTime, 1, 3);
        gridPane.add(libraryBookSearch, 1, 4);
        gridPane.add(returnBook, 1, 5);
        gridPane.add(libraryReport, 1, 6);
        gridPane.add(currentDateTime, 2, 2);
        gridPane.add(borrowedBooksSearch, 2, 3);
        gridPane.add(payFine, 2, 4);
        gridPane.add(purchaseBook, 2, 5);
        gridPane.add(quit, 2, 6);
        gridPane.add(newVisitor, 3, 2);
        gridPane.add(endVisit, 3, 3);
        gridPane.add(borrowBook, 3, 4);
        gridPane.add(bookStoreSearch, 3, 5);


        return gridPane;
    }

    public static void main(String[] args) {
        launch(args);


    }
}
