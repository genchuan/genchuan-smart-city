package cn.iocoder.yudao.module.envirhealth.service.dictionary.abnormaltype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.abnormaltype.vo.AbnormalTypeOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.abnormaltype.vo.AbnormalTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.abnormaltype.vo.AbnormalTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.AbnormalTypeDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection.AbnormalTypeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.ABNORMAL_TYPE_NOT_EXISTS;

/**
 * 垃圾异常类型字典 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AbnormalTypeServiceImpl implements AbnormalTypeService {

    @Resource
    private AbnormalTypeMapper abnormalTypeMapper;

    @Override
    public Long createAbnormalType(AbnormalTypeSaveReqVO createReqVO) {
        // 插入
        AbnormalTypeDO abnormalType = BeanUtils.toBean(createReqVO, AbnormalTypeDO.class);
        abnormalTypeMapper.insert(abnormalType);
        // 返回
        return abnormalType.getId();
    }

    @Override
    public void updateAbnormalType(AbnormalTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateAbnormalTypeExists(updateReqVO.getId());
        // 更新
        AbnormalTypeDO updateObj = BeanUtils.toBean(updateReqVO, AbnormalTypeDO.class);
        abnormalTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteAbnormalType(Long id) {
        // 校验存在
        validateAbnormalTypeExists(id);
        // 删除
        abnormalTypeMapper.deleteById(id);
    }

    private void validateAbnormalTypeExists(Long id) {
        if (abnormalTypeMapper.selectById(id) == null) {
            throw exception(ABNORMAL_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public AbnormalTypeDO getAbnormalType(Long id) {
        return abnormalTypeMapper.selectById(id);
    }

    @Override
    public PageResult<AbnormalTypeDO> getAbnormalTypePage(AbnormalTypePageReqVO pageReqVO) {
        return abnormalTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<AbnormalTypeOptionVO> getAbnormalTypeOptions() {

        List<AbnormalTypeDO> list;
        list = abnormalTypeMapper.selectList(
                new LambdaQueryWrapperX<AbnormalTypeDO>()
                        .eq(AbnormalTypeDO::getDeleted, 0)
                        .orderByDesc(AbnormalTypeDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, abnormalTypeDO -> {
            AbnormalTypeOptionVO vo = new AbnormalTypeOptionVO();
            vo.setLabel(abnormalTypeDO.getAbnormalName());
            vo.setValue(abnormalTypeDO.getAbnormalTypeId());
            return vo;
        });
    }
}