package cn.iocoder.yudao.module.evaluate.service.baseinfo.relatedobject;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.relatedobject.vo.RelatedObjectPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.relatedobject.vo.RelatedObjectSaveReqVO;
import cn.iocoder.yudao.module.evaluate.controller.common.vo.SelectOptionRespVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.baseinfo.relatedobject.RelatedObjectDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.relatedobject.RelatedObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.RELATED_OBJECT_NOT_EXISTS;

/**
 * 关联对象 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class RelatedObjectServiceImpl implements RelatedObjectService {

    @Resource
    private RelatedObjectMapper relatedObjectMapper;

    @Override
    public Long createRelatedObject(RelatedObjectSaveReqVO createReqVO) {
        // 插入
        RelatedObjectDO relatedObject = BeanUtils.toBean(createReqVO, RelatedObjectDO.class);
        relatedObjectMapper.insert(relatedObject);
        // 返回
        return relatedObject.getId();
    }

    @Override
    public void updateRelatedObject(RelatedObjectSaveReqVO updateReqVO) {
        // 校验存在
        validateRelatedObjectExists(updateReqVO.getId());
        // 更新
        RelatedObjectDO updateObj = BeanUtils.toBean(updateReqVO, RelatedObjectDO.class);
        relatedObjectMapper.updateById(updateObj);
    }

    @Override
    public void deleteRelatedObject(Long id) {
        // 校验存在
        validateRelatedObjectExists(id);
        // 删除
        relatedObjectMapper.deleteById(id);
    }

    private void validateRelatedObjectExists(Long id) {
        if (relatedObjectMapper.selectById(id) == null) {
            throw exception(RELATED_OBJECT_NOT_EXISTS);
        }
    }

    @Override
    public RelatedObjectDO getRelatedObject(Long id) {
        return relatedObjectMapper.selectById(id);
    }

    @Override
    public PageResult<RelatedObjectDO> getRelatedObjectPage(RelatedObjectPageReqVO pageReqVO) {
        return relatedObjectMapper.selectPage(pageReqVO);
    }
    @Override
    public List<SelectOptionRespVO> getRelatedObjectSimpleList() {
        List<RelatedObjectDO> list = relatedObjectMapper.selectList(
                new LambdaQueryWrapperX<RelatedObjectDO>().eq(RelatedObjectDO::getStatusId, 1)
        );
        // 转换：value存 id(Long), label存 related_name
        return list.stream()
                .map(item -> new SelectOptionRespVO(item.getRelatedId(), item.getRelatedName()))
                .collect(Collectors.toList());
    }
}