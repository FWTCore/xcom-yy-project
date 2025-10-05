package com.ruoyi.business.service;

import com.ruoyi.business.domain.entity.EmployeeDO;
import com.ruoyi.business.domain.model.Employee;
import com.ruoyi.business.domain.model.EmployeeDetailVO;

import java.util.List;

/**
 * Employee Service
 *
 * @author xcom
 * @date 2025/9/20
 */

public interface EmployeeService {

    /**
     * 查询员工
     *
     * @param id 员工主键
     * @return 员工
     */
    EmployeeDO selectEmployeeById(Long id);

    /**
     * 查询分类
     *
     * @param ids  员工主键
     * @return 分类
     */
    List<EmployeeDO> selectEmployeeByIds(List<Long> ids);

    /**
     * 查询员工列表
     *
     * @param employee 员工
     * @return 员工集合
     */
    List<EmployeeDO> selectEmployeeList(Employee employee);
    /**
     * 查询员工列表
     *
     * @param employee 员工
     * @return 员工集合
     */
    List<EmployeeDetailVO> selectEmployeeDetailList(Employee employee);

    /**
     * 新增员工
     *
     * @param employee 员工
     * @return 结果
     */
    int insertEmployee(EmployeeDO employee);

    /**
     * 修改员工
     *
     * @param employee 员工
     * @return 结果
     */
    int updateEmployee(EmployeeDO employee);

    /**
     * 批量删除员工
     *
     * @param ids 需要删除的员工主键集合
     * @return 结果
     */
    int deleteEmployeeByIds(Long[] ids);

    /**
     * 删除员工信息
     *
     * @param id 员工主键
     * @return 结果
     */
    int deleteEmployeeById(Long id);


    /**
     * 新增、更新数据
     * @param data
     * @return
     */
    Boolean upsetData(EmployeeDO data);

}
