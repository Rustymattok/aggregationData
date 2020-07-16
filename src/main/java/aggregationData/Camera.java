package aggregationData;

import java.util.Objects;

/**
 * Class for final pars json elements.
 */
public class Camera implements Comparable<Object> {
    private String id;
    private String urlType;
    private String videoUrl;
    private String value;
    private String ttl;

    public Camera() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Camera)) return false;
        Camera camera = (Camera) object;
        return Objects.equals(id, camera.id) &&
                Objects.equals(urlType, camera.urlType) &&
                Objects.equals(videoUrl, camera.videoUrl) &&
                Objects.equals(value, camera.value) &&
                Objects.equals(ttl, camera.ttl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, urlType, videoUrl, value, ttl);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }
}
