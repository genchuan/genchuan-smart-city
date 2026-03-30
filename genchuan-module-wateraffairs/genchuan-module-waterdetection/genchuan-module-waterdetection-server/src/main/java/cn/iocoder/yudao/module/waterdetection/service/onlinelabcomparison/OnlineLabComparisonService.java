package cn.iocoder.yudao.module.waterdetection.service.onlinelabcomparison;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.onlinelabcomparison.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.onlinelabcomparison.OnlineLabComparisonDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 在线数据与实验室比对 Service 接口
 *
 * @author zcq
 */
public interface OnlineLabComparisonService {

    /**
     * 创建在线数据与实验室比对
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOnlineLabComparison(@Valid OnlineLabComparisonSaveReqVO createReqVO);

    /**
     * 更新在线数据与实验室比对
     *
     * @param updateReqVO 更新信息
     */
    void updateOnlineLabComparison(@Valid OnlineLabComparisonSaveReqVO updateReqVO);

    /**
     * 删除在线数据与实验室比对
     *
     * @param id 编号
     */
    void deleteOnlineLabComparison(Long id);

    /**
     * 获得在线数据与实验室比对
     *
     * @param id 编号
     * @return 在线数据与实验室比对
     */
    OnlineLabComparisonDO getOnlineLabComparison(Long id);

    /**
     * 获得在线数据与实验室比对分页
     *
     * @param pageReqVO 分页查询
     * @return 在线数据与实验室比对分页
     */
    PageResult<OnlineLabComparisonDO> getOnlineLabComparisonPage(OnlineLabComparisonPageReqVO pageReqVO);

}