package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.DepartmentDO;
import com.ruoyi.business.domain.model.Department;
import com.ruoyi.business.mapper.DepartmentMapper;
import com.ruoyi.business.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Department Service
 *
 * @author xcom
 * @date 2025/9/20
 */

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;

    /**
     * 查询部门
     *
     * @param id 部门主键
     * @return 部门
     */
    @Override
    public DepartmentDO selectDepartmentById(Long id) {
        return departmentMapper.selectDepartmentById(id);
    }

    /**
     * 查询部门
     *
     * @param ids 部门主键
     * @return 部门
     */
    @Override
    public List<DepartmentDO> selectDepartmentByIds(List<Long> ids) {
        return departmentMapper.selectDepartmentByIds(ids);
    }

    /**
     * 查询部门列表
     *
     * @param department 部门
     * @return 部门
     */
    @Override
    public List<DepartmentDO> selectDepartmentList(Department department) {
        return departmentMapper.selectDepartmentList(department);
    }

    /**
     * 新增部门
     *
     * @param department 部门
     * @return 结果
     */
    @Override
    public int insertDepartment(DepartmentDO department) {
        return departmentMapper.insertDepartment(department);
    }

    /**
     * 修改部门
     *
     * @param department 部门
     * @return 结果
     */
    @Override
    public int updateDepartment(DepartmentDO department) {
        return departmentMapper.updateDepartment(department);
    }

    /**
     * 批量删除部门
     *
     * @param ids 需要删除的部门主键
     * @return 结果
     */
    @Override
    public int deleteDepartmentByIds(Long[] ids) {
        return departmentMapper.deleteDepartmentByIds(ids);
    }

    /**
     * 删除部门信息
     *
     * @param id 部门主键
     * @return 结果
     */
    @Override
    public int deleteDepartmentById(Long id) {
        return departmentMapper.deleteDepartmentById(id);
    }
}
