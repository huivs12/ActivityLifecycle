# ActivityLifecycle

####Acitivyt的生命周期分为：

1. 典型情况下的生命周期

2. 异常情况下的生命周期



####Activity的生命周期方法

onCreate -> onRestart -> onStart -> onResume -> onPause -> onStop -> onDestory （代码中的注释说明每个方法的作用，以及执行的时间，需要处理的事情。）

这是一种典型的生命周期


异常情况下的生命周期：

当资源相关系统配置发生改变时，会销毁当前Activity，并重新创建当前的Activity。

当系统内存不足时，系统会销毁处于后台的Activity，可能不会执行onDestory方法。当重新打这个Activity时，会被重新创建。

在异常情况下，有两个比较重要的方法：

onSaveInstanceState及onRestoreInstanceState 用于存储数据及获取存储的数据。


比如系统配置发生改变后，不想让Activity重建，可以在AndroidMainfast文件Activity标签中设置configChanges属性。
比较常用的属性：orientatioin,locale,keyboardHideen



####每个View都有onSaveInstanceState及onRestoreInstanceState方法用于保存和恢复数据，运行机制如下：

首先Activity被意外终止后，Activity会调用onSaveInstanceState保存数据，然后Activity会委托Window去保存数据，接着Window再委拖它上面的顶级容器去保存数据。
顶层容器是一个ViewGroup，很可能是一个DecorView，最后顶层容器再通知它的子元素来保存数据。
