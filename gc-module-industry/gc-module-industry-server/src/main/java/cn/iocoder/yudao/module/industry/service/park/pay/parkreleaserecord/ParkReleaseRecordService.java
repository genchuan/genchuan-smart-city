package cn.iocoder.yudao.module.industry.service.park.pay.parkreleaserecord;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreleaserecord.vo.ParkReleaseRecordAddReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreleaserecord.vo.ParkReleaseRecordPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreleaserecord.vo.ParkReleaseRecordSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkreleaserecord.ParkReleaseRecordDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 放行记录 Service 接口
 *
 * @author lxs
 */
public interface ParkReleaseRecordService {

    /**
     * 创建放行记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkReleaseRecord(@Valid ParkReleaseRecordSaveReqVO createReqVO);

    /**
     * 更新放行记录
     *
     * @param updateReqVO 更新信息
     */
    void updateParkReleaseRecord(@Valid ParkReleaseRecordSaveReqVO updateReqVO);

    /**
     * 删除放行记录
     *
     * @param id 编号
     */
    void deleteParkReleaseRecord(Long id);

    /**
     * 获得放行记录
     *
     * @param id 编号
     * @return 放行记录
     */
    ParkReleaseRecordDO getParkReleaseRecord(Long id);

    /**
     * 获得放行记录分页
     *
     * @param pageReqVO 分页查询
     * @return 放行记录分页
     */
    PageResult<ParkReleaseRecordDO> getParkReleaseRecordPage(ParkReleaseRecordPageReqVO pageReqVO);


    Long addParkReleaseRecord(ParkReleaseRecordAddReqVO addReqVO);
}
