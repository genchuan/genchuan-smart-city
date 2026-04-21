package cn.iocoder.yudao.module.studentmgmt.service.coopenterprise;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.coopenterprise.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.coopenterprise.CoopEnterpriseDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.coopenterprise.CoopEnterpriseMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 校企合作 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CoopEnterpriseServiceImpl implements CoopEnterpriseService {

    @Resource
    private CoopEnterpriseMapper coopEnterpriseMapper;

    @Override
    public Long createCoopEnterprise(CoopEnterpriseSaveReqVO createReqVO) {
        // 插入
        CoopEnterpriseDO coopEnterprise = BeanUtils.toBean(createReqVO, CoopEnterpriseDO.class);
        coopEnterpriseMapper.insert(coopEnterprise);

        // 返回
        return coopEnterprise.getId();
    }

    @Override
    public void updateCoopEnterprise(CoopEnterpriseSaveReqVO updateReqVO) {
        // 校验存在
        validateCoopEnterpriseExists(updateReqVO.getId());
        // 更新
        CoopEnterpriseDO updateObj = BeanUtils.toBean(updateReqVO, CoopEnterpriseDO.class);
        coopEnterpriseMapper.updateById(updateObj);
    }

    @Override
    public void deleteCoopEnterprise(Long id) {
        // 校验存在
        validateCoopEnterpriseExists(id);
        // 删除
        coopEnterpriseMapper.deleteById(id);
    }

    @Override
        public void deleteCoopEnterpriseListByIds(List<Long> ids) {
        // 删除
        coopEnterpriseMapper.deleteByIds(ids);
        }


    private void validateCoopEnterpriseExists(Long id) {
        if (coopEnterpriseMapper.selectById(id) == null) {
            throw exception(COOP_ENTERPRISE_NOT_EXISTS);
        }
    }

    @Override
    public CoopEnterpriseDO getCoopEnterprise(Long id) {
        return coopEnterpriseMapper.selectById(id);
    }

    @Override
    public PageResult<CoopEnterpriseDO> getCoopEnterprisePage(CoopEnterprisePageReqVO pageReqVO) {
        return coopEnterpriseMapper.selectPage(pageReqVO);
    }

}