import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Image {

    public String get_path(String path, String name, String extencion)
    {
        return path + "/" + name + "." + extencion;
    }

    public BufferedImage getImg(String f)
    {
        BufferedImage img	= null;
        File file = new File(f);

        try
        {
            img = ImageIO.read(file);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,
                    "Image could not be read!","Error",JOptionPane.ERROR_MESSAGE);
        }
        return img;
    }


    public boolean setImg(BufferedImage img, File file, String extencion)
    {
        try
        {
            file.delete();
            ImageIO.write(img,extencion,file);
            return true;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,
                    "File could not be saved!","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public BufferedImage user_space(BufferedImage img)
    {
        BufferedImage new_img  = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D graphics = new_img.createGraphics();
        graphics.drawRenderedImage(img, null);
        graphics.dispose();
        return new_img;
    }
}
