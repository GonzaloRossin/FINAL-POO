package POO.F_JULIO.Frontend;

import POO.F_JULIO.Backend.*;
import POO.F_JULIO.Backend.Model.*;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PaintPane extends BorderPane {

    // BackEnd
    CanvasState canvasState;

    // Canvas y relacionados
    Canvas canvas = new Canvas(800, 600);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    Color lineColor = Color.BLACK;
    Color fillColor = Color.YELLOW;

    // Botones Barra Izquierda
    ToggleButton selectionButton = new ToggleButton("Seleccionar");
    ToggleButton rectangleButton = new ToggleButton("Rectángulo");
    ToggleButton circleButton = new ToggleButton("Círculo");
    ToggleButton lineButton = new ToggleButton("Linea");
    ToggleButton squareButton = new ToggleButton("Cuadrado");
    ToggleButton ellipseButton = new ToggleButton("Elipse");
    ToggleButton eraseButton = new ToggleButton("Borrar");

    //Bordes de figura
    private Slider borders = new Slider(1, 100, 1);
    private Label borderCaption=new Label("Borde:");
    private ColorPicker bordercolor= new ColorPicker(lineColor);
    //Relleno
    private Label fillerCaption=new Label("Relleno:"+'\n');
    private ColorPicker fillercolor= new ColorPicker(fillColor);

    // Dibujar una figura
    private Point startPoint;

    // Seleccionar una figura
    Figure selectedFigure;

    // StatusBar
    StatusPane statusPane;

    public PaintPane(CanvasState canvasState, StatusPane statusPane) {
        this.canvasState = canvasState;
        this.statusPane = statusPane;
        ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton,ellipseButton,lineButton,squareButton,eraseButton};
        borderCaption.setTextFill(Color.BLACK);
        ToggleGroup tools = new ToggleGroup();
        for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(90);
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }
        VBox buttonsBox = new VBox(10);
        buttonsBox.getChildren().addAll(toolsArr);
        buttonsBox.getChildren().add(7,borderCaption);
        buttonsBox.getChildren().add(8,borders);
        buttonsBox.getChildren().add(9,bordercolor);
        buttonsBox.getChildren().add(10,fillerCaption);
        buttonsBox.getChildren().add(11,fillercolor);
        buttonsBox.setPadding(new Insets(5));
        buttonsBox.setStyle("-fx-background-color: #999");
        buttonsBox.setPrefWidth(100);
        canvas.setOnMousePressed(event -> {
            startPoint = new Point(event.getX(), event.getY());
        });
        canvas.setOnMouseReleased(event -> {
            Point endPoint = new Point(event.getX(), event.getY());
            if(startPoint == null) {
                return ;
            }
            if(endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY()) {
                return ;
            }
            Figure newFigure = null;
            if(rectangleButton.isSelected()) {
                newFigure = new Rectangle(startPoint, endPoint);
            }
            else if(circleButton.isSelected()) {
                double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
                newFigure = new Circle(startPoint, circleRadius);
            }else if(lineButton.isSelected()) {
                newFigure = new Line(startPoint, endPoint);
            }else if(ellipseButton.isSelected()){
                newFigure= new Ellipse(startPoint,endPoint);
            }else if(squareButton.isSelected()) {
                newFigure = new Square(startPoint, endPoint);
            }else if(borders.isValueChanging()) {//AJUSTE DE GROSOR DE BORDE
                gc.setLineWidth(borders.getValue());
            }else if(eraseButton.isSelected()){
                canvasState.removeFigures(startPoint,endPoint);
                return;
            }else{return;}
            canvasState.addFigure(newFigure);
            startPoint = null;
            redrawCanvas();
        });
        canvas.setOnMouseMoved(event -> {//MOUSEHOVER
            Point eventPoint = new Point(event.getX(), event.getY());
            boolean found = false;
            StringBuilder label = new StringBuilder();
            for(Figure figure : canvasState.figures()) {
                if(figure.figureBelongs(eventPoint)) {
                    found = true;
                    label.append(figure.toString());
                }
            }
            if(found) {
                statusPane.updateStatus(label.toString());
            } else {
                statusPane.updateStatus(eventPoint.toString());
            }
        });
        bordercolor.setOnAction(new EventHandler<ActionEvent>() {//seleccion general de color de borde y relleno
            @Override
            public void handle(ActionEvent event) {
                lineColor=bordercolor.getValue();
                redrawCanvas();
            }
        });
        fillercolor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fillColor=fillercolor.getValue();
                redrawCanvas();
            }
        });
        borders.valueProperty().addListener(new ChangeListener<Number>() {//CONTROL DE GROSOR DE BORDE
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                gc.setLineWidth(newValue.doubleValue());
                redrawCanvas();
            }
        });
        canvas.setOnMouseClicked(event -> {
            if(eraseButton.isSelected()) {//BORRADO SINGULAR
                Point eventpoint = new Point(event.getX(), event.getY());
                canvasState.removeFigure(eventpoint);
                redrawCanvas();
            }else if(selectionButton.isSelected()) {//SELECCION DE FIGURA
                Point eventPoint = new Point(event.getX(), event.getY());
                boolean found = false;
                StringBuilder label = new StringBuilder("Se seleccionó: ");
                for (Figure figure : canvasState.figures()) {
                    if(figure.figureBelongs(eventPoint)) {
                        found = true;
                        selectedFigure = figure;
                        label.append(figure.toString());
                    }
                }
                if (found) {
                    bordercolor.setOnAction(new EventHandler<ActionEvent>() {//seleccion general de color de borde y relleno
                        @Override
                        public void handle(ActionEvent event) {
                            selectedFigure.setColor(bordercolor.getValue());
                            redrawCanvas();
                        }
                    });
                    statusPane.updateStatus(label.toString());
                } else {
                    selectedFigure = null;
                    statusPane.updateStatus("Ninguna figura encontrada");
                }
                redrawCanvas();
            }
        });
        canvas.setOnMouseDragged(event -> {
             if(selectionButton.isSelected()) {
                Point eventPoint = new Point(event.getX(), event.getY());
                double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
                double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
                selectedFigure.move(diffX,diffY);
                redrawCanvas();
            }
        });
        setLeft(buttonsBox);
        setRight(canvas);
    }

    void redrawCanvas() {//TRAZADO DE FIGURAS
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for(Figure figure : canvasState.figures()) {
            if(figure == selectedFigure) {
                gc.setStroke(Color.RED);
            } else {
                gc.setStroke(figure.getColor());
            }
            gc.setFill(fillColor);
            if(figure instanceof Readable){
            if(figure instanceof Ellipse){
                Ellipse elipse=(Ellipse) figure;
                gc.strokeOval(elipse.getTopLeft().x,elipse.getTopLeft().y,elipse.base(),elipse.height());
                gc.fillOval(elipse.getTopLeft().x,elipse.getTopLeft().y,elipse.base(),elipse.height());
            }
            else if(figure instanceof Square){
                Square square=(Square) figure;
                gc.strokeRect(square.getTopLeft().x,square.getTopLeft().y,square.Side(),square.Side());
                gc.fillRect(square.getTopLeft().x,square.getTopLeft().y,square.Side(),square.Side());
            }else {
                Rectangle rectangle = (Rectangle) figure;
                gc.fillRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(), rectangle.base(), rectangle.height());
                gc.strokeRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(), rectangle.base(), rectangle.height());
            }
            }else if(figure instanceof Circle) {
                Circle circle = (Circle) figure;
                double diameter = circle.getRadius() * 2;
                gc.fillOval(circle.getCenterPoint().getX() - circle.getRadius(), circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
                gc.strokeOval(circle.getCenterPoint().getX() - circle.getRadius(), circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
            }else if(figure instanceof Line){
                Line line=(Line) figure;
                gc.strokeLine(line.getP1().x,line.getP1().y,line.getP2().x,line.getP2().y);
            }
        }
    }

}
