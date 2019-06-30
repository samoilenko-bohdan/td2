package TD2.news;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class MesNews {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void main(String[] args) {
        System.out.println("Premiere jour");
        System.out.println("Choisissez un element de menu");
        try {
            action();
        } catch (MalformedURLException e) {
            System.err.println("Wrong URL. Try again");
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found. Try again");
        }
    }

    public static void creer() {
        System.out.println("Creating data base");
        BaseDeNews.initialise();
        System.out.println("Created");
    }

    public static void ouvrir() {
        System.out.println("Ouvrir");
    }

    public static void sauvegarder() {
        System.out.println("Savegarder");
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

    public static void inserer() throws MalformedURLException, FileNotFoundException {
        System.out.println("Inserer");
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose type of news. Input - 1 if it " +
                "is an article and 2 if it is photo");
        int type = Integer.parseInt(sc.nextLine());
        System.out.println("Article will be created");
        System.out.println("Titre: ");
        String titre = sc.nextLine();
        System.out.println(type);
        System.out.println("La date: ");
        String reponse = sc.nextLine();
        LocalDate date = LocalDate.parse(reponse, FORMAT);
        System.out.println("L'auteur: ");
        String auteur = sc.nextLine();
        System.out.println("Source: ");
        String temp = sc.nextLine();
        URL source = new URL(temp);
        News news;
        if (type == 1) {
            System.out.println("Content: ");
            String content = sc.nextLine();
            System.out.println("URL to long version of article: ");
            URL longVersionOfText = new URL(sc.nextLine());
            System.out.println("Is there a paper version: ");
            boolean isPaperVersion = sc.nextLine().toLowerCase().equals("yes");
            news = new Article(titre, date, auteur, source,
                    content, longVersionOfText, isPaperVersion);
        } else {
            System.out.println("Path to an image: ");
            Image image = new Image(new FileInputStream(sc.nextLine()));
            System.out.println("Width of the image: ");
            int width = sc.nextInt();
            System.out.println("Height of the image:");
            int height = sc.nextInt();
            System.out.println("Colorful or not:");
            boolean isColorful = sc.nextLine().toLowerCase().equals("yes");

            news = new Photo(titre, date, auteur, source,
                    image, width, height, isColorful);
        }
        System.out.println("News was created");
        BaseDeNews.ajoute(news);
    }

    public static void supprimer() {
        afficher();
        Scanner scan = new Scanner(System.in);
        System.out.println("Quelle actualitÃ© voulez-vous supprimer ?");
        int i = scan.nextInt();
        BaseDeNews.removeNews(i);
        System.out.println("Supprimer");
    }

    public static void rechercher() {
        System.out.println("Rechercher");
    }

    public static void quitter() {
        System.out.println("Quitter");
    }

    public static void action() throws MalformedURLException, FileNotFoundException  {
        Scanner scan = new Scanner(System.in);
        int choice;
        do {
            choice = scan.nextInt();
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