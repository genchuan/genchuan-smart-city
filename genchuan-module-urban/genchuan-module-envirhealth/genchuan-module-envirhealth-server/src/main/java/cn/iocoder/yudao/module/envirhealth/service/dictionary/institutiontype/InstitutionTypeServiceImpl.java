package cn.iocoder.yudao.module.envirhealth.service.dictionary.institutiontype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.institutiontype.vo.InstitutionTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.institutiontype.vo.InstitutionTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.InstitutionTypeDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.dictionary.InstitutionTypeMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.INSTITUTION_TYPE_NOT_EXISTS;

/**
 * 机构类型字典 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class InstitutionTypeServiceImpl implements InstitutionTypeService {

    @Resource
    private InstitutionTypeMapper institutionTypeMapper;

    @Override
    public Long createInstitutionType(InstitutionTypeSaveReqVO createReqVO) {
        // 插入
        InstitutionTypeDO institutionType = BeanUtils.toBean(createReqVO, InstitutionTypeDO.class);
        institutionTypeMapper.insert(institutionType);
        // 返回
        return institutionType.getId();
    }

    @Override
    public void updateInstitutionType(InstitutionTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateInstitutionTypeExists(updateReqVO.getId());
        // 更新
        InstitutionTypeDO updateObj = BeanUtils.toBean(updateReqVO, InstitutionTypeDO.class);
        institutionTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteInstitutionType(Long id) {
        // 校验存在
        validateInstitutionTypeExists(id);
        // 删除
        institutionTypeMapper.deleteById(id);
    }

    private void validateInstitutionTypeExists(Long id) {
        if (institutionTypeMapper.selectById(id) == null) {
            throw exception(INSTITUTION_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public InstitutionTypeDO getInstitutionType(Long id) {
        return institutionTypeMapper.selectById(id);
    }

    @Override
    public PageResult<InstitutionTypeDO> getInstitutionTypePage(InstitutionTypePageReqVO pageReqVO) {
        return institutionTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OptionVO> getInstitutionTypeOptions() {

        List<InstitutionTypeDO> list;
        list = institutionTypeMapper.selectList(
                new LambdaQueryWrapperX<InstitutionTypeDO>()
                        .eq(InstitutionTypeDO::getDeleted, 0)
                        .orderByDesc(InstitutionTypeDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, institutionTypeDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(institutionTypeDO.getName());
            vo.setValue(institutionTypeDO.getSysInstitutionTypeId());
            return vo;
        });
    }
}