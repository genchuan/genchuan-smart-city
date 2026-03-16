package cn.iocoder.yudao.module.envirhealth.service.facility;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.facility.vo.FacilityPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.facility.vo.FacilitySaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.facility.FacilityDO;
import cn.iocoder.yudao.module.envirhealth.util.vo.OptionVO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 设施字典 Service 接口
 *
 * @author 芋道源码
 */
public interface FacilityService {

    /**
     * 创建设施字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFacility(@Valid FacilitySaveReqVO createReqVO);

    /**
     * 更新设施字典
     *
     * @param updateReqVO 更新信息
     */
    void updateFacility(@Valid FacilitySaveReqVO updateReqVO);

    /**
     * 删除设施字典
     *
     * @param id 编号
     */
    void deleteFacility(Long id);

    /**
     * 获得设施字典
     *
     * @param id 编号
     * @return 设施字典
     */
    FacilityDO getFacility(Long id);

    /**
     * 获得设施字典分页
     *
     * @param pageReqVO 分页查询
     * @return 设施字典分页
     */
    PageResult<FacilityDO> getFacilityPage(FacilityPageReqVO pageReqVO);

    /**
     * 获得设施下拉框选项
     * @return 下拉框选项列表
     */
    List<OptionVO> getFacilityOptions();
}