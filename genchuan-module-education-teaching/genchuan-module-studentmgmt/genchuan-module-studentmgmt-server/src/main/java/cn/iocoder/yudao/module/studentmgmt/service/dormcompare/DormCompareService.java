package cn.iocoder.yudao.module.studentmgmt.service.dormcompare;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcompare.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormcompare.DormCompareDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 宿舍评比 Service 接口
 *
 * @author 芋道源码
 */
public interface DormCompareService {

    /**
     * 创建宿舍评比
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDormCompare(@Valid DormCompareSaveReqVO createReqVO);

    /**
     * 更新宿舍评比
     *
     * @param updateReqVO 更新信息
     */
    void updateDormCompare(@Valid DormCompareSaveReqVO updateReqVO);

    /**
     * 删除宿舍评比
     *
     * @param id 编号
     */
    void deleteDormCompare(Long id);

    /**
    * 批量删除宿舍评比
    *
    * @param ids 编号
    */
    void deleteDormCompareListByIds(List<Long> ids);

    /**
     * 获得宿舍评比
     *
     * @param id 编号
     * @return 宿舍评比
     */
    DormCompareDO getDormCompare(Long id);

    /**
     * 获得宿舍评比分页
     *
     * @param pageReqVO 分页查询
     * @return 宿舍评比分页
     */
    PageResult<DormCompareDO> getDormComparePage(DormComparePageReqVO pageReqVO);

}