package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.entity.EmployeeDO;
import com.ruoyi.business.domain.model.Employee;
import com.ruoyi.business.domain.model.EmployeeDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 员工;(employee)表数据库访问层
 * @author :xcom
 * @date : 2025-9-18
 */
@Mapper
public interface EmployeeMapper {
    /**
     * 查询员工
     *
     * @param id 员工主键
     * @return 员工
     */
    EmployeeDO selectEmployeeById(Long id);

    /**
     * 查询员工
     *
     * @param ids 员工主键
     * @return 员工
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
     * 删除员工
     *
     * @param id 员工主键
     * @return 结果
     */
    int deleteEmployeeById(Long id);

    /**
     * 批量删除员工
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteEmployeeByIds(Long[] ids);
}