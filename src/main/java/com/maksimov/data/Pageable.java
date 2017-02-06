package com.maksimov.data;

/**
 * Abstract interface for pagination information.
 */
public interface Pageable {

    /**
     * Returns the page to be returned.
     *
     * @return the page to be returned.
     */
    int getPageNumber();

    /**
     * Returns the number of items to be returned.
     *
     * @return the number of items of that page
     */
    int getPageSize();

    /**
     * Return the field of the items to be sorted.
     *
     * @return the {@link String} field of the items to be sorted.
     */
    String getSort();

    /**
     * Returns the first number of the row of current page.
     *
     * @return the offset to be taken
     */
    int getFirstResult();

    boolean hasPrevious();

    int getTotalPages();

    void setTotalPages(int totalPages);

    boolean hasNext();

    void setNext(boolean next);

}
