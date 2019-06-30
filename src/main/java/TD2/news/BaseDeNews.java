package TD2.news;

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

    public static void removeNews(int index) {
        Iterator<News> iterator = newsSet.iterator();
        for(int num = 1; iterator.hasNext(); num++) {
            if (num == index) {
                if (num == 1) {
                    iterator.next();
                }
                iterator.remove();
                return;
            }
            iterator.next();
        }
        System.out.println("News with this number does not exist");
    }
}