package aggregationData;

import java.util.Objects;

/**
 * Class for final pars json elements.
 */
public class CameraValueData {
    private String value;
    private String ttl;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof CameraValueData)) return false;
        CameraValueData that = (CameraValueData) object;
        return getValue().equals(that.getValue()) &&
                getTtl().equals(that.getTtl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getTtl());
    }

    public String getValue() {
        return value;
    }


    public String getTtl() {
        return ttl;
    }

}
