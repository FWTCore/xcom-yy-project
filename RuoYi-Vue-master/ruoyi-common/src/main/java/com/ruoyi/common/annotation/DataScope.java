package com.ruoyi.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据权限过滤注解
 * 
 * @author ruoyi
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {
    /**
     * 部门表的别名
     */
    String deptAlias() default "";

    /**
     * 用户表的别名
     */
    String userAlias() default "";

    /**
     * 项目表的别名
     */
    String projectAlias() default "";

    /**
     * 是否是本表
     * @return
     */
    boolean isSelfTable() default false;

    /**
     * 项目是否升上
     * @return
     */
    boolean isUpgrade() default false;

    /**
     * 是否忽略header的项目
     * @return
     */
    boolean isIgnoreHeaderProject() default false;

    /**
     * 权限字符（用于多个角色匹配符合要求的权限）默认根据权限注解@ss获取，多个权限用逗号分隔开来
     */
    String permission() default "";
}
