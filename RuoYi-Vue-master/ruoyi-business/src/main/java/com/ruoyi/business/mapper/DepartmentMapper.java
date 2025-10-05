package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.entity.DepartmentDO;
import com.ruoyi.business.domain.model.Department;
import com.ruoyi.business.domain.model.DepartmentDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 部门;(department)表数据库访问层
 * @author :xcom
 * @date : 2025-9-18
 */
@Mapper
public interface DepartmentMapper {
    /**
     * 查询部门
     *
     * @param id 部门主键
     * @return 部门
     */
    DepartmentDO selectDepartmentById(Long id);

    /**
     * 查询部门
     *
     * @param ids 部门主键
     * @return 部门
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
     * 查询部门列表
     * @param department
     * @return
     */
    List<DepartmentDetailVO> selectDepartmentDetailList(Department department);

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
     * 删除部门
     *
     * @param id 部门主键
     * @return 结果
     */
    int deleteDepartmentById(Long id);

    /**
     * 批量删除部门
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteDepartmentByIds(Long[] ids);
}