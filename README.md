轻松驾APP

模块包
1. activity        放置 APP功能中除四大功能以外的Activity
2. adapter         放置 APP中所有的adapter
3. bean            放置 APP中所有的自定义Bean
4. db              放置 数据库操作的类
5. driverexam      放置 驾考模块业务
6. driverschool    放置 驾校模块业务
7. exchange        放置 交流模块业务
8. fragment        主页的Fragment
9. others          APP中其他的业务，比如广播，服务等
10.tools           放置 业务模块业务
11.utils           APP中的工具类

命名规则
1. 四大组件及Fragment  
   需要加上对应的后缀，比如MainActiivty,DownloadService,SMSReceiver
   
2. Adapter命名  
   以Adapter结束 比如 MainAdapter
   
3. Bean命名 
   以对应模块开头
   driverexam,以Exam开头
   driverschool，以Scho开头
   exchange,以Exch开头
   tools,以Tool开头
   
4. 布局命名
   a. Activity布局命名    actiivty_对应的Activity名字   activity_main.xml
   b. Fragment布局命名    fragment_对应的Fragment名字   fragment_main.xml
   c. 适配器布局命名       item_对应的Adapter名字        item_main.xml
   d. 弹框 布局命名        dialog_对用窗口名字           dialog_exit.xml
   e. 自定义View布局命名   view_自定义View名字           view_calender.xml
   
5. ID命名
   R.id.布局名称_组件简称_业务   R.id.main_tv_name
                              R.id.main_et_phone
