package cn.iocoder.yudao.module.smartcity.service.landscaping;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.landscaping.vo.LandscapingPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.landscaping.vo.LandscapingSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.landscaping.LandscapingDO;
import jakarta.validation.Valid;

/**
 * 园林绿化 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface LandscapingService {

    /**
     * 创建园林绿化
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createLandscaping(@Valid LandscapingSaveReqVO createReqVO);

    /**
     * 更新园林绿化
     *
     * @param updateReqVO 更新信息
     */
    void updateLandscaping(@Valid LandscapingSaveReqVO updateReqVO);

    /**
     * 删除园林绿化
     *
     * @param id 编号
     */
    void deleteLandscaping(Long id);

    /**
     * 获得园林绿化
     *
     * @param id 编号
     * @return 园林绿化
     */
    LandscapingDO getLandscaping(Long id);

    /**
     * 获得园林绿化分页
     *
     * @param pageReqVO 分页查询
     * @return 园林绿化分页
     */
    PageResult<LandscapingDO> getLandscapingPage(LandscapingPageReqVO pageReqVO);

}