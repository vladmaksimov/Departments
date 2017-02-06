package com.maksimov.data;

public class Page implements Pageable {

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

    @Override
    public int getPageNumber() {
        return page;
    }

    @Override
    public int getPageSize() {
        return size;
    }

    @Override
    public String getSort() {
        return sort;
    }

    @Override
    public int getFirstResult() {
        return (page - 1) * size;
    }

    @Override
    public boolean hasPrevious() {
        return page > 1;
    }

    @Override
    public int getTotalPages() {
        return totalPages;
    }

    @Override
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
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
