package TD2.news;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MesNews {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Premiere jour");
        System.out.println("Choisissez un element de menu");
        action();
    }

    public static void creer() {
        System.out.println("Creating data base");
        BaseDeNews.initialise();
        System.out.println("Created");
    }

    public static void ouvrir() {
        System.out.println("Please, enter the name of file, " +
                "from which you want to restore a db");
        String fileName = SCANNER.nextLine();
        while (true) {
            try {
                BaseDeNews.restoreDataBase(fileName);
                System.out.println("Data was restored");
                break;
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Wrong input/output or object does not exist. Try again" + e.getMessage());
            }
        }
    }

    public static void sauvegarder() {
        System.out.println("Please, enter the name of file, where you want to save a db");
        String fileName = SCANNER.nextLine();
        while (true) {
            try {
                BaseDeNews.saveStateOfDataBase(fileName);
                System.out.println("Data was saved");
                break;
            } catch (IOException e) {
                System.err.println("Wrong input/output or object does not exist. Try again" + e.getMessage());
            }
        }
    }

    public static void afficher() {
        TreeSet<News> base = BaseDeNews.getNewsSet();
        Iterator<News> iterator = base.iterator();
        int num = 1;
        if (base.size() == 0) {
            System.out.println("Base is empty");
        }
        while (iterator.hasNext()) {
            System.out.println(num + ". " + iterator.next());
            num++;
        }
    }

    public static void inserer() {
        System.out.println("Inserer");
        System.out.println("Choose type of news. Input - 1 if it " +
                "is an article and 2 if it is photo");
        int type = Integer.parseInt(SCANNER.nextLine());
        System.out.println((type == 1) ? "Article will be created" : "Photo will be created");
        System.out.println("Titre: ");
        String titre = SCANNER.nextLine();
        LocalDate date;
        while (true) {
            try {
                System.out.println("La date: ");
                String reponse = SCANNER.nextLine();
                date = LocalDate.parse(reponse, FORMAT);
                break;
            } catch (DateTimeParseException e) {
                System.err.println("Wrong format of date. Try  again");
            }
        }
        System.out.println("L'auteur: ");
        String auteur = SCANNER.nextLine();
        URL source;
        while (true) {
            try {
                System.out.println("Source: ");
                String temp = SCANNER.nextLine();
                source = new URL(temp);
                break;
            } catch (MalformedURLException e) {
                System.err.println("Wrong URL. Try Again " + e.getMessage());
            }
        }
        News news;
        if (type == 1) {
            System.out.println("Content: ");
            String content = SCANNER.nextLine();
            URL longVersionOfText;
            while (true) {
                try {
                    System.out.println("URL to long version of article: ");
                    String url = SCANNER.nextLine();
                    longVersionOfText = new URL(url);
                    break;
                } catch (MalformedURLException e) {
                    System.err.println("Wrong URL. Try Again " + e.getMessage());
                }
            }
            System.out.println("Is there a paper version: ");
            boolean isPaperVersion = SCANNER.nextLine().toLowerCase().equals("yes");
            news = new Article(titre, date, auteur, source,
                    content, longVersionOfText, isPaperVersion);
        } else {
            System.out.println("Path to an image: ");
            Image image;
            while (true) {
                try {
                    image = new Image(new FileInputStream(SCANNER.nextLine()));
                    break;
                } catch (FileNotFoundException e) {
                    System.err.println("Wrong input/output or object does not exist" + e.getMessage());
                }
            }
            System.out.println("Width of the image: ");
            int width = SCANNER.nextInt();
            System.out.println("Height of the image:");
            int height = Integer.parseInt(SCANNER.nextLine());
            ;
            System.out.println("Colorful or not:");
            boolean isColorful = SCANNER.nextLine().toLowerCase().equals("yes");

            news = new Photo(titre, date, auteur, source,
                    image, width, height, isColorful);
        }
        System.out.println("News was created");
        BaseDeNews.ajoute(news);
    }

    public static void supprimer() {
        afficher();
        System.out.println("Quelle actualitÃ© voulez-vous supprimer ?");
        int i = SCANNER.nextInt();
        BaseDeNews.removeNews(i);
        System.out.println("Supprimer");
    }

    public static void rechercher() {
        System.out.println("What would you like to find?. Please enter a word or words.");
        String condition = SCANNER.nextLine();
        Pattern pattern = Pattern.compile("\\b" + condition + "\\b", Pattern.UNICODE_CHARACTER_CLASS);
        TreeSet<News> resultsOfSearch = BaseDeNews
                .getNewsSet()
                .stream()
                .filter((news ->
                pattern.matcher(news.toString()).find()))
                .collect(Collectors.toCollection(TreeSet::new));
        AtomicInteger counter = new AtomicInteger(1);
        System.out.println((resultsOfSearch.isEmpty()) ? "News is not found" : "Results of searching:");
        resultsOfSearch.forEach((news) -> {
            System.out.println(counter + ". " + news);
            counter.getAndIncrement();
        });
    }

    public static void quitter() {
        System.out.println("Quitter");
    }

    public static void action() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    creer();
                    break;
                case 2:
                    ouvrir();
                    break;
                case 3:
                    sauvegarder();
                    break;
                case 4:
                    afficher();
                    break;
                case 5:
                    inserer();
                    break;
                case 6:
                    supprimer();
                    break;
                case 7:
                    rechercher();
                    break;
                case 8:
                    quitter();
                    break;
                default:
                    System.out.print("Choisissez un numero entre 1 et 7");
            }
        }
        while (choice != 8);
    }
}