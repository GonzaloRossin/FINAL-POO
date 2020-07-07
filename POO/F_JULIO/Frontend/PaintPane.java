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

    // Botones Barra Izquierda
    private ToggleButton selectionButton = new ToggleButton("Seleccionar");
    private ToggleButton rectangleButton = new ToggleButton("Rectángulo");
    private ToggleButton circleButton = new ToggleButton("Círculo");
    private ToggleButton lineButton = new ToggleButton("Linea");
    private ToggleButton squareButton = new ToggleButton("Cuadrado");
    private ToggleButton ellipseButton = new ToggleButton("Elipse");
    private Button eraseButton = new Button("Borrar");
    private Button moveToFrontButton=new Button("Al frente");
    private Button moveToLastButton=new Button("Al fondo");

    //Bordes de figura
    private Slider borders = new Slider(1, 10, 1);
    private Label borderCaption=new Label("Borde:");
    private ColorPicker bordercolor= new ColorPicker(Color.BLACK);
    //Relleno
    private Label fillerCaption=new Label("Relleno:"+'\n');
    private ColorPicker fillercolor= new ColorPicker(Color.YELLOW);

    // Dibujar una figura
    private Point startPoint;

    // StatusBar
    StatusPane statusPane;

    public PaintPane(CanvasState canvasState, StatusPane statusPane) {
        this.canvasState = canvasState;
        this.statusPane = statusPane;
        ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton,ellipseButton,lineButton,squareButton};
        borderCaption.setTextFill(Color.BLACK);
        ToggleGroup tools = new ToggleGroup();
        for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(90);
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }
        VBox buttonsBox = new VBox(10);
        buttonsBox.getChildren().addAll(toolsArr);
        buttonsBox.getChildren().addAll(moveToFrontButton,moveToLastButton,eraseButton,borders,bordercolor,fillerCaption,fillercolor);
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
            if(!(lineButton.isSelected()||circleButton.isSelected()) && (endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY())) {
                return ;
            }
            Figure newFigure = null;
            if(rectangleButton.isSelected()) {
                newFigure = new Rectangle(startPoint, endPoint,bordercolor.getValue(),fillercolor.getValue(),borders.getValue());
            }
            else if(circleButton.isSelected()) {
                double circleRadius = startPoint.distanceTo(endPoint);
                newFigure = new Circle(startPoint, circleRadius,bordercolor.getValue(),fillercolor.getValue(),borders.getValue());
            }else if(lineButton.isSelected()) {
                newFigure = new Line(startPoint, endPoint,bordercolor.getValue(),borders.getValue());
            }else if(ellipseButton.isSelected()){
                newFigure= new Ellipse(startPoint,endPoint,bordercolor.getValue(),fillercolor.getValue(),borders.getValue());
            }else if(squareButton.isSelected()) {
                newFigure = new Square(startPoint, endPoint,bordercolor.getValue(),fillercolor.getValue(),borders.getValue());
            }else if(selectionButton.isSelected()){//SELECCION DE FIGURAS
                canvasState.clearSelection();
                canvasState.setSelection(startPoint,endPoint);
                boolean found = false;
                StringBuilder label = new StringBuilder("Se seleccionó: ");
                if(!canvasState.selectionIsEmpty()) {
                    found = true;
                    for(Figure selectedfigure : canvasState.getselectedfigures()){
                        label.append(selectedfigure.toString());
                    }
                }
                if(found){
                    bordercolor.setOnAction(new EventHandler<ActionEvent>() {//seleccion multiple de color de borde
                        @Override
                        public void handle(ActionEvent event) {
                            canvasState.changeLineColor(bordercolor.getValue());
                            redrawCanvas();
                        }
                    });
                    fillercolor.setOnAction(new EventHandler<ActionEvent>() {//SELECCION MULTIPLE DE COLOR DE RELLENO
                        @Override
                        public void handle(ActionEvent event) {
                            canvasState.changeFillColor(fillercolor.getValue());
                            redrawCanvas();
                        }
                    });
                    borders.valueProperty().addListener(new ChangeListener<Number>() {//SELECCION MULTIPLE DE GROSOR DE BORDE PARA SELECCIONES
                        @Override
                        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                            canvasState.changeLineWidth(newValue.doubleValue());
                            redrawCanvas();
                        }
                    });
                    statusPane.updateStatus(label.toString());
                }else {
                    statusPane.updateStatus("Ninguna figura encontrada");
                }
            }
            else {return;}
            if(newFigure!=null) {
                canvasState.addFigure(newFigure);
            }
            startPoint = null;
            redrawCanvas();
        });
        moveToFrontButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for(Figure figure:canvasState.getselectedfigures()){
                    canvasState.moveToFront(figure);
                }
                redrawCanvas();
            }
        });
        moveToLastButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for(Figure figure:canvasState.getselectedfigures()){
                    canvasState.moveToLast(figure);
                }
                redrawCanvas();
            }
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
        borders.valueProperty().addListener(new ChangeListener<Number>() {//CONTROL DE GROSOR DE BORDE SIN SELECCIONAR
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                gc.setLineWidth(newValue.doubleValue());
            }
        });
        eraseButton.setOnAction(new EventHandler<ActionEvent>() {//FUNCIONALIDAD DE BORRADO
            @Override
            public void handle(ActionEvent event) {
                canvasState.removeSelection();
                canvasState.clearSelection();
                redrawCanvas();
            }
        });
        canvas.setOnMouseDragged(event -> {//MOVIMIENTO DE FIGURAS
             if(selectionButton.isSelected()) {
                Point eventPoint = new Point(event.getX(), event.getY());
                double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
                double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
                canvasState.moveSelection(diffX,diffY);
                redrawCanvas();
            }
        });
        setLeft(buttonsBox);
        setRight(canvas);
    }

    void redrawCanvas() {//TRAZADO DE FIGURAS
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for(Figure figure : canvasState.figures()) {
            if(canvasState.containsselection(figure)) {
                gc.setStroke(Color.RED);
            } else {
                gc.setStroke(figure.getBordercolor());
            }
            gc.setFill(figure.getFillercolor());
            gc.setLineWidth(figure.getLinewidth());
            if(figure instanceof Rectangle){
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
