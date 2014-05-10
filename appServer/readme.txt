1、登陆，登出的用户id的保存
2、跨JVM的乱码问题
3、分层 AOP的通知  ---- 周三
4、返回值的value区分 ---- 周四
5、jvm内存大小的调整
6、安装程序、注册成服务、系统所有情况的跑通 ---- 周五
7、会话的记录----session

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

#物理端登录
<TCP><action>TerminalLogin</action><value><DeskID>123456</DeskID></value><category>Account</category><address>OP</address></TCP>

用户登入
<TCP><action>PlayerLogin</action><value><PlayerID>user4</PlayerID><DeskID>00000000</DeskID></value></TCP>
用户登出
<TCP><action>PlayerLogout</action><value><PlayerID>user1</PlayerID></value></TCP>
