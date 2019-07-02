package TD2.news;

import java.net.URL;
import java.time.LocalDate;

public class Article extends News {
    static final long serialVersionUID = 6L;

    private String content;
    private URL longVersionOfText;
    private boolean isPaperVersion;

    public Article(String titre, LocalDate date, String auteur, URL source, String content, URL longVersionOfText, boolean isPaperVersion) {
        super(titre, date, auteur, source);
        this.content = content;
        this.longVersionOfText = longVersionOfText;
        this.isPaperVersion = isPaperVersion;
    }

    public Article() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public URL getLongVersionOfText() {
        return longVersionOfText;
    }

    public void setLongVersionOfText(URL longVersionOfText) {
        this.longVersionOfText = longVersionOfText;
    }

    public boolean isPaperVersion() {
        return isPaperVersion;
    }

    public void setPaperVersion(boolean paperVersion) {
        isPaperVersion = paperVersion;
    }

    @Override
    public String toString() {
        return super.toString() + "\n Article: " +
                "content: " + content  +
                ", link to article: " + longVersionOfText +
                ", there is paper version: " + isPaperVersion;
    }
}
