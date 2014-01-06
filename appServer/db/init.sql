drop table if EXISTS PlayerInfo;
create table PlayerInfo(
	PlayerID char(10) primary key comment '串号',
	Gender int(1) comment '性别枚举（0~2分别表示不确定、男、女）' ,
	Nickname char(16) comment '昵称',
	Icon int(2) comment '头像枚举',
	Grade int(5) comment '等级' ,
	LogPW char(16) comment '密码串'
)COMMENT = '玩家数据';

drop table if EXISTS PlayerScore;
create table PlayerScore(
	PlayerID char(10) primary key comment '串号',
	Score int(5) comment '积分',
	Money int(5) comment '金钱',
	Prop0 int(5) comment 'N种道具的数量'
)comment '玩家积分';

drop table if EXISTS DeskInfo;
create table DeskInfo(
	DeskID char(8) primary key comment '串号',
	Nickname char(12)comment '昵称',
	RoomID char(6)comment '串号'
)comment '物理终端数据';

drop table if EXISTS RoomInfo;
create table RoomInfo(
	RoomID char(6) primary key comment '串号',
	Nickname char(12) comment '昵称',
	Grade int(5) comment '等级',
	Mode int(1) comment '工作模式（0~1分别表示单桌、多桌）',
	Status int(1) comment '工作状态（保留）'
)comment '物理房间数据';

drop table if EXISTS GameInfo;
create table GameInfo(
	GameID char(6) primary key comment '串号',
	Nickname char(12) comment '昵称',
	Interdesk boolean comment '是否可以跨终端',
	Mode int(1) comment '工作模式（0~5分别表示单人、双人、三人、四人、六人、多人）',
	Valid boolean comment '当前是否有效'
)comment '游戏数据';

drop table if EXISTS ChatLog;
create table ChatLog(
	SN int(10) primary key comment '流水号',
	chatTime timestamp comment '时间戳',
	SenderID char(10) comment '发送者串号',
	RecverID char(10) comment '接收者串号',
	Memo varchar(256) comment '聊天内容'
)comment '聊天数据';

drop table if EXISTS DBLog;
create table DBLog(
	SN int(10) primary key comment '流水号',
	chatTime timestamp comment '时间戳',
	User char(20) comment '操作者账号',
	Script varchar(256) comment '数据库操作描述'
)comment '数据库操作日志';

drop table if EXISTS TerminalLog;
create table TerminalLog(
	SN int(10) primary key comment '流水号',
	chatTime timestamp comment '时间戳',
	DeskID char(8) comment '终端串号',
	Script varchar(1024)comment '终端日志内容'
)comment '终端日志';