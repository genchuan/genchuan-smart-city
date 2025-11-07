package cn.iocoder.yudao.module.smartcity.service.municipalpublicutilities;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.municipalpublicutilities.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.municipalpublicutilities.MunicipalPublicUtilitiesDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.municipalpublicutilities.MunicipalPublicUtilitiesMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 市政公用 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class MunicipalPublicUtilitiesServiceImpl implements MunicipalPublicUtilitiesService {

    @Resource
    private MunicipalPublicUtilitiesMapper municipalPublicUtilitiesMapper;

    @Override
    public Long createMunicipalPublicUtilities(MunicipalPublicUtilitiesSaveReqVO createReqVO) {
        // 插入
        MunicipalPublicUtilitiesDO municipalPublicUtilities = BeanUtils.toBean(createReqVO, MunicipalPublicUtilitiesDO.class);
        municipalPublicUtilitiesMapper.insert(municipalPublicUtilities);
        // 返回
        return municipalPublicUtilities.getId();
    }

    @Override
    public void updateMunicipalPublicUtilities(MunicipalPublicUtilitiesSaveReqVO updateReqVO) {
        // 校验存在
        validateMunicipalPublicUtilitiesExists(updateReqVO.getId());
        // 更新
        MunicipalPublicUtilitiesDO updateObj = BeanUtils.toBean(updateReqVO, MunicipalPublicUtilitiesDO.class);
        municipalPublicUtilitiesMapper.updateById(updateObj);
    }

    @Override
    public void deleteMunicipalPublicUtilities(Long id) {
        // 校验存在
        validateMunicipalPublicUtilitiesExists(id);
        // 删除
        municipalPublicUtilitiesMapper.deleteById(id);
    }

    private void validateMunicipalPublicUtilitiesExists(Long id) {
        if (municipalPublicUtilitiesMapper.selectById(id) == null) {
            throw exception(MUNICIPAL_PUBLIC_UTILITIES_NOT_EXISTS);
        }
    }

    @Override
    public MunicipalPublicUtilitiesDO getMunicipalPublicUtilities(Long id) {
        return municipalPublicUtilitiesMapper.selectById(id);
    }

    @Override
    public PageResult<MunicipalPublicUtilitiesDO> getMunicipalPublicUtilitiesPage(MunicipalPublicUtilitiesPageReqVO pageReqVO) {
        return municipalPublicUtilitiesMapper.selectPage(pageReqVO);
    }

}