package cn.iocoder.yudao.module.envir.service.garbagetransfer;

import java.util.*;

import cn.iocoder.yudao.module.envir.dal.dataobject.garbagetransfer.GarbageTransferDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.roadcleaning.RoadCleaningDetailDO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envir.controller.admin.garbagetransfer.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.garbagetransfer.GarbageTransferDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 垃圾转运站 Service 接口
 *
 * @author 芋道源码
 */
public interface GarbageTransferService {

    /**
     * 创建垃圾转运站
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGarbageTransfer(@Valid GarbageTransferSaveReqVO createReqVO);

    /**
     * 更新垃圾转运站
     *
     * @param updateReqVO 更新信息
     */
    void updateGarbageTransfer(@Valid GarbageTransferSaveReqVO updateReqVO);

    /**
     * 删除垃圾转运站
     *
     * @param id 编号
     */
    void deleteGarbageTransfer(Long id);

    /**
     * 获得垃圾转运站
     *
     * @param id 编号
     * @return 垃圾转运站
     */
    GarbageTransferDO getGarbageTransfer(Long id);

    /**
     * 获得垃圾转运站分页
     *
     * @param pageReqVO 分页查询
     * @return 垃圾转运站分页
     */
    PageResult<GarbageTransferDO> getGarbageTransferPage(GarbageTransferPageReqVO pageReqVO);

    /**
     * 获得垃圾转运列表详情
     *
     * @return 垃圾转运详情列表
     */
    List<GarbageTransferDetailDO> getGarbageTransferListDetail();
}