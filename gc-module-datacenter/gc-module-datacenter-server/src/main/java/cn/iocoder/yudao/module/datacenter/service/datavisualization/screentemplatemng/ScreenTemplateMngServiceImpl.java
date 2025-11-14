package cn.iocoder.yudao.module.datacenter.service.datavisualization.screentemplatemng;

import cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.screentemplatemng.vo.ScreenTemplateMngPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.screentemplatemng.vo.ScreenTemplateMngSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.datavisualization.screentemplatemng.ScreenTemplateMngDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.datavisualization.screentemplatemng.ScreenTemplateMngMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 大屏模板管理 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ScreenTemplateMngServiceImpl implements ScreenTemplateMngService {

    @Resource
    private ScreenTemplateMngMapper screenTemplateMngMapper;

    @Override
    public Long createScreenTemplateMng(ScreenTemplateMngSaveReqVO createReqVO) {
        // 插入
        ScreenTemplateMngDO screenTemplateMng = BeanUtils.toBean(createReqVO, ScreenTemplateMngDO.class);
        screenTemplateMngMapper.insert(screenTemplateMng);
        // 返回
        return screenTemplateMng.getId();
    }

    @Override
    public void updateScreenTemplateMng(ScreenTemplateMngSaveReqVO updateReqVO) {
        // 校验存在
        validateScreenTemplateMngExists(updateReqVO.getId());
        // 更新
        ScreenTemplateMngDO updateObj = BeanUtils.toBean(updateReqVO, ScreenTemplateMngDO.class);
        screenTemplateMngMapper.updateById(updateObj);
    }

    @Override
    public void deleteScreenTemplateMng(Long id) {
        // 校验存在
        validateScreenTemplateMngExists(id);
        // 删除
        screenTemplateMngMapper.deleteById(id);
    }

    private void validateScreenTemplateMngExists(Long id) {
        if (screenTemplateMngMapper.selectById(id) == null) {
            throw exception(SCREEN_TEMPLATE_MNG_NOT_EXISTS);
        }
    }

    @Override
    public ScreenTemplateMngDO getScreenTemplateMng(Long id) {
        return screenTemplateMngMapper.selectById(id);
    }

    @Override
    public PageResult<ScreenTemplateMngDO> getScreenTemplateMngPage(ScreenTemplateMngPageReqVO pageReqVO) {
        return screenTemplateMngMapper.selectPage(pageReqVO);
    }

}