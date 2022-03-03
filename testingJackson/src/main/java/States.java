import com.fasterxml.jackson.annotation.JsonIgnore;

public class States {
    //@JsonIgnore
    private String state;
    private String slug;
    private String code;
    private String nickname;
    private String website;
    private String admission_date;
    private String admission_number;
    private String capital_city;
    private String capital_url;
    private String population;
    private String population_rank;
    private String constitution_url;
    private String state_flag_url;
    private String state_seal_url;
    private String map_image_url;
    private String landscape_background_url;
    private String skyline_background_url;
    private String twitter_url;
    private String facebook_url;


    //constructor

    /*
    public States(String state, String slug, String code, String nickname, String website, String admission_date, String admission_number, String capital_city, String population, String population_rank, String constitution_url, String state_flag_url, String state_seal_url, String map_image_url, String landscape_background_url, String skyline_background_url, String twitter_url, String facebook_url) {
        this.state = state;
        this.slug = slug;
        this.code = code;
        this.nickname = nickname;
        this.website = website;
        this.admission_date = admission_date;
        this.admission_number = admission_number;
        this.capital_city = capital_city;
        this.population = population;
        this.population_rank = population_rank;
        this.constitution_url = constitution_url;
        this.state_flag_url = state_flag_url;
        this.state_seal_url = state_seal_url;
        this.map_image_url = map_image_url;
        this.landscape_background_url = landscape_background_url;
        this.skyline_background_url = skyline_background_url;
        this.twitter_url = twitter_url;
        this.facebook_url = facebook_url;
    }
     */

    //getters

    public String getState() {
        return state;
    }

    public String getSlug() {
        return slug;
    }

    public String getCode() {
        return code;
    }

    public String getNickname() {
        return nickname;
    }

    public String getWebsite() {
        return website;
    }

    public String getAdmission_date() {
        return admission_date;
    }

    public String getAdmission_number() {
        return admission_number;
    }

    public String getCapital_city() {
        return capital_city;
    }

    public String getCapital_url() {
        return capital_url;
    }

    public String getPopulation() {
        return population;
    }

    public String getPopulation_rank() {
        return population_rank;
    }

    public String getConstitution_url() {
        return constitution_url;
    }

    public String getState_flag_url() {
        return state_flag_url;
    }

    public String getState_seal_url() {
        return state_seal_url;
    }

    public String getMap_image_url() {
        return map_image_url;
    }

    public String getLandscape_background_url() {
        return landscape_background_url;
    }

    public String getSkyline_background_url() {
        return skyline_background_url;
    }

    public String getTwitter_url() {
        return twitter_url;
    }

    public String getFacebook_url() {
        return facebook_url;
    }
}
