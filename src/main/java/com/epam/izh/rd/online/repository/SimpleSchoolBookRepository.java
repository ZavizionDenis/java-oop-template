package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository <SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook [] newSchoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks = newSchoolBooks;
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int findBookCount = 0;
        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                findBookCount++;
            }
        }

        if (findBookCount > 0) {
            SchoolBook[] findBooks = new SchoolBook[findBookCount];
            int nextFindBookIndex = 0;
            for (int j = 0; j < findBooks.length; j++) {
                for (int i = nextFindBookIndex; i < schoolBooks.length; i++) {
                    if (schoolBooks[i].getName().equals(name)) {
                        findBooks[j] = schoolBooks[i];
                        nextFindBookIndex = i + 1;
                    }
                }
            }
            return findBooks;
        }
        return new SchoolBook[0];
    }

    @Override
    public boolean removeByName(String name) {
        int findBookforDelete = 0;
        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                findBookforDelete++;
            }
        }

        if (findBookforDelete > 0) {
            SchoolBook [] newSchoolBooks = new SchoolBook[schoolBooks.length - findBookforDelete];
            int lastBookIndex = 0;
            for (int i = 0; i < schoolBooks.length; i++) {
                if (!schoolBooks[i].getName().equals(name)) {
                    newSchoolBooks[lastBookIndex] = schoolBooks[i];
                    lastBookIndex++;
                }
            }
            schoolBooks = newSchoolBooks;
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}