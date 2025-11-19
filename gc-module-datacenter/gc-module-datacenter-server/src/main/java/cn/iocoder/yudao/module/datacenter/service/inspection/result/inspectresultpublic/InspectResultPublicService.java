package cn.iocoder.yudao.module.datacenter.service.inspection.result.inspectresultpublic;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresultpublic.vo.InspectResultPublicPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresultpublic.vo.InspectResultPublicSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.result.inspectresultpublic.InspectResultPublicDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检结果公示 Service 接口
 *
 * @author zcq
 */
public interface InspectResultPublicService {

    /**
     * 创建巡查巡检结果公示
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectResultPublic(@Valid InspectResultPublicSaveReqVO createReqVO);

    /**
     * 更新巡查巡检结果公示
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectResultPublic(@Valid InspectResultPublicSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检结果公示
     *
     * @param id 编号
     */
    void deleteInspectResultPublic(Long id);

    /**
     * 获得巡查巡检结果公示
     *
     * @param id 编号
     * @return 巡查巡检结果公示
     */
    InspectResultPublicDO getInspectResultPublic(Long id);

    /**
     * 获得巡查巡检结果公示分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检结果公示分页
     */
    PageResult<InspectResultPublicDO> getInspectResultPublicPage(InspectResultPublicPageReqVO pageReqVO);

}