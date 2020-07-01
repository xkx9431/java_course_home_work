### 作业情况

1. 五子棋:

   源代码："06_homeWork\week2\FiveChessGame.java"

   测试代码："06_homeWork\week2\FiveChessGameTest.java"

2. 设计类

+ 2.1 第一步：设计和实现以下类

    （1）手机卡类 特征：卡类型、卡号、用户名、密码、账户余额、通话时长(分钟)、上网流量 行为：显示（卡号 + 用户名 + 当前余额）
    06_homeWork\week2\SimCard.java

    （2）通话套餐类 特征：通话时长、短信条数、每月资费 行为: 显示所有套餐信息
    06_homeWork\week2\TelPackage.java

    （3）上网套餐类 特征：上网流量、每月资费 行为：显示所有套餐信息
    06_homeWork\week2\SurfPackage.java

    （4）用户消费信息类 特征：统计通话时长、统计上网流量、每月消费金额
    06_homeWork\week2\ConsumerDetail.java

+ 2.2  第二步：设计和实现以下枚举类 手机卡的类型总共有 3 种：大卡、小卡、微型卡
    06_homeWork\week2\SimcardSizeEnum.java


+ 2.3 第三步：实体类的优化 将通话套餐类和上网套餐类中相同的特征和行为提取出来组成抽象套餐类。
06_homeWork\week2\ConsumerDetail.java

+ 2.4 第四步：创建并实现以下接口

    （1）通话服务接口 抽象方法: 参数 1: 通话分钟, 参数 2: 手机卡类对象 让通话套餐类实现通话服务接口。
        06_homeWork\week2\TelSvc.java

    （2）上网服务接口 抽象方法: 参数 1: 上网流量, 参数 2: 手机卡类对象 让上网套餐类实现上网服务接口。
        06_homeWork\week2\SurfSvc.java