package com.excilys.computer_database.model;

import java.util.List;

import com.excilys.computer_database.model.utils.Order;

/**
 * Page model that store information of a page from the point of view of the database.
 * @author rlarroque
 */
public class Page {

    private int currentPage;
    private int offset;
    private int totalComputer;
    private int startIndex;
    private int totalPage;
    private Order order;
    private String filter;
    private List<Computer> computers;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotalComputer() {
        return totalComputer;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public void setTotalComputer(int totalComputer) {
        this.totalComputer = totalComputer;
    }

    public List<Computer> getComputers() {
        return computers;
    }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Constructor.
     * @param currentPage page number
     * @param offset offset
     * @param filter filter
     */
    public Page(int currentPage, int offset, String filter) {
        this.currentPage = currentPage;
        this.offset = offset;
        this.filter = filter;
    }

    /**
     * Default constructor.
     */
    public Page() {

    }

}