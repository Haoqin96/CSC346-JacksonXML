import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Nested is the POJO used for the nested XML example.
 *
 * @author: Group 7
 * @since: February 2022
 *
 */

public class Nested {
    private String credit;
    private String creditUrl;
    private Image image;
    private String suggestedPickup;
    private String suggestedPickupPeriod;

    //constructors
    public Nested(){}

    public Nested(String credit, String creditUrl, Image image, String suggestedPickup, String suggestedPickupPeriod){
        setCredit(credit);
        setCreditUrl(creditUrl);
        setImage(image);
        setSuggestedPickup(suggestedPickup);
        setSuggestedPickupPeriod(suggestedPickupPeriod);
    }

    //getters and setters
    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCreditUrl() {
        return creditUrl;
    }

    public void setCreditUrl(String creditUrl) {
        this.creditUrl = creditUrl;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getSuggestedPickup() {
        return suggestedPickup;
    }

    public void setSuggestedPickup(String suggestedPickup) {
        this.suggestedPickup = suggestedPickup;
    }

    public String getSuggestedPickupPeriod() {
        return suggestedPickupPeriod;
    }

    public void setSuggestedPickupPeriod(String suggestedPickupPeriod) {
        this.suggestedPickupPeriod = suggestedPickupPeriod;
    }

    public static class Image{
        private String url;
        private String title;
        private String link;

        //constructors
        public Image(){}

        public Image(String url, String title, String link){
            setUrl(url);
            setTitle(title);
            setLink(link);
        }

        //getters and setters

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }//end of Image
}//end of nested
