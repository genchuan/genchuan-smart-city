package cn.iocoder.yudao.module.envir.service.dept;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envir.controller.admin.dept.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 部门 Service 接口
 *
 * @author 芋道源码
 */
public interface DeptService {

    /**
     * 创建部门
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDept(@Valid DeptSaveReqVO createReqVO);

    /**
     * 更新部门
     *
     * @param updateReqVO 更新信息
     */
    void updateDept(@Valid DeptSaveReqVO updateReqVO);

    /**
     * 删除部门
     *
     * @param id 编号
     */
    void deleteDept(Long id);

    /**
     * 获得部门
     *
     * @param id 编号
     * @return 部门
     */
    DeptDO getDept(Long id);

    /**
     * 获得部门分页
     *
     * @param pageReqVO 分页查询
     * @return 部门分页
     */
    PageResult<DeptDO> getDeptPage(DeptPageReqVO pageReqVO);

}