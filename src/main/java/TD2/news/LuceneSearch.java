package TD2.news;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.TreeSet;

public class LuceneSearch {
    private final static String LUCENE_DIRECTORY = ".";

    private LuceneSearch() {}

    public void initWriter(TreeSet<News> news) throws IOException {
        Analyzer analyzer = new StopAnalyzer();
        Directory dir = FSDirectory.open(Paths.get(LUCENE_DIRECTORY));
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter writer = new IndexWriter(dir, iwc);
        Document doc = new Document();
        FieldType myFieldType = new FieldType();
        myFieldType.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
        myFieldType.setStored(true);
        myFieldType.setTokenized(true);
        myFieldType.freeze();
        writer.addDocument(doc);
        writer.commit();
        writer.close();
        writer.close();
    }
}
