package com.peng44.web_template.commons.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nile
 */
@Data
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 8759874641772522011L;

    /**
     * asc 升序
     * desc 倒序
     */
    private static final String SORT_ASC = "asc";
    private static final String SORT_DESC = "desc";

    /**
     * 当前页
     */
    private Integer currentPage = 1;

    /**
     * 每页显示条数
     */
    private Integer pageSize = 20;

    /**
     * 总页数
     */
    private Integer totalPage = 0;

    /**
     * 总条数
     */
    private Integer totalCount = 0;

    /**
     * 分页起始位置
     */
    private Integer index;

    /**
     * 数据
     */
    private List<T> list;

    /**
     * 条件参数
     */
    public Map<String, Object> params = new HashMap<>(16);

    /**s
     * 排序列
     */
    private String sortColumn;

    /**
     * 排序方式
     */
    private String sortMethod = "asc";

    /**
     * 获取当前页 头溢出 当前当前页数小于1时，赋值为1
     * @return
     */
    public Integer getCurrentPage(){
        if(currentPage < 1){
            return 1;
        }
        return this.currentPage;
    }

    /**
     * 获取index
     * @return
     */
    public Integer getIndex(){
        return (currentPage - 1) * pageSize;
    }

    /**
     * 设置总条数的时候计算总页数
     * @param totalCount
     */
    public void setTotalCount(Integer totalCount){
        this.totalCount = totalCount;
        this.totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
    }

    /**
     * 设置排序方式
     */
    public void setSortMethod(String sortMethod) {
        if (StringUtils.isBlank(sortMethod)) {
            this.sortMethod = SORT_ASC;
        }
        if (sortMethod.toLowerCase().startsWith(SORT_ASC)) {
            this.sortMethod = SORT_ASC;
        } else if (sortMethod.toLowerCase().startsWith(SORT_DESC)) {
            this.sortMethod = SORT_DESC;
        } else {
            this.sortMethod = SORT_ASC;
        }
    }

    /**
     * 分页输出
     */
    public void pagingDate(){
        int start = (currentPage-1) * pageSize;
        int end =  currentPage * pageSize;
        if(currentPage <= totalPage && end >= totalCount){
            this.list = list.subList(start, totalCount);
        }else if(currentPage > totalPage){
            this.currentPage = totalPage;
            this.list = currentPage > 0 ? list.subList((currentPage-1) * pageSize, totalCount):null;
        }else {
            this. list = list.subList(start , end);
        }
    }
}
