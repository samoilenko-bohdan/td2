package TD2.news;

import javafx.scene.image.Image;

import java.net.URL;
import java.time.LocalDate;

public class Photo extends News {
    private Image image;
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
        return super.toString() + " Photo{" +
                "image=" + image +
                ", width=" + width +
                ", height=" + height +
                ", isColorful=" + isColorful +
                '}';
    }
}
