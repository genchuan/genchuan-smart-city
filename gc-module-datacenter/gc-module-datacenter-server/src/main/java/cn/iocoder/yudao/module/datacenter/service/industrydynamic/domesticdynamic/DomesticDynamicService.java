package cn.iocoder.yudao.module.datacenter.service.industrydynamic.domesticdynamic;

import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.domesticdynamic.vo.DomesticDynamicPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.domesticdynamic.vo.DomesticDynamicSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.industrydynamic.domesticdynamic.DomesticDynamicDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 国内行业动态 Service 接口
 *
 * @author 亘川智城
 */
public interface DomesticDynamicService {

    /**
     * 创建国内行业动态
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDomesticDynamic(@Valid DomesticDynamicSaveReqVO createReqVO);

    /**
     * 更新国内行业动态
     *
     * @param updateReqVO 更新信息
     */
    void updateDomesticDynamic(@Valid DomesticDynamicSaveReqVO updateReqVO);

    /**
     * 删除国内行业动态
     *
     * @param id 编号
     */
    void deleteDomesticDynamic(Long id);

    /**
     * 获得国内行业动态
     *
     * @param id 编号
     * @return 国内行业动态
     */
    DomesticDynamicDO getDomesticDynamic(Long id);

    /**
     * 获得国内行业动态分页
     *
     * @param pageReqVO 分页查询
     * @return 国内行业动态分页
     */
    PageResult<DomesticDynamicDO> getDomesticDynamicPage(DomesticDynamicPageReqVO pageReqVO);

}