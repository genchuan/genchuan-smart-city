package cn.iocoder.yudao.module.datacenter.service.alarm.traceability.relatedriskident;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.relatedriskident.vo.RelatedRiskIdentPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.relatedriskident.vo.RelatedRiskIdentSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.traceability.relatedriskident.RelatedRiskIdentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 关联风险识别 Service 接口
 *
 * @author zcq
 */
public interface RelatedRiskIdentService {

    /**
     * 创建关联风险识别
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRelatedRiskIdent(@Valid RelatedRiskIdentSaveReqVO createReqVO);

    /**
     * 更新关联风险识别
     *
     * @param updateReqVO 更新信息
     */
    void updateRelatedRiskIdent(@Valid RelatedRiskIdentSaveReqVO updateReqVO);

    /**
     * 删除关联风险识别
     *
     * @param id 编号
     */
    void deleteRelatedRiskIdent(Long id);

    /**
     * 获得关联风险识别
     *
     * @param id 编号
     * @return 关联风险识别
     */
    RelatedRiskIdentDO getRelatedRiskIdent(Long id);

    /**
     * 获得关联风险识别分页
     *
     * @param pageReqVO 分页查询
     * @return 关联风险识别分页
     */
    PageResult<RelatedRiskIdentDO> getRelatedRiskIdentPage(RelatedRiskIdentPageReqVO pageReqVO);

}