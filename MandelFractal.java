package mandelbrot;

import javafx.scene.image.PixelWriter;
import java.awt.*;

public class MandelFractal implements ComplexDrawable{
  private final int n = 100;
  int r;

  @Override
  public void draw(PixelWriter pw, Complex a, Complex b, int w, int h){
    for(int y = 0; y < h; y++){
      for(int x = 0; x < w; x++){
        double width = (b.re() - a.re())/w;
        double height = (b.im() - a.im())/h;

        Complex p = new Complex();
        p.setRe(a.re() + width * x);
        p.setIm(a.im() + height * y);

        pw.setArgb(x, y, mandelbrot(p));
      }
    }
  }

  private int mandelbrot(Complex p){
    Complex z = new Complex();

    for(int i = 0; i < n; i++){
      z.mul(z);
      z.add(p);

      if(Complex.sqrAbs(z) > (r * r)){
        Color color = new Color(lets_color(i));
        return color.getRGB();
      }
    }

    Color color = new Color(0,0,0);
    return color.getRGB();
  }

  private int lets_color(int index){
    int[] colors = new int[n];
    for(int i = 0; i < n; i++){
      colors[i] = Color.HSBtoRGB((i+230f)/(360f), (i+87f)/100f, (i*1.9f+33f)/100f);
    }
    
    return colors[index];
  }
}
