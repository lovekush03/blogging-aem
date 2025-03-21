package com.blogproject.core.models;

import java.util.List;

public interface ListOfBlogsModel {
    String getParentPath();
    List<Blogs> getBlogList();
}
