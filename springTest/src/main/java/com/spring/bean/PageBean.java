package com.spring.bean;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 显示格式 每页3条，共8条，第1页，共3页   首页  上一页  下一页  尾页
 *   Go
 */
public class PageBean {

    private int page = 1;// 页码

    private int rows = 3;// 页大小

    private int total = 0;// 总记录数

    private boolean pagination = true;// 是否分页

    private String url;
    private Map<String, String[]> paramMap = new HashMap<>();
    //	定义初始化方法，存入上一次请求的值
    public void setRequest(HttpServletRequest req) {
        this.setPage(req.getParameter("page"));
        this.setRows(req.getParameter("rows"));
        this.setPagination(req.getParameter("pagination"));
        // getRequestURL获取到浏览器请求的全路径
        this.setUrl(req.getRequestURL().toString());
        // getParameterMap可以获取到一次url请求所携带的所有参数
        this.setParamMap(req.getParameterMap());

    }

    public void setPagination(String pagination) {
        if (StringUtils.isNotBlank(pagination)) {
            this.setPagination(!"false".equals(pagination));
        }
    }

    public void setRows(String rows) {
        if (StringUtils.isNotBlank(rows))
            this.setRows(Integer.valueOf(rows));

    }

    public void setPage(String page) {
        if (StringUtils.isNotBlank(page)) {
            this.setPage(Integer.valueOf(page));
        }
    }

    public PageBean() {
        super();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String[]> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String[]> paramMap) {
        this.paramMap = paramMap;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTotal(String total) {
        this.total = Integer.parseInt(total);
    }

    public boolean isPagination() {
        return pagination;
    }

    public void setPagination(boolean pagination) {
        this.pagination = pagination;
    }

    /**
     * 获得起始记录的下标
     *
     * @return
     */
    public int getStartIndex() {
        return (this.page - 1) * this.rows;
    }

    @Override
    public String toString() {
        return "PageBean [page=" + page + ", rows=" + rows + ", total=" + total + ", pagination=" + pagination + "]";
    }

    /**
     * 获取到总页数
     * @return
     */
    public int getMaxPage() {
        return this.total % this.rows == 0 ?
                this.total / this.rows :
                (this.total / this.rows) + 1;
    }
    /**
     * 获取下一页页码
     * @return
     */
    public int getNextPage() {
        return this.page < this.getMaxPage() ? this.page+1 : this.page;
    }

    /**
     * 获取上一页页码
     * @return
     */
    public int getPreviousPage() {
        return this.page > 1 ? this.page-1 : this.page;
    }

}



