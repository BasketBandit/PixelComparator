import java.awt.Color

import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.io.File

object Main {
  def main(args: Array[String]): Unit = {
    try
      compare(args(0), args(1), args(2), args(3))
    catch {
      case e: Exception => e.printStackTrace()
    }
  }

  @throws[Exception]
  private def compare(inputImagePath1: String, inputImagePath2: String, outputFilePath: String, outputFileName: String): Unit = {
    val in1 = ImageIO.read(new File(inputImagePath1))
    val in2 = ImageIO.read(new File(inputImagePath2))

    if(in1.getWidth != in2.getWidth || in1.getHeight != in2.getHeight) {
      System.out.println("Images aren't the same dimensions.")
      return
    }

    val img = new BufferedImage(in1.getWidth, in1.getHeight, BufferedImage.TYPE_INT_RGB)

    for(y <- 0 until in1.getHeight) {
      for(x <- 0 until in1.getWidth()) {
        if(in1.getRGB(x, y) != in2.getRGB(x, y)) {
          img.setRGB(x, y, in2.getRGB(x, y))
        } else {
          img.setRGB(x, y, Color.WHITE.getRGB)
        }
      }
    }

    var outPath = outputFilePath
    if(!outputFilePath.endsWith("/")) {
      outPath += "/"
    }

    ImageIO.write(img, "jpg", new File(outPath + outputFileName + ".jpg"))
    System.out.println("Image successfully created.")
  }
}

