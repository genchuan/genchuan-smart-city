package cn.iocoder.yudao.module.facility.service.manhole.manholecover;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholecover.vo.ManholeCoverPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholecover.vo.ManholeCoverSaveReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholecover.ManholeCoverDO;
import jakarta.validation.Valid;

/**
 * 窨井盖设施 Service 接口
 *
 * @author 亘川智城
 */
public interface ManholeCoverService {

    /**
     * 创建窨井盖设施
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCover(@Valid ManholeCoverSaveReqVO createReqVO);

    /**
     * 更新窨井盖设施
     *
     * @param updateReqVO 更新信息
     */
    void updateCover(@Valid ManholeCoverSaveReqVO updateReqVO);

    /**
     * 删除窨井盖设施
     *
     * @param id 编号
     */
    void deleteCover(Long id);

    /**
     * 获得窨井盖设施
     *
     * @param id 编号
     * @return 窨井盖设施
     */
    ManholeCoverDO getCover(Long id);

    /**
     * 获得窨井盖设施分页
     *
     * @param pageReqVO 分页查询
     * @return 窨井盖设施分页
     */
    PageResult<ManholeCoverDO> getCoverPage(ManholeCoverPageReqVO pageReqVO);

}