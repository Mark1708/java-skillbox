public class Book {
    private final String name;
    private final String author;
    private final int pageNum;
    private final String ISBN;

    public Book(String name, String author, int pageNum, String ISBN) {
        this.name = name;
        this.author = author;
        this.pageNum = pageNum;
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public int getPageNum() {
        return pageNum;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getAuthor() {
        return author;
    }
}
