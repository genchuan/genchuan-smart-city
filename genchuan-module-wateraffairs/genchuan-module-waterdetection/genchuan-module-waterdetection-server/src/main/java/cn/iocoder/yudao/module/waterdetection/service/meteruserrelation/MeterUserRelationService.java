package cn.iocoder.yudao.module.waterdetection.service.meteruserrelation;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.meteruserrelation.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.meteruserrelation.MeterUserRelationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 户表关联及变更管理 Service 接口
 *
 * @author zcq
 */
public interface MeterUserRelationService {

    /**
     * 创建户表关联及变更管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMeterUserRelation(@Valid MeterUserRelationSaveReqVO createReqVO);

    /**
     * 更新户表关联及变更管理
     *
     * @param updateReqVO 更新信息
     */
    void updateMeterUserRelation(@Valid MeterUserRelationSaveReqVO updateReqVO);

    /**
     * 删除户表关联及变更管理
     *
     * @param id 编号
     */
    void deleteMeterUserRelation(Long id);

    /**
     * 获得户表关联及变更管理
     *
     * @param id 编号
     * @return 户表关联及变更管理
     */
    MeterUserRelationDO getMeterUserRelation(Long id);

    /**
     * 获得户表关联及变更管理分页
     *
     * @param pageReqVO 分页查询
     * @return 户表关联及变更管理分页
     */
    PageResult<MeterUserRelationDO> getMeterUserRelationPage(MeterUserRelationPageReqVO pageReqVO);

}