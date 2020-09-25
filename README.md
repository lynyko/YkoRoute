      跨模块启动activity的方式有很多。1、隐式启动，这种方式很少使用；2、通过目标activity的类名进行启动；3、使用路由框架跳转到目标activity。

        路由框架，可以很好地实现模块间的解耦，目前使用最多的是ARouter，是阿里开源的路由框架，自己使用过此框架。ARouter的功能很丰富，能够跨模块启动activity，也能获取Fragment，支持拦截器，巴啦巴啦很多功能。自己在使用过程中有个需求，ARouter做不到，那就是有时我们点某个按钮时，会访问后台，根据后台返回数据来判断是否要跳转到activity，而访问后台和跳转是对调用者隐藏的，所以这部分代码只能放在目标模块。为了实现路由框架能满足上面的要求，自己动了撸个路由框架的念头。

        通过对别人的代码的学习，自己撸出了个功能很简单的路由框架。框架除了启动activity和获得Fragment外，还支持启动Controller，用于开发者在模块里做一些事情。目前只实现基本的功能，以后可以在此基础上扩展其他功能。支持java和kotlin。

1、在模块的build.gradle里添加依赖和配置

java：

android{

    defaultConfig{

        javaCompileOptions{

            annotationProcessorOptions{

                arguments = [moduleName :project.getName() ]

            }

        }

    }

}



dependencies{

    implementation project(':compiler')

    annotationProcessor project(':compiler')

}

kotlin：

applyplugin:'kotlin-android'

applyplugin:'kotlin-kapt'

applyplugin:'kotlin-android-extensions'



kapt{

    arguments{

        arg("moduleName",project.getName())

    }

}

dependencies{

    implementation project(':compiler')

    kapt project(':compiler')

}

同时在根build.gradle添加

classpath"org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.31"



2、添加混淆规则

-keep class com.yko.route.**{*;}



3、添加注解

在所要跳转的Activity上添加注解

@Route(path ="/mall/mallJava")

public class MallJavaextends Activity{

@Override

protected void onCreate(@Nullable Bundle savedInstanceState) {

}

path的值可以随意，框架根据path的值来获得不同的Activity，只要不相同就可以。



4、使用前进行初始化

YkoHut.getInstance().init(this);

5、使用实例

获得Fragment：YkoHut.getInstance().getFragment("/mall/fragment");

获得support包的Fragment：YkoHut.getInstance().getFragmentV4("/mall/fragment");

跳转到Activity：YkoHut.getInstance().startActivity(MainActivity.this, "/BBS/BBSJava", null);

跳转到Controller：YkoHut.getInstance().startController(MainActivity.this, "/bbs/controller", null, null);