package danipix.anirss.user;

import java.util.List;

/**
 * Created by Dani Pix on 2/7/2015.
 */
public class User {

    private String name;
    private String waifu;
    private String waifu_or_husbando;
    private String waifu_slug;
    private String waifu_char_id;
    private String location;
    private String website;
    private String avatar;
    private String cover_image;
    private String about;
    private String bio;
    private int karma;
    private long life_spent_on_anime;
    private boolean show_adult_content;
    private String title_language_preference;
    private String last_library_updated;
    private boolean following;
    private List<UserFavorites> favorites;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWaifu() {
        return waifu;
    }

    public void setWaifu(String waifu) {
        this.waifu = waifu;
    }

    public String getWaifu_or_husbando() {
        return waifu_or_husbando;
    }

    public void setWaifu_or_husbando(String waifu_or_husbando) {
        this.waifu_or_husbando = waifu_or_husbando;
    }

    public String getWaifu_slug() {
        return waifu_slug;
    }

    public void setWaifu_slug(String waifu_slug) {
        this.waifu_slug = waifu_slug;
    }

    public String getWaifu_char_id() {
        return waifu_char_id;
    }

    public void setWaifu_char_id(String waifu_char_id) {
        this.waifu_char_id = waifu_char_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public long getLife_spent_on_anime() {
        return life_spent_on_anime;
    }

    public void setLife_spent_on_anime(long life_spent_on_anime) {
        this.life_spent_on_anime = life_spent_on_anime;
    }

    public boolean isShow_adult_content() {
        return show_adult_content;
    }

    public void setShow_adult_content(boolean show_adult_content) {
        this.show_adult_content = show_adult_content;
    }

    public String getTitle_language_preference() {
        return title_language_preference;
    }

    public void setTitle_language_preference(String title_language_preference) {
        this.title_language_preference = title_language_preference;
    }

    public String getLast_library_updated() {
        return last_library_updated;
    }

    public void setLast_library_updated(String last_library_updated) {
        this.last_library_updated = last_library_updated;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public List<UserFavorites> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<UserFavorites> favorites) {
        this.favorites = favorites;
    }
}
