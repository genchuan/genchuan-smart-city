package cn.iocoder.yudao.module.envirhealth.service.garbagecollection.garbageabnormal;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.GarbageAbnormalPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.GarbageAbnormalSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.card.abnormal.GarbageAbnormalCardAbnormalRespVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageAbnormalDetailDO;
import cn.iocoder.yudao.module.envirhealth.util.circle.vo.CircleVO;
import cn.iocoder.yudao.module.envirhealth.util.column.vo.ColumnVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageAbnormalDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection.GarbageAbnormalMapper;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 垃圾异常记录 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class GarbageAbnormalServiceImpl implements GarbageAbnormalService {

    @Resource
    private GarbageAbnormalMapper garbageAbnormalMapper;

    @Override
    public Long createGarbageAbnormal(GarbageAbnormalSaveReqVO createReqVO) {
        // 插入
        GarbageAbnormalDO garbageAbnormal = BeanUtils.toBean(createReqVO, GarbageAbnormalDO.class);
        garbageAbnormalMapper.insert(garbageAbnormal);
        // 返回
        return garbageAbnormal.getId();
    }

    @Override
    public void updateGarbageAbnormal(GarbageAbnormalSaveReqVO updateReqVO) {
        // 校验存在
        validateGarbageAbnormalExists(updateReqVO.getId());
        // 更新
        GarbageAbnormalDO updateObj = BeanUtils.toBean(updateReqVO, GarbageAbnormalDO.class);
        garbageAbnormalMapper.updateById(updateObj);
    }

    @Override
    public void deleteGarbageAbnormal(Long id) {
        // 校验存在
        validateGarbageAbnormalExists(id);
        // 删除
        garbageAbnormalMapper.deleteById(id);
    }

    private void validateGarbageAbnormalExists(Long id) {
        if (garbageAbnormalMapper.selectById(id) == null) {
            throw exception(GARBAGE_ABNORMAL_NOT_EXISTS);
        }
    }

    @Override
    public GarbageAbnormalDO getGarbageAbnormal(Long id) {
        return garbageAbnormalMapper.selectById(id);
    }

    @Override
    public PageResult<GarbageAbnormalDO> getGarbageAbnormalPage(GarbageAbnormalPageReqVO pageReqVO) {
        return garbageAbnormalMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<GarbageAbnormalDetailDO> getGarbageAbnormalDetailPage(GarbageAbnormalPageReqVO pageReqVO) {
        Long total = garbageAbnormalMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<GarbageAbnormalDetailDO> list = garbageAbnormalMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    public GarbageAbnormalCardAbnormalRespVO getGarbageAbnormalCardAbnormal() {
        GarbageAbnormalCardAbnormalRespVO statistics = new GarbageAbnormalCardAbnormalRespVO();

        // 1. 待处置异常总数（handle_status = 待处置）
        GarbageAbnormalPageReqVO toHandleReq = new GarbageAbnormalPageReqVO();
        toHandleReq.setHandleStatus("待处置");
        statistics.setToHandleTotal(garbageAbnormalMapper.selectCount(toHandleReq));

        // 2. 高优先级数（priority = 高）
        GarbageAbnormalPageReqVO highPriorityReq = new GarbageAbnormalPageReqVO();
        highPriorityReq.setPriority("高");
        statistics.setHighPriorityTotal(garbageAbnormalMapper.selectCount(highPriorityReq));

        // 3. 超时未处理数（is_timeout = 是）
        GarbageAbnormalPageReqVO timeoutReq = new GarbageAbnormalPageReqVO();
        timeoutReq.setIsTimeout("是");
        statistics.setTimeoutTotal(garbageAbnormalMapper.selectCount(timeoutReq));

        return statistics;
    }

    @Override
    public List<CircleVO> getGarbageAbnormalTypeCircleAbnormal() {
        return garbageAbnormalMapper.selectAbnormalTypeCircle();
    }

    @Override
    public List<CircleVO> getGarbageAbnormalAreaDistributionCircle() {
        return garbageAbnormalMapper.selectAreaDistributionCircle();
    }

    @Override
    public List<ColumnVO> getHandlerAbnormalColumn() {
        return garbageAbnormalMapper.selectHandlerAbnormalColumn();
    }
}