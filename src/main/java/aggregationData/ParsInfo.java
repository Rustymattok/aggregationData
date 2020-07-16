package aggregationData;

import java.util.Objects;

/**
 * Class for final pars json elements.
 */
public class ParsInfo implements Comparable<Object> {
    private String id;
    private String sourceDataUrl;
    private String tokenDataUrl;

    public ParsInfo() {
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ParsInfo)) return false;
        ParsInfo parsInfo = (ParsInfo) object;
        return getId().equals(parsInfo.getId()) &&
                getSourceDataUrl().equals(parsInfo.getSourceDataUrl()) &&
                getTokenDataUrl().equals(parsInfo.getTokenDataUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSourceDataUrl(), getTokenDataUrl());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceDataUrl() {
        return sourceDataUrl;
    }

    public void setSourceDataUrl(String sourceDataUrl) {
        this.sourceDataUrl = sourceDataUrl;
    }

    public String getTokenDataUrl() {
        return tokenDataUrl;
    }

    public void setTokenDataUrl(String tokenDataUrl) {
        this.tokenDataUrl = tokenDataUrl;
    }
}
