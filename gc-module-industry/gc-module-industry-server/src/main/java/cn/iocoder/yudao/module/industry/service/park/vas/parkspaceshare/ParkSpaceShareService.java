package cn.iocoder.yudao.module.industry.service.park.vas.parkspaceshare;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshare.vo.ParkSpaceSharePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshare.vo.ParkSpaceShareSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkspaceshare.ParkSpaceShareDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 车位共享配置 Service 接口
 *
 * @author lxs
 */
public interface ParkSpaceShareService {

    /**
     * 创建车位共享配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkSpaceShare(@Valid ParkSpaceShareSaveReqVO createReqVO);

    /**
     * 更新车位共享配置
     *
     * @param updateReqVO 更新信息
     */
    void updateParkSpaceShare(@Valid ParkSpaceShareSaveReqVO updateReqVO);

    /**
     * 删除车位共享配置
     *
     * @param id 编号
     */
    void deleteParkSpaceShare(Long id);

    /**
     * 获得车位共享配置
     *
     * @param id 编号
     * @return 车位共享配置
     */
    ParkSpaceShareDO getParkSpaceShare(Long id);

    /**
     * 获得车位共享配置分页
     *
     * @param pageReqVO 分页查询
     * @return 车位共享配置分页
     */
    PageResult<ParkSpaceShareDO> getParkSpaceSharePage(ParkSpaceSharePageReqVO pageReqVO);

}
