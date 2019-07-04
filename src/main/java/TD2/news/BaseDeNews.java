package TD2.news;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.TreeSet;

public final class BaseDeNews {
    private static TreeSet<News> newsSet;

    public static void initialise() {
        newsSet = new TreeSet<>();
    }

    public static TreeSet<News> getNewsSet() {
        return newsSet;
    }

    public static void ajoute(News news) {
        if (newsSet == null) {
            initialise();
        }
        newsSet.add(news);
    }

    public static void saveStateOfDataBase(String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName.trim().toLowerCase() + ".txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(newsSet);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public static void restoreDataBase(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName.trim().toLowerCase() + ".txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        newsSet = (TreeSet<News>) objectInputStream.readObject();
        objectInputStream.close();
    }

    public static boolean removeNews(int index) {
        Iterator<News> iterator = newsSet.iterator();
        for(int num = 1; iterator.hasNext(); num++) {
            if (num == index) {
                if (num == 1) {
                    iterator.next();
                }
                iterator.remove();
                return true;
            }
            iterator.next();
        }
        System.out.println("News with this number does not exist");
        return false;
    }
}