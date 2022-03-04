import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * States is the POJO used for the array expressed as XML example.
 *
 * @author: Group 7
 * @since: February 2022
 *
 */

public class States {
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
