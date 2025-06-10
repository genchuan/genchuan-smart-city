package cn.iocoder.yudao.module.smartcity.service.environmentalsanitation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.environmentalsanitation.vo.EnvironmentalSanitationPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.environmentalsanitation.vo.EnvironmentalSanitationSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.environmentalsanitation.EnvironmentalSanitationDO;
import jakarta.validation.Valid;

/**
 * 市容环卫 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface EnvironmentalSanitationService {

    /**
     * 创建市容环卫
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEnvironmentalSanitation(@Valid EnvironmentalSanitationSaveReqVO createReqVO);

    /**
     * 更新市容环卫
     *
     * @param updateReqVO 更新信息
     */
    void updateEnvironmentalSanitation(@Valid EnvironmentalSanitationSaveReqVO updateReqVO);

    /**
     * 删除市容环卫
     *
     * @param id 编号
     */
    void deleteEnvironmentalSanitation(Long id);

    /**
     * 获得市容环卫
     *
     * @param id 编号
     * @return 市容环卫
     */
    EnvironmentalSanitationDO getEnvironmentalSanitation(Long id);

    /**
     * 获得市容环卫分页
     *
     * @param pageReqVO 分页查询
     * @return 市容环卫分页
     */
    PageResult<EnvironmentalSanitationDO> getEnvironmentalSanitationPage(EnvironmentalSanitationPageReqVO pageReqVO);

}