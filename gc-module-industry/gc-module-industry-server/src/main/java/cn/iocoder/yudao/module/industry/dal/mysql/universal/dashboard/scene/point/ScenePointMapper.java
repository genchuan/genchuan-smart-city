package cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.point;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.point.vo.ScenePointQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.point.vo.ScenePointRespVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 场景点位数据 Mapper
 *
 */
@Mapper
public interface ScenePointMapper {

    /**
     * 查询场景点位数据
     *
     * @param scenePointQueryReqVO 查询参数
     * @return ScenePointRespVO 结果
     */
    ScenePointRespVO getScenePoint(ScenePointQueryReqVO scenePointQueryReqVO);

}
