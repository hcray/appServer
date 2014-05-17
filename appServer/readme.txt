1、登陆，登出的用户id的保存
2、跨JVM的乱码问题
3、分层 AOP的通知  ---- 周三
4、返回值的value区分 ---- 周四
5、jvm内存大小的调整
6、安装程序、注册成服务、系统所有情况的跑通 ---- 周五
7、会话的记录----session
8、传输内容的加密
   

insert into playerinfo values('user1',1,'user1',1,1,'user1');
insert into playerinfo values('user2',2,'user2',2,2,'user2');
insert into playerinfo values('user3',3,'user3',3,3,'user3');
insert into playerinfo values('user4',4,'user4',4,4,'user4');
insert into playerinfo values('user5',5,'user5',5,5,'user5');
insert into playerinfo values('user6',6,'user6',6,6,'user6');
insert into playerinfo values('user7',7,'user7',7,7,'user7');
insert into playerinfo values('user8',8,'user8',8,8,'user8');
insert into playerinfo values('user9',9,'user9',9,9,'user9');
insert into playerinfo values('user10',10,'user10',10,10,'user10');

insert into playerscore values('user1',100,100,100);
insert into playerscore values('user2',100,100,100);
insert into playerscore values('user3',100,100,100);
insert into playerscore values('user4',100,100,100);
insert into playerscore values('user5',100,100,100);
insert into playerscore values('user6',100,100,100);
insert into playerscore values('user7',100,100,100);
insert into playerscore values('user8',100,100,100);
insert into playerscore values('user9',100,100,100);
insert into playerscore values('user10',100,100,100);


<TCP><Command>login</Command><PlayerID>user7</PlayerID><DeskID>00000000</DeskID></TCP>
<TCP><Command>queryScore</Command><PlayerID>user7</PlayerID></TCP>
<TCP><Command>queryGames</Command></TCP>
<TCP><Command>openStage</Command><GameID>abcdef</GameID><HostIndex>2</HostIndex><PlayerID>user8</PlayerID></TCP>
<TCP><Command>refreshStage</Command><StageSN>1</StageSN></TCP>
<TCP><Command>queryStage</Command><GameID>abcdef</GameID><PlayerID>user4</PlayerID></TCP>
<TCP><Command>searchPlayer</Command></TCP>
<TCP><Command>sendMessage</Command><SenderID>user7</SenderID><RecoverID>user5</RecoverID><Memo>哈喽</Memo></TCP>
<TCP><Command>startup</Command><DeskID>00000000</DeskID></TCP>
<TCP><Command>shutdown</Command><DeskID>00000000</DeskID></TCP>
<TCP><Command>present</Command><SenderID>user4</SenderID><RecverID>user10</RecverID><Score>1</Score><Money>2</Money><Prop0>0</Prop0></TCP>

物理端登录
<TCP><action>TerminalLogin</action><value><DeskID>123456</DeskID></value><category>Account</category><address>OP</address></TCP>
物理端签出
<TCP><action>TerminalLogout</action><value><DeskID>123456</DeskID></value></TCP>
设备登记
<TCP><action>LoginWrite</action><value><DeskID>00000000</DeskID><Script>xxxxxxxxx</Script></value></TCP>
用户登入
<TCP><action>PlayerLogin</action><value><PlayerID>user4</PlayerID><DeskID>00000000</DeskID></value></TCP>
用户登出
<TCP><action>PlayerLogout</action><value><PlayerID>user4</PlayerID></value></TCP>
用户查分
<TCP><action>QueryScore</action><value><PlayerID>user4</PlayerID></value></TCP>
社交:
询众
<TCP><action>QueryAllPlayer</action><value><PlayerID>user4</PlayerID></value></TCP>
