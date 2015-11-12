package com.wan7451.base;


import android.view.View;

/**
 * Created by Administrator on 2015/10/24.
 */
public class ErrorMessage {

    private String message;
    private int errorIcon;
    private String errorBtnText;
    private View.OnClickListener l;
    private boolean isShowProgress;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorIcon() {
        return errorIcon;
    }

    public void setErrorIcon(int errorIcon) {
        this.errorIcon = errorIcon;
    }

    public String getErrorBtnText() {
        return errorBtnText;
    }

    public void setErrorBtnText(String errorBtnText) {
        this.errorBtnText = errorBtnText;
    }

    public View.OnClickListener getL() {
        return l;
    }

    public void setL(View.OnClickListener l) {
        this.l = l;
    }

    public boolean isShowProgress() {
        return isShowProgress;
    }

    public void setIsShowProgress(boolean isShowProgress) {
        this.isShowProgress = isShowProgress;
    }
}
