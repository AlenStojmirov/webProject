package com.example.webproject.web.restControllers;

import com.example.webproject.model.Bookmark;
import com.example.webproject.service.BookmarkService;

import java.util.List;
import java.util.Optional;

import com.example.webproject.service.serviceImpl.MapValidationErrorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/bookmarks", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class BookmarkApi {

    private final BookmarkService bookmarkService;
    private final MapValidationErrorServiceImpl mapValidationErrorService;

    public BookmarkApi(BookmarkService bookmarkService, MapValidationErrorServiceImpl mapValidationErrorService) {
        this.bookmarkService = bookmarkService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Bookmark> getAllBookmarks(){
        return bookmarkService.getAllBookmarks();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createBookmark(@Valid @RequestBody Bookmark bookmark, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;
        return this.bookmarkService.save(bookmark);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Bookmark> getBookmark(@PathVariable long id){
        return this.bookmarkService.findById(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Bookmark updateBookmark(@PathVariable long id,
                                   @RequestBody Bookmark bookmark){
        bookmark.setId(id);
        return this.bookmarkService.save(bookmark);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBookmark(@PathVariable long id){
        this.bookmarkService.deleteBookmark(id);
    }

}
