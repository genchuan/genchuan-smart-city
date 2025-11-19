package cn.iocoder.yudao.module.datacenter.service.inspection.result.inspectresultpublic;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresultpublic.vo.InspectResultPublicPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresultpublic.vo.InspectResultPublicSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.result.inspectresultpublic.InspectResultPublicDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.result.inspectresultpublic.InspectResultPublicMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检结果公示 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectResultPublicServiceImpl implements InspectResultPublicService {

    @Resource
    private InspectResultPublicMapper inspectResultPublicMapper;

    @Override
    public Long createInspectResultPublic(InspectResultPublicSaveReqVO createReqVO) {
        // 插入
        InspectResultPublicDO inspectResultPublic = BeanUtils.toBean(createReqVO, InspectResultPublicDO.class);
        inspectResultPublicMapper.insert(inspectResultPublic);
        // 返回
        return inspectResultPublic.getId();
    }

    @Override
    public void updateInspectResultPublic(InspectResultPublicSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectResultPublicExists(updateReqVO.getId());
        // 更新
        InspectResultPublicDO updateObj = BeanUtils.toBean(updateReqVO, InspectResultPublicDO.class);
        inspectResultPublicMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectResultPublic(Long id) {
        // 校验存在
        validateInspectResultPublicExists(id);
        // 删除
        inspectResultPublicMapper.deleteById(id);
    }

    private void validateInspectResultPublicExists(Long id) {
        if (inspectResultPublicMapper.selectById(id) == null) {
            throw exception(INSPECT_RESULT_PUBLIC_NOT_EXISTS);
        }
    }

    @Override
    public InspectResultPublicDO getInspectResultPublic(Long id) {
        return inspectResultPublicMapper.selectById(id);
    }

    @Override
    public PageResult<InspectResultPublicDO> getInspectResultPublicPage(InspectResultPublicPageReqVO pageReqVO) {
        return inspectResultPublicMapper.selectPage(pageReqVO);
    }

}