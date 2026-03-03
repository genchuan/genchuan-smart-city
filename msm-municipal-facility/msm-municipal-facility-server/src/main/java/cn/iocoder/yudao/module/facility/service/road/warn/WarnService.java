package cn.iocoder.yudao.module.facility.service.road.warn;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.facility.controller.admin.road.warn.vo.WarnPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.warn.vo.WarnSaveReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.warn.WarnDO;
import jakarta.validation.Valid;

/**
 * 预警 Service 接口
 *
 * @author 亘川智城
 */
public interface WarnService {

    /**
     * 创建预警
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWarn(@Valid WarnSaveReqVO createReqVO);

    /**
     * 更新预警
     *
     * @param updateReqVO 更新信息
     */
    void updateWarn(@Valid WarnSaveReqVO updateReqVO);

    /**
     * 删除预警
     *
     * @param id 编号
     */
    void deleteWarn(Long id);

    /**
     * 获得预警
     *
     * @param id 编号
     * @return 预警
     */
    WarnDO getWarn(Long id);

    /**
     * 获得预警分页
     *
     * @param pageReqVO 分页查询
     * @return 预警分页
     */
    PageResult<WarnDO> getWarnPage(WarnPageReqVO pageReqVO);

}
