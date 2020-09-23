package com.yko.route;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;



/**
 * Created by yko on 2017/9/17.
 */
@SupportedOptions("moduleName")
@SupportedAnnotationTypes(RouteConstant.ANNOTATION_YKO_ROUTE)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@AutoService(Processor.class)
public class YkoProcess extends AbstractProcessor {
    private Messager messager = null;
    private Elements elementUtils = null;
    private Types types;
    public static final String ACTIVITY = "android.app.Activity";
    public static final String ACTIVITY_V7 = "android.support.v7.app.AppCompatActivity";
    public static final String FRAGMENT = "android.app.Fragment";
    public static final String FRAGMENT_V4 = "android.support.v4.app.Fragment";
    public static final String SERVICE = "android.app.Service";
    public static final String CONTROLLER = "com.yko.route.Controller";

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(Route.class.getCanonicalName());
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        messager = processingEnvironment.getMessager();
        elementUtils = processingEnvironment.getElementUtils();
        types = processingEnvironment.getTypeUtils();            // Get type utils.
    }

    private void log(Element element, String message, Object... args) {
        messager.printMessage(Diagnostic.Kind.NOTE, String.format(message, args), element);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Route.class);
        if (elements == null || elements.isEmpty()) {
            return true;
        }
        String moduleName = processingEnv.getOptions().get("moduleName");
        log(null, "module name:" + moduleName);
        Set<TypeElement> typeElements = new HashSet<>();
        ParameterizedTypeName mapTypeName = ParameterizedTypeName.get(ClassName.get(Map.class),
                ClassName.get(String.class), ClassName.get(Class.class));
        ParameterSpec mapParameterSpec = ParameterSpec.builder(mapTypeName, "map").build();

        MethodSpec.Builder methodHandle = MethodSpec.methodBuilder("routeMap")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(mapParameterSpec);

        TypeMirror typeActivity = elementUtils.getTypeElement(ACTIVITY).asType();
        TypeMirror typeActivityV7 = elementUtils.getTypeElement(ACTIVITY_V7).asType();
        TypeMirror typeService = elementUtils.getTypeElement(SERVICE).asType();
        TypeMirror typeFragment = elementUtils.getTypeElement(FRAGMENT).asType();
        TypeMirror typeFragmentV4 = elementUtils.getTypeElement(FRAGMENT_V4).asType();
        TypeMirror typeController = elementUtils.getTypeElement(CONTROLLER).asType();

        for(Element element : elements){

            TypeMirror tm = element.asType();
            if (types.isSubtype(tm, typeActivity) ||
                    types.isSubtype(tm, typeActivityV7) ||
                    types.isSubtype(tm, typeService) ||
                    types.isSubtype(tm, typeController) ||
                    types.isSubtype(tm, typeFragment) ||
                    types.isSubtype(tm, typeFragmentV4)) {
                typeElements.add((TypeElement) element);
                Route route = element.getAnnotation(Route.class);
                String path = route.path();
                String className = ((TypeElement) element).getQualifiedName().toString();
                methodHandle.addStatement("map.put($S, $T.class)",
                        path,
                        ClassName.get(className.substring(0, className.lastIndexOf(".")), className.substring(className.lastIndexOf(".") + 1)));
            }
        }
        String fullName = IRoute.class.getName();
        int index = fullName.lastIndexOf(".");
        String packageName = fullName.substring(0, index);
        String simpleName = IRoute.class.getSimpleName();


        TypeSpec type = TypeSpec.classBuilder(moduleName + RouteConstant.YKO_MAP)
                .addSuperinterface(ClassName.get(packageName, simpleName))
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodHandle.build())
                .build();
        try {
            JavaFile.builder(RouteConstant.PACKAGE_NAME, type).build().writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
