package com.maksimov.models;

public class Page {

    private int page;
    private int size;
    private int totalPages;
    private String sort;
    private boolean next;

    public Page(Integer page, Integer size, String sort) {
        if (page < 0) {
            throw new IllegalArgumentException("Page number can't be less then 0");
        }

        if (size < 0) {
            throw new IllegalArgumentException("Page size can't be less then 0");
        }
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    public int getPageNumber() {
        return page;
    }

    public int getPageSize() {
        return size;
    }

    public String getSort() {
        return sort;
    }

    public int getFirstResult() {
        return (page - 1) * size;
    }

    public boolean hasPrevious() {
        return page > 1;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean hasNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Page page1 = (Page) o;

        return page == page1.page && getSort().equals(page1.getSort());
    }

    @Override
    public int hashCode() {
        int result = page;
        result = 31 * result + size;
        result = 31 * result + getSort().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Page {" +
                "page=" + page +
                ", size=" + size +
                ", sort='" + sort + '\'' +
                '}';
    }
}
