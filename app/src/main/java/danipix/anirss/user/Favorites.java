package danipix.anirss.user;

/**
 * Created by Dani Pix on 3/7/2015.
 */
public class Favorites {

    private long id;
    private long user_id;
    private long item_id;
    private String item_type;
    private String created_at;
    private String updated_at;
    private long fav_rank;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFav_rank() {
        return fav_rank;
    }

    public void setFav_rank(long fav_rank) {
        this.fav_rank = fav_rank;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
