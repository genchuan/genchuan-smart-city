package cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivappscene;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivappscene.vo.DomDivAppScenePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivappscene.vo.DomDivAppSceneSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivappscene.DomDivAppSceneDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regional.domdivappscene.DomDivAppSceneMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 应用场景分域 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class DomDivAppSceneServiceImpl implements DomDivAppSceneService {

    @Resource
    private DomDivAppSceneMapper domDivAppSceneMapper;

    @Override
    public Long createDomDivAppScene(DomDivAppSceneSaveReqVO createReqVO) {
        // 插入
        DomDivAppSceneDO domDivAppScene = BeanUtils.toBean(createReqVO, DomDivAppSceneDO.class);
        domDivAppSceneMapper.insert(domDivAppScene);
        // 返回
        return domDivAppScene.getId();
    }

    @Override
    public void updateDomDivAppScene(DomDivAppSceneSaveReqVO updateReqVO) {
        // 校验存在
        validateDomDivAppSceneExists(updateReqVO.getId());
        // 更新
        DomDivAppSceneDO updateObj = BeanUtils.toBean(updateReqVO, DomDivAppSceneDO.class);
        domDivAppSceneMapper.updateById(updateObj);
    }

    @Override
    public void deleteDomDivAppScene(Long id) {
        // 校验存在
        validateDomDivAppSceneExists(id);
        // 删除
        domDivAppSceneMapper.deleteById(id);
    }

    private void validateDomDivAppSceneExists(Long id) {
        if (domDivAppSceneMapper.selectById(id) == null) {
            throw exception(DOM_DIV_APP_SCENE_NOT_EXISTS);
        }
    }

    @Override
    public DomDivAppSceneDO getDomDivAppScene(Long id) {
        return domDivAppSceneMapper.selectById(id);
    }

    @Override
    public PageResult<DomDivAppSceneDO> getDomDivAppScenePage(DomDivAppScenePageReqVO pageReqVO) {
        return domDivAppSceneMapper.selectPage(pageReqVO);
    }

}