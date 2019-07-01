package TD2.news;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;

public class Photo extends News {
    static final long serialVersionUID = 11L;

    private transient Image image;
    private int width;
    private int height;
    private boolean isColorful;

    public Photo(String titre, LocalDate date, String auteur, URL source, Image image, int width, int height, boolean isColorful) {
        super(titre, date, auteur, source);
        this.image = image;
        this.width = width;
        this.height = height;
        this.isColorful = isColorful;
    }

    public Photo() {
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        image = SwingFXUtils.toFXImage(ImageIO.read(s), null);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "jpg", s);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isColorful() {
        return isColorful;
    }

    public void setColorful(boolean colorful) {
        isColorful = colorful;
    }

    @Override
    public String toString() {
        return super.toString() + " Photo:" +
                ", width=" + width +
                ", height=" + height +
                ", isColorful=" + isColorful +
                '}';
    }
}
