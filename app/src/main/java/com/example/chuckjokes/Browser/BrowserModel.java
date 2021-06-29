package com.example.chuckjokes.Browser;

public class BrowserModel {

    private boolean LoadWithOverviewMode = true;
    private boolean UseWideViewPort = true;
    private boolean BuiltInZoomControls = true;
    private boolean DisplayZoomControls = false;
    private boolean JavaScriptEnabled = true;

    private int maxProgress = 100;

    private String defaultUrl = "http://www.icndb.com/api/";  //мб из ресурсов?

    public void setBuiltInZoomControls(boolean builtInZoomControls) {
        BuiltInZoomControls = builtInZoomControls;
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public void setDisplayZoomControls(boolean displayZoomControls) {
        DisplayZoomControls = displayZoomControls;
    }

    public void setLoadWithOverviewMode(boolean loadWithOverviewMode) {
        LoadWithOverviewMode = loadWithOverviewMode;
    }

    public void setUseWideViewPort(boolean useWideViewPort) {
        UseWideViewPort = useWideViewPort;
    }

    public void setJavaScriptEnabled(boolean javaScriptEnabled) {
        JavaScriptEnabled = javaScriptEnabled;
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public boolean isLoadWithOverviewMode() {
        return LoadWithOverviewMode;
    }

    public boolean isUseWideViewPort() {
        return UseWideViewPort;
    }

    public boolean isBuiltInZoomControls() {
        return BuiltInZoomControls;
    }

    public boolean isDisplayZoomControls() {
        return DisplayZoomControls;
    }

    public boolean isJavaScriptEnabled() {
        return JavaScriptEnabled;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }
}
