package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (author == null || author.getName().isEmpty() || author.getLastName().isEmpty()) {
            return false;
        }

        if (findByFullName(author.getName(), author.getLastName()) == null) {
            Author[] newAuthors = Arrays.copyOf(authors, authors.length + 1);
            authors = newAuthors;
            authors[authors.length - 1] = author;
            return true;
        }

        return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        if (name.isEmpty() || lastname.isEmpty()) {
            return null;
        }

        for (Author author : authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (author == null) {
            return false;
        }

        if (findByFullName(author.getName(), author.getLastName()) != null) {
            Author[] newAuthors = Arrays.copyOf(authors, authors.length - 1);
            int newAuthorsIndex = 0;
            for (Author item : authors) {
                if (!item.equals(author)) {
                    newAuthors[newAuthorsIndex] = item;
                    newAuthorsIndex++;
                }
            }
            authors = newAuthors;
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
