package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService <SchoolBook> {
    private BookRepository<SchoolBook> schoolBookBookRepository;
    private AuthorService authorService;

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }

    @Override
    public boolean save(SchoolBook book) {
        if (book == null) {
            return false;
        }
        if (authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName()) != null) {
            return schoolBookBookRepository.save(book);
        }
        return false;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        if (name.isEmpty()) {
            return new SchoolBook[0];
        }

        return schoolBookBookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        if (name.isEmpty()) {
            return 0;
        }

        return schoolBookBookRepository.findByName(name).length;
    }

    @Override
    public boolean removeByName(String name) {
        if (name.isEmpty()) {
            return false;
        }

        return schoolBookBookRepository.removeByName(name);
    }

    @Override
    public int count() {
        return schoolBookBookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {
        if (findByName(name).length == 0) {
            return null;
        }

        SchoolBook book = findByName(name)[0];
        return authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName());
    }
}