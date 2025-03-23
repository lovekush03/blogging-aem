package com.blogproject.core.Utils;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Utils {
    public static String getThumbnailPath(SlingHttpServletRequest request, Page page) {
        String ImagePath = page.getPath() + "/jcr:content/cq:featuredimage/file/jcr:content";
        ResourceResolver resolver = request.getResourceResolver();
        Resource ImageNode = resolver.getResource(ImagePath);
        String ImageLink = "";
        try {
            ValueMap properties = ImageNode.getValueMap();
            if (properties.containsKey("jcr:data")) { // Check if binary data exists
                ImageLink = resolver.map(request, ImagePath) + "/jcr:data";
            }
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