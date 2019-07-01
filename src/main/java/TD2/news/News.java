package TD2.news;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;

abstract class News implements Comparable<News>, Serializable {

    private String titre;
    private LocalDate date;
    private String auteur;
    private URL source;

    public News(String titre, LocalDate date, String auteur, URL source) {
        this.titre = titre;
        this.date = date;
        this.auteur = auteur;
        this.source = source;
    }

    public News() {
    }

    @Override
    public int compareTo(News o) {
        return this.titre.compareTo(o.titre);
    }

    @Override
    public String toString() {
        return "News: Titre :" + titre + " le " + date
                + " auteur : " + auteur + " source : " + source;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public URL getSource() {
        return source;
    }

    public void setSource(URL source) {
        this.source = source;
    }
}