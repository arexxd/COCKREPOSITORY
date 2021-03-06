import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List
 
/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for(Pixel[] rowArray : pixels)
    {
      for(Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }
  
  public void keepOnlyRed()
  {
    Pixel[][] pixels = this.getPixels2D();
    for(Pixel[] rowArray : pixels)
    {
      for(Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
        pixelObj.setGreen(0);
      }
    }
  }
  
  public void keepOnlyGreen()
  {
    Pixel[][] pixels = this.getPixels2D();
    for(Pixel[] rowArray : pixels)
    {
      for(Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setBlue(0);
      }
    }
  }
  
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for(Pixel[] rowArray : pixels)
    {
      for(Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(255-pixelObj.getRed());
        pixelObj.setGreen(255-pixelObj.getGreen());
        pixelObj.setBlue(255-pixelObj.getBlue());
      }
    }
  }
  
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for(Pixel[] rowArray : pixels)
    {
      for(Pixel pixelObj : rowArray)
      {
        int value = (pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue())/3;
        pixelObj.setRed(value);
        pixelObj.setGreen(value);
        pixelObj.setBlue(value);
      }
    }
  }
  
  /*
   public void fixUnderwater()
   {
   int red = 0;
   int green = 0;
   int blue = 0;
   int count = 0;
   
   Pixel[][] pixels = this.getPixels2D();
   for(Pixel[] rowArray : pixels)
   {
   for(Pixel pixelObj : rowArray)
   {
   count++;
   red += pixelObj.getRed();
   green += pixelObj.getGreen();
   blue += pixelObj.getBlue();
   }
   }
   red = (red/count)*4/8;
   green = (green/count)*7/8;
   blue = (blue/count)*7/8;
   
   for(Pixel[] rowArray : pixels)
   {
   for(Pixel pixelObj : rowArray)
   {
   pixelObj.setRed((pixelObj.getRed()-red)*2);
   pixelObj.setGreen((pixelObj.getGreen()-green)*2);
   pixelObj.setBlue((pixelObj.getBlue()-blue)*2);
   }
   }
   
   }
   */
  
  public void fixUnderwater()
  {
    int red = 0;
    int green = 0;
    int blue = 0;
    int count = 0;
    
    Pixel[][] pixels = this.getPixels2D();
    for(int col = 0; col < pixels[0].length; col++)
    {
      count = 0;
      red = 0;
      blue = 0;
      green = 0;
      for(int row = 0; row < pixels.length; row++)
      {
        count++;
        Pixel pixelObj = pixels[row][col];
        red += pixelObj.getRed();
        green += pixelObj.getGreen();
        blue += pixelObj.getBlue();
      }
      
      red = (red/count)*4/8;
      green = (green/count)*6/8;
      blue = (blue/count)*6/8;
      
      for(int row = 0; row < pixels.length; row++)
      {
        Pixel pixelObj = pixels[row][col];
        pixelObj.setRed((pixelObj.getRed() - red)*2);
        pixelObj.setGreen((pixelObj.getGreen() - green)*2);
        pixelObj.setBlue((pixelObj.getBlue() - blue)*2);
      }
    }
    
    for(int row = 0; row < pixels.length; row++)
    {
      count = 0;
      red = 0;
      blue = 0;
      green = 0;
      for(int col = 0; col < pixels[0].length; col++)
      {
        count++;
        Pixel pixelObj = pixels[row][col];
        red += pixelObj.getRed();
        green += pixelObj.getGreen();
        blue += pixelObj.getBlue();
      }
      
      red = (red/count)*4/8;
      green = (green/count)*6/8;
      blue = (blue/count)*6/8;
      
      for(int col = 0; col < pixels[0].length; col++)
      {
        Pixel pixelObj = pixels[row][col];
        pixelObj.setRed((pixelObj.getRed() - red)*2);
        pixelObj.setGreen((pixelObj.getGreen() - green)*2);
        pixelObj.setBlue((pixelObj.getBlue() - blue)*2);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  public void mirrorVerticalRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = width-1; col >= width / 2; col--)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length / 2; row++)
    {
      for (int col = 0; col < width; col++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[pixels.length - 1 - row][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    } 
  }
  
  public void mirrorHorizontalBotToTop()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int width = pixels[0].length;
    for (int row = pixels.length-1; row >= pixels.length / 2; row--)
    {
      for (int col = 0; col < width; col++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[pixels.length - 1 - row][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    } 
  }
  
  public void mirrorDiagonal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel blPixel = null;
    Pixel trPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length && row <pixels[0].length; row++)
    {
      for (int col = 0; col < row; col++)
      {
        blPixel = pixels[row][col];
        trPixel = pixels[col][row];
        trPixel.setColor(blPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        count++;
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
          [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
    
    System.out.println("Count: " + count);
  }
  
  public void mirrorArms()
  {
    int mirrorPointX = 193;
    int mirrorPointY = 204;
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int col = 105; col < 170; col++)
    {
      // loop from 13 to just before the mirror point
      for (int row = 159; row < mirrorPointX; row++)
      {
        
        topPixel = pixels[row][col];      
        bottomPixel = pixels[mirrorPointX - row + mirrorPointX]                       
          [mirrorPointY - col + mirrorPointY];
        bottomPixel.setColor(topPixel.getColor());
      }
    }
    
    for (int col = 239; col < 291; col++)
    {
      // loop from 13 to just before the mirror point
      for (int row = 159; row < mirrorPointX; row++)
      {
        
        topPixel = pixels[row][col];      
        bottomPixel = pixels[mirrorPointX - row + mirrorPointX]                       
          [mirrorPointY - col + mirrorPointY];
        bottomPixel.setColor(topPixel.getColor());
      }
    }
  }
  
  public void mirrorGull()
  {
    int mirrorPoint = 343;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 235; row < 332; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 238; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
          [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                   int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }
  
  public void copy(Picture fromPic, 
                   int startRow, int startCol, int copyFromRow, int copyToRow, int copyFromCol, int copyToCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = copyFromRow, toRow = startRow; 
         fromRow < copyToRow &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = copyFromCol, toCol = startCol; 
           fromCol < copyToCol &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }
  
  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  public void myCollage()
  {
    Picture gull = new Picture("seagull.jpg");
    Picture negativeGull = new Picture(gull);
    Picture redGull = new Picture(gull);
    Picture greenGull = new Picture(gull);
    Picture blueGull = new Picture(gull);
    Picture grayGull = new Picture(gull);
    negativeGull.negate();
    redGull.keepOnlyRed();
    greenGull.keepOnlyGreen();
    blueGull.keepOnlyBlue();
    grayGull.grayscale();
    
    this.copy(negativeGull, 0, 0, 230, 332, 238, 345);
    this.copy(redGull, 102, 0, 230, 332, 238, 345);
    this.copy(greenGull, 204, 0, 230, 332, 238, 345);
    this.copy(blueGull, 306, 0, 230, 332, 238, 345);
    this.copy(grayGull, 408, 0, 230, 332, 238, 345);
    this.mirrorVertical();
    this.write("myCollage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    int[][] newPixels = new int[pixels.length][pixels[0].length];
    Color rightColor = null;
    for (int row = 0; row < pixels.length-1; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          newPixels[row][col] = 1;
        else
          newPixels[row][col] = 0;
      }
    }
    
    for (int col = 0; col < pixels[0].length; col++)
    {
      for (int row = 0; row < pixels.length-1; row++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row+1][col];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          newPixels[row][col] = 1;
        else
          newPixels[row][col] = 0;
      }
    }
    
    for (int col = 0; col < pixels[0].length; col++)
    {
      for (int row = 0; row < pixels.length; row++)
      {
        if(newPixels[row][col] == 1)
          pixels[row][col].setColor(Color.BLACK);
        else
          pixels[row][col].setColor(Color.WHITE);
      }
    }
  }
  
  public void blurGS (int rad, double intens) {
    Pixel[][] pixels = this.getPixels2D();
    int height = pixels.length;
    int width = pixels[0].length;
    double norm = 0.;
    double intensSquared2 = 2 * intens * intens;
    double SQRT2PI = Math.sqrt(2 * Math.PI);
    double invIntensSqrPi = 1 / (SQRT2PI * intens);
    double[] mask = new double[2 * rad + 1];
    int[][] outGS = new int[height - 2 * rad][width - 2 * rad];
    
    //Create Gaussian kernel
    for (int x = -rad; x < rad + 1; x++) {
      double exp = Math.exp(-((x * x) / intensSquared2));
      
      mask[x + rad] = invIntensSqrPi * exp;
      norm += mask[x + rad];
    }
    
    //Convolve image with kernel horizontally
    for (int r = rad; r < height - rad; r++) {
      for (int c = rad; c < width - rad; c++) {
        double sum = 0.;
        
        for (int mr = -rad; mr < rad + 1; mr++) {
          sum += (mask[mr + rad] * pixels[r][c + mr].getAverage());
        }
        
        //Normalize channel after blur
        sum /= norm;
        outGS[r - rad][c - rad] = (int) Math.round(sum);
      }
    }
    
    //Convolve image with kernel vertically
    for (int r = rad; r < height - rad; r++) {
      for (int c = rad; c < width - rad; c++) {
        double sum = 0.;
        
        for(int mr = -rad; mr < rad + 1; mr++) {
          sum += (mask[mr + rad] * pixels[r][c + mr].getAverage());
        }
        
        //Normalize channel after blur
        sum /= norm;
        outGS[r - rad][c - rad] = (int) Math.round(sum);
      }
    }
    
    for (int row = 0; row<outGS.length; row++)
    {
      for (int col = 0; col<outGS[0].length; col++)
      {
        pixels[row + rad][col + rad].setRed(outGS[row][col]);
        pixels[row + rad][col + rad].setBlue(outGS[row][col]);
        pixels[row + rad][col + rad].setGreen(outGS[row][col]);
      }
    }
  }
  
  public void contrast(double factor)
  {
    Pixel[][] pixels = this.getPixels2D();
    for (int row = 0; row<pixels.length; row++)
    {
      for (int col = 0; col<pixels[0].length; col++)
      {
        if((255-pixels[row][col].getAverage())<(pixels[row][col].getAverage()))
        {
          pixels[row][col].setRed((int)(255-((255-pixels[row][col].getAverage())*factor)));
          pixels[row][col].setBlue(pixels[row][col].getRed());
          pixels[row][col].setGreen(pixels[row][col].getRed());
        }
        else
        {
          pixels[row][col].setRed((int)((pixels[row][col].getAverage())*factor));
          pixels[row][col].setBlue(pixels[row][col].getRed());
          pixels[row][col].setGreen(pixels[row][col].getRed());
        }
      }
    }
  }
  
  public void brighten(double factor)
  {
    Pixel[][] pixels = this.getPixels2D();
    for (int row = 0; row<pixels.length; row++)
    {
      for (int col = 0; col<pixels[0].length; col++)
      {
        pixels[row][col].setRed((int)((pixels[row][col].getAverage())*factor));
        pixels[row][col].setBlue(pixels[row][col].getRed());
        pixels[row][col].setGreen(pixels[row][col].getRed());
      }
    }
  }
  
  public void edgeDetection2(int edgeDist, int length)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    int[][] newPixels = new int[pixels.length][pixels[0].length];
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = length; 
           col < pixels[0].length-length-1; col++)
      {
        int count = 0;
        int value = 0;
        for(int i = -length; i<length+1; i++)
        {
          count++;
          value += pixels[row][col+i].getAverage();
        }
        int avg = value/count;
        
        for(int i = -length; i<length; i++)
        {
          leftPixel = pixels[row][col+i];
          rightPixel = pixels[row][col+i+1];
          rightColor = rightPixel.getColor();
          if (leftPixel.colorDistance(rightColor) > edgeDist && ((leftPixel.getAverage()<avg && rightPixel.getAverage()>avg) || (leftPixel.getAverage()>avg && rightPixel.getAverage()<avg)))
            newPixels[row][col+i] = 1;
          else
            newPixels[row][col+i] = 0;
        }
      }
    }
    
     for (int row = length; row < pixels.length-length-1; row++)
    {
      for (int col = 0; 
           col < pixels[0].length; col++)
      {
        int count = 0;
        int value = 0;
        for(int i = -length; i<length+1; i++)
        {
          count++;
          value += pixels[row+i][col].getAverage();
        }
        int avg = value/count;
        
        for(int i = -length; i<length; i++)
        {
          leftPixel = pixels[row+i][col];
          rightPixel = pixels[row+i+1][col];
          rightColor = rightPixel.getColor();
          if (leftPixel.colorDistance(rightColor) > edgeDist && ((leftPixel.getAverage()<avg && rightPixel.getAverage()>avg) || (leftPixel.getAverage()>avg && rightPixel.getAverage()<avg)))
            newPixels[row+i][col] = 1;
          else
            newPixels[row+i][col] = 0;
        }
      }
    }
    
    for (int col = 0; col < pixels[0].length; col++)
    {
      for (int row = 0; row < pixels.length; row++)
      {
        if(newPixels[row][col] == 1)
          pixels[row][col].setColor(Color.BLACK);
        else
          pixels[row][col].setColor(Color.WHITE);
      }
    }
  }
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this