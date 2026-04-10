package cn.iocoder.yudao.module.studentmgmt.service.fundsystem;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.fundsystem.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.fundsystem.FundSystemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.fundsystem.FundSystemMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 资助系统 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class FundSystemServiceImpl implements FundSystemService {

    @Resource
    private FundSystemMapper fundSystemMapper;

    @Override
    public Long createFundSystem(FundSystemSaveReqVO createReqVO) {
        // 插入
        FundSystemDO fundSystem = BeanUtils.toBean(createReqVO, FundSystemDO.class);
        fundSystemMapper.insert(fundSystem);

        // 返回
        return fundSystem.getId();
    }

    @Override
    public void updateFundSystem(FundSystemSaveReqVO updateReqVO) {
        // 校验存在
        validateFundSystemExists(updateReqVO.getId());
        // 更新
        FundSystemDO updateObj = BeanUtils.toBean(updateReqVO, FundSystemDO.class);
        fundSystemMapper.updateById(updateObj);
    }

    @Override
    public void deleteFundSystem(Long id) {
        // 校验存在
        validateFundSystemExists(id);
        // 删除
        fundSystemMapper.deleteById(id);
    }

    @Override
        public void deleteFundSystemListByIds(List<Long> ids) {
        // 删除
        fundSystemMapper.deleteByIds(ids);
        }


    private void validateFundSystemExists(Long id) {
        if (fundSystemMapper.selectById(id) == null) {
            throw exception(FUND_SYSTEM_NOT_EXISTS);
        }
    }

    @Override
    public FundSystemDO getFundSystem(Long id) {
        return fundSystemMapper.selectById(id);
    }

    @Override
    public PageResult<FundSystemDO> getFundSystemPage(FundSystemPageReqVO pageReqVO) {
        return fundSystemMapper.selectPage(pageReqVO);
    }

}