package com.ruoyi.business.domain.model;

import com.ruoyi.business.domain.entity.CategoryDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 分类;category数据表的DO对象
 *
 * @author : xcom
 * @date : 2025-9-18
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Category extends CategoryDO {

    private String searchName;

}