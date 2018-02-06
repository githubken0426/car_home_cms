package com.gtercn.carhome.cms.entity;

public class Version {
    private String id;

    private Integer versionCode;
    private String versionName;
    private String content;
    private String url;
    private Integer minCode;
    private String minVersion;
    private String minContent;
    private String state;
    private Integer systemBj;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName == null ? null : versionName.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getMinCode() {
        return minCode;
    }

    public void setMinCode(Integer minCode) {
        this.minCode = minCode;
    }

    public String getMinVersion() {
        return minVersion;
    }

    public void setMinVersion(String minVersion) {
        this.minVersion = minVersion == null ? null : minVersion.trim();
    }

    public String getMinContent() {
        return minContent;
    }

    public void setMinContent(String minContent) {
        this.minContent = minContent == null ? null : minContent.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getSystemBj() {
        return systemBj;
    }

    public void setSystemBj(Integer systemBj) {
        this.systemBj = systemBj;
    }
}