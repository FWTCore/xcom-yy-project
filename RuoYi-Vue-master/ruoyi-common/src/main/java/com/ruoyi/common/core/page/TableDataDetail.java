package com.ruoyi.common.core.page;

import java.io.Serializable;
import java.util.List;

/**
 * TableDataDetail
 *
 * @author xcom
 * @date 2025/10/7
 */

public class TableDataDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private long              total;

    /** 列表数据 */
    private List<?>           data;

    /** 消息状态码 */
    private int               code;

    /** 消息内容 */
    private String            msg;

    /**
     * 表格数据对象
     */
    public TableDataDetail() {
    }

    /**
     * 分页
     *
     * @param list 列表数据
     * @param total 总记录数
     */
    public TableDataDetail(List<?> list, long total) {
        this.data = list;
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> rows) {
        this.data = rows;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
