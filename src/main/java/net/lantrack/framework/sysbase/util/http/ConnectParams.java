package net.lantrack.framework.sysbase.util.http;

/**
 *
 */
public class ConnectParams {

    private String urlBase;
    private String contentType;

    public ConnectParams() {
    }

    public ConnectParams(String urlBase) {
        this.urlBase = urlBase;
    }

    public ConnectParams(String urlBase, String contentType) {
        this.urlBase = urlBase;
        this.contentType = contentType;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "ConnectParams{" +
                "urlBase='" + urlBase + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
