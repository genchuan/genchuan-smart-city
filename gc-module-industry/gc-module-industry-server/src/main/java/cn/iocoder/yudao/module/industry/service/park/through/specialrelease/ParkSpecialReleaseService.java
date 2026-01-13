package cn.iocoder.yudao.module.industry.service.park.through.specialrelease;

import cn.iocoder.yudao.module.industry.controller.admin.park.through.specialrelease.vo.ParkSpecialReleasePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.specialrelease.vo.ParkSpecialReleaseSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.specialrelease.ParkSpecialReleaseDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 特殊放行 Service 接口
 *
 * @author zhucongquan
 */
public interface ParkSpecialReleaseService {

    /**
     * 创建特殊放行
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkSpecialRelease(@Valid ParkSpecialReleaseSaveReqVO createReqVO);

    /**
     * 更新特殊放行
     *
     * @param updateReqVO 更新信息
     */
    void updateParkSpecialRelease(@Valid ParkSpecialReleaseSaveReqVO updateReqVO);

    /**
     * 删除特殊放行
     *
     * @param id 编号
     */
    void deleteParkSpecialRelease(Long id);

    /**
     * 获得特殊放行
     *
     * @param id 编号
     * @return 特殊放行
     */
    ParkSpecialReleaseDO getParkSpecialRelease(Long id);

    /**
     * 获得特殊放行分页
     *
     * @param pageReqVO 分页查询
     * @return 特殊放行分页
     */
    PageResult<ParkSpecialReleaseDO> getParkSpecialReleasePage(ParkSpecialReleasePageReqVO pageReqVO);

}