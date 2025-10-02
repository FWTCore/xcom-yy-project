package com.ruoyi.business.service;

import com.ruoyi.business.domain.entity.DepartmentDO;
import com.ruoyi.business.domain.model.Department;

import java.util.List;

/**
 * Department Service
 *
 * @author xcom
 * @date 2025/9/20
 */

public interface DepartmentService {
    /**
     * 查询部门
     *
     * @param id 部门主键
     * @return 部门
     */
    DepartmentDO selectDepartmentById(Long id);

    /**
     * 查询分类
     *
     * @param ids  部门主键
     * @return 分类
     */
    List<DepartmentDO> selectDepartmentByIds(List<Long> ids);

    /**
     * 查询部门列表
     *
     * @param department 部门
     * @return 部门集合
     */
    List<DepartmentDO> selectDepartmentList(Department department);

    /**
     * 新增部门
     *
     * @param department 部门
     * @return 结果
     */
    int insertDepartment(DepartmentDO department);

    /**
     * 修改部门
     *
     * @param department 部门
     * @return 结果
     */
    int updateDepartment(DepartmentDO department);

    /**
     * 批量删除部门
     *
     * @param ids 需要删除的部门主键集合
     * @return 结果
     */
    int deleteDepartmentByIds(Long[] ids);

    /**
     * 删除部门信息
     *
     * @param id 部门主键
     * @return 结果
     */
    int deleteDepartmentById(Long id);

    /**
     * 新增、更新数据
     * @param data
     * @return
     */
    Boolean upsetData(DepartmentDO data);
}
