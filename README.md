Mandelbrot set zoom with my own Complex class in JavaFX
============================
* base: Complex class, Field interface, DivideByZeroException class
* core: MandelbrotFX class, MandelFractal class, ComplexDrawable interface, Mandelbrot class and fxml file 

## Complex class (implements interface Field\<Complex>):
- multiple constructors (e.g. string to real and imaginary part of the complex number)
- addition, subtraction, multiplication and division
- absolute value, square root of abs, phase, real and imaginary part
- static equivalents for above functions
- functions to convert complex number to string, string to Complex object
- set appropriate values for complex number

## Mandelbrot set:
- MandelbrotFX class with main method to launch program
- MandelFractal class (implements interface ComplexDrawable) with draw method
- Mandelbrot class (sets text fields, mouse events, functions to draw on and clear canvas)
