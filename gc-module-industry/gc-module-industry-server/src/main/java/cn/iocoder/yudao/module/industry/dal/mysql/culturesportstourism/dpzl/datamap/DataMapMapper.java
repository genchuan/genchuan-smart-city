// DataMapMapper.java
package cn.iocoder.yudao.module.industry.dal.mysql.culturesportstourism.dpzl.datamap;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap.vo.DataMapQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap.vo.DataMapRespVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataMapMapper extends BaseMapper<DataMapRespVO> {
    List<DataMapRespVO> queryDataMap(DataMapQueryReqVO queryVO);
}