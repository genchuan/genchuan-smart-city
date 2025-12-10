package cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.base;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.base.vo.UniversalScenePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.base.vo.UniversalSceneRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.base.vo.UniversalSceneSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.base.UniversalSceneMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.industry.dal.dataobject.universalscene.UniversalSceneDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;



import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 通用场景表，一级和二级场景 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class UniversalSceneServiceImpl implements UniversalSceneService {

    @Resource
    private UniversalSceneMapper universalSceneMapper;

    @Override
    public Long createUniversalScene(UniversalSceneSaveReqVO createReqVO) {
        // 1. 自动生成 sceneId，使用 UUID
        if (createReqVO.getSceneId() == null || createReqVO.getSceneId().isEmpty()) {
            createReqVO.setSceneId(UUID.randomUUID().toString().replace("-", ""));
        }

        // 2. 如果父级 ID 不为 0，校验父场景是否存在
        if (createReqVO.getParentId() != 0) {
            UniversalSceneDO parentScene = universalSceneMapper.selectById(createReqVO.getParentId());
            if (parentScene == null) {
                throw exception(new ErrorCode(400, "父级场景不存在"));
            }
        }
        // 3. 如果父 ID 为 0，则 level 必须为 1,否则必须为2
        if (createReqVO.getParentId() == 0) {
            if (!Objects.equals(createReqVO.getLevel(), 1)) {
                throw exception(new ErrorCode(400, "一级场景的 level 必须为 1"));
            }
        }else {
            if (!Objects.equals(createReqVO.getLevel(), 2)) {
                throw exception(new ErrorCode(400, "二级场景的 level 必须为 2"));
            }
        }

// 4. 场景名称禁止重复
        LambdaQueryWrapper<UniversalSceneDO> labelWrapper = new LambdaQueryWrapper<>();
        labelWrapper.eq(UniversalSceneDO::getLabel, createReqVO.getLabel());
        Long labelCount = universalSceneMapper.selectCount(labelWrapper);
        if (labelCount > 0) {
            throw exception(new ErrorCode(400, "场景名称已存在"));
        }

// 5. 场景值禁止重复
        LambdaQueryWrapper<UniversalSceneDO> valueWrapper = new LambdaQueryWrapper<>();
        valueWrapper.eq(UniversalSceneDO::getValue, createReqVO.getValue());
        Long valueCount = universalSceneMapper.selectCount(valueWrapper);
        if (valueCount > 0) {
            throw exception(new ErrorCode(400, "场景值已存在"));
        }


        // 插入
        UniversalSceneDO universalScene = BeanUtils.toBean(createReqVO, UniversalSceneDO.class);
        universalSceneMapper.insert(universalScene);

        // 返回新建的 ID
        return universalScene.getId();
    }

    @Override
    public void updateUniversalScene(UniversalSceneSaveReqVO updateReqVO) {
        // 校验存在
        validateUniversalSceneExists(updateReqVO.getId());
        // 更新
        UniversalSceneDO updateObj = BeanUtils.toBean(updateReqVO, UniversalSceneDO.class);
        universalSceneMapper.updateById(updateObj);
    }

    @Override
    public void deleteUniversalScene(Long id) {
        // 校验存在
        validateUniversalSceneExists(id);
        // 删除
        universalSceneMapper.deleteById(id);
    }

    private void validateUniversalSceneExists(Long id) {
        if (universalSceneMapper.selectById(id) == null) {
            throw exception(UNIVERSAL_SCENE_NOT_EXISTS);
        }
    }

    @Override
    public List<UniversalSceneRespVO> listByParentId(Long id) {
        // 1. 查询数据库
        LambdaQueryWrapper<UniversalSceneDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UniversalSceneDO::getParentId, id)
                .orderByAsc(UniversalSceneDO::getId);
        List<UniversalSceneDO> sceneList = universalSceneMapper.selectList(queryWrapper);

        // 2. 转换 DO -> VO
        return sceneList.stream().map(scene -> {
            UniversalSceneRespVO vo = new UniversalSceneRespVO();
            // 手动拷贝字段
            vo.setId(scene.getId());
            vo.setLabel(scene.getLabel());
            vo.setValue(scene.getValue());
            vo.setSceneId(scene.getSceneId());
            vo.setDescription(scene.getDescription());
            return vo;
        }).collect(Collectors.toList());
    }



    @Override
    public UniversalSceneDO getUniversalScene(Long id) {
        return universalSceneMapper.selectById(id);
    }
    @Override
    public PageResult<UniversalSceneDO> getUniversalScenePage(UniversalScenePageReqVO pageReqVO) {
        return universalSceneMapper.selectPage(pageReqVO);
    }

}
