package cn.iocoder.yudao.module.studentmgmt.service.dormassign;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dormassign.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormassign.DormAssignDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 宿舍分配 Service 接口
 *
 * @author 芋道源码
 */
public interface DormAssignService {

    /**
     * 创建宿舍分配
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDormAssign(@Valid DormAssignSaveReqVO createReqVO);

    /**
     * 更新宿舍分配
     *
     * @param updateReqVO 更新信息
     */
    void updateDormAssign(@Valid DormAssignSaveReqVO updateReqVO);

    /**
     * 删除宿舍分配
     *
     * @param id 编号
     */
    void deleteDormAssign(Long id);

    /**
    * 批量删除宿舍分配
    *
    * @param ids 编号
    */
    void deleteDormAssignListByIds(List<Long> ids);

    /**
     * 获得宿舍分配
     *
     * @param id 编号
     * @return 宿舍分配
     */
    DormAssignDO getDormAssign(Long id);

    /**
     * 获得宿舍分配分页
     *
     * @param pageReqVO 分页查询
     * @return 宿舍分配分页
     */
    PageResult<DormAssignDO> getDormAssignPage(DormAssignPageReqVO pageReqVO);

}