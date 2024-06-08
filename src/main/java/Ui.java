import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
//import javafx.scene.shape.Path;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;


/**
 * UI class
 * ELL
 *
 * @since 2024/6/2 12:19
 **/
public class Ui extends Application {
    public Pane root;
    public Scene scene;
    public int state = 0;
    public final String admin = "admin";
    public final String password = "123456";
    public Button loginButton;
    public Button registerButton;
    public Text loginText;
    public Text registerText;
    public Text loginText2;
    public TextField loginTextField;
    public TextField registerTextField;
    public TextField passwordTextField;
    public TextField passwordTextField2;
    Button back;
    ArrayList<Admins> admins = new ArrayList<>();
    ArrayList<Reader> readers = new ArrayList<>();
    //ArrayList<Book> books = new ArrayList<>();
    TableView<AdminsFX> adminTable = new TableView<>();
    TableView<Reader> readerTable = new TableView<>();
    TableView<Book> bookTable = new TableView<>();
    TableView<BorrowDB> borrowTable = new TableView<>();
    TableView<ReserveDB> reserveTable = new TableView<>();
    TableView<Message> messageTable = new TableView<>();
    TableView<MessageFX> messageTable2 = new TableView<>();
    Reader reader = new Reader();
    String filePath = "";

    public boolean exist(String filePath) {
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }

    public void drawSelf(Stage stage) {
        stage.setWidth(700);
        root.getChildren().clear();
        Rectangle rectangle = new Rectangle(120, 700);
        rectangle.setFill(Color.LIGHTBLUE);
        rectangle.setStroke(Color.BLACK);
        Rectangle bbb = new Rectangle(110, 110);
        bbb.setFill(Color.BLACK);
        bbb.setLayoutX(5);
        bbb.setLayoutY(5);
        root.getChildren().add(rectangle);
        root.getChildren().add(bbb);
        Image image;
        if (exist("./src/main/pic/" + reader.getId() + ".png")) {
            image = new Image("fILe:./src/main/pic/" + reader.getId() + ".png");
        }
        else {
            image = new Image("file:./src/main/pic/tx.jpg");
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setLayoutX(10);
        imageView.setLayoutY(10);
        root.getChildren().add(imageView);
        Rectangle ground = new Rectangle(700, 700);
        ground.setFill(Color.GRAY);
        ground.setOpacity(0.5);
        ground.setLayoutX(0);
        ground.setLayoutY(0);
        ImageView imageView1 = new ImageView(image);
        imageView1.setFitHeight(400);
        imageView1.setFitWidth(400);
        imageView1.setLayoutX(150);
        imageView1.setLayoutY(150);
        //imageView1.setOpacity(0.8);
        imageView.setOnMouseClicked(e -> {
            root.getChildren().addAll(ground);
            root.getChildren().addAll(imageView1);
            imageView1.setOnMouseClicked(e1 -> {
                root.getChildren().removeAll(ground, imageView1);
            });
            ground.setOnMouseClicked(e1 -> {
                root.getChildren().removeAll(ground, imageView1);
            });
        });
        Button back = new Button("返回");
        setButton(back, 20, 470, 80, 30);
        Button move0 = new Button("修改姓名");
        Button move1 = new Button("修改账号");
        Button move2 = new Button("修改年龄");
        Button move3 = new Button("修改电话");
        Button tx = new Button("修改头像");
        Text id = new Text("账号：" + reader.getId());
        Text name = new Text("姓名：" + reader.getName());
        Text age = new Text("年龄：" + reader.getAge());
        Text phone = new Text("电话：" + reader.getPhone());
        setButton(tx, 20, 120, 80, 30);
        setButton(move0, 20, 170, 80, 30);
        setButton(move1, 20, 220, 80, 30);
        setButton(move2, 20, 270, 80, 30);
        setButton(move3, 20, 320, 80, 30);
        name.setLayoutX(250);
        name.setLayoutY(100);
        id.setLayoutX(250);
        id.setLayoutY(180);
        age.setLayoutX(250);
        age.setLayoutY(260);
        phone.setLayoutX(250);
        phone.setLayoutY(340);
        name.setFont(Font.font(30));
        id.setFont(Font.font(30));
        age.setFont(Font.font(30));
        phone.setFont(Font.font(30));
        Button move4 = new Button("修改密码");
        setButton(move4, 20, 370, 80, 30);
        Button move5 = new Button("注销账号");
        setButton(move5, 20, 420, 80, 30);
        move5.setOnMouseClicked(e->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("确认");
            alert.setHeaderText("确认注销账号吗？");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ReaderDB readerDB = new ReaderDB();
                readerDB.deleteReader(reader.getId());
                begin(stage);
                reader = null;
            }
        });

        tx.setOnMouseClicked(e->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择头像");
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                filePath = selectedFile.getAbsolutePath();
                ImageDB imageDB = new ImageDB();
                imageDB.writeImageToDB(new File(filePath), reader.getId());
                imageDB.readImageFromDB(reader.getId());
                Image image2 = new Image("file:./src/main/pic/" + reader.getId() + ".png");
                imageView.setImage(image2);
                imageView1.setImage(image2);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("成功");
                alert.setHeaderText("头像修改成功");
                alert.setContentText("");
                alert.showAndWait();
            }
        });

        root.getChildren().addAll(move2, move0, move1, move3, move4, move5, tx);
        root.getChildren().addAll(id,name,age,phone,back);
        back.setOnMouseClicked(e->{
            stage.setWidth(1000);
            drawUser(stage);
        });
        move0.setOnMouseClicked(e->{
            TextInputDialog dialog = new TextInputDialog(reader.getName());
            dialog.setTitle("修改个人信息");
            dialog.setHeaderText("请输入新的信息");
            dialog.setContentText("姓名:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(n->{
                ReaderDB reader1DB = new ReaderDB();
                if (n.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText("输入不能为空");
                    alert.setContentText("错误的输入");
                    alert.showAndWait();
                    return;
                }
                reader1DB.updateReaderName(n, reader.getId());
                name.setText("姓名：" + n);
                reader.setName(n);
            });
        });

        move1.setOnMouseClicked(e->{
            TextInputDialog dialog = new TextInputDialog(reader.getId());
            dialog.setTitle("修改个人信息");
            dialog.setHeaderText("请输入新的信息");
            dialog.setContentText("账号:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(n->{
                ReaderDB reader1DB = new ReaderDB();
                if (n.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText("输入不能为空");
                    alert.setContentText("错误的输入");
                    alert.showAndWait();
                    return;
                }
                ArrayList<Reader> readerList = reader1DB.getAllReaders();
                for (Reader r : readerList) {
                    if (r.getId().equals(n)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("错误");
                        alert.setHeaderText("账号已存在或本id未改变");
                        alert.setContentText("错误的输入");
                        alert.showAndWait();
                        return;
                    }
                }
                reader1DB.updateReaderId(n, reader.getId());
                id.setText("账号：" + n);
                reader.setId(n);
            });
        });

        move2.setOnMouseClicked(e->{
            TextInputDialog dialog = new TextInputDialog(Integer.toString(reader.getAge()));
            dialog.setTitle("修改个人信息");
            dialog.setHeaderText("请输入新的信息");
            dialog.setContentText("年龄:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(n->{
                if (n.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText("输入不能为空");
                    alert.setContentText("错误的输入");
                    alert.showAndWait();
                    return;
                }
                ReaderDB reader1DB = new ReaderDB();
                reader1DB.updateReaderAge(Integer.parseInt(n), reader.getId());
                age.setText("年龄：" + n);
                reader.setAge(Integer.parseInt(n));
            });
        });

        move3.setOnMouseClicked(e->{
            TextInputDialog dialog = new TextInputDialog(reader.getPhone());
            dialog.setTitle("修改个人信息");
            dialog.setHeaderText("请输入新的信息");
            dialog.setContentText("电话:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(n->{
                if (n.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText("输入不能为空");
                    alert.setContentText("错误的输入");
                    alert.showAndWait();
                    return;
                }
                ReaderDB reader1DB = new ReaderDB();
                reader1DB.updateReaderPhone(n, reader.getId());
                phone.setText("电话：" + n);
                reader.setPhone(n);
            });
        });

        move4.setOnMouseClicked(e->{
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("修改密码");
            dialog.setHeaderText("请输入旧的密码");
            dialog.setContentText("密码:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(n->{
                if (n.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText("输入不能为空");
                    alert.setContentText("错误的输入");
                    alert.showAndWait();
                    return;
                }
                if (!n.equals(reader.getPassword())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText("密码错误");
                    alert.setContentText("错误的输入");
                    alert.showAndWait();
                    return;
                }
                TextInputDialog dialog1 = new TextInputDialog();
                dialog1.setTitle("修改密码");
                dialog1.setHeaderText("请输入新的密码");
                dialog1.setContentText("密码:");
                Optional<String> result1 = dialog1.showAndWait();
                result1.ifPresent(n1->{
                    if (n1.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("错误");
                        alert.setHeaderText("输入不能为空");
                        alert.setContentText("错误的输入");
                        alert.showAndWait();
                        return;
                    }
                    ReaderDB reader1DB = new ReaderDB();
                    reader1DB.updateReaderPassword(n1, reader.getId());
                    reader.setPassword(n1);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("成功");
                    alert.setHeaderText("修改成功");
                    alert.setContentText("密码修改成功");
                    alert.showAndWait();
                });
            });
        });

    }

    public void drawMessage(Stage stage) {
        root.getChildren().clear();
        ImageView imageView = new ImageView(new Image("file:pic/back2.jpg"));
        imageView.setFitHeight(700);
        imageView.setFitWidth(1000);
        imageView.setX(0);
        imageView.setY(0);
        root.getChildren().add(imageView);
        TableColumn bookName = new TableColumn<>("书名");
        bookName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn bookId = new TableColumn<>("书号");
        bookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn borrowTime = new TableColumn<>("借阅时间");
        borrowTime.setCellValueFactory(new PropertyValueFactory<>("borrowTime"));
        TableColumn returnTime = new TableColumn<>("归还时间");
        returnTime.setCellValueFactory(new PropertyValueFactory<>("returnTime"));
        bookName.setPrefWidth(150);
        bookId.setPrefWidth(150);
        borrowTime.setPrefWidth(150);
        returnTime.setPrefWidth(150);
        messageTable2.getColumns().clear();
        messageTable2.getColumns().addAll(bookName, bookId, borrowTime, returnTime);
        messageTable2.getItems().clear();
        ResDB resDB = new ResDB();
        ArrayList<MessageFX> messages = resDB.getMessages(reader.getId());
        messageTable2.getItems().addAll(messages);
        messageTable2.setLayoutX(200);
        messageTable2.setLayoutY(100);
        messageTable2.setPrefWidth(600);
        messageTable2.setPrefHeight(500);
        Button btn = new Button("返回");
        setButton(btn, 10, 10, 80, 40);
        btn.setOnAction(e -> {
            drawUser(stage);
        });
        root.getChildren().addAll(messageTable2, btn);
    }

    public void drawReserve(Stage stage)  {
        root.getChildren().clear();
        ImageView imageView = new ImageView(new Image("file:pic/back2.jpg"));
        imageView.setFitHeight(700);
        imageView.setFitWidth(1000);
        imageView.setX(0);
        imageView.setY(0);
        root.getChildren().add(imageView);
        TableColumn bookName = new TableColumn<>("书名");
        bookName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn bookId = new TableColumn<>("书号");
        bookId.setCellValueFactory(new PropertyValueFactory<>("bid"));
        TableColumn reserveTime = new TableColumn<>("预约时间");
        reserveTime.setCellValueFactory(new PropertyValueFactory<>("reserveDate"));
        bookName.setPrefWidth(200);
        bookId.setPrefWidth(200);
        reserveTime.setPrefWidth(200);
        reserveTable.getColumns().clear();
        reserveTable.getColumns().addAll(bookName, bookId, reserveTime);
        reserveTable.getItems().clear();
        ResDB resDB = new ResDB();
        ArrayList<ReserveDB> reserves = resDB.getReservations(reader.getId());
        reserveTable.getItems().addAll(reserves);
        reserveTable.setLayoutX(200);
        reserveTable.setLayoutY(100);
        reserveTable.setPrefWidth(600);
        reserveTable.setPrefHeight(500);

        Button back = new Button("返回");
        setButton(back, 10, 10, 100, 40);
        back.setOnMouseClicked(e->{
            drawUser(stage);
        });
        reserveTable.setOnMouseClicked(e->{
            ReserveDB selected = reserveTable.getSelectionModel().getSelectedItem();
            if(selected != null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("确认");
                alert.setHeaderText("确认取消预约");
                alert.setContentText("是否取消预约？");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    ResDB resDB1 = new ResDB();
                    resDB1.cancelReservation(reader.getId(),selected.getBid());
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("提示");
                    alert1.setHeaderText("取消预约成功");
                    alert1.setContentText("已取消预约");
                    alert1.showAndWait();
                    drawReserve(stage);
                }
            }
        });
        root.getChildren().addAll(reserveTable, back);
    }

    public void drawRe(Stage stage) {
        root.getChildren().clear();
        ImageView imageView = new ImageView(new Image("file:pic/back2.jpg"));
        imageView.setFitHeight(700);
        imageView.setFitWidth(1000);
        imageView.setX(0);
        imageView.setY(0);
        root.getChildren().add(imageView);
        TableColumn bookName = new TableColumn<>("书名");
        bookName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn bookId = new TableColumn<>("书号");
        bookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn borrowTime = new TableColumn<>("借阅时间");
        borrowTime.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        TableColumn returnTime = new TableColumn<>("归还时间");
        returnTime.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        bookName.setPrefWidth(150);
        bookId.setPrefWidth(150);
        borrowTime.setPrefWidth(150);
        returnTime.setPrefWidth(150);
        borrowTable.getColumns().clear();
        borrowTable.getColumns().addAll(bookName, bookId, borrowTime, returnTime);
        borrowTable.getItems().clear();
        ResDB resDB = new ResDB();
        ArrayList<BorrowDB> borrows = resDB.getBorrows(reader.getId());
        borrowTable.getItems().addAll(borrows);
        borrowTable.setLayoutX(200);
        borrowTable.setLayoutY(100);
        borrowTable.setPrefWidth(600);
        borrowTable.setPrefHeight(500);
        Button back = new Button("返回");
        setButton(back, 10, 10, 100, 40);
        root.getChildren().addAll(borrowTable, back);
        back.setOnMouseClicked(e->{
            drawUser(stage);
        });
        borrowTable.setOnMouseClicked(e->{
            BorrowDB selected = borrowTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                if (!selected.getReturnDate().equals("未还")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("提示");
                    alert.setHeaderText("还书失败");
                    alert.setContentText("已还书");
                    alert.showAndWait();
                    return;
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("提示");
                alert.setHeaderText("确认还书");
                alert.setContentText("是否还书");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    ResDB resDB1 = new ResDB();
                    resDB1.returnBook(reader.getId(), selected.getId());
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("提示");
                    alert1.setHeaderText("还书成功");
                    alert1.setContentText("已还书");
                    alert1.showAndWait();
                    drawRe(stage);
                }
            }
        });
    }

    public void drawUser(Stage stage)  {
        root.getChildren().clear();
        stage.setHeight(700);
        stage.setWidth(1000);
        stage.setX(300);
        stage.setY(100);
        ImageView imageView = new ImageView(new Image("file:pic/back2.jpg"));
        imageView.setFitHeight(700);
        imageView.setFitWidth(1000);
        imageView.setX(0);
        imageView.setY(0);
        root.getChildren().add(imageView);
        Text welText = new Text( "欢迎使用图书管理系统:" + reader.getName());
        welText.setLayoutX(330);
        welText.setLayoutY(50);
        welText.setFont(Font.font(25));
        Button self = new Button("个人信息");
        Button returnBook = new Button("查看借书");
        Button cancelReserve = new Button("查看预约");
        Button search = new Button("搜索图书");
        Button message = new Button("违规消息");
        Button back = new Button("返回");
        setButton(self, 100, 150, 200, 100);
        setButton(returnBook, 400, 150, 200, 100);
        setButton(cancelReserve, 700, 150, 200, 100);
        setButton(search, 100, 400, 200, 100);
        setButton(message, 400, 400, 200, 100);
        setButton(back, 700, 400, 200, 100);
        self.setFont(Font.font(25));
        returnBook.setFont(Font.font(25));
        cancelReserve.setFont(Font.font(25));
        search.setFont(Font.font(25));
        message.setFont(Font.font(25));
        back.setFont(Font.font(25));

        message.setOnMouseClicked(e->{
            drawMessage(stage);
        });

        cancelReserve.setOnMouseClicked(e->{
            drawReserve(stage);
        });

        returnBook.setOnMouseClicked(e->{
            drawRe(stage);
        });

        self.setOnAction(e->{
            drawSelf(stage);
        });
        back.setOnMouseClicked(e->{
            begin(stage);
        });
        search.setOnMouseClicked(e->{
            root.getChildren().clear();
            Button back1 = new Button("返回");
            setButton(back1, 10, 10, 100, 40);
            back1.setOnMouseClicked(e1->{
                drawUser(stage);
            });
            root.getChildren().addAll(imageView);
            root.getChildren().addAll(back1);
            drawBooks(4);
        });
        root.getChildren().addAll(back, welText, self, returnBook, cancelReserve, search, message);
    }

    public void add_book() {
        Button commit = new Button("提交");
        setButton(commit, 450, 300, 100, 40);
        String[] info = {"图书号","书名","作者","定价"};
        ArrayList<Text> texts = new ArrayList<>();
        ArrayList<TextField> textFields = new ArrayList<>();
        for (int i = 0; i < info.length; i++) {
            Text text = new Text(info[i]);
            text.setLayoutX(400);
            text.setLayoutY(100 + i * 50);
            TextField textField = new TextField();
            setTextField(textField, 450, 80 + i * 50, 300, 40);
            textField.setPromptText(info[i]);
            texts.add(text);
            textFields.add(textField);
        }
        commit.setOnMouseClicked(e->{
            for (int i = 0; i < 4; i++) {
                if (textFields.get(i).getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText("错误");
                    alert.setContentText("内容不能为空");
                    alert.showAndWait();
                    return;
                }
            }
            String id = textFields.get(0).getText();
            String name = textFields.get(1).getText();
            String author = textFields.get(2).getText();
            float price = Float.parseFloat(textFields.get(3).getText());
            Book book = new Book(name, author, id, price, 0, 0, 0);
            BookDB DB = new BookDB();
            ArrayList<Book> bs = DB.getAllBooks();
            for (Book b : bs) {
                if (b.getId().equals(book.getId())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText("错误");
                    alert.setContentText("这个id已经存在了");
                    alert.showAndWait();
                    return;
                }
            }
            DB.insertBook(book);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("成功");
            alert.setHeaderText("成功");
            alert.setContentText("信息录入成功");
            alert.showAndWait();
        });
        root.getChildren().addAll(texts);
        root.getChildren().addAll(commit);
        root.getChildren().addAll(textFields);
    }

    public void setButton(Button button, double x1, double x2, double x3, double x4) {
        button.setLayoutX(x1);
        button.setLayoutY(x2);
        button.setPrefWidth(x3);
        button.setPrefHeight(x4);
    }

    public void setTextField(TextField text, double x1, double x2, double x3, double x4) {
        text.setLayoutX(x1);
        text.setLayoutY(x2);
        text.setPrefWidth(x3);
        text.setPrefHeight(x4);
    }

    public void paintBAdmin(Stage stage) {
        Button fanHui = new Button("退出登录");
        setButton(fanHui, 0, 60, 80, 30);
        fanHui.setOnMouseClicked(e -> {
            begin(stage);
        });
        ImageView imageView = new ImageView(new Image("file:pic/back2.jpg"));
        imageView.setFitHeight(700);
        imageView.setFitWidth(1000);
        imageView.setX(0);
        imageView.setY(0);
        root.getChildren().add(imageView);
        MenuButton adminMenu = new MenuButton("菜单");
        adminMenu.setLayoutX(0);
        adminMenu.setLayoutY(0);
        adminMenu.setPrefWidth(80);
        adminMenu.setPrefHeight(30);
        adminMenu.setFont(Font.font("华文行楷", FontWeight.BOLD, 15));
        MenuItem addAdmin = new MenuItem("添加管理员");
        MenuItem deleteAdmin = new MenuItem("删除管理员");
        MenuItem updateAdmin = new MenuItem("修改管理员");
        MenuItem as = new MenuItem("查看管理员");
        adminMenu.getItems().addAll(addAdmin, deleteAdmin, updateAdmin, as);
        updateAdmin.setOnAction(e -> {
            adminMenu.setVisible(false);
            AdminsDB adminsDB = new AdminsDB();
            admins = adminsDB.getAllAdmins();
            drawAdmins();
            Button back = new Button("返回");
            setButton(back, 0, 0, 100, 50);
            root.getChildren().add(back);
            Text text = new Text("管理员编号:");
            text.setLayoutX(90);
            text.setLayoutY(170);
            text.setFont(Font.font("宋体", FontWeight.BOLD, 20));
            TextField textField = new TextField();
            setTextField(textField, 200, 150, 200, 40);
            TextField textField2 = new TextField();
            setTextField(textField2, 200, 200, 200, 40);
            TextField textField3 = new TextField();
            setTextField(textField3, 200, 250, 200, 40);
            TextField textField4 = new TextField();
            setTextField(textField4, 200, 300, 200, 40);
            Text text3 = new Text("新的密码:");
            Text text2 = new Text("新的姓名:");
            Text text1 = new Text("新的编号:");
            text1.setFont(Font.font("宋体", FontWeight.BOLD, 20));
            text2.setFont(Font.font("宋体", FontWeight.BOLD, 20));
            text3.setFont(Font.font("宋体", FontWeight.BOLD, 20));
            text1.setLayoutX(100);
            text1.setLayoutY(220);
            text2.setLayoutX(100);
            text2.setLayoutY(270);
            text3.setLayoutX(100);
            text3.setLayoutY(320);
            Button button = new Button("确定");
            setButton(button, 200, 400, 100, 50);
            root.getChildren().addAll(text, textField, button, text1, textField2, text2, textField3, text3, textField4);
            button.setOnMouseClicked(e1 -> {
                String id = textField.getText();
                String password = textField4.getText();
                String name = textField3.getText();
                String newId = textField2.getText();
                AdminsDB adminsDB1 = new AdminsDB();
                admins = adminsDB1.getAllAdmins();
                boolean tag = false;
                boolean tag1 = false;
                for (int i = 0; i < admins.size(); i++) {
                    if (admins.get(i).getId().equals(newId) && !admins.get(i).getId().equals(id)) {
                        tag1 = true;
                    }
                }
                for (int i = 0; i < admins.size(); i++) {
                    if (admins.get(i).getId().equals(id) && !tag1) {
                        root.getChildren().removeAll(adminTable, back, button, text, textField, text1, textField2, text2, textField3, text3, textField4);
                        adminMenu.setVisible(true);
                        Admins admin = new Admins();
                        if (password.isEmpty()) {
                            admin.setPassword(admins.get(i).getPassword());
                        }
                        else {
                            admin.setPassword(password);
                        }
                        if (name.isEmpty()) {
                            admin.setName(admins.get(i).getName());
                        }
                        else {
                            admin.setName(name);
                        }
                        if (newId.isEmpty()){
                            admin.setId(admins.get(i).getId());
                        }
                        else {
                            admin.setId(newId);
                        }
                        admin.setState(1);
                        adminsDB1.deleteAdmin(id);
                        adminsDB1.createAdmin(admin);
                        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                        al.setTitle("成功");
                        al.setHeaderText("操作成功");
                        al.showAndWait();
                        tag = true;
                        break;
                    }
                }
                if (!tag){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText("没有这个管理员或要改的id已经存在");
                    alert.setContentText("请重新输入");
                    alert.showAndWait();
                }
            });

            back.setOnMouseClicked(e1 -> {
                root.getChildren().removeAll(adminTable, back, button, text, textField, text1, textField2, text2, textField3, text3, textField4);
                adminMenu.setVisible(true);
            });
        });

        deleteAdmin.setOnAction(e -> {
            adminMenu.setVisible(false);
            AdminsDB adminsDB = new AdminsDB();
            admins = adminsDB.getAllAdmins();
            drawAdmins();
            Button back = new Button("返回");
            setButton(back, 0, 0, 100, 50);
            root.getChildren().add(back);
            Text text = new Text("请输入要删除的管理员编号:");
            text.setLayoutX(200);
            text.setLayoutY(100);
            text.setFont(Font.font("宋体", FontWeight.BOLD, 20));
            TextField textField = new TextField();
            textField.setLayoutX(200);
            textField.setLayoutY(150);
            textField.setPrefHeight(40);
            textField.setPrefWidth(200);
            Button button = new Button("删除");
            setButton(button, 200, 200, 80, 40);
            root.getChildren().addAll(text, textField, button);
            button.setOnMouseClicked(e1 -> {
                String id = textField.getText();
                AdminsDB adminsDB1 = new AdminsDB();
                admins = adminsDB1.getAllAdmins();
                boolean tag = false;
                for (int i = 0; i < admins.size(); i++) {
                    if (admins.get(i).getId().equals(id)) {
                        root.getChildren().removeAll(adminTable, back, button, text, textField);
                        adminMenu.setVisible(true);
                        adminsDB1.deleteAdmin(id);
                        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                        al.setTitle("成功");
                        al.setHeaderText("操作成功");
                        al.showAndWait();
                        tag = true;
                        break;
                    }
                }
                if (!tag){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText("没有这个管理员");
                    alert.setContentText("请重新输入");
                    alert.showAndWait();
                }
            });
            back.setOnMouseClicked(e1 -> {
                root.getChildren().removeAll(adminTable, back, button, text, textField);
                adminMenu.setVisible(true);
            });
        });

        as.setOnAction(e -> {
            adminMenu.setVisible(false);
            AdminsDB adminsDB = new AdminsDB();
            admins = adminsDB.getAllAdmins();
            drawAdmins();
            Button back = new Button("返回");
            setButton(back, 0, 0, 100, 50);
            back.setOnMouseClicked(e1 -> {
                root.getChildren().removeAll(adminTable, back);
                adminMenu.setVisible(true);
            });
            root.getChildren().add(back);
        });

        addAdmin.setOnAction(e -> {
            String[] info = {"姓名:", "编号:", "密码:"};
            ArrayList<TextField> textFields = new ArrayList<>();
            ArrayList<Text> texts = new ArrayList<>();
            Text text = new Text("请输入管理员信息:");
            text.setLayoutX(200);
            text.setLayoutY(100);
            text.setFont(Font.font("宋体", FontWeight.BOLD, 20));
            adminMenu.setVisible(false);
            root.getChildren().add(text);
            for (int i = 0; i < 3; i++) {
                Text t = new Text(info[i]);
                t.setLayoutX(250);
                t.setLayoutY(160 + 50 * i);
                t.setFont(Font.font("宋体", FontWeight.BOLD, 15));
                TextField t1 = new TextField();
                t1.setLayoutX(300);
                t1.setLayoutY(150 + 50 * i);
                textFields.add(t1);
                texts.add(t);
                root.getChildren().addAll(t, t1);
            }
            Button b = new Button("确认");
            setButton(b, 300, 300, 80, 30);
            b.setFont(Font.font("宋体", FontWeight.BOLD, 15));
            Button c = new Button("取消");
            setButton(c, 400, 300, 80, 30);
            c.setFont(Font.font("宋体", FontWeight.BOLD, 15));
            root.getChildren().addAll(b, c);
            c.setOnMouseClicked(e1 -> {
                root.getChildren().removeAll(textFields);
                root.getChildren().removeAll(texts);
                root.getChildren().removeAll(text, b, c);
                adminMenu.setVisible(true);
            });
            b.setOnAction(e1 -> {
                AdminsDB adminsDB = new AdminsDB();
                ArrayList<Admins> admins_tmp = adminsDB.getAllAdmins();
                String name = textFields.get(0).getText();
                String id = textFields.get(1).getText();
                String password = textFields.get(2).getText();
                if (id == null || id.isEmpty() || password == null || password.isEmpty() || name == null || name.isEmpty())  {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText("输入不能为空");
                    alert.setContentText("请重新输入");
                    alert.showAndWait();
                    return;
                }
                for (int i = 0; i < admins_tmp.size(); i++) {
                    if (admins_tmp.get(i).getId().equals(id)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("错误");
                        alert.setHeaderText("编号已存在");
                        alert.setContentText("请重新输入");
                        alert.showAndWait();
                        return;
                    }
                }
                Admins a = new Admins(name, password, id, 1);
                adminsDB.createAdmin(a);
                Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                al.setTitle("成功");
                al.setHeaderText("操作成功");
                al.setContentText("可以继续操作或者返回");
                al.showAndWait();
            });
        });
        root.getChildren().add(fanHui);
        root.getChildren().add(adminMenu);
    }

    public void drawAdmins() {
        TableColumn id = new TableColumn("id");
        TableColumn aname = new TableColumn("name");
        id.setPrefWidth(100);
        aname.setPrefWidth(100);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        aname.setCellValueFactory(new PropertyValueFactory<>("aname"));
        ArrayList<AdminsFX> adminFXs = new ArrayList<>();
        adminTable.getColumns().clear();
        adminTable.getColumns().addAll(id, aname);
        for (int i = 0; i < admins.size(); i++) {
            AdminsFX f = new AdminsFX();
            f.id = admins.get(i).getId();
            f.aname = admins.get(i).getName();
            adminFXs.add(f);
        }
        adminTable.getItems().clear();
        adminTable.getItems().addAll(adminFXs);
        adminTable.setLayoutX(400);
        adminTable.setLayoutY(100);
        adminTable.setPrefWidth(200);
        adminTable.setPrefHeight(500);
        root.getChildren().addAll(adminTable);
    }

    public void drawBooks(int state) {
        TableColumn id = new TableColumn("id");
        TableColumn bname = new TableColumn("name");
        TableColumn author = new TableColumn("author");
        TableColumn price = new TableColumn("price");
        TableColumn bstatus = new TableColumn("状态，0可借");
        TableColumn borrow_times = new TableColumn("borrowTimes");
        TableColumn reserve_times = new TableColumn("reserveTimes");

        id.setPrefWidth(100);
        bname.setPrefWidth(200);
        author.setPrefWidth(200);
        price.setPrefWidth(100);
        bstatus.setPrefWidth(100);
        borrow_times.setPrefWidth(100);
        reserve_times.setPrefWidth(100);

        // 数据绑定
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        bname.setCellValueFactory(new PropertyValueFactory<>("name"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        bstatus.setCellValueFactory(new PropertyValueFactory<>("bStatus"));
        borrow_times.setCellValueFactory(new PropertyValueFactory<>("borrowTimes"));
        reserve_times.setCellValueFactory(new PropertyValueFactory<>("reserveTimes"));

        BookDB booksDB = new BookDB();
        ArrayList<Book> books = booksDB.getAllBooks();
        bookTable.getColumns().clear();
        bookTable.getColumns().addAll(id, bname, author, price, bstatus, borrow_times, reserve_times);
        bookTable.getItems().clear();
        bookTable.getItems().addAll(books);
        bookTable.setLayoutX(200);
        bookTable.setLayoutY(100);
        bookTable.setPrefWidth(700);
        bookTable.setPrefHeight(500);
        // 设置表样式
        root.getChildren().addAll(bookTable);

        if (state == 3) {
            bookTable.setOnMouseClicked(event -> {
                Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
                if (selectedBook != null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("图书信息");
                    alert.setHeaderText(null);
                    String info = "图书号: " + selectedBook.getId() + "\n" + "书名: " + selectedBook.getName();
                    info += "\n" + "作者: " + selectedBook.getAuthor();
                    info += "\n" + "价格: " + selectedBook.getPrice();
                    info += "\n" + "状态：" + (selectedBook.getBStatus() == 0 ? "可借" : "不可借");
                    info += "\n" + "借阅次数：" + selectedBook.getBorrowTimes();
                    info += "\n" + "预约次数：" + selectedBook.getReserveTimes();
                    alert.setContentText(info);
                    alert.showAndWait();
                }
            });
        }

        if (state == 4) {
            bookTable.setOnMouseClicked(event -> {
                Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
                if (selectedBook != null) {
                    Stage dialogStage = new Stage();
                    dialogStage.initModality(Modality.APPLICATION_MODAL);
                    Pane pane = new Pane();
                    String info = "图书号: " + selectedBook.getId() + "\n" + "书名: " + selectedBook.getName();
                    info += "\n" + "作者: " + selectedBook.getAuthor();
                    info += "\n" + "价格: " + selectedBook.getPrice();
                    info += "\n" + "状态：" + (selectedBook.getBStatus() == 0 ? "可借" : "不可借");
                    info += "\n" + "借阅次数：" + selectedBook.getBorrowTimes();
                    info += "\n" + "预约次数：" + selectedBook.getReserveTimes();
                    Button back = new Button("返回");
                    Button borrow = new Button("借阅");
                    Button reserve = new Button("预约");
                    Text me = new Text(info);
                    me.setFont(Font.font(16));
                    me.setLayoutX(100);
                    me.setLayoutY(100);
                    setButton(borrow, 80, 300, 60, 30);
                    setButton(reserve, 150, 300, 60, 30);
                    setButton(back, 220, 300, 60, 30);
                    reserve.setOnMouseClicked(event1 -> {
                        ResDB resDB = new ResDB();
                        int s = resDB.reserveBook(reader.getId(), selectedBook.getId());
                        if (s == 0) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("预约图书");
                            alert.setHeaderText(null);
                            alert.setContentText("预约成功");
                            BookDB bookDB = new BookDB();
                            ArrayList<Book> bs = bookDB.getAllBooks();
                            bookTable.getItems().clear();
                            bookTable.getItems().addAll(bs);
                            alert.showAndWait();
                        }
                        else if (s == 2) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("预约图书");
                            alert.setHeaderText(null);
                            alert.setContentText("该图书已经被他人预约");
                            alert.showAndWait();
                        }

                    });

                    borrow.setOnMouseClicked(event1 -> {
                        ResDB resDB = new ResDB();
                        int s = resDB.borrowBook(reader.getId(), selectedBook.getId());
                        if (s == 5) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("借阅图书");
                            alert.setHeaderText(null);
                            alert.setContentText("借阅成功");
                            BookDB bookDB = new BookDB();
                            ArrayList<Book> bs = bookDB.getAllBooks();
                            bookTable.getItems().clear();
                            bookTable.getItems().addAll(bs);
                            alert.showAndWait();
                        }
                        else if (s == 1) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("借阅图书");
                            alert.setHeaderText(null);
                            alert.setContentText("一天不允许借两次同一本书");
                            alert.showAndWait();
                        }
                        else if (s == 4) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("借阅图书");
                            alert.setHeaderText(null);
                            alert.setContentText("该图书已经被他人借阅");
                            alert.showAndWait();
                        }
                        else if (s == 6) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("借阅图书");
                            alert.setHeaderText(null);
                            alert.setContentText("你没预约");
                            alert.showAndWait();
                        }
                    });
                    pane.getChildren().addAll(borrow, reserve, back, me);
                    back.setOnMouseClicked(event1 -> dialogStage.close());
                    dialogStage.setScene(new Scene(pane, 400, 500));
                    dialogStage.showAndWait();
                }
            });
        }

        if (state == 1) {
            bookTable.setOnMouseClicked(event -> {
                Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
                if (selectedBook != null) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("删除图书");
                    alert.setHeaderText(null);
                    alert.setContentText("是否删除图书 " + selectedBook.getName() + " ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        BookDB bookDB = new BookDB();
                        bookDB.deleteBook(selectedBook.getId());
                        bookTable.getItems().remove(selectedBook);
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("删除成功");
                        alert1.setHeaderText(null);
                        alert1.setContentText("图书 " + selectedBook.getName() + " 删除成功");
                        alert1.showAndWait();
                    }
                }
            });
        }

        if (state == 2) {
            bookTable.setOnMouseClicked(event -> {
                Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
                Book book = new Book();
                if (selectedBook != null) {
                    String[] info = {"图书号","书名","作者","定价"};
                    ArrayList<Text> texts = new ArrayList<>();
                    ArrayList<TextField> textFields = new ArrayList<>();
                    for (int i = 0; i < info.length; i++) {
                        Text text = new Text(info[i]);
                        text.setLayoutX(100);
                        text.setLayoutY(100 + i * 50);
                        TextField textField = new TextField();
                        setTextField(textField, 150, 80 + i * 50, 200, 40);
                        textField.setPromptText(info[i]);
                        texts.add(text);
                        textFields.add(textField);
                    }
                    Stage dialogStage = new Stage();
                    dialogStage.initModality(Modality.APPLICATION_MODAL);
                    Pane pane = new Pane();
                    Button back = new Button();
                    setButton(back, 220, 300, 60, 30);
                    back.setText("返回");
                    Button commit = new Button("提交");
                    commit.setOnMouseClicked(e -> {
                        BookDB b = new BookDB();
                        ArrayList<Book> bs = b.getAllBooks();
                        if (textFields.get(0).getText().isEmpty()) {
                            book.setId(selectedBook.getId());
                        }
                        else {
                            book.setId(textFields.get(0).getText());
                        }
                        if (textFields.get(1).getText().isEmpty()) {
                            book.setName(selectedBook.getName());
                        }
                        else {
                            book.setName(textFields.get(1).getText());
                        }
                        if (textFields.get(2).getText().isEmpty()) {
                            book.setAuthor(selectedBook.getAuthor());
                        }
                        else {
                            book.setAuthor(textFields.get(2).getText());
                        }
                        if (textFields.get(3).getText().isEmpty()) {
                            book.setPrice(selectedBook.getPrice());
                        }
                        else {
                            book.setPrice(Float.parseFloat(textFields.get(3).getText()));
                        }
                        for (Book b1 : bs) {
                            if (b1.getId().equals(book.getId()) && !b1.getId().equals(selectedBook.getId())) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("错误");
                                alert.setHeaderText("错误");
                                alert.setContentText("图书ID重复");
                                alert.showAndWait();
                                return;
                            }
                        }
                        book.setBorrowTimes(selectedBook.getBorrowTimes());
                        book.setReserveTimes(selectedBook.getReserveTimes());
                        book.setBStatus(selectedBook.getBStatus());
                        BookDB bookDB = new BookDB();
                        bookDB.updateBook(book,selectedBook.getId());
                        selectedBook.setId(book.getId());
                        selectedBook.setName(book.getName());
                        selectedBook.setAuthor(book.getAuthor());
                        selectedBook.setPrice(book.getPrice());
                        bs.clear();
                        bs.addAll(bookDB.getAllBooks());
                        dialogStage.close();
                        bookTable.getItems().clear();
                        bookTable.getItems().addAll(bs);
                    });
                    setButton(commit, 140, 300, 60, 30);
                    back.setOnMouseClicked(ee -> dialogStage.close());
                    pane.getChildren().addAll(back, commit);
                    pane.getChildren().addAll(texts);
                    pane.getChildren().addAll(textFields);
                    dialogStage.setScene(new Scene(pane,400,400));
                    dialogStage.show();
                }
            });
        }

        TextField search = new TextField();
        setTextField(search, 200, 50, 600, 40);
        search.setPromptText("请输入书名或作者名");
        Button searchButton = new Button("搜索");
        setButton(searchButton, 700, 50, 100, 40);
        searchButton.setOnMouseClicked(event -> {
            BookDB bookDB = new BookDB();
            ArrayList<Book> books1 = bookDB.getBooks(search.getText());
            bookTable.getItems().clear();
            bookTable.getItems().addAll(books1);
        });
        root.getChildren().addAll(search, searchButton);
    }

    public void paintMessage() {
        TableColumn bookId = new TableColumn("book_ID");
        TableColumn readerId = new TableColumn("reader_ID");
        TableColumn borrowDate = new TableColumn("borrow_Date");
        TableColumn re_date = new TableColumn("re_Date");
        TableColumn state = new TableColumn("state");

        bookId.setPrefWidth(100);
        readerId.setPrefWidth(100);
        borrowDate.setPrefWidth(100);
        re_date.setPrefWidth(100);
        state.setPrefWidth(100);

        // 数据绑定
        bookId.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        readerId.setCellValueFactory(new PropertyValueFactory<>("reader_id"));
        borrowDate.setCellValueFactory(new PropertyValueFactory<>("borrow_date"));
        re_date.setCellValueFactory(new PropertyValueFactory<>("return_date"));
        state.setCellValueFactory(new PropertyValueFactory<>("status"));

        messageTable.getItems().clear();
        messageTable.getColumns().clear();
        messageTable.getColumns().addAll(bookId, readerId, borrowDate, re_date, state);
        MessageDB DB = new MessageDB();
        ArrayList<Message> ms = DB.getAllmessages();
        if (!ms.isEmpty()) {
            messageTable.getItems().addAll(ms);
        }
        messageTable.setPrefWidth(500);
        messageTable.setPrefHeight(500);
        messageTable.setLayoutX(300);
        messageTable.setLayoutY(100);
        root.getChildren().addAll(messageTable);
        messageTable.setOnMouseClicked(event -> {
            Message selectedMessage = messageTable.getSelectionModel().getSelectedItem();
            if (selectedMessage != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("确认");
                alert.setHeaderText("确定已经处理该记录了吗？");
                ButtonType buttonTypeYes = new ButtonType("是");
                ButtonType buttonTypeNo = new ButtonType("否");
                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes){
                    DB.deleteMessage(selectedMessage.getBook_id(), selectedMessage.getReader_id(), selectedMessage.getBorrow_date());
                    messageTable.getItems().remove(selectedMessage);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("提示");
                    alert1.setHeaderText("删除成功");
                    alert1.showAndWait();
                }
            }
        });
    }

    public void paintAdmin(Stage stage) {
        Rectangle blue = new Rectangle(0, 0, 200, 670);
        Image image = new Image("file:pic/back3.png");
        ImageView background = new ImageView(image);
        background.setFitHeight(670);
        background.setFitWidth(800);
        background.setX(200);
        background.setY(0);
        root.getChildren().addAll(background);
        blue.setFill(Color.rgb(20, 180, 250));
        blue.setStroke(Color.rgb(20, 18, 0));
        root.getChildren().addAll(blue);
        ArrayList<Button> buttons = new ArrayList<>();
        Lighting l = new Lighting();
        blue.setEffect(l);
        for (int i = 0; i < 6; i++) {
            Button b = new Button();
            //b.setEffect(l);
            b.setFont(Font.font("华文琥珀", FontWeight.BOLD, 15));
            setButton(b, 30, 60 + i * 100, 140, 60);
            b.setStyle("-fx-background-color: #fffff0");
            buttons.add(b);
        }
        buttons.getFirst().setText("加入图书");
        buttons.get(1).setText("删除图书");
        buttons.get(2).setText("修改图书");
        buttons.get(3).setText("查询图书");
        buttons.get(4).setText("处理违规记录");
        buttons.get(5).setText("退出登录");

        buttons.get(5).setOnMouseClicked(e -> {
            begin(stage);
        });

        buttons.get(4).setOnMouseClicked(e -> {
            buttons.get(4).setStyle("-fx-background-color: #000000");
            for (int i = 0; i < 6; i++){
                buttons.get(i).setDisable(true);
            }
            Button back = new Button("返回");
            setButton(back, 200, 0, 100, 40);
            back.setOnMouseClicked(event -> {
                root.getChildren().clear();
                root.getChildren().addAll(background);
                root.getChildren().addAll(blue);
                root.getChildren().addAll(buttons);
                for (int i = 0; i < 6; i++){
                    buttons.get(i).setDisable(false);
                }
                buttons.get(4).setStyle("-fx-background-color: #fffff0");
            });
            paintMessage();
            root.getChildren().addAll(back);
        });

        buttons.get(0).setOnMouseClicked(e->{
            buttons.get(0).setStyle("-fx-background-color: #000000");
            for (int i = 0; i < 6; i++){
                buttons.get(i).setDisable(true);
            }
            Button back = new Button("返回");
            setButton(back, 600, 300, 100, 40);
            back.setOnMouseClicked(event -> {
                root.getChildren().clear();
                root.getChildren().addAll(background);
                root.getChildren().addAll(blue);
                root.getChildren().addAll(buttons);
                for (int i = 0; i < 6; i++){
                    buttons.get(i).setDisable(false);
                }
                buttons.get(0).setStyle("-fx-background-color: #fffff0");
            });
            add_book();
            root.getChildren().addAll(back);
        });
        buttons.get(3).setOnMouseClicked(e -> {
            buttons.get(3).setStyle("-fx-background-color: #000000");
            for (int i = 0; i < 6; i++){
                buttons.get(i).setDisable(true);
            }
            Button back = new Button("返回");
            setButton(back, 800, 50, 100, 40);
            back.setOnMouseClicked(event -> {
                root.getChildren().clear();
                root.getChildren().addAll(background);
                root.getChildren().addAll(blue);
                root.getChildren().addAll(buttons);
                for (int i = 0; i < 6; i++){
                    buttons.get(i).setDisable(false);
                }
                buttons.get(3).setStyle("-fx-background-color: #fffff0");
            });
            root.getChildren().addAll(back);
            drawBooks(3);
        });
        buttons.get(1).setOnMouseClicked(e -> {
            buttons.get(1).setStyle("-fx-background-color: #000000");
            for (int i = 0; i < 6; i++){
                buttons.get(i).setDisable(true);
            }
            Button back = new Button("返回");
            setButton(back, 800, 50, 100, 40);
            back.setOnMouseClicked(event -> {
                for (int i = 0; i < 6; i++){
                    buttons.get(i).setDisable(false);
                }
                root.getChildren().clear();
                root.getChildren().addAll(background);
                root.getChildren().addAll(blue);
                root.getChildren().addAll(buttons);
                buttons.get(1).setStyle("-fx-background-color: #fffff0");
            });
            root.getChildren().addAll(back);
            drawBooks(1);
        });
        buttons.get(2).setOnMouseClicked(e -> {
            buttons.get(2).setStyle("-fx-background-color: #000000");
            for (int i = 0; i < 6; i++){
                buttons.get(i).setDisable(true);
            }
            Button back = new Button("返回");
            setButton(back, 800, 50, 100, 40);
            back.setOnMouseClicked(event -> {
                for (int i = 0; i < 6; i++){
                    buttons.get(i).setDisable(false);
                }
                root.getChildren().clear();
                root.getChildren().addAll(background);
                root.getChildren().addAll(blue);
                root.getChildren().addAll(buttons);
                buttons.get(2).setStyle("-fx-background-color: #fffff0");
            });
            root.getChildren().addAll(back);
            drawBooks(2);
        });

        root.getChildren().addAll(buttons);
    }

    public void adminLogin(Stage stage){
        root.getChildren().clear();
        Image image = new Image("file:pic/back1.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(500);
        imageView.setFitHeight(400);
        root.getChildren().add(imageView);
        loginButton = new Button("登录");
        loginText = new Text("用户名");
        loginText2 = new Text("密码");
        passwordTextField = new PasswordField();
        loginTextField = new TextField();
        loginButton.setLayoutX(200);
        loginButton.setLayoutY(200);
        loginText.setLayoutX(150);
        loginText.setLayoutY(110);
        loginText2.setLayoutX(160);
        loginText2.setLayoutY(160);
        loginTextField.setLayoutX(200);
        loginTextField.setLayoutY(100);
        passwordTextField.setLayoutX(200);
        passwordTextField.setLayoutY(150);
        back = new Button("返回");
        back.setLayoutX(300);
        back.setLayoutY(200);
        root.getChildren().addAll(back);
        back.setOnMouseClicked(e -> {
            begin(stage);
        });

        loginButton.setOnMouseClicked(e -> {
            if (loginTextField.getText().equals(admin) && passwordTextField.getText().equals(password)) {
                state = 2;
                AdminsDB adminsDB = new AdminsDB();
                Admins admin = new Admins("admin", "123456","A001",2);
                admins = adminsDB.getAllAdmins();
                stage.setX(200);
                stage.setY(100);
                stage.setHeight(700);
                stage.setWidth(1000);
                root.getChildren().clear();
                paintBAdmin(stage);
            }
            else {
                AdminsDB adminsDB = new AdminsDB();
                admins = adminsDB.getAllAdmins();
                Admins admin = new Admins();
                boolean tag = false;
                for (int i = 0; i < admins.size(); i++) {
                    if (loginTextField.getText().equals(admins.get(i).getId()) && passwordTextField.getText().equals(admins.get(i).getPassword())) {
                        admin = admins.get(i);
                        tag = true;
                        break;
                    }
                }
                if (!tag) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText("用户名或密码错误");
                    alert.setContentText("请重新输入");
                    alert.showAndWait();
                    return;
                }
                stage.setX(200);
                stage.setY(100);
                stage.setWidth(1000);
                stage.setHeight(700);
                root.getChildren().clear();
                paintAdmin(stage);
            }
        });
        root.getChildren().addAll(loginButton, loginText, loginTextField, passwordTextField, loginText2);
    }

    public void register(Stage stage){
        stage.setHeight(600);
        root.getChildren().clear();
        Image image = new Image("file:pic/back1.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(500);
        imageView.setFitHeight(570);
        root.getChildren().add(imageView);
        registerButton = new Button("注册");
        loginText = new Text("用户id");
        loginText2 = new Text("密码");
        Text loginText3 = new Text("确认密码");
        Text loginText4 = new Text("姓名");
        Text loginText5 = new Text("电话");
        Text loginText6 = new Text("年龄");
        loginText3.setLayoutX(140);
        loginText3.setLayoutY(215);
        passwordTextField = new PasswordField();
        passwordTextField2 = new PasswordField();
        TextField nameTextField = new TextField();
        TextField phoneTextField = new TextField();
        TextField ageTextField = new TextField();
        nameTextField.setLayoutX(200);
        nameTextField.setLayoutY(250);
        phoneTextField.setLayoutX(200);
        phoneTextField.setLayoutY(300);
        ageTextField.setLayoutX(200);
        ageTextField.setLayoutY(350);
        loginTextField = new TextField();
        registerButton.setLayoutX(200);
        registerButton.setLayoutY(400);
        loginText.setLayoutX(150);
        loginText.setLayoutY(115);
        loginText2.setLayoutX(160);
        loginText2.setLayoutY(165);
        loginText4.setLayoutX(150);
        loginText4.setLayoutY(265);
        loginText5.setLayoutX(150);
        loginText5.setLayoutY(315);
        loginText6.setLayoutX(150);
        loginText6.setLayoutY(365);
        loginTextField.setLayoutX(200);
        loginTextField.setLayoutY(100);
        passwordTextField.setLayoutX(200);
        passwordTextField.setLayoutY(150);
        passwordTextField2.setLayoutX(200);
        passwordTextField2.setLayoutY(200);
        back = new Button("返回");
        back.setLayoutX(300);
        back.setLayoutY(400);
        root.getChildren().addAll(back);
        back.setOnMouseClicked(e -> {
            begin(stage);
        });
        root.getChildren().addAll(loginText4, loginText5, loginText6, nameTextField, phoneTextField, ageTextField);
        root.getChildren().addAll(registerButton, loginText, loginTextField, loginText3, passwordTextField, passwordTextField2, loginText2);
        registerButton.setOnMouseClicked(e -> {
            String id = loginTextField.getText();
            String name = nameTextField.getText();
            String phone = phoneTextField.getText();
            int age = -1;
            if (!ageTextField.getText().isEmpty()) {
                age = Integer.parseInt(ageTextField.getText());
            }
            String password = passwordTextField.getText();
            String password2 = passwordTextField2.getText();
            if(id.isEmpty() || name.isEmpty() || phone.isEmpty() || age == -1 || password.isEmpty() || password2.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("提示");
                alert.setHeaderText(null);
                alert.setContentText("请填写完整信息");
                alert.showAndWait();
                return;
            }
            if(!password.equals(password2)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("提示");
                alert.setHeaderText(null);
                alert.setContentText("两次密码不一致");
                alert.showAndWait();
                return;
            }
            Reader reader = new Reader(name, id, age, phone, password);
            ReaderDB readerDB = new ReaderDB();
            ArrayList<Reader> readers = readerDB.getAllReaders();
            for(Reader r : readers){
                if(r.getId().equals(id)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("提示");
                    alert.setHeaderText(null);
                    alert.setContentText("该用户id已存在");
                    alert.showAndWait();
                    return;
                }
            }
            readerDB.createReader(reader);
            File file = new File("src/main/pic/" + id + ".png");
            if (file.exists()) {
                //ImageDB imageDB = new ImageDB();
                //imageDB.writeImageToDB(file,reader.getId());
                //imageDB.readImageFromDB(reader.getId());
                file.delete();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText(null);
            alert.setContentText("注册成功");
            alert.showAndWait();
            root.getChildren().clear();
            begin(stage);
        });
    }

    public void userLogin(Stage stage){
        root.getChildren().clear();
        Image image = new Image("file:pic/back1.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(500);
        imageView.setFitHeight(400);
        root.getChildren().add(imageView);
        loginButton = new Button("登录");
        loginText = new Text("用户名");
        loginText2 = new Text("密码");
        passwordTextField = new PasswordField();
        // passwordTextField = new TextField();
        loginTextField = new TextField();
        loginButton.setLayoutX(200);
        loginButton.setLayoutY(200);
        loginText.setLayoutX(150);
        loginText.setLayoutY(110);
        loginText2.setLayoutX(160);
        loginText2.setLayoutY(160);
        loginTextField.setLayoutX(200);
        loginTextField.setLayoutY(100);
        passwordTextField.setLayoutX(200);
        passwordTextField.setLayoutY(150);
        back = new Button("返回");
        back.setLayoutX(300);
        back.setLayoutY(200);
        back.setOnMouseClicked(e -> {
            begin(stage);
        });
        root.getChildren().addAll(back);
        root.getChildren().addAll(loginButton, loginText, loginTextField, loginText2, passwordTextField);
        loginButton.setOnMouseClicked(e -> {
            String id = loginTextField.getText();
            String password = passwordTextField.getText();
            if(id.isEmpty() || password.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("提示");
                alert.setHeaderText(null);
                alert.setContentText("请填写完整信息");
                alert.showAndWait();
                return;
            }
            ReaderDB readerDB = new ReaderDB();
            ArrayList<Reader> readers = readerDB.getAllReaders();
            boolean isExist = false;
            for(Reader r : readers){
                if(r.getId().equals(id) && r.getPassword().equals(password)){
                    reader = r;
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("提示");
                alert.setHeaderText(null);
                alert.setContentText("用户名或密码错误");
                alert.showAndWait();
                return;
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText(null);
            alert.setContentText("登录成功");
            alert.showAndWait();
            root.getChildren().clear();
            drawUser(stage);
        });
    }

    public void begin(Stage stage) {
        stage.setWidth(500);
        stage.setHeight(430);
        stage.setX(500);
        stage.setY(200);
        root.getChildren().clear();
        Image image = new Image("file:pic/back1.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(500);
        imageView.setFitHeight(400);
        root.getChildren().add(imageView);
        Button deLu = new Button("用户登录");
        Button deLu2 = new Button("管理员登录");
        Button deZhu = new Button("用户注册");
        Light light = new Light.Distant();
        Lighting lighting = new Lighting();
        //light.setColor(Color.MEDIUMBLUE);
        lighting.setLight(light);
        deLu.setLayoutX(180);
        deLu.setLayoutY(80);
        deLu2.setLayoutX(180);
        deLu2.setLayoutY(180);
        deZhu.setLayoutX(180);
        deZhu.setLayoutY(280);
        deLu2.setPrefHeight(70);
        deLu2.setPrefWidth(140);
        deLu.setPrefHeight(70);
        deLu.setPrefWidth(140);
        deZhu.setPrefHeight(70);
        deZhu.setPrefWidth(140);
        deLu.setEffect(lighting);
        deLu2.setEffect(lighting);
        deZhu.setEffect(lighting);

        deLu.setOnAction(e -> {
            userLogin(stage);
        });
        deLu2.setOnMouseClicked(e -> {
            adminLogin(stage);
        });
        deZhu.setOnMouseClicked(e -> {
            register(stage);
        });
        root.getChildren().addAll(deLu, deLu2, deZhu);
    }

    @Override
    public void start(Stage stage) {
        root = new Pane();
        begin(stage);
        scene = new Scene(root, 1000, 700);
        stage.setTitle("Ell");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
