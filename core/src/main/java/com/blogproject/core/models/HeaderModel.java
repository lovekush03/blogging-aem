package com.blogproject.core.models;

import java.util.List;

public interface HeaderModel {
        String getLogoImage();
    String getLogoText();
    List<NavItems> getNavItems();
}