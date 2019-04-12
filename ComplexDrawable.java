package mandelbrot;

import javafx.scene.image.PixelWriter;

@FunctionalInterface
public interface ComplexDrawable{
    void draw(PixelWriter pw, Complex a, Complex b, int w, int h);
}
