
import java.io.File;

import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;


public class Steganography{

    public boolean encode(String path, String original, String ext1, String stegan, String message)
    {
        Image Img  = new Image();
        Text Text = new Text();
        String	file 	= Img.get_path(path,original,ext1);
        BufferedImage 	img_original	= Img.getImg(file);
        BufferedImage img = Img.user_space(img_original);
        img = Text.add_text(img,message);

        return(Img.setImg(img,new File(Img.get_path(path,stegan,"png")),"png"));
    }

    public String decode(String path, String name)
    {
        Image Img  = new Image();
        Text Text = new Text();
        byte[] decoded;
        try
        {

            BufferedImage image  = Img.user_space(Img.getImg(Img.get_path(path,name,"png")));
            decoded = Text.decode_text(Text.get_byte_data(image));
            return(new String(decoded));
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,
                    "There is no hidden message in this image!","Error",
                    JOptionPane.ERROR_MESSAGE);
            return "";
        }
    }
}