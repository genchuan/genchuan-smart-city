package cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.field;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.field.vo.SceneFieldPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.field.vo.SceneFieldRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.field.vo.SceneFieldSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.base.UniversalSceneDO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.field.SceneFieldDO;
import cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.base.UniversalSceneMapper;
import cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.field.SceneFieldMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import java.util.Arrays;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 场景字段 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class SceneFieldServiceImpl implements SceneFieldService {

    @Resource
    private SceneFieldMapper sceneFieldMapper;

    @Resource
    private UniversalSceneMapper universalSceneMapper;

    @Override
    public Long createSceneField(SceneFieldSaveReqVO createReqVO) {
        // 1.校验所属场景是否存在
        String sceneCode = createReqVO.getSceneCode();
        if (StringUtils.isBlank(sceneCode)) {
            throw exception(new ErrorCode(400, "sceneCode 不能为空"));
        }
        boolean sceneExists = universalSceneMapper.selectCount(
                new LambdaQueryWrapper<UniversalSceneDO>()
                        .eq(UniversalSceneDO::getSceneCode, sceneCode)
        ) > 0;
        if (!sceneExists) {
            throw exception(new ErrorCode(400, "父级场景不存在"));
        }

        // 2.校验唯一字段标识是否唯一（同一场景下）
        boolean fieldCodeExists = sceneFieldMapper.selectCount(
                new LambdaQueryWrapper<SceneFieldDO>()
                        .eq(SceneFieldDO::getFieldCode, createReqVO.getFieldCode())
        ) > 0;
        if (fieldCodeExists) {
            throw exception(new ErrorCode(400, "该场景下唯一字段标识已存在"));
        }

        // 3.校验 label 名称是否唯一（同一场景下）
        boolean labelExists = sceneFieldMapper.selectCount(
                new LambdaQueryWrapper<SceneFieldDO>()
                        .eq(SceneFieldDO::getLabel, createReqVO.getLabel())
        ) > 0;
        if (labelExists) {
            throw exception(new ErrorCode(400, "该场景下字段名称已存在"));
        }

        // 4.校验 type 是否属于 normal/status/coord
        List<String> validTypes = Arrays.asList("normal", "status", "coord");
        if (!validTypes.contains(createReqVO.getType())) {
            throw exception(new ErrorCode(400, "字段类型不合法，只能是 normal/status/coord"));
        }

        // 5.转换 VO → DO
        SceneFieldDO sceneField = BeanUtils.toBean(createReqVO, SceneFieldDO.class);

        // 6.插入数据库
        sceneFieldMapper.insert(sceneField);

        // 7.返回自增主键
        return sceneField.getId();
    }



    @Override
    public void updateSceneField(SceneFieldSaveReqVO updateReqVO) {
        // 校验存在
        validateSceneFieldExists(updateReqVO.getId());
        // 更新
        SceneFieldDO updateObj = BeanUtils.toBean(updateReqVO, SceneFieldDO.class);
        sceneFieldMapper.updateById(updateObj);
    }

    @Override
    public void deleteSceneField(Long id) {
        // 校验存在
        validateSceneFieldExists(id);
        // 删除
        sceneFieldMapper.deleteById(id);
    }

    private void validateSceneFieldExists(Long id) {
        if (sceneFieldMapper.selectById(id) == null) {
            throw exception(SCENE_FIELD_NOT_EXISTS);
        }
    }

    @Override
    public SceneFieldDO getSceneField(Long id) {
        return sceneFieldMapper.selectById(id);
    }

    @Override
    public PageResult<SceneFieldDO> getSceneFieldPage(SceneFieldPageReqVO pageReqVO) {
        return sceneFieldMapper.selectPage(pageReqVO);
    }

    @Override
    public List<SceneFieldRespVO> listFiledBySceneId(String sceneCode) {
        // 1. 查询数据库实体列表
        List<SceneFieldDO> list = sceneFieldMapper.selectList(
                new LambdaQueryWrapper<SceneFieldDO>()
                        .eq(SceneFieldDO::getSceneCode, sceneCode)
                        .eq(SceneFieldDO::getDeleted, 0)
        );

        // 2. 转换为 VO 列表
        return list.stream().map(item -> {
            SceneFieldRespVO vo = new SceneFieldRespVO();
            vo.setId(item.getId());
            vo.setSceneCode(item.getSceneCode());
            vo.setKey(item.getFieldCode());
            vo.setLabel(item.getLabel());
            vo.setType(item.getType());
            vo.setCreateTime(item.getCreateTime());
            return vo;
        }).toList();
    }


}
