package com.example.apt_processor;

import com.example.apt_annotation.BindView;
import com.google.auto.service.AutoService;
import com.google.auto.service.processor.AutoServiceProcessor;
import com.squareup.javapoet.JavaFile;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)
public class BindViewProcess extends AutoServiceProcessor {
    private Messager message;
    private Elements mElements;
    private Filer filer;
    private Types types;
    private Map<String, ClassCreatorProxy> map = new HashMap<>();


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        message = processingEnvironment.getMessager();
        mElements = processingEnvironment.getElementUtils();
        filer = processingEnvironment.getFiler();
        types = processingEnvironment.getTypeUtils();
        super.init(processingEnvironment);
    }

    @Override
    public Set<String> getSupportedOptions() {
        HashSet<String> supported = new LinkedHashSet<>();
        supported.add(BindView.class.getCanonicalName());
        return supported;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        message.printMessage(Diagnostic.Kind.NOTE, "processing...");
        map.clear();;
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(BindView.class);
        for (Element element:elements) {
            VariableElement variableElement = (VariableElement) element;
            TypeElement classElement = (TypeElement) element.getEnclosingElement();
            String fullClassName = classElement.getQualifiedName().toString();
            ClassCreatorProxy proxy = map.get(fullClassName);
            if(proxy == null){
                proxy = new ClassCreatorProxy(mElements,classElement);
                map.put(fullClassName,proxy);
            }
            BindView bindView = variableElement.getAnnotation(BindView.class);
            int id = bindView.value();
            proxy.putElement(id,variableElement);
        }
        for (String key : map.keySet()) {
            ClassCreatorProxy proxyInfo = map.get(key);
//            try {
//                message.printMessage(Diagnostic.Kind.NOTE, " --> create " + proxyInfo.getProxyClassFullName());
//                JavaFileObject jfo = processingEnv.getFiler().createSourceFile(proxyInfo.getProxyClassFullName(), proxyInfo.getTypeElement());
//                Writer writer = jfo.openWriter();
//                writer.write(proxyInfo.generateJavaCode());
//                writer.flush();
//                writer.close();
//            } catch (IOException e) {
//                message.printMessage(Diagnostic.Kind.NOTE, " --> create " + proxyInfo.getProxyClassFullName() + "error");
//            }
            JavaFile javaFile = JavaFile.builder(proxyInfo.getPackageName(),proxyInfo.generateJavaCode2()).build();
            try {
                javaFile.writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        message.printMessage(Diagnostic.Kind.NOTE, "process finished");
        return true;
    }
}