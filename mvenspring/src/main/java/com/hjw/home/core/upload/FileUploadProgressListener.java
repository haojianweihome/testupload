package com.hjw.home.core.upload;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

public class FileUploadProgressListener implements ProgressListener{
	private HttpSession session;
    public void setSession(HttpSession session){
        this.session=session;
        Progress status = new Progress();//±£´æÉÏ´«×´Ì¬
        session.setAttribute("status", status);
    }
    @Override
    public void update(long bytesRead, long contentLength, int items) {
        Progress status = (Progress) session.getAttribute("status");
        status.setBytesRead(bytesRead);
        status.setContentLength(contentLength);
        status.setItems(items);

    }
}
