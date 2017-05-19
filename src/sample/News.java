package sample; /**
 * Created by Nicholas on 10/05/2017.
 */

import com.google.gson.Gson;

import java.io.*;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URL;
import java.util.List;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class News {

    //http://stackoverflow.com/questions/7467568/parsing-json-from-url
    //download the URL (as text)
    public static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    static class Page {
        String status;
        String source;
        String sortBy;
        List<Article> articles;
    }

    //parse it (and here you have some options).
    static class Article {
        String author;
        String title;
        String description;
        String url;
        String urlToImage;
        String publishedAt;
    }


    public static void main(String[] args) throws Exception {

        String json = readUrl("https://newsapi.org/v1/articles?source=bbc-"
                + "news&sortBy=top&apiKey=b54652c17cf34853bca1ddce60f6f1cd");

        Gson gson = new Gson();
        Page page = gson.fromJson(json, Page.class);

        System.out.println(page.source.toUpperCase().replace('-', ' '));

        for (Article article : page.articles) {
            System.out.println("    " + article.title);
        }

    }
}