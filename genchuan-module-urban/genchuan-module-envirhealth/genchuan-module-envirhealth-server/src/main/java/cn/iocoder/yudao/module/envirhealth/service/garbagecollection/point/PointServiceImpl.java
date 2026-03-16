package cn.iocoder.yudao.module.envirhealth.service.garbagecollection.point;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.point.PointOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.point.PointPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.point.PointSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.PointDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection.PointMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.POINT_NOT_EXISTS;

/**
 * 点位 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PointServiceImpl implements PointService {

    @Resource
    private PointMapper pointMapper;

    @Override
    public Long createPoint(PointSaveReqVO createReqVO) {
        // 插入
        PointDO point = BeanUtils.toBean(createReqVO, PointDO.class);
        pointMapper.insert(point);
        // 返回
        return point.getId();
    }

    @Override
    public void updatePoint(PointSaveReqVO updateReqVO) {
        // 校验存在
        validatePointExists(updateReqVO.getId());
        // 更新
        PointDO updateObj = BeanUtils.toBean(updateReqVO, PointDO.class);
        pointMapper.updateById(updateObj);
    }

    @Override
    public void deletePoint(Long id) {
        // 校验存在
        validatePointExists(id);
        // 删除
        pointMapper.deleteById(id);
    }

    private void validatePointExists(Long id) {
        if (pointMapper.selectById(id) == null) {
            throw exception(POINT_NOT_EXISTS);
        }
    }

    @Override
    public PointDO getPoint(Long id) {
        return pointMapper.selectById(id);
    }

    @Override
    public PageResult<PointDO> getPointPage(PointPageReqVO pageReqVO) {
        return pointMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PointOptionVO> getPointOptions() {

        List<PointDO> list = pointMapper.selectList(
                new LambdaQueryWrapperX<PointDO>()
                        .eq(PointDO::getStatus, 1)
                        .eq(PointDO::getDeleted, 0)
                        .orderByDesc(PointDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, pointDO -> {
            PointOptionVO vo = new PointOptionVO();
            vo.setLabel(pointDO.getPointName());
            vo.setValue(pointDO.getPointId());
            return vo;
        });
    }
}