package sothrguiapplication;

public class Book
{
   private int id,pages,number_available;
   private float price;
   private String title,author,publisher,category,contain_pictures,contain_cd;

    public String getContain_pictures() {
        return contain_pictures;
    }

    public void setContain_pictures(String contain_pictures) {
        this.contain_pictures = contain_pictures;
    }

    public int getNumber_available() {
        return number_available;
    }

    public void setNumber_available(int number_available) {
        this.number_available = number_available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContain_cd() {
        return contain_cd;
    }

    public void setContain_cd(String contain_cd) {
        this.contain_cd = contain_cd;
    }
}
