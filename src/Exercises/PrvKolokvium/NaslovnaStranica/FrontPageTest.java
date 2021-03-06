package Exercises.PrvKolokvium.NaslovnaStranica;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

class Category{
    private String categoryName;

    public Category(String s){
        categoryName = s;
    }

    public int compare(Category s){
        return this.categoryName.compareTo (s.categoryName);
    }

}

abstract class NewsItem{
    protected String title;
    protected Date date;
    protected Category category;

    public NewsItem(String title, Date date, Category category) {
        this.title = title;
        this.date = date;
        this.category = category;
    }

    public abstract String getTeaser();
}

class TextNewsItem extends NewsItem{
    private String text;

    public TextNewsItem(String title, Date date, Category category, String text) {
        super (title, date, category);
        this.text = text;
    }
    public String getTeaser(){
        return String.format ("%s\n%d\n%s\n",
                this.title,
                this.date.toInstant ().until(Instant.now (), ChronoUnit.SECONDS),
                this.text.substring (0, 80));
    }
}

class MediaNewsItem extends NewsItem{
    private String url;
    private int views;

    public MediaNewsItem(String header, Date date, Category category, String url, int views) {
        super (header, date, category);
        this.url = url;
        this.views = views;
    }

    public String getTeaser(){
        return String.format ("%s\n%d\n%s\n%d\n", this.title, this.date.getMinutes (), this.url, this.views);
    }
}

class FrontPage{
    private List<NewsItem> newsItems;
    private Category[] categories;

    public FrontPage(Category[] categories) {
        newsItems = new ArrayList<> ();
        this.categories = categories;
    }

    public void addNewsItem(NewsItem newsItem){
        newsItems.add (newsItem);
    }

    public List<NewsItem> listByCategory(Category category){
        List<NewsItem> ret = new ArrayList<> ();
        for(int i=0; i< newsItems.size (); i++){
            if(newsItems.get (i).category.compare (category) == 0)
                ret.add (newsItems.get (i));
        }
        return ret;
    }

    public List<NewsItem> listByCategoryName(String category) throws CategoryNotFoundException {
        List<NewsItem> ret = new ArrayList<> ();
        Category cat = new Category (category);
        for(int i=0; i< newsItems.size (); i++){
            if(newsItems.get (i).category.compare (cat) == 0)
                ret.add (newsItems.get (i));
        }
        if(ret.size () == 0) throw new CategoryNotFoundException(category);
        return ret;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder ();
        for(int i=0; i< newsItems.size (); i++){
            sb.append (newsItems.get (i).getTeaser ());
        }
        return sb.toString ();
    }

}


class CategoryNotFoundException extends Exception{
    private String category;

    public CategoryNotFoundException(String category) {
        this.category = category;
    }

    public String getMessage(){
        return category;
    }
}

public class FrontPageTest {
    public static void main(String[] args) {
        // Reading
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] parts = line.split(" ");
        Category[] categories = new Category[parts.length];
        for (int i = 0; i < categories.length; ++i) {
            categories[i] = new Category(parts[i]);
        }
        int n = scanner.nextInt();
        scanner.nextLine();
        FrontPage frontPage = new FrontPage(categories);
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            cal = Calendar.getInstance();
            int min = scanner.nextInt();
            cal.add(Calendar.MINUTE, -min);
            Date date = cal.getTime();
            scanner.nextLine();
            String text = scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
            TextNewsItem tni = new TextNewsItem(title, date, categories[categoryIndex], text);
            frontPage.addNewsItem(tni);
        }

        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            int min = scanner.nextInt();
            cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, -min);
            scanner.nextLine();
            Date date = cal.getTime();
            String url = scanner.nextLine();
            int views = scanner.nextInt();
            scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
            MediaNewsItem mni = new MediaNewsItem(title, date, categories[categoryIndex], url, views);
            frontPage.addNewsItem(mni);
        }
        // Execution
        String category = scanner.nextLine();
        System.out.println(frontPage);
        for(Category c : categories) {
            System.out.println(frontPage.listByCategory(c).size());
        }
        try {
            System.out.println(frontPage.listByCategoryName(category).size());
        } catch(CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}


// Vasiot kod ovde