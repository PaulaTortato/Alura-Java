import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.plaf.FontUIResource;

public class StickerGenerator {
    void create(InputStream inputStream, String fileName) {
        try {
            BufferedImage oldImage = ImageIO.read(inputStream);

            int width = oldImage.getWidth();
            int height = oldImage.getHeight();
            int newHeight = height + 200;
            BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

            Graphics2D graphics = newImage.createGraphics();
            FontUIResource font = new FontUIResource(Font.SERIF, Font.BOLD, 64);
            graphics.setFont(font);
            graphics.setColor(Color.BLACK);

            graphics.drawImage(oldImage, 0, 0, null);
            graphics.drawString("TOP", (width - 64) / 2, newHeight - 100);

            File newFile = new File("H:/VsCode/Alura/saida/" + fileName + ".png");
            ImageIO.write(newImage, "png", newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
}
