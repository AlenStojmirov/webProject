package com.example.webproject.service;

import com.example.webproject.model.Bookmark;
import java.util.List;
import java.util.Optional;

public interface BookmarkService {

    Bookmark save(Bookmark bookmark);

    List<Bookmark> getAllBookmarks();

    Optional<Bookmark> findById(long id);

    Bookmark updateBookmark(long id, String name, double rating);

    void deleteBookmark(long id);
}
