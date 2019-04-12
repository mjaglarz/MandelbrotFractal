package mandelbrot;

import java.lang.String;
import java.lang.Math;

public class Complex implements Field<Complex>{
  private double r, i;

  public Complex(){
    this.r = 0;
    this.i = 0;
  }
  public Complex(double r){
    this.r = r;
    this.i = 0;
  }
  public Complex(double r, double i){
    this.r = r;
    this.i = i;
  }
  public Complex(Complex a){
    this.r = a.r;
    this.i = a.i;
  }
  public Complex(String s){
  s = s.replaceAll(" ", "");
    if(s.isEmpty()){
      this.r = 0;
      this.i = 0;
    }

    if(s.contains("+")){
      String[] parts = s.split("[+]");
      this.r = Double.parseDouble(parts[0]);
      if(parts[1].charAt(0) != 'i'){
        parts[1] = parts[1].replace("i", "");
      }else{
        parts[1] = parts[1].replace("i", "1");
      }
      this.i = Double.parseDouble(parts[1]);
    }else if(s.contains("-")){
      if(s.charAt(0) == '-'){
        int counter = 0;
        for(int i = 0; i < s.length(); i++){
        if(s.charAt(i) == '-'){
          counter++;
        }
      }
      if(counter == 2){
        String[] parts = s.split("-", 3);
        double re = Double.parseDouble(parts[1]);
        if(parts[2].charAt(0) != 'i'){
          parts[2] = parts[2].replace("i", "");
        }else{
          parts[2] = parts[2].replace("i", "1");
        }
        double im = Double.parseDouble(parts[2]);
        this.r = -re;
        this.i = -im;
      }else{
        if(s.contains("i")){
          if(s.charAt(1) != 'i'){
            s = s.replace("i", "");
          }else{
            s = s.replace("i", "1");
          }
        }
        this.r = 0;
        this.i = Double.parseDouble(s);
      }
    }else{
      String[] parts = s.split("-");
      this.r = Double.parseDouble(parts[0]);
      if(parts[1].charAt(0) != 'i'){
        parts[1] = parts[1].replace("i", "");
      }else{
        parts[1] = parts[1].replace("i", "1");
      }
      double im = Double.parseDouble(parts[1]);
      this.i = -im;
    }
  }else if(s.contains("i")){
    if(s.charAt(0) != 'i'){
      s = s.replace("i", "");
    }else{
      s = s.replace("i", "1");
    }
    this.r = 0;
    this.i = Double.parseDouble(s);
  }else{
    this.r = Double.parseDouble(s);
    this.i = 0;
  }
}

@Override
public Complex add(Complex b){
  this.r += b.r;
  this.i += b.i;

  return this;
}

@Override
public Complex sub(Complex b){
  this.r -= b.r;
  this.i -= b.i;

  return this;
}

@Override
public Complex mul(Complex b){
  double re = this.r;
  double im = this.i;
  double reB = b.r;
  double imB = b.i;
  this.r = re * reB - im * imB;
  this.i = im * reB + re * imB;

  return this;
}

@Override
public Complex div(Complex b) throws DivideByZeroException{
  double denominator = b.r * b.r + b.i * b.i;
  if(denominator == 0){
    throw new DivideByZeroException();
  }

  double re = this.r;
  double im = this.i;
  this.r = (re * b.r + im * b.i) / denominator;
  this.i = (im * b.r - re * b.i) / denominator;

  return this;
}

public double abs(){
  return Math.hypot(this.r, this.i);
}

public double sqrAbs(){
  return this.r * this.r + this.i * this.i;
}

public double phase(){
  return Math.atan2(this.i, this.r);
}

public double re(){
  return this.r;
}

public double im(){
  return this.i;
}


public static Complex add(Complex a, Complex b){
  double re = a.r + b.r;
  double im = a.i + b.i;

  return new Complex(re, im);
}

public static Complex sub(Complex a, Complex b){
  double re = a.r - b.r;
  double im = a.i - b.i;

  return new Complex(re, im);
}

public static Complex mul(Complex a, Complex b){
  double re = a.r * b.r - a.i * b.i;
  double im = a.i * b.r + a.r * b.i;

  return new Complex(re, im);
}

public static Complex div(Complex a, Complex b) throws DivideByZeroException{
  double denominator = Math.cbrt(b.r) + Math.cbrt(b.i);
  if(denominator == 0){
    throw new DivideByZeroException();
  }

  double re = (a.r * b.r + a.i * b.i) / denominator;
  double im = (a.i * b.r - a.r * b.i) / denominator;

  return new Complex(re, im);
}

public static double abs(Complex a){
  return Math.hypot(a.r, a.i);
}

public static double phase(Complex a){
  return Math.atan2(a.i, a.r);
}

public static double sqrAbs(Complex a){
  return a.r * a.r + a.i * a.i;
}

public static double re(Complex a){
  return a.r;
}

public static double im(Complex a){
  return a.i;
}

@Override
public String toString(){
  if(this.r == 0 && this.i == 0){
    return "0";
  }else if(this.r == 0){
    return this.i + "i";
  }else if(this.i == 0){
    return this.r + "";
  }else if(this.i > 0){
    return this.r + "+" + this.i + "i";
  }else{
    return this.r + "-" + (-this.i) + "i";
  }
}

public static Complex valueOf(String s){
  if(s.isEmpty()){
    return new Complex(0, 0);
  }

  if(s.contains("+")){
    String[] parts = s.split("[+]");
    double re = Double.parseDouble(parts[0]);
    if(parts[1].charAt(0) != 'i'){
      parts[1] = parts[1].replace("i", "");
      double im = Double.parseDouble(parts[1]);

      return new Complex(re, im);
    }else{
      parts[1] = parts[1].replace("i", "1");
      double im = Double.parseDouble(parts[1]);

      return new Complex(re, im);
    }
  }else if(s.contains("-")){
    if(s.charAt(0) == '-'){
      int counter = 0;
      for(int i = 0; i < s.length(); i++){
      if(s.charAt(i) == '-'){
        counter++;
      }
    }
    if(counter == 2){
      String[] parts = s.split("-", 3);
      double re = Double.parseDouble(parts[1]);
      if(parts[2].charAt(0) != 'i'){
        parts[2] = parts[2].replace("i", "");
        double im = Double.parseDouble(parts[2]);

        return new Complex(-re, -im);
      }else{
        parts[2] = parts[2].replace("i", "1");
        double im = Double.parseDouble(parts[2]);

        return new Complex(-re, -im);
      }
    }else{
      if(s.contains("i")){
        if(s.charAt(1) != 'i'){
          s = s.replace("i", "");
          double im = Double.parseDouble(s);

          return new Complex(0, im);
        }else{
          s = s.replace("i", "1");
          double im = Double.parseDouble(s);

          return new Complex(0, im);
        }
      }else{
        double re = Double.parseDouble(s);

        return new Complex(re, 0);
      }
    }
    }else{
      String[] parts = s.split("-");
      double re = Double.parseDouble(parts[0]);
      if(parts[1].charAt(0) != 'i'){
        parts[1] = parts[1].replace("i", "");
        double im = Double.parseDouble(parts[1]);

        return new Complex(re, -im);
      }else{
        parts[1] = parts[1].replace("i", "1");
        double im = Double.parseDouble(parts[1]);

        return new Complex(re, -im);
      }
    }
  }else if(s.contains("i")){
    if(s.charAt(0) != 'i'){
      s = s.replace("i", "");
      double im = Double.parseDouble(s);

      return new Complex(0, im);
    }else{
      s = s.replace("i", "1");
      double im = Double.parseDouble(s);

      return new Complex(0, im);
    }
  }else{
    double re = Double.parseDouble(s);

    return new Complex(re, 0);
  }
}

  public void setRe(double r){
    this.r = r;
  }

  public void setIm(double i){
    this.i = i;
  }

  public void setVal(Complex a){
    this.r = a.r;
    this.i = a.i;
  }

  public void setVal(double r, double i){
    this.r = r;
    this.i = i;
  }
}
