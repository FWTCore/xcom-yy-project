package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.EmployeeDO;
import com.ruoyi.business.domain.model.Employee;
import com.ruoyi.business.mapper.EmployeeMapper;
import com.ruoyi.business.service.EmployeeService;
import com.ruoyi.common.exception.ServiceException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Employee Service
 *
 * @author xcom
 * @date 2025/9/20
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 查询员工
     *
     * @param id 员工主键
     * @return 员工
     */
    @Override
    public EmployeeDO selectEmployeeById(Long id) {
        return employeeMapper.selectEmployeeById(id);
    }

    /**
     * 查询员工
     *
     * @param ids 员工主键
     * @return 员工
     */
    @Override
    public List<EmployeeDO> selectEmployeeByIds(List<Long> ids) {
        return employeeMapper.selectEmployeeByIds(ids);
    }

    /**
     * 查询员工列表
     *
     * @param employee 员工
     * @return 员工
     */
    @Override
    public List<EmployeeDO> selectEmployeeList(Employee employee) {
        return employeeMapper.selectEmployeeList(employee);
    }

    /**
     * 新增员工
     *
     * @param employee 员工
     * @return 结果
     */
    @Override
    public int insertEmployee(EmployeeDO employee) {
        employee.setBaseFieldValue();
        return employeeMapper.insertEmployee(employee);
    }

    /**
     * 修改员工
     *
     * @param employee 员工
     * @return 结果
     */
    @Override
    public int updateEmployee(EmployeeDO employee) {
        employee.setUpdatedFieldValue();
        return employeeMapper.updateEmployee(employee);
    }

    /**
     * 批量删除员工
     *
     * @param ids 需要删除的员工主键
     * @return 结果
     */
    @Override
    public int deleteEmployeeByIds(Long[] ids) {
        return employeeMapper.deleteEmployeeByIds(ids);
    }

    /**
     * 删除员工信息
     *
     * @param id 员工主键
     * @return 结果
     */
    @Override
    public int deleteEmployeeById(Long id) {
        return employeeMapper.deleteEmployeeById(id);
    }

    @Override
    public Boolean upsetData(EmployeeDO data) {
        Employee employee = new Employee();
        employee.setDeptId(data.getDeptId());
        employee.setEmployeeName(data.getEmployeeName());
        List<EmployeeDO> existNameData = this.selectEmployeeList(employee);
        if (ObjectUtils.isEmpty(data.getId())) {
            if (CollectionUtils.isNotEmpty(existNameData)) {
                throw new ServiceException("员工名称已存在");
            }
            this.insertEmployee(data);
        } else {
            if (CollectionUtils.isNotEmpty(existNameData)) {
                if (existNameData.size() > 1) {
                    throw new ServiceException("员工名称已存在");
                }
                EmployeeDO existNameObj = existNameData.get(0);
                if (!existNameObj.getId().equals(data.getId())) {
                    throw new ServiceException("员工名称已存在");
                }
            }
            EmployeeDO existNameObj = this.selectEmployeeById(data.getId());
            if (ObjectUtils.isEmpty(existNameObj)) {
                throw new ServiceException("编辑的员工不存在");
            }
            this.updateEmployee(data);
        }
        return true;
    }
}
