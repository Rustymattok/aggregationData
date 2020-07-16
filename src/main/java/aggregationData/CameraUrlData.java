package aggregationData;

import java.util.Objects;

/**
 * Class for final pars json elements.
 */
public class CameraUrlData {
    private String urlType;
    private String videoUrl;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof CameraUrlData)) return false;
        CameraUrlData that = (CameraUrlData) object;
        return urlType.equals(that.urlType) &&
                videoUrl.equals(that.videoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(urlType, videoUrl);
    }

    public String getUrlType() {
        return urlType;
    }


    public String getVideoUrl() {
        return videoUrl;
    }

}
