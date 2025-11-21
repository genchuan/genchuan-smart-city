package cn.iocoder.yudao.module.datacenter.service.industrydynamic.internationaldynamic;

import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.internationaldynamic.vo.InternationalDynamicPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.internationaldynamic.vo.InternationalDynamicSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.industrydynamic.internationaldynamic.InternationalDynamicDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 国际行业动态 Service 接口
 *
 * @author 亘川智城
 */
public interface InternationalDynamicService {

    /**
     * 创建国际行业动态
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInternationalDynamic(@Valid InternationalDynamicSaveReqVO createReqVO);

    /**
     * 更新国际行业动态
     *
     * @param updateReqVO 更新信息
     */
    void updateInternationalDynamic(@Valid InternationalDynamicSaveReqVO updateReqVO);

    /**
     * 删除国际行业动态
     *
     * @param id 编号
     */
    void deleteInternationalDynamic(Long id);

    /**
     * 获得国际行业动态
     *
     * @param id 编号
     * @return 国际行业动态
     */
    InternationalDynamicDO getInternationalDynamic(Long id);

    /**
     * 获得国际行业动态分页
     *
     * @param pageReqVO 分页查询
     * @return 国际行业动态分页
     */
    PageResult<InternationalDynamicDO> getInternationalDynamicPage(InternationalDynamicPageReqVO pageReqVO);

}