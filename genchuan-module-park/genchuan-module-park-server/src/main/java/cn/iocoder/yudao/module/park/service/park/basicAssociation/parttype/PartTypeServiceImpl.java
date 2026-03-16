package cn.iocoder.yudao.module.park.service.park.basicAssociation.parttype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.parttype.vo.PartTypePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.parttype.vo.PartTypeSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.parttype.PartTypeDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.basicAssociation.parttype.PartTypeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.PART_TYPE_NOT_EXISTS;

/**
 * 管理部件类别 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class PartTypeServiceImpl implements PartTypeService {

    @Resource
    private PartTypeMapper partTypeMapper;

    @Override
    public Long createPartType(PartTypeSaveReqVO createReqVO) {
        // 插入
        PartTypeDO partType = BeanUtils.toBean(createReqVO, PartTypeDO.class);
        partTypeMapper.insert(partType);
        // 返回
        return partType.getId();
    }

    @Override
    public void updatePartType(PartTypeSaveReqVO updateReqVO) {
        // 校验存在
        validatePartTypeExists(updateReqVO.getId());
        // 更新
        PartTypeDO updateObj = BeanUtils.toBean(updateReqVO, PartTypeDO.class);
        partTypeMapper.updateById(updateObj);
    }

    @Override
    public void deletePartType(Long id) {
        // 校验存在
        validatePartTypeExists(id);
        // 删除
        partTypeMapper.deleteById(id);
    }

    private void validatePartTypeExists(Long id) {
        if (partTypeMapper.selectById(id) == null) {
            throw exception(PART_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public PartTypeDO getPartType(Long id) {
        return partTypeMapper.selectById(id);
    }

    @Override
    public PageResult<PartTypeDO> getPartTypePage(PartTypePageReqVO pageReqVO) {
        return partTypeMapper.selectPage(pageReqVO);
    }

}
