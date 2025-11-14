package cn.iocoder.yudao.module.datacenter.service.schemesimcompare;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.schemesimcompare.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.schemesimcompare.SchemeSimCompareDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.schemesimcompare.SchemeSimCompareMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 方案模拟对比统计 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class SchemeSimCompareServiceImpl implements SchemeSimCompareService {

    @Resource
    private SchemeSimCompareMapper schemeSimCompareMapper;

    @Override
    public Long createSchemeSimCompare(SchemeSimCompareSaveReqVO createReqVO) {
        // 插入
        SchemeSimCompareDO schemeSimCompare = BeanUtils.toBean(createReqVO, SchemeSimCompareDO.class);
        schemeSimCompareMapper.insert(schemeSimCompare);
        // 返回
        return schemeSimCompare.getId();
    }

    @Override
    public void updateSchemeSimCompare(SchemeSimCompareSaveReqVO updateReqVO) {
        // 校验存在
        validateSchemeSimCompareExists(updateReqVO.getId());
        // 更新
        SchemeSimCompareDO updateObj = BeanUtils.toBean(updateReqVO, SchemeSimCompareDO.class);
        schemeSimCompareMapper.updateById(updateObj);
    }

    @Override
    public void deleteSchemeSimCompare(Long id) {
        // 校验存在
        validateSchemeSimCompareExists(id);
        // 删除
        schemeSimCompareMapper.deleteById(id);
    }

    private void validateSchemeSimCompareExists(Long id) {
        if (schemeSimCompareMapper.selectById(id) == null) {
            throw exception(SCHEME_SIM_COMPARE_NOT_EXISTS);
        }
    }

    @Override
    public SchemeSimCompareDO getSchemeSimCompare(Long id) {
        return schemeSimCompareMapper.selectById(id);
    }

    @Override
    public PageResult<SchemeSimCompareDO> getSchemeSimComparePage(SchemeSimComparePageReqVO pageReqVO) {
        return schemeSimCompareMapper.selectPage(pageReqVO);
    }

}