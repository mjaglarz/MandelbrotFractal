package mandelbrot;

public class DivideByZeroException extends Exception{
  private String error;

  DivideByZeroException(){
        error =  "You can't divide by zero!";
    }

  @Override
  public String getMessage(){
    return error;
  }
}
