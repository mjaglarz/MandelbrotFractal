package mandelbrot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Mandelbrot extends MandelFractal{
  public Canvas canvas;
  private GraphicsContext gc;
  private double x1, y1, x2, y2;
  private int w = 720;
  private int h = 720;
  private Complex a = new Complex(-2,2);
  private Complex b = new Complex(2,-2);

  @FXML
  private TextField rField;

  @FXML
  private TextField wField;

  @FXML
  private TextField hField;

  @FXML
  private TextField aReal;

  @FXML
  private TextField aImaginary;

  @FXML
  private TextField bReal;

  @FXML
  private TextField bImaginary;

  public void initialize(){
    gc = canvas.getGraphicsContext2D();
    clear(gc);
  }

  private void clear(GraphicsContext gc){
    gc.setFill(Color.WHITE);
    gc.setGlobalBlendMode(BlendMode.SRC_OVER);
    gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
  }

  private void rect(GraphicsContext gc){
    double x = x1;
    double y = y1;
    double w = x2 - x1;
    double h = y2 - y1;

    if(w < 0){
      x = x2;
      w = -w;
    }

    if(h < 0){
      y = y2;
      h = -h;
    }

    gc.strokeRect(x + 0.5, y + 0.5, w, h);
  }

  public void mouseMoves(MouseEvent mouseEvent){
    double x = mouseEvent.getX();
    double y = mouseEvent.getY();
    gc.setGlobalBlendMode(BlendMode.DIFFERENCE);
    gc.setStroke(Color.WHITE);
    rect(gc);
    x2 = x;
    y2 = y;
    rect(gc);
  }

  public void mousePressed(MouseEvent mouseEvent){
    x1 = mouseEvent.getX();
    y1 = mouseEvent.getY();
    x2 = x1;
    y2 = y1;
  }

  public void mouseReleased(MouseEvent mouseEvent){
    rect(gc);
    System.out.format("%f %f %f %f\n", x1, y1, x2, y2);

    double temp;
    if(x1 > x2){
      temp = x2;
      x2 = x1;
      x1 = temp;
    }

    if(y1 > y2){
      temp = y2;
      y2 = y1;
      y1 = temp;
    }

    double xRange = (b.re() - a.re()) / (w - 1);
    double yRange = (b.im() - a.im()) / (h - 1);

    double aReal = x1 * xRange + a.re();
    double aImaginary = y1 * yRange + a.im();
    double bReal = x2 * xRange + a.re();
    double bImaginary = y2 * yRange + a.im();

    a.setVal(aReal, aImaginary);
    b.setVal(bReal, bImaginary);

    WritableImage wr = new WritableImage(w, h);
    PixelWriter pw = wr.getPixelWriter();

    draw(pw,a,b,w,h);

    gc.setGlobalBlendMode(BlendMode.SRC_OVER);
    gc.drawImage(wr, 0, 0, w, h);
  }

  public void clearCanvas(ActionEvent actionEvent){
    clear(gc);
  }

  @FXML
  public void draw(ActionEvent actionEvent){
    if(!rField.getText().equalsIgnoreCase("Set range of function") && !rField.getText().isEmpty()){
      r = Integer.parseInt(rField.getText());
      clear(gc);
    }else{
      r = 2;
    }
    if(!wField.getText().equalsIgnoreCase("Set width of picture") && !wField.getText().isEmpty()){
      w = Integer.parseInt(wField.getText());
      clear(gc);
    }else{
      w = 720;
    }
    if(!hField.getText().equalsIgnoreCase("Set height of picture") && !hField.getText().isEmpty()){
      h = Integer.parseInt(hField.getText());
      clear(gc);
    }else{
      h = 720;
    }
    if(!aReal.getText().equalsIgnoreCase("1st real number") && !aReal.getText().isEmpty()){
      a.setRe(Integer.parseInt(aReal.getText()));
      clear(gc);
    }else{
      a.setRe(-2);
    }
    if(!aImaginary.getText().equalsIgnoreCase("1st imaginary number") && !aImaginary.getText().isEmpty()){
      a.setIm(Integer.parseInt(aImaginary.getText()));
      clear(gc);
    }else{
      a.setIm(2);
    }
    if(!bReal.getText().equalsIgnoreCase("2nd real number") && !bReal.getText().isEmpty()){
      b.setRe(Integer.parseInt(bReal.getText()));
      clear(gc);
    }else{
      b.setRe(2);
    }
    if(!bImaginary.getText().equalsIgnoreCase("2nd imaginary number") && !bImaginary.getText().isEmpty()){
      b.setIm(Integer.parseInt(bImaginary.getText()));
      clear(gc);
    }else{
      b.setIm(-2);
    }

    WritableImage wr = new WritableImage(w, h);
    PixelWriter pw = wr.getPixelWriter();

    draw(pw,a,b,w,h);

    gc.setGlobalBlendMode(BlendMode.SRC_OVER);
    gc.drawImage(wr, 0, 0, w, h);
  }
}
