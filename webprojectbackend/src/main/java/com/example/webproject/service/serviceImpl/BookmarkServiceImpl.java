package com.example.webproject.service.serviceImpl;

import com.example.webproject.model.Bookmark;
import com.example.webproject.model.exception.ObjectExistException;
import com.example.webproject.model.exception.InvalidBookmarkException;
import com.example.webproject.repository.BookmarkRepository;
import com.example.webproject.service.BookmarkService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    public BookmarkServiceImpl(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @Override
    public Bookmark save(Bookmark bookmark) {
        try {
            return this.bookmarkRepository.save(bookmark);
        } catch (Exception e) {
            throw new ObjectExistException("Bookmark with name "+bookmark.getName()+" already exist");
        }
    }

    @Override
    public List<Bookmark> getAllBookmarks() {
        return this.bookmarkRepository.findAll();
    }

    @Override
    public Optional<Bookmark> findById(long id) {
        return this.bookmarkRepository.findById(id);
    }

    @Override
    public Bookmark updateBookmark(long id, String name, double rating) {

        Bookmark bookmark = this.bookmarkRepository.findById(id).orElseThrow(InvalidBookmarkException::new);

        bookmark.setName(name);
        bookmark.setRating(rating);
        return this.bookmarkRepository.save(bookmark);
    }

    @Override
    public void deleteBookmark(long id) {
        this.bookmarkRepository.deleteById(id);
    }
}
