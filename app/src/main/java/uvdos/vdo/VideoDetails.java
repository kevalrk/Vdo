package uvdos.vdo;

/**
 * Created by on 9/21/2016.
 */
public class VideoDetails {
    int id;
    public String data;
    public String displayName;
    public String size;
    public String path;
    public String thumbNail;

    public VideoDetails (int id, String data, String displayName, String size, String path, String thumbNail) {
        this.id = id;
        this.data = data;
        this.displayName = displayName;
        this.size = size;
        this.path=path;
        this.thumbNail=thumbNail;
    }

    public VideoDetails() {
    }

    public void setThumbNail(String thumbNail) {
        this.thumbNail = thumbNail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public  String getDisplayName() {
        return displayName;
    }

    public  String getPath() { return path; }

    public void setPath(String path) {
        this.path = path;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    public String getSize() {
        return size;
    }

}
