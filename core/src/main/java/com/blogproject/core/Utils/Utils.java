package com.blogproject.core.Utils;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Utils {
    public static String getThumbnailPath(SlingHttpServletRequest request, Page page) {
        String ImagePath = page.getPath() + "/jcr:content/root/responsivegrid_291805153/banner";
        ResourceResolver resolver = request.getResourceResolver();
        Resource bannerNode = resolver.getResource(ImagePath);
        String ImageLink = "";
        try {
            ImageLink = bannerNode.getValueMap().get("bannerImage", String.class);
        } catch (Exception e) {
            return "{\"message\" : \" Could not get Banner Image from the Banner Component of Page\"";
        }
        return ImageLink;
    }

    public static String formatDate(Page page) {
        Calendar date = page.getProperties().get("jcr:created", Calendar.class);
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
        return formatter.format(date.getTime());
    }
}