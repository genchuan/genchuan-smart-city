package cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarnappscene;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnappscene.vo.EarlyWarnAppScenePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnappscene.vo.EarlyWarnAppSceneSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnappscene.EarlyWarnAppSceneDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.region.earlywarnappscene.EarlyWarnAppSceneMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 按应用场景预警告警统计 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EarlyWarnAppSceneServiceImpl implements EarlyWarnAppSceneService {

    @Resource
    private EarlyWarnAppSceneMapper earlyWarnAppSceneMapper;

    @Override
    public Long createEarlyWarnAppScene(EarlyWarnAppSceneSaveReqVO createReqVO) {
        // 插入
        EarlyWarnAppSceneDO earlyWarnAppScene = BeanUtils.toBean(createReqVO, EarlyWarnAppSceneDO.class);
        earlyWarnAppSceneMapper.insert(earlyWarnAppScene);
        // 返回
        return earlyWarnAppScene.getId();
    }

    @Override
    public void updateEarlyWarnAppScene(EarlyWarnAppSceneSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnAppSceneExists(updateReqVO.getId());
        // 更新
        EarlyWarnAppSceneDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnAppSceneDO.class);
        earlyWarnAppSceneMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnAppScene(Long id) {
        // 校验存在
        validateEarlyWarnAppSceneExists(id);
        // 删除
        earlyWarnAppSceneMapper.deleteById(id);
    }

    private void validateEarlyWarnAppSceneExists(Long id) {
        if (earlyWarnAppSceneMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_APP_SCENE_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnAppSceneDO getEarlyWarnAppScene(Long id) {
        return earlyWarnAppSceneMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnAppSceneDO> getEarlyWarnAppScenePage(EarlyWarnAppScenePageReqVO pageReqVO) {
        return earlyWarnAppSceneMapper.selectPage(pageReqVO);
    }

}