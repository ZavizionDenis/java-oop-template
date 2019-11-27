package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.repository.AuthorRepository;

public class SimpleAuthorService implements AuthorService {
    private AuthorRepository authorRepository;

    public SimpleAuthorService() {
    }

    public SimpleAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean save(Author author) {
        if (author == null || author.getName().isEmpty() || author.getLastName().isEmpty()) {
            return false;
        }

        return authorRepository.save(author);
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        if (name.isEmpty() || lastname.isEmpty()) {
            return null;
        }

        return authorRepository.findByFullName(name, lastname);
    }

    @Override
    public boolean remove(Author author) {
        if (author == null || author.getName().isEmpty() || author.getLastName().isEmpty()) {
            return false;
        }

        return authorRepository.remove(author);
    }

    @Override
    public int count() {
        return authorRepository.count();
    }
}
