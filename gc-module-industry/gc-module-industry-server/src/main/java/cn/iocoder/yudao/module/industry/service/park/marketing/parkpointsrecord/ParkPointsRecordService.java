package cn.iocoder.yudao.module.industry.service.park.marketing.parkpointsrecord;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrecord.vo.ParkPointsRecordPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrecord.vo.ParkPointsRecordSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.marketing.parkpointsrecord.ParkPointsRecordDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 积分变动记录 Service 接口
 *
 * @author lxs
 */
public interface ParkPointsRecordService {

    /**
     * 创建积分变动记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkPointsRecord(@Valid ParkPointsRecordSaveReqVO createReqVO);

    /**
     * 更新积分变动记录
     *
     * @param updateReqVO 更新信息
     */
    void updateParkPointsRecord(@Valid ParkPointsRecordSaveReqVO updateReqVO);

    /**
     * 删除积分变动记录
     *
     * @param id 编号
     */
    void deleteParkPointsRecord(Long id);

    /**
     * 获得积分变动记录
     *
     * @param id 编号
     * @return 积分变动记录
     */
    ParkPointsRecordDO getParkPointsRecord(Long id);

    /**
     * 获得积分变动记录分页
     *
     * @param pageReqVO 分页查询
     * @return 积分变动记录分页
     */
    PageResult<ParkPointsRecordDO> getParkPointsRecordPage(ParkPointsRecordPageReqVO pageReqVO);

}
