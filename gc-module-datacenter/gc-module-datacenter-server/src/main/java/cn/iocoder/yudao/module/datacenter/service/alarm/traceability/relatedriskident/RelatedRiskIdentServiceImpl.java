package cn.iocoder.yudao.module.datacenter.service.alarm.traceability.relatedriskident;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.relatedriskident.vo.RelatedRiskIdentPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.relatedriskident.vo.RelatedRiskIdentSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.traceability.relatedriskident.RelatedRiskIdentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.traceability.relatedriskident.RelatedRiskIdentMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 关联风险识别 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class RelatedRiskIdentServiceImpl implements RelatedRiskIdentService {

    @Resource
    private RelatedRiskIdentMapper relatedRiskIdentMapper;

    @Override
    public Long createRelatedRiskIdent(RelatedRiskIdentSaveReqVO createReqVO) {
        // 插入
        RelatedRiskIdentDO relatedRiskIdent = BeanUtils.toBean(createReqVO, RelatedRiskIdentDO.class);
        relatedRiskIdentMapper.insert(relatedRiskIdent);
        // 返回
        return relatedRiskIdent.getId();
    }

    @Override
    public void updateRelatedRiskIdent(RelatedRiskIdentSaveReqVO updateReqVO) {
        // 校验存在
        validateRelatedRiskIdentExists(updateReqVO.getId());
        // 更新
        RelatedRiskIdentDO updateObj = BeanUtils.toBean(updateReqVO, RelatedRiskIdentDO.class);
        relatedRiskIdentMapper.updateById(updateObj);
    }

    @Override
    public void deleteRelatedRiskIdent(Long id) {
        // 校验存在
        validateRelatedRiskIdentExists(id);
        // 删除
        relatedRiskIdentMapper.deleteById(id);
    }

    private void validateRelatedRiskIdentExists(Long id) {
        if (relatedRiskIdentMapper.selectById(id) == null) {
            throw exception(RELATED_RISK_IDENT_NOT_EXISTS);
        }
    }

    @Override
    public RelatedRiskIdentDO getRelatedRiskIdent(Long id) {
        return relatedRiskIdentMapper.selectById(id);
    }

    @Override
    public PageResult<RelatedRiskIdentDO> getRelatedRiskIdentPage(RelatedRiskIdentPageReqVO pageReqVO) {
        return relatedRiskIdentMapper.selectPage(pageReqVO);
    }

}