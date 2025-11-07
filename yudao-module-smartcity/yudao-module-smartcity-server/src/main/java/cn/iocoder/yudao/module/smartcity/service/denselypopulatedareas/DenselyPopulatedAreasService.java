package cn.iocoder.yudao.module.smartcity.service.denselypopulatedareas;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.denselypopulatedareas.vo.DenselyPopulatedAreasPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.denselypopulatedareas.vo.DenselyPopulatedAreasSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.denselypopulatedareas.DenselyPopulatedAreasDO;
import jakarta.validation.Valid;

/**
 * 人员密集场所安全 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface DenselyPopulatedAreasService {

    /**
     * 创建人员密集场所安全
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDenselyPopulatedAreas(@Valid DenselyPopulatedAreasSaveReqVO createReqVO);

    /**
     * 更新人员密集场所安全
     *
     * @param updateReqVO 更新信息
     */
    void updateDenselyPopulatedAreas(@Valid DenselyPopulatedAreasSaveReqVO updateReqVO);

    /**
     * 删除人员密集场所安全
     *
     * @param id 编号
     */
    void deleteDenselyPopulatedAreas(Long id);

    /**
     * 获得人员密集场所安全
     *
     * @param id 编号
     * @return 人员密集场所安全
     */
    DenselyPopulatedAreasDO getDenselyPopulatedAreas(Long id);

    /**
     * 获得人员密集场所安全分页
     *
     * @param pageReqVO 分页查询
     * @return 人员密集场所安全分页
     */
    PageResult<DenselyPopulatedAreasDO> getDenselyPopulatedAreasPage(DenselyPopulatedAreasPageReqVO pageReqVO);

}