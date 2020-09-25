###     跨模块启动activity的方式有很多。1、隐式启动，这种方式很少使用；2、通过目标activity的类名进行启动；3、使用路由框架跳转到目标activity。

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