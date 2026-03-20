package cn.iocoder.yudao.module.envirhealth.service.dictionary.complainttype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.complainttype.vo.ComplaintTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.complainttype.vo.ComplaintTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.ComplaintTypeDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet.ComplaintTypeMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.COMPLAINT_TYPE_NOT_EXISTS;

/**
 * 投诉类型字典 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ComplaintTypeServiceImpl implements ComplaintTypeService {

    @Resource
    private ComplaintTypeMapper complaintTypeMapper;

    @Override
    public Long createComplaintType(ComplaintTypeSaveReqVO createReqVO) {
        // 插入
        ComplaintTypeDO complaintType = BeanUtils.toBean(createReqVO, ComplaintTypeDO.class);
        complaintTypeMapper.insert(complaintType);
        // 返回
        return complaintType.getId();
    }

    @Override
    public void updateComplaintType(ComplaintTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateComplaintTypeExists(updateReqVO.getId());
        // 更新
        ComplaintTypeDO updateObj = BeanUtils.toBean(updateReqVO, ComplaintTypeDO.class);
        complaintTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteComplaintType(Long id) {
        // 校验存在
        validateComplaintTypeExists(id);
        // 删除
        complaintTypeMapper.deleteById(id);
    }

    private void validateComplaintTypeExists(Long id) {
        if (complaintTypeMapper.selectById(id) == null) {
            throw exception(COMPLAINT_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public ComplaintTypeDO getComplaintType(Long id) {
        return complaintTypeMapper.selectById(id);
    }

    @Override
    public PageResult<ComplaintTypeDO> getComplaintTypePage(ComplaintTypePageReqVO pageReqVO) {
        return complaintTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OptionVO> getComplaintTypeOptions() {

        List<ComplaintTypeDO> list;
        list = complaintTypeMapper.selectList(
                new LambdaQueryWrapperX<ComplaintTypeDO>()
                        .eq(ComplaintTypeDO::getDeleted, 0)
                        .orderByDesc(ComplaintTypeDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, complaintTypeDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(complaintTypeDO.getComplaintName());
            vo.setValue(complaintTypeDO.getComplaintTypeId());
            return vo;
        });
    }
}