import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

public class Text {

    public BufferedImage add_text(BufferedImage img, String text)
    {
        byte byte_data[]  = get_byte_data(img);
        byte msg[] = text.getBytes();
        byte len[]   = bit_conversion(msg.length);
        try
        {
            encode_text(byte_data, len,  0);
            encode_text(byte_data, msg, 32);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,
                    "Target File cannot hold message!", "Error",JOptionPane.ERROR_MESSAGE);
        }
        return img;
    }

    public byte[] get_byte_data(BufferedImage img)
    {
        WritableRaster raster   = img.getRaster();
        DataBufferByte buffer = (DataBufferByte)raster.getDataBuffer();
        return buffer.getData();
    }

    public byte[] bit_conversion(int i)
    {
        byte byte3 = (byte)((i & 0xFF000000) >>> 24);
        byte byte2 = (byte)((i & 0x00FF0000) >>> 16);
        byte byte1 = (byte)((i & 0x0000FF00) >>> 8 );
        byte byte0 = (byte)((i & 0x000000FF));
        return(new byte[]{byte3,byte2,byte1,byte0});
    }

    public byte[] encode_text(byte[] image, byte[] addition, int offset)
    {
        if(addition.length + offset > image.length)
        {
            throw new IllegalArgumentException("Not enough space");
        }
        for(int i=0; i<addition.length; ++i)
        {
            int add = addition[i];
            for(int bit=7; bit>=0; --bit, ++offset)
            {
                int b = (add >>> bit) & 1;
                image[offset] = (byte)((image[offset] & 0xFE) | b );
            }
        }
        return image;
    }

    public byte[] decode_text(byte[] image)
    {
        int len = 0;
        int offset  = 32;
        for(int i=0; i<32; ++i)
        {
            len = (len << 1) | (image[i] & 1);
        }

        byte[] res = new byte[len];
        for(int b=0; b<res.length; ++b )
        {

            for(int i=0; i<8; ++i, ++offset)
            {
                res[b] = (byte)((res[b] << 1) | (image[offset] & 1));
            }
        }
        return res;
    }
}
