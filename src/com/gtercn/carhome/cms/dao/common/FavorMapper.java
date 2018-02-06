package com.gtercn.carhome.cms.dao.common;

import org.apache.ibatis.annotations.Param;

public interface FavorMapper {
	/**
	 * 删除收藏
	 * 
	 * @param referenceId
	 *            收藏id
	 * @param type
	 *            收藏类别 1:自驾游2:车友会3:达人圈4:资讯 5:促销 6:紧急救援 7:四类服务
	 * @return 2017-3-22 上午09:25:56
	 */
	int deleteByCondition(@Param("favorId") String favorId,
			@Param("favorType") String favorType);
}