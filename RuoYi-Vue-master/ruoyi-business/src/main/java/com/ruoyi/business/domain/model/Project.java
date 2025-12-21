package com.ruoyi.business.domain.model;

import com.ruoyi.business.domain.entity.ProjectDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 项目;project数据表的DO对象
 *
 * @author : xcom
 * @date : 2025-9-18
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Project extends ProjectDO {

    private String searchName;
}