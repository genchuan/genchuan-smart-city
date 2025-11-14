package cn.iocoder.yudao.module.datacenter.service.polcomplianceck;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.polcomplianceck.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.polcomplianceck.PolComplianceCkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.polcomplianceck.PolComplianceCkMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 政策合规校验 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PolComplianceCkServiceImpl implements PolComplianceCkService {

    @Resource
    private PolComplianceCkMapper polComplianceCkMapper;

    @Override
    public Long createPolComplianceCk(PolComplianceCkSaveReqVO createReqVO) {
        // 插入
        PolComplianceCkDO polComplianceCk = BeanUtils.toBean(createReqVO, PolComplianceCkDO.class);
        polComplianceCkMapper.insert(polComplianceCk);
        // 返回
        return polComplianceCk.getId();
    }

    @Override
    public void updatePolComplianceCk(PolComplianceCkSaveReqVO updateReqVO) {
        // 校验存在
        validatePolComplianceCkExists(updateReqVO.getId());
        // 更新
        PolComplianceCkDO updateObj = BeanUtils.toBean(updateReqVO, PolComplianceCkDO.class);
        polComplianceCkMapper.updateById(updateObj);
    }

    @Override
    public void deletePolComplianceCk(Long id) {
        // 校验存在
        validatePolComplianceCkExists(id);
        // 删除
        polComplianceCkMapper.deleteById(id);
    }

    private void validatePolComplianceCkExists(Long id) {
        if (polComplianceCkMapper.selectById(id) == null) {
            throw exception(POL_COMPLIANCE_CK_NOT_EXISTS);
        }
    }

    @Override
    public PolComplianceCkDO getPolComplianceCk(Long id) {
        return polComplianceCkMapper.selectById(id);
    }

    @Override
    public PageResult<PolComplianceCkDO> getPolComplianceCkPage(PolComplianceCkPageReqVO pageReqVO) {
        return polComplianceCkMapper.selectPage(pageReqVO);
    }

}