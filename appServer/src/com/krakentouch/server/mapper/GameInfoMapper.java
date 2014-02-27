package com.krakentouch.server.mapper;

import java.util.List;
import com.krakentouch.server.domain.GameInfo;

public interface GameInfoMapper {
	public List<GameInfo> queryAllGame();
}
